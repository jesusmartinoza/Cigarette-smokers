package mx.jesusmartinoza.bees.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mx.jesusmartinoza.bees.BeesAndBear;
import mx.jesusmartinoza.bees.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "A Bear, Honey Pot and Bees";
		config.width = 600;
		config.height = 480;
		new LwjglApplication(new Main(), config);
	}
}
