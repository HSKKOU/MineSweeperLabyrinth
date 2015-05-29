package MineSweeper;

// 地雷を持つマス
public class CellWithBomb extends Cell {

	public CellWithBomb() {
		super();
		this.mHasMine = true;
	}
}
