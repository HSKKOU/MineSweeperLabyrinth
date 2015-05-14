import GUI.*;
import Inputs.InputManager;

public class Controller {
	public Controller(){
		// UI管理クラス
		UIController uiC = new UIController();
		// 入力管理クラス
		InputManager IM = new InputManager(uiC);
		uiC.setInputManage(IM);
	}
}