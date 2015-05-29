package MineSweeper;

import Inputs.InputManager;
import static global.Constants.*;

// InputManagerの出力を受けてプレイヤーの行動を出力する
public class PlayerController {
	
	private InputManager IM;
	private int direction = KEY_DIR_NONE;
	private boolean isDestroying = false;
	private boolean isChecking = false;
	private int forwardDirection = KEY_DIR_UP;

	public PlayerController(){
		this.IM = InputManager.getInstance();
	}
	
	// プレイヤーが向いている方向を取得
	public int getForward(){return forwardDirection;}
	
	// プレイヤーの向いている方向を設定。すでに向いている場合はfalse
	public boolean isChangeForward(int _dir){
		if(forwardDirection == _dir){return false;}
		forwardDirection = _dir;
		return true;
	}
	
	// プレイヤーの進みたい方向を取得
	public int getDirection(){
		int imDir = IM.getDirection();
		if(direction == KEY_DIR_NONE && imDir != KEY_DIR_NONE){
			direction = imDir;
			return direction;
		}else if(direction != KEY_DIR_NONE && imDir == KEY_DIR_NONE){direction = imDir;}
		return KEY_DIR_NONE;
	}
	
	// プレイヤーがマス破壊したいかどうか取得
	public boolean isDestroying(){
		boolean imDes = IM.IsDestroy();		
		if(!isDestroying && imDes){
			isDestroying = imDes;
			return isDestroying;
		}else if(isDestroying && !imDes){isDestroying = imDes;}
		return false;
	}
	
	// プレイヤーが旗をたてたいかどうか取得
	public boolean isChecking(){
		boolean imChe = IM.IsCheck();
		if(!isChecking && imChe){
			isChecking = imChe;
			return isChecking;
		}else if(isChecking && !imChe){isChecking = imChe;}
		return false;
	}
}
