package mx.jesusmartinoza.smokers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mx.jesusmartinoza.smokers.CigarretteSmokers;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fumadores";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new CigarretteSmokers(), config);
	}
}
