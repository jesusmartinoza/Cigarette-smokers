package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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
	private boolean busy;

	private Thread thread;

	public Bee(int id, HoneyPot honeyPot, float x, float y) {
		super(new Texture("bee.png"));
		this.id = id;
		this.honeyPot = honeyPot;
		thread = new Thread(this);
		portion = 1;
		honeyPotCenter = new Vector2(honeyPot.getX() + 40, honeyPot.getY() + 40);

		setSize(30, 30);
		originalPos = new Vector2(x, y);
		setPosition(x, y);
		thread.start();
	}

	/**
	 * Translate to given coordinates.
	 * Just translate when the difference is greater than 2
	 * @param dest Destination vector
	 */
	private void translateTo(Vector2 dest) {
		float x = dest.x;
		float y = dest.y;
		if(Math.abs(getX() - x) > 2)
			translateX(getX() < x ? 1 : -1);
		if(Math.abs(getY() - y) > 2)
			translateY(getY() < y ? 1 : -1);
	}

	/**
	 * Animate bee translation.
	 * @param batch
	 * @param delta
	 */
	@Override
	public void draw(Batch batch, float delta) {
		if(busy) {
			setColor(Color.CORAL);
			translateTo(honeyPotCenter);
		} else {
			translateTo(originalPos);
			setColor(Color.WHITE);
		}

		draw(batch);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(MathUtils.random(8, 20) * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!honeyPot.isBusy()) {
				honeyPot.addPortion(portion);
				Gdx.app.log("BEE " + id, "Add " + portion + " portions");
				busy = !busy;
			}
		}
	}
}
