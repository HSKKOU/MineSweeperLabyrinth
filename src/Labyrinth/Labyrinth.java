package Labyrinth;

import static constants.Constants.*;

import java.util.Random;

// 迷路作成クラス
public class Labyrinth {
	
	public enum LabyState{
		Root, Wall, Start, Goal
	}

	// 迷路のルート作成
	public static LabyState[][] createLabyrinth(int _level){
		Random rnd = new Random();
		int width = LEVEL_CELL_SIZE[_level][W], 
			height = LEVEL_CELL_SIZE[_level][H],
			start = rnd.nextInt(width),
			goal = rnd.nextInt(width);
		LabyState[][] labyrinth = new LabyState[height][width];
		
		for(int i=0; i<labyrinth.length; i++)
			for(int j=0; j<labyrinth[i].length; j++)
				labyrinth[i][j] = LabyState.Wall;
		
		
		
		labyrinth[height-1][start] = LabyState.Start;
		labyrinth[0][goal] = LabyState.Goal;
		
		printLabyrinth(labyrinth);
		
		return labyrinth;
	}
	
	// デバッグ用作成迷路表示
	private static void printLabyrinth(LabyState[][] labyrinth){
		System.out.println("-----------------------Labyrinth-------------------------");
		
		for(int i=0; i<labyrinth.length; i++){
			for(int j=0; j<labyrinth[i].length; j++){
				if(labyrinth[i][j] == LabyState.Root){
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
