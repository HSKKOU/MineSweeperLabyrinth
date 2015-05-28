package GUI;

import static global.Constants.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import Inputs.InputManager;

// 各画面パネルの共通クラス
public abstract class CommonJPanel extends JPanel {

	// 親JFrame
	protected UIController parentFrame;
	// 入力管理クラス
	protected InputManager IM;
	
	public CommonJPanel(UIController _parentFrame){
		this.parentFrame = _parentFrame;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}
}
