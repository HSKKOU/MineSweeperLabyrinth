package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import Inputs.InputManager;
import static constants.Constants.*;

public class GameUI extends CommonJPanel implements Runnable {
	// serialID
	private static final long serialVersionUID = 1L;
	
	// FPS
	private static final int FRAME_RATE = 60;
	private long startTime = 0;
	private int mDrawFrameCount = 0;
	
	// 画面Panel
	private JPanel mGameScreen;
	private JPanel mStateScreen;
	
	private int mGameFrameSize[];
	private int mPaddings[] = {0, 0};
	
	private InputManager IM;
	
	public GameUI(UIController parentFrame, int level){
		super(parentFrame);
		this.setLayout(new FlowLayout());
		
		mStateScreen = new JPanel();
		mStateScreen.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		this.add(mStateScreen);
		
		mGameScreen = new JPanel();
		mGameScreen.setPreferredSize(new Dimension(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT));
		this.add(mGameScreen);
		
		mGameFrameSize = LEVEL_CELL_SIZE[level];
		mPaddings[W] = (GAME_FRAME_WIDTH - CELL_WIDTH * mGameFrameSize[W]) / 2;
		mPaddings[H] = (GAME_FRAME_HEIGHT - CELL_HEIGHT * mGameFrameSize[H]) / 2;
		
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	// 描画
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)mGameScreen.getGraphics();
		for(int i=0; i<mGameFrameSize[W]; i++){
			for(int j=0; j<mGameFrameSize[H]; j++){
				Rectangle rect = new Rectangle(i*CELL_WIDTH + mPaddings[W], j*CELL_HEIGHT + mPaddings[H], CELL_WIDTH, CELL_HEIGHT);
				g2.setColor(Color.BLACK);
				g2.draw(rect);			
			}
		}
		
		mDrawFrameCount++;
	}
	
	// 更新処理
	private void update(){
		System.out.println(
				"方向:" + IM.getDirection()
				+ ", 破壊:" + IM.IsDestroy()
				+ ", 旗:" + IM.IsCheck()
		);
		repaint();
	}

	// Thread内処理
	public void run(){
		IM = new InputManager(parentFrame);
		long error = 0;
		long idealSleep = (1000 << 16) / FRAME_RATE;
		long prevTime = 0;
		long currentTime = System.currentTimeMillis() << 16;
		long sleepTime = 0;
		while(true){
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
}
