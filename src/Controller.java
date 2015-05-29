import GUI.*;
import Inputs.InputManager;

public class Controller {
	public Controller(){
		// UI管理クラス
		UIController uiC = new UIController();
		// 入力管理クラス
		InputManager IM = InputManager.getInstance();
		IM.setFrame(uiC);
		// 初期画面表示
		uiC.changePanel(new TitleUI(uiC));
	}
}