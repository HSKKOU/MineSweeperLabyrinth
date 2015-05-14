import GUI.*;
import Inputs.InputManager;

public class Controller {
	public Controller(){
		UIController uiC = new UIController();
		InputManager IM = new InputManager(uiC);
		uiC.setInputManage(IM);
	}
}
