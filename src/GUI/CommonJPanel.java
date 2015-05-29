package GUI;

import static global.Constants.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

// 各画面パネルの共通クラス
public abstract class CommonJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// 親JFrame
	protected UIController parentFrame;
	
	public CommonJPanel(UIController _parentFrame){
		this.parentFrame = _parentFrame;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}
}