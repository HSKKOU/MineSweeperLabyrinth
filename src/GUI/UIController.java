package GUI;

import static constants.Constants.*;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import Inputs.InputManager;

public class UIController extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private InputManager IM;

	public UIController(){
		super();
		this.setTitle("MainSweeper Labyrinth");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);	
		
		this.setLayout(new FlowLayout());
		
		changePanel(new TitleUI(this));
	}
	
	public void changePanel(CommonJPanel _nextPanel){
		getContentPane().removeAll();
		getContentPane().add(_nextPanel);
		this.setVisible(true);
		requestFocus();
	}
	
	public void setInputManage(InputManager _IM){
		this.IM = _IM;
	}
	
	public InputManager getInputManager(){
		return this.IM;
	}
}
