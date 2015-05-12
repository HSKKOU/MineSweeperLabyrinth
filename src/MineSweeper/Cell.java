package MineSweeper;

public class Cell {

	// マスの状態
	enum CellState{
		Normal,
		Checked,
		Destroyed,
	}
	
	private CellState mState;
	private boolean mHasMine;
	private int mAroundMines;
	
	public Cell(boolean _hasMine){
		this.mHasMine = _hasMine;
		this.mState = CellState.Normal;
	}
	
	public void setAroundMines(int _mines){
		this.mAroundMines = _mines;
	}
	
	public boolean hasMine(){
		return this.mHasMine;
	}
	
	public void destroyCellWithExplosion(){
		this.mState = CellState.Destroyed;
	}
}
