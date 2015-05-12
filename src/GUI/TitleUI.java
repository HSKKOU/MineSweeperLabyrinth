package GUI;

import static constants.Constants.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleUI extends CommonJPanel implements ActionListener {
	
	public TitleUI(UIController parentFrame){
		super(parentFrame);
		
		this.setLayout(new FlowLayout());
		
		JLabel titleLabel = new JLabel("MineSweeper Labyrinth");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT/2));
		JPanel titlePanel = new JPanel();
		titlePanel.add(titleLabel);
		this.add(titlePanel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(400, 150));
		buttonsPanel.setLayout(new GridLayout(3, 1));
		for(int i=0; i<3; i++){
			JButton goGameButton = new JButton("LEVEL" + (i+1));
			goGameButton.addActionListener(this);
			goGameButton.setActionCommand("" + (i+1));
			buttonsPanel.add(goGameButton);
		}
		this.add(buttonsPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameUI gameUI = new GameUI(parentFrame, Integer.parseInt(e.getActionCommand()));
		parentFrame.changePanel(gameUI);
	}
}
