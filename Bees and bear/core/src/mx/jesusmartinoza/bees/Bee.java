package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class Bee extends Sprite implements Runnable {

	private int id;
	private HoneyPot honeyPot;

	private Thread thread;

	private static int portion;

	public Bee(int id, HoneyPot honeyPot, float x, float y) {
		super(new Texture("bee.png"));
		this.id = id;
		this.honeyPot = honeyPot;
		thread = new Thread(this);
		portion = 1;

		setSize(30, 30);
		setPosition(x, y);
		thread.start();
	}

	public void draw(Batch batch, float delta) {
		draw(batch);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!honeyPot.isBusy()) {
				honeyPot.addPortion(portion);
				Gdx.app.log("BEE " + id, "Add " + portion + " portions");
			}
		}
	}
}
