package Labyrinth;

import global.Point;
import java.util.Random;
import Level.LevelState;

// 迷路作成クラス
public class Labyrinth {
	public enum LabyState{Route, Wall, Start, Goal}
	
	private static LabyState[][] mLabyrinth;
	private static Point size, start, goal;
	private static LevelState ls;
	
	// 最短路での各y座標におけるルートのあるx座標記憶
	private static int routeCache[];

	// 迷路の作成
	public static LabyState[][] createLabyrinth(LevelState _ls){
		ls = _ls;
		size = new Point(ls.gameCellSizeW, ls.gameCellSizeH);
		start = new Point((new Random()).nextInt(size.x), 0);
		goal = new Point((new Random()).nextInt(size.x), size.y - 1);
		mLabyrinth = new LabyState[size.y][size.x];
		
		// 初期化
		for(int i=0; i<size.y; i++)
			for(int j=0; j<size.x; j++)
				mLabyrinth[i][j] = LabyState.Wall;
		
		mLabyrinth[start.y][start.x] = LabyState.Start;
		mLabyrinth[goal.y][goal.x] = LabyState.Goal;
		
		createRoute();
		
		// createRoute() で消えたStart, Goalマスを復元
		mLabyrinth[start.y][start.x] = LabyState.Start;
		mLabyrinth[goal.y][goal.x] = LabyState.Goal;

//		printLabyrinth(mLabyrinth);
		
		return mLabyrinth;
	}
	
	// 迷路のルート作成
	private static void createRoute(){
		// Route作成者
		Point router = start.clone(), delta;
		routeCache = new int[size.y];
		
		// StartからGoalまでのランダム最短路生成
		while(true){
			if(mLabyrinth[router.y][router.x] == LabyState.Goal){break;}
			if(mLabyrinth[router.y][router.x] != LabyState.Start){
				mLabyrinth[router.y][router.x] = LabyState.Route;
				routeCache[router.y] = router.x;
			}
			delta = calcDirectionVector(router, goal);
			if(delta.x == 0){router.y++;}
			else if(delta.y == 0){router.x += delta.x / Math.abs(delta.x);}
			else{
				if((new Random()).nextBoolean()){router.y++;}
				else{router.x += delta.x / Math.abs(delta.x);}
			}
		}
		
		// Routeのランダムな地点を横に引き伸ばす
		// Level数までの乱数の数だけ引き伸ばす。引き伸ばし先はランダム。
		int rangeNum = (ls.gameCellSizeH - 2) / ls.curveNum - 1;
		for(int n=0; n<ls.curveNum; n++){
			int rangeStart = n * rangeNum + 1,
				curveY = (new Random()).nextInt(rangeNum) + rangeStart, // 引き伸ばす中心のY座標
				curveXStart = routeCache[curveY],                       // curveYに対応するx座標
				curveXEnd = (new Random()).nextInt(mLabyrinth[curveY].length);  // 引き伸ばし終わりのx座標
			
			if(curveXStart == curveXEnd){break;}

			int cxs = curveXStart, cxe = curveXEnd;
			// Swap
			if(cxs > cxe){int tmp = cxs; cxs = cxe; cxe = tmp;}
			
			for(int i=cxs; i<=cxe; i++){
				mLabyrinth[curveY-1][i] = mLabyrinth[curveY+1][i] = LabyState.Route;
				mLabyrinth[curveY][i] = LabyState.Wall;
			}
			
			mLabyrinth[curveY][curveXStart] = LabyState.Wall;
			mLabyrinth[curveY][curveXEnd] = LabyState.Route;
		}
		
		// 初期位置の周囲１マスは地雷なし
		mLabyrinth[1][start.x] = LabyState.Route;
		if(start.x > 0){mLabyrinth[0][start.x-1] = mLabyrinth[1][start.x-1] = LabyState.Route;}
		if(start.x < size.x-1){mLabyrinth[0][start.x+1] = mLabyrinth[1][start.x+1] = LabyState.Route;}
	}
	
	// ２点間の二次元ベクトルを計算
	private static Point calcDirectionVector(Point vo, Point v1){return new Point(v1.x - vo.x, v1.y - vo.y);}
	
	// デバッグ用作成迷路表示
	private static void printLabyrinth(LabyState[][] labyrinth){
		System.out.println("-----------------------Labyrinth-------------------------");
		
		for(int i=labyrinth.length-1; i>=0; i--){
			for(int j=0; j<labyrinth[i].length; j++){
				if(labyrinth[i][j] == LabyState.Route){
					System.out.print("o");
				}else if(labyrinth[i][j] == LabyState.Wall){
					System.out.print("-");
				}else if(labyrinth[i][j] == LabyState.Start){
					System.out.print("S");
				}else if(labyrinth[i][j] == LabyState.Goal){
					System.out.print("G");
				}
			}
			System.out.println("");
		}
		
		System.out.println("---------------------------------------------------------");
	}
}
