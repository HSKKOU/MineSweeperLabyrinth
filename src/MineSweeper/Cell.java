package MineSweeper;

// マス
public class Cell {

	// マスの状態
	public enum CellState{
		Normal,
		Checked,
		Destroyed,
		Start,
		Goal,
		Player,
	}
	protected CellState mState;
	protected CellState mStateStack;

	// 地雷所持フラグ
	protected boolean mHasMine;
	// 周囲の地雷数
	protected int mAroundMines;
	
	public Cell(){
		this.mState = CellState.Normal;
		this.mStateStack = CellState.Normal;
	}
	public Cell(CellState _cs){
		this();
		this.mState = _cs;
	}
	
	// マスの状態
	public CellState getState(){return mState;}
	// 周囲の地雷数 get, set
	public int getAroundMines(){return this.mAroundMines;}
	public void setAroundMines(int _mines){this.mAroundMines = _mines;}
	public void inclementMines(){this.mAroundMines++;}
	
	// 地雷の所持取得
	public boolean hasMine(){return this.mHasMine;}
	
	// マスの破壊
	public void destroyCell(){
		if(this.mState == CellState.Checked){return;}
		this.mState = CellState.Destroyed;
	}
	// マスにフラグを立てる
	public void trigCheckCell(){
		if(this.mState == CellState.Normal){
			this.mState = CellState.Checked;
		}else if(this.mState == CellState.Checked){
			this.mState = CellState.Normal;
		}
	}
	
	// Playerが立てるか
	public boolean canStandPlayer(){
		if(this.mState == CellState.Normal || this.mState == CellState.Checked || this.mState == CellState.Player){return false;}
		return true;
	}
	
	// Playerが立つ
	public void standPlayer(){
		if(this.mState == CellState.Normal || this.mState == CellState.Checked || this.mState == CellState.Player){return;}
		this.mStateStack = this.mState;
		this.mState = CellState.Player;
	}

	// Playerが去った
	public void leavePlayer(){
		this.mState = this.mStateStack;
	}
	
	public boolean isStandingGoal(){
		return this.mState == CellState.Player && this.mStateStack == CellState.Goal;
	}
}
