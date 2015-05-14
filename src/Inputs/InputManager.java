package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GUI.*;

import static constants.Constants.*;

public class InputManager implements KeyListener {

	private int mCurrentDirection = KEY_DIR_NONE;
	
	private int UP = 0;
	private int RIGHT = 1;
	private int DOWN = 2;
	private int LEFT = 3;
	private int DESTROY = 4;
	private int CHECK = 5;
	private static int KEY_NUMS = 6;
	private boolean mKey[] = new boolean[KEY_NUMS];
		
	public InputManager(UIController _parentFrame) {
		_parentFrame.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent _e) {
		int pressedNum = 0;
		for(int i=0; i<KEY_NUMS; i++){
			if(mKey[i]){
				pressedNum++;
				if(pressedNum > 1){return;}
			}
		}
		int keyCode = _e.getKeyCode();
		switch(keyCode){
			case KEY_DIR_UP:
				mKey[UP] = true;
				mCurrentDirection = keyCode;
				break;
			case KEY_DIR_RIGHT:
				mKey[RIGHT] = true;
				mCurrentDirection = keyCode;
				break;
			case KEY_DIR_DOWN:
				mKey[DOWN] = true;
				mCurrentDirection = keyCode;
				break;
			case KEY_DIR_LEFT:
				mKey[LEFT] = true;
				mCurrentDirection = keyCode;
				break;
 			case KEY_ACT_DESTROY:
 				mKey[DESTROY] = true;
				break;
			case KEY_ACT_CHECK:
				mKey[CHECK] = true;
				break;
			default:
				break;
		}
	}
	
	public void keyReleased(KeyEvent _e) {
		int keyCode = _e.getKeyCode();
		switch(keyCode){
			case KEY_DIR_UP:
				mKey[UP] = false;
				mCurrentDirection = getPressedDirectionKey();
				break;
			case KEY_DIR_RIGHT:
				mKey[RIGHT] = false;
				mCurrentDirection = getPressedDirectionKey();
				break;
			case KEY_DIR_DOWN:
				mKey[DOWN] = false;
				mCurrentDirection = getPressedDirectionKey();
				break;
			case KEY_DIR_LEFT:
				mKey[LEFT] = false;
				mCurrentDirection = getPressedDirectionKey();
				break;
			case KEY_ACT_DESTROY:
				mKey[DESTROY] = false;
				break;
			case KEY_ACT_CHECK:
				mKey[CHECK] = false;
				break;
			default:
				break;
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	private int getPressedDirectionKey(){
		if(mKey[UP]){return KEY_DIR_UP;}
		else if(mKey[RIGHT]){return KEY_DIR_RIGHT;}
		else if(mKey[DOWN]){return KEY_DIR_DOWN;}
		else if(mKey[LEFT]){return KEY_DIR_LEFT;}
		else{return KEY_DIR_NONE;}
	}
	
	public int getDirection(){
		return mCurrentDirection;
	}
	
	public boolean IsDestroy(){
		return mKey[DESTROY];
	}
	
	public boolean IsCheck(){
		return mKey[CHECK];
	}
}
