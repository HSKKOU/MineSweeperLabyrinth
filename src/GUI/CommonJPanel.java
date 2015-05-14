package GUI;

import static constants.Constants.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import Inputs.InputManager;

public abstract class CommonJPanel extends JPanel {

	protected UIController parentFrame;
	protected InputManager IM;
	
	public CommonJPanel(UIController _parentFrame){
		this.parentFrame = _parentFrame;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}
}
