package MineSweeper;

import static constants.Constants.*;

public class FieldController {
	
	private Cell cells[][];

	public FieldController(int _level){
		cells = new Cell[LEVEL_CELL_SIZE[_level][W]][LEVEL_CELL_SIZE[_level][H]];
	}
	
}
