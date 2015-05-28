package Level;

import static global.Constants.*;

public class LevelFactory {
	public static LevelState createLevel(String _level){
		if(_level.equals(LEVEL1)){
			return new Level1();
		}else if(_level.equals(LEVEL2)){
			return new Level2();
		}else if(_level.equals(LEVEL3)){
			return new Level3();
		}else{
			return null;
		}
	}
}
