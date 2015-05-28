package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GUI.*;
import static global.Constants.*;

// 入力管理
public class InputManager implements KeyListener {
	// 最近入力されている方向キー
	private int mCurrentDirection = KEY_DIR_NONE;
	
	// 方向・アクション定数
	private int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, DESTROY = 4, CHECK = 5;
	private static int KEY_NUMS = 6;
	// キー入力フラグの配列（最大２つtrueになりえる）
	private boolean mKey[] = new boolean[KEY_NUMS];
	
	// シングルトン
	private static InputManager IM;

	// リスナー登録するJFrame
	private static UIController parentFrame; 
		
	private InputManager(){}
	
	public static InputManager getInstance(){
		if(IM == null){IM = new InputManager();}
		return IM;
	}
	
	public void setFrame(UIController _parentFrame) {
		parentFrame = _parentFrame;
		parentFrame.addKeyListener(this);
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	// 現在入力されている方向キーを選別
	private int getPressedDirectionKey(){
		if(mKey[UP]){return KEY_DIR_UP;}
		else if(mKey[RIGHT]){return KEY_DIR_RIGHT;}
		else if(mKey[DOWN]){return KEY_DIR_DOWN;}
		else if(mKey[LEFT]){return KEY_DIR_LEFT;}
		else{return KEY_DIR_NONE;}
	}
	
	// 現在入力されている方向キー取得
	public int getDirection(){return mCurrentDirection;}
	// マス破壊キーの入力取得
	public boolean IsDestroy(){return mKey[DESTROY];}
	// 旗キーの入力取得
	public boolean IsCheck(){return mKey[CHECK];}
}
