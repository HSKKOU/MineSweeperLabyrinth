package MineSweeper;

import java.util.Random;

import Labyrinth.Labyrinth.LabyState;
import MineSweeper.Cell.CellState;

public class CellFactory {

	// 地雷生成確率
	private static final int PROB_MINE = 30;
	
	public static Cell createCell(LabyState _ls){
		if(_ls == LabyState.Wall){
			int rnd = (new Random()).nextInt(100);
			if(rnd > PROB_MINE){return new CellPlain();}
			else{return new CellWithBomb();}
		}else if(_ls == LabyState.Route){
			return new CellPlain();
		}else if(_ls == LabyState.Start){
			return new Cell(CellState.Start);
		}else{
			return new Cell(CellState.Goal);
		}
	}
}
