package global;

import java.awt.event.KeyEvent;

// 定数まとめクラス
public class Constants {	
	private Constants(){}
	
	// Window枠サイズ
	public static int FRAME_WIDTH = 900;
	public static int FRAME_HEIGHT = 700;
	
	// ゲーム枠サイズ
	public static int GAME_FRAME_WIDTH = 601;
	public static int GAME_FRAME_HEIGHT = 451;
	
	// マスサイズ
	public static int CELL_WIDTH = 30;
	public static int CELL_HEIGHT = 30;
	
	// マスの文字padding
	public static double CELL_STR_PADDING_H = CELL_WIDTH / 3.3;
	public static double CELL_STR_PADDING_V = CELL_HEIGHT / 1.3;
	
	// ゲームレベル
	public static String LEVELstr = "LEVEL";
	public static String LEVEL1 = LEVELstr + 1;
	public static String LEVEL2 = LEVELstr + 2;
	public static String LEVEL3 = LEVELstr + 3;
	
	// 縦、横の定数化
	public static int W = 0;
	public static int H = 1;

	// int型KeyAction定数のグローバル変数化
	public static final int KEY_DIR_NONE = -1;
	public static final int KEY_DIR_UP = KeyEvent.VK_UP;
	public static final int KEY_DIR_RIGHT = KeyEvent.VK_RIGHT;
	public static final int KEY_DIR_DOWN = KeyEvent.VK_DOWN;
	public static final int KEY_DIR_LEFT = KeyEvent.VK_LEFT;
	public static final int KEY_ACT_DESTROY = KeyEvent.VK_SPACE;
	public static final int KEY_ACT_CHECK = KeyEvent.VK_SHIFT;

	public static String KEY2STR(int _KEY){
		String str = "NONE";
		switch(_KEY){
			case KEY_DIR_UP:
				str = "UP";
				break;
			case KEY_DIR_RIGHT:
				str = "RIGHT";
				break;
			case KEY_DIR_DOWN:
				str = "DOWN";
				break;
			case KEY_DIR_LEFT:
				str = "LEFT";
				break;
			default: break;
		}
		
		return str;
	}
}
