package MineSweeper;

import Inputs.InputManager;
import static global.Constants.*;

public class PlayerController {
	
	private InputManager IM;
	private int direction = KEY_DIR_NONE;
	private boolean isDestroying = false;
	private boolean isChecking = false;
	private int forwardDirection = KEY_DIR_UP;

	public PlayerController(){
		
	}
	
	public void setInputManager(InputManager _IM){
		this.IM = _IM;
	}
	
	public int getForward(){
		return forwardDirection;
	}
	
	public boolean isChangeForward(int _dir){
		if(forwardDirection == _dir){return false;}
		forwardDirection = _dir;
		return true;
	}
	
	public int getDirection(){
		int imDir = IM.getDirection();
		if(direction == KEY_DIR_NONE && imDir != KEY_DIR_NONE){
			direction = imDir;
			return direction;
		}else{
			if(direction != KEY_DIR_NONE && imDir == KEY_DIR_NONE){direction = imDir;}
			return KEY_DIR_NONE;
		}
	}
	
	public boolean isDestroying(){
		boolean imDes = IM.IsDestroy();		
		if(!isDestroying && imDes){
			isDestroying = imDes;
			return isDestroying;
		}else{
			if(isDestroying && !imDes){isDestroying = imDes;}
			return false;
		}
	}
	
	public boolean isChecking(){
		boolean imChe = IM.IsCheck();
		if(!isChecking && imChe){
			isChecking = imChe;
			return isChecking;
		}else{
			if(isChecking && !imChe){isChecking = imChe;}
			return false;
		}
	}
}
