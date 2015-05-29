package MineSweeper;

// 地雷を持たないマス
public class CellPlain extends Cell {

	public CellPlain() {
		super();
		this.mHasMine = false;
	}
}
