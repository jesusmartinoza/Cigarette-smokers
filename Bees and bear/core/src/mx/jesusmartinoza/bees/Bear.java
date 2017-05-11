package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class Bear extends Sprite implements Runnable {

	private HoneyPot honeyPot;
	private boolean eating;
	private static final int DELAY = 500;
	private int duration;
	private Thread thread;

	/**
	 * @param honeyPot Shared honeyPot instance.
	 */
	public Bear(HoneyPot honeyPot) {
		super(new Texture("bear.png"));
		this.honeyPot = honeyPot;
		thread = new Thread(this);

		setSize(180, 180);
		setPosition(80, 200);
		thread.start();
	}

	/**
	 * Eat honey pot in 2 seconds
	 */
	private void eat() {
		honeyPot.setBusy(true);
		duration += DELAY;
		eating = true;

		if(duration > 2000) {
			eating = false;
			honeyPot.clear();
			honeyPot.setBusy(false);
			Gdx.app.log("BEAR", "The bear eat all the honey pot");
		}
	}

	/*
	public void draw(Batch batch) {
		if(eating) {

		} else { // sleeping

		}
	}*/

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(honeyPot.isFull()) {
				eat();
			}
		}
	}
}
