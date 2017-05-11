package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Game;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class Main extends Game {

	@Override
	public void create() {
		setScreen(new BeesAndBear());
	}

}