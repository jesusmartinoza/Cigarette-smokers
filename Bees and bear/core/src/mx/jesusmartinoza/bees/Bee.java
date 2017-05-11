package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class Bee extends Sprite implements Runnable {

	private int id;
	private HoneyPot honeyPot;
	private Vector2 originalPos;
	private Vector2 honeyPotCenter;
	private static int portion;
	private float duration;

	private Thread thread;
	private static final int DELAY = 1000;


	public Bee(int id, HoneyPot honeyPot, float x, float y) {
		super(new Texture("bee.png"));
		this.id = id;
		this.honeyPot = honeyPot;
		thread = new Thread(this);
		portion = 1;
		honeyPotCenter = new Vector2(honeyPot.getX(), honeyPot.getY());

		setSize(30, 30);
		originalPos = new Vector2(x, y);
		setPosition(x, y);
		thread.start();
	}

	/**
	 * Animate bee translation.
	 * @param batch
	 * @param delta
	 */
	@Override
	public void draw(Batch batch, float delta) {
		duration += delta;

		/*if(duration >= 0.5f) {
			translate(1, 1);
			duration = 0;
		} else
			translate(-1, -1);*/

		draw(batch);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(DELAY);
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
