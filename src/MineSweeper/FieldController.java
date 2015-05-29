package MineSweeper;

import global.Point;
import static global.Constants.*;
import Labyrinth.Labyrinth.*;
import Level.LevelState;
import MineSweeper.Cell.CellState;

// マインスイーパーのフィールド管理
public class FieldController {
	
	// マスの集合
	private Cell cells[][];
	
	// フィールドサイズ
	private int mFieldWidth;
	private int mFieldHeight;
	
	// Player位置
	private Point playerLoc;

	public FieldController(LevelState _ls){
		mFieldWidth = _ls.gameCellSizeW;
		mFieldHeight = _ls.gameCellSizeH;
		playerLoc = null;
		cells = new Cell[mFieldHeight][mFieldWidth];
		
		LabyState[][] labyrinth = Labyrinth.Labyrinth.createLabyrinth(_ls);
		
		// Labyrinthが生成した迷路をもとに各マスを生成
		for(int i=0; i<labyrinth.length; i++){
			for(int j=0; j<labyrinth[i].length; j++){
				cells[i][j] = CellFactory.createCell(labyrinth[i][j]);
				if(cells[i][j].getState() == CellState.Start){
					cells[i][j].standPlayer();
					playerLoc = new Point(j, i);
				}
			}
		}
		
		// 生成したマスをもとに各マスの周囲にある地雷の数を計算
		for(int i=0; i<labyrinth.length; i++){
			for(int j=0; j<labyrinth[i].length; j++){
				if(!cells[i][j].hasMine()){continue;}
				inclementAroundMines(j, i);
			}
		}
		
//		printField(cells);
//		printMinesNum(cells);
	}
	
	// (x, y)の座標にあるマスの周囲８マスにある地雷数を計算
	private void inclementAroundMines(int x, int y){
		for(int dy=-1; dy<=1; dy++){
			for(int dx=-1; dx<=1; dx++){
				if(dx == 0 && dy == 0){continue;}
				try{
					cells[y + dy][x + dx].inclementMines();
				}catch(Exception e){	
				}
			}
		}
	}
	
	// (x, y)にあるマスの状態を取得
	public CellState getCellState(int x, int y){return cells[y][x].getState();}
	// (x, y)にあるマスの周囲にある地雷数を取得
	public int getCellAroundMines(int x, int y){return cells[y][x].getAroundMines();}
	
	// Playerの移動（移動可能かどうかを返す）
	public boolean movePlayer(int _direction){
		Point delta = direction2delta(_direction);
		
		assert playerLoc != null : "playerLoc is null!!";
		
		try{
			if(cells[playerLoc.y + delta.y][playerLoc.x + delta.x].canStandPlayer()){
				cells[playerLoc.y][playerLoc.x].leavePlayer();
				playerLoc.x += delta.x;
				playerLoc.y += delta.y;
				cells[playerLoc.y][playerLoc.x].standPlayer();
				return true;
			}
		}catch(Exception e){}
		return false;
	}
	
	// プレイヤーが破壊しようとするマスに地雷があるか
	public boolean hasBomb(int _direction){
		Point delta = direction2delta(_direction);
		
		assert playerLoc != null : "playerLoc is null!!";
		
		try{
			if(cells[playerLoc.y + delta.y][playerLoc.x + delta.x].hasMine()){return true;}
		}catch(Exception e){}
		
		return false;
	}
	
	// プレイヤーが破壊しようとするマスを破壊
	public void destroyCell(int _direction){
		Point delta = direction2delta(_direction);
		
		assert playerLoc != null : "playerLoc is null!!";
		
		try{
			cells[playerLoc.y + delta.y][playerLoc.x + delta.x].destroyCell();
		}catch(Exception e){}
	}

	// プレイヤーが旗を立てようとするマスに旗を立てる
	public void checkCell(int _direction){
		Point delta = direction2delta(_direction);
		
		assert playerLoc != null : "playerLoc is null!!";
		
		try{
			cells[playerLoc.y + delta.y][playerLoc.x + delta.x].trigCheckCell();
		}catch(Exception e){}
	}

	// 方向を示すKeyEventから(dx, dy)の２次元ベクトルを生成する
	private Point direction2delta(int _direction){
		Point delta = Point.zero();
		if(_direction == KEY_DIR_NONE){return delta;}
		switch(_direction){
			case KEY_DIR_UP: delta.y = 1; break;
			case KEY_DIR_RIGHT: delta.x = 1; break;
			case KEY_DIR_DOWN: delta.y = -1; break;
			case KEY_DIR_LEFT: delta.x = -1; break;
			default: return delta;
		}
		return delta;
	}
	
	// プレイヤーがゴールに辿り着いたかどうか
	public boolean reachGoal(){return cells[playerLoc.y][playerLoc.x].isStandingGoal();}
	
	// フィールドサイズ取得
	public int getWidth(){return mFieldWidth;}
	public int getHeight(){return mFieldHeight;}
	
	// デバッグ用Mine表示
	private static void printField(Cell[][] cs){
		System.out.println("-----------------------Mine Sweeper-------------------------");
		
		for(int i=cs.length-1; i>=0; i--){
			for(int j=0; j<cs[i].length; j++){
				if(cs[i][j].getState() == CellState.Start){
					System.out.print("S");
				}else if(cs[i][j].getState() == CellState.Goal){
					System.out.print("G");
				}else if(cs[i][j].hasMine()){
					System.out.print("o");
				}else if(cs[i][j].getState() == CellState.Player){
					System.out.print("P");
				}else{
					System.out.print("-");
				}
			}
			System.out.println("");
		}
		
		System.out.println("------------------------------------------------------------");
	}
	// デバッグ用Mine数表示
	private static void printMinesNum(Cell[][] cs){
		System.out.println("-----------------------Mine Sweeper-------------------------");
		
		for(int i=cs.length-1; i>=0; i--){
			for(int j=0; j<cs[i].length; j++){
				System.out.print(cs[i][j].getAroundMines());
			}
			System.out.println("");
		}
		
		System.out.println("------------------------------------------------------------");
	}
}
