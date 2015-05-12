package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static constants.Constants.*;

public class GameUI extends CommonJPanel {

	// serialID
	private static final long serialVersionUID = 1L;
	
	private JPanel mGameScreen;
	private JPanel mStateScreen;
	
	public GameUI(UIController parentFrame, int level){
		super(parentFrame);
		this.setLayout(new FlowLayout());
		
		mStateScreen = new JPanel();
		mStateScreen.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		this.add(mStateScreen);
		
		mGameScreen = new JPanel();
		mGameScreen.setPreferredSize(new Dimension(FRAME_WIDTH-200, FRAME_HEIGHT-100));
		this.add(mGameScreen);
	}
	
	// 描画
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)mGameScreen.getGraphics();
		for(int i=0; i<(FRAME_WIDTH-200)/CELL_WIDTH; i++){
			for(int j=0; j<(FRAME_HEIGHT-100)/CELL_HEIGHT; j++){
				Rectangle rect = new Rectangle(i*CELL_WIDTH, j*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
				g2.setColor(Color.BLACK);
				g2.draw(rect);			
			}
		}
	}
}
