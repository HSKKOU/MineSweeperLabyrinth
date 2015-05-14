package MineSweeper;

// マス
public class Cell {

	// マスの状態
	enum CellState{
		Normal,
		Checked,
		Destroyed,
	}
	private CellState mState;

	// 地雷所持フラグ
	private boolean mHasMine;
	// 周囲の地雷数
	private int mAroundMines;
	
	public Cell(boolean _hasMine){
		this.mHasMine = _hasMine;
		this.mState = CellState.Normal;
	}
	
	// マスの状態
	public CellState getState(){return mState;}
	// 周囲の地雷数 get, set
	public int getAroundMines(){return this.mAroundMines;}
	public void setAroundMines(int _mines){this.mAroundMines = _mines;}
	// 地雷の所持取得
	public boolean hasMine(){return this.mHasMine;}
	
	// マスの破壊
	public void destroyCell(){this.mState = CellState.Destroyed;}
	// マスにフラグを立てる
	public void trigCheckCell(){
		if(this.mState == CellState.Normal){
			this.mState = CellState.Checked;
		}else if(this.mState == CellState.Checked){
			this.mState = CellState.Normal;
		}
	}
}
