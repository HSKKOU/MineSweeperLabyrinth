package constants;

import java.awt.event.KeyEvent;

public class Constants {
	
	private Constants(){}
	
	public static int FRAME_WIDTH = 900;
	public static int FRAME_HEIGHT = 700;
	
	public static int GAME_FRAME_WIDTH = 601;
	public static int GAME_FRAME_HEIGHT = 451;
	
	public static int CELL_WIDTH = 30;
	public static int CELL_HEIGHT = 30;
	
	public static int LEVEL1 = 0;
	public static int LEVEL2 = 1;
	public static int LEVEL3 = 2;
	
	public static int LEVEL_CELL_SIZE[][] = {
		{7,5},
		{12,8},
		{20,15}
	};
	public static int W = 0;
	public static int H = 1;

	public static final int KEY_DIR_NONE = -1;
	public static final int KEY_DIR_UP = KeyEvent.VK_UP;
	public static final int KEY_DIR_RIGHT = KeyEvent.VK_RIGHT;
	public static final int KEY_DIR_DOWN = KeyEvent.VK_DOWN;
	public static final int KEY_DIR_LEFT = KeyEvent.VK_LEFT;
	public static final int KEY_ACT_DESTROY = KeyEvent.VK_SPACE;
	public static final int KEY_ACT_CHECK = KeyEvent.VK_SHIFT;

}
