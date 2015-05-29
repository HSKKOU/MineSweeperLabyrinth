package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Level.LevelFactory;
import Level.LevelState;
import MineSweeper.*;
import MineSweeper.Cell.CellState;
import static global.Constants.*;

// ゲーム画面
public class GameUI extends CommonJPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	// FPS
	private static final int FRAME_RATE = 30;
	
	// 画面Panel
	private JPanel mGameScreen;
	private JPanel mStateScreen;
	private JLabel mGameFinishedLabel;
	
	// 画面サイズ
	private int mPaddingsW;
	private int mPaddingsH;
	
	// マインスイーパーのフィールド管理クラス
	private FieldController FC;
	
	// 操作するプレイヤー管理クラス
	private PlayerController PC;
	
	// ゲームスレッド実行フラグ
	private boolean running = false;
	
	public GameUI(UIController _parentFrame, String _level){
		super(_parentFrame);
		this.setLayout(new FlowLayout());
		
		_parentFrame.getRootPane().setDoubleBuffered(true);
		
		// フィールドおよびプレイヤーのステータス表示パネル
		mStateScreen = new JPanel();
		mStateScreen.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		this.add(mStateScreen);
		
		// マインスイーパーマス表示パネル
		mGameScreen = new JPanel();
		mGameScreen.setPreferredSize(new Dimension(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT));
		this.add(mGameScreen);
		
		// タイトルで選んだレベルの取得
		LevelState ls = LevelFactory.createLevel(_level);
		assert ls != null : "LevelState = null";
		
		// マインスイーパーマス表示パネル内の表示Padding
		mPaddingsW = (GAME_FRAME_WIDTH - CELL_WIDTH * ls.gameCellSizeW) / 2;
		mPaddingsH = (GAME_FRAME_HEIGHT - CELL_HEIGHT * ls.gameCellSizeH) / 2;
		
		// 各管理クラスの初期化
		FC = new FieldController(ls);
		PC = new PlayerController();
		
		// ゲームスレッドの作成、開始
		Thread gameThread = new Thread(this);
		running = true;
		gameThread.start();
	}
	
	
	// 描画
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)mGameScreen.getGraphics();
		AffineTransform af = new AffineTransform();
		int maxHeight = FC.getHeight()*CELL_HEIGHT + mPaddingsH;
		int angle = 0;
		Font cellFont = new Font("Arial", Font.BOLD, 20);
		Color fillColor, strColor;
		String cellStr = "";
		for(int i=0; i<FC.getHeight(); i++){
			for(int j=0; j<FC.getWidth(); j++){
				af.setToRotation(0);
				g2.setTransform(af);
				angle = 0;
				fillColor = Color.WHITE;
				strColor = Color.BLACK;
				cellStr = "";
				
				CellState cs = FC.getCellState(j, i);
				if(cs == CellState.Normal){fillColor = Color.GRAY;}
				else if(cs == CellState.Checked){cellStr = "C";fillColor = Color.YELLOW;}
				else if(cs == CellState.Destroyed){cellStr = "" + FC.getCellAroundMines(j, i);}
				else if(cs == CellState.Start){cellStr = "S";strColor = Color.RED;}
				else if(cs == CellState.Goal){cellStr = "G";strColor = Color.BLUE;}
				else if(cs == CellState.Player){
					cellStr = "P";
					strColor = Color.GREEN;
					switch(PC.getForward()){
						case KEY_DIR_UP: angle = 0; break;
						case KEY_DIR_RIGHT: angle = 90; break;
						case KEY_DIR_DOWN: angle = 180; break;
						case KEY_DIR_LEFT: angle = -90; break;
						default: break;
					}
				}else{}

				int ox = j*CELL_WIDTH + mPaddingsW, oy = maxHeight - (i+1)*CELL_HEIGHT;
				Rectangle rect = new Rectangle(ox, oy, CELL_WIDTH, CELL_HEIGHT);

				// マスの中を塗る
				g2.setColor(fillColor);
				g2.fill(rect);
				
				// マスの枠を描く
				g2.setColor(Color.BLACK);
				g2.draw(rect);
				
				// マスに文字を書く
				af.setToRotation(Math.PI / 180 * angle, ox + CELL_WIDTH/2, oy + CELL_HEIGHT/2);
				g2.setTransform(af);
				g2.setFont(cellFont);
				g2.setColor(strColor);
				g2.drawString(cellStr, (float)(ox + CELL_STR_PADDING_H), (float)(oy + CELL_STR_PADDING_V));
			}
		}
	}
	
	// 更新処理
	private void update(){
		// 入力を取得
		int pDir = PC.getDirection();
		boolean pIsDes = PC.isDestroying();
		boolean pIsChe = PC.isChecking();
		
		// 入力を表示
//		System.out.println("方向:" + pDir + ", 破壊:" + pIsDes + ", 旗:" + pIsChe);
		
		// Player移動処理
		if(pDir != KEY_DIR_NONE){
//			System.out.println("方向:" + KEY2STR(pDir));
			if(!PC.isChangeForward(pDir)){FC.movePlayer(pDir);}
			if(FC.reachGoal()){gameClear();}
		}
		
		if(pIsDes){
//			System.out.println("破壊！");
			if(FC.hasBomb(PC.getForward())){gameOver();}
			else{FC.destroyCell(PC.getForward());}
		}else if(pIsChe){
//			System.out.println("旗！");
			FC.checkCell(PC.getForward());
		}
		
		repaint();
	}

	// Thread内処理（{FRAME_RATE}FPSで更新）
	public void run(){
		long error = 0;
		long idealSleep = (1000 << 16) / FRAME_RATE;
		long prevTime = 0;
		long currentTime = System.currentTimeMillis() << 16;
		long sleepTime = 0;
		while(running){
			prevTime = currentTime;
			
			update();
			
			currentTime = System.currentTimeMillis() << 16;
			sleepTime = idealSleep - (currentTime - prevTime) - error;
			if(sleepTime < 0x20000){sleepTime = 0x20000;}
			prevTime = currentTime;
			try {
				Thread.sleep(sleepTime >> 16);
			} catch (InterruptedException e) {
			}
			currentTime = System.currentTimeMillis() << 16;
			error = currentTime - prevTime - sleepTime;
		}
	}
	
	// GameOver処理
	private void gameOver(){
		gameFinished();
		mGameFinishedLabel.setText("GAME OVER");
	}
	
	// GameClear処理
	private void gameClear(){
		gameFinished();
		mGameFinishedLabel.setText("GAME CLEAR");
	}
	
	// Gameの終了共通処理
	private void gameFinished(){
		running = false;
		mStateScreen.removeAll();
		
		mGameFinishedLabel = new JLabel("");
		mGameFinishedLabel.setFont(new Font("Arial", Font.BOLD, 40));
		mStateScreen.add(mGameFinishedLabel);
		
		JButton backButton = new JButton("タイトルに戻る");
		backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TitleUI titleUI = new TitleUI(parentFrame);
				parentFrame.changePanel(titleUI);
			}
		});
		mStateScreen.add(backButton);
	}
}
