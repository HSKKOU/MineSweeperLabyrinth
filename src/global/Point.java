package global;

// ２次元ベクトル
public class Point {
	public int x;
	public int y;
	
	public Point(int _x, int _y){
		this.x = _x;
		this.y = _y;
	}
	
	// 複製
	public Point clone(){return new Point(this.x, this.y);}
	
	// ゼロベクトル
	public static Point zero(){return new Point(0, 0);}
}