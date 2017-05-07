package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Game;

/**
 * Created by jesusmartinez on 06/05/17.
 */
public class Main extends Game {

	@Override
	public void create() {
		setScreen(new CigarretteSmokers());
	}
}
