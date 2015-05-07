package GUI;

import javax.swing.JFrame;

public class MainUI extends JFrame {

	// serialID
	private static final long serialVersionUID = 1L;

	public MainUI(){
		super();
		this.initUI();
	}

	// UI初期化
	private void initUI(){
		this.setTitle("MainSweeper Labyrinth");
		this.setBounds(100, 0, 900, 700);
		this.setVisible(true);
	}
}
