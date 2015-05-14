package MineSweeper;

import static constants.Constants.*;
import Labyrinth.Labyrinth.*;

// マインスイーパーのフィールド管理
public class FieldController {
	
	// マスの集合
	private Cell cells[][];
	
	// フィールドサイズ
	private int mFieldWidth;
	private int mFieldHeight;

	public FieldController(int _level){
		mFieldWidth = LEVEL_CELL_SIZE[_level][W];
		mFieldHeight = LEVEL_CELL_SIZE[_level][H];
		cells = new Cell[mFieldHeight][mFieldWidth];
		
		LabyState[][] labyrinth = Labyrinth.Labyrinth.createLabyrinth(_level);
	}
	
	// フィールドサイズ取得
	public int getWidth(){return mFieldWidth;}
	public int getHeight(){return mFieldHeight;}
}
