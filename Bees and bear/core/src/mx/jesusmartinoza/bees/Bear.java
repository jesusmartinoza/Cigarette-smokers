package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class Bear extends Sprite implements Runnable {

	private HoneyPot honeyPot;
	private boolean eating;
	private static final int DELAY = 500;
	private Vector2 originalPos;
	private Thread thread;

	/**
	 * @param honeyPot Shared honeyPot instance.
	 */
	public Bear(HoneyPot honeyPot) {
		super(new Texture("bear.png"));
		this.honeyPot = honeyPot;
		thread = new Thread(this);

		originalPos = new Vector2(100, 200);
		setSize(180, 180);
		setPosition(100, 200);
		thread.start();
	}

	/**
	 * Eat honey pot in 2 seconds
	 */
	private void eat() {
		honeyPot.setBusy(true);
		eating = true;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		eating = false;
		honeyPot.clear();
		honeyPot.setBusy(false);
		Gdx.app.log("BEAR", "The bear eat all the honey pot");
	}

	/**
	 * Translate to given coordinates.
	 * Just translate when the difference is greater than 2
	 */
	private void translateTo(float x, float y) {
		if(Math.abs(getX() - x) > 4)
			translateX(getX() < x ? 2 : -4);
		if(Math.abs(getY() - y) > 4)
			translateY(getY() < y ? 2 : -2);
	}

	public void draw(Batch batch) {
		if(eating) {
			translateTo(honeyPot.getX(), honeyPot.getY() - 40);
		} else { // sleeping
			translateTo(originalPos.x, originalPos.y);
		}
		super.draw(batch);
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(honeyPot.isFull() && !eating) {
				Gdx.app.log("BEAR", "The bear begins eat");
				eat();
			}
		}
	}
}
