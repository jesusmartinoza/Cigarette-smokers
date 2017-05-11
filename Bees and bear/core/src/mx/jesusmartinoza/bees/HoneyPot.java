package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class HoneyPot extends Sprite {

	private int totalPortions;
	private int currentPortions;
	private boolean busy;
	private static BitmapFont font;

	/**
	 * Init honey pot with random totalPortions
	 */
	public HoneyPot() {
		super(new Texture("honey_pot.png"));
		font = new BitmapFont(Gdx.files.internal("regular.fnt"));
		totalPortions = MathUtils.random(15, 40);
		currentPortions = 0;

		setSize(100, 100);
		setPosition(385, 210);
		font.getData().setScale(0.85f);
		Gdx.app.log("HONEYPOT", "Created with " + totalPortions + " portions");
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);

		font.draw(batch, "" + currentPortions + " of " + totalPortions, getX() + 10, getY() - 60);
	}

	/**
	 * @return Busy flag
	 */
	public boolean isBusy() {
		return busy;
	}

	/**
	 * Set busy flag
	 * @param busy
	 */
	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	/**
	 * Add n portion to honeypot
	 * @param portion
	 */
	public void addPortion(int portion) {
		currentPortions += portion;
	}

	/**
	 * @return Is the honey pot full?
	 */
	public boolean isFull() {
		return currentPortions >= totalPortions;
	}

	/**
	 * Set currentPortions to 0
	 */
	public void clear() {
		currentPortions = 0;
	}
}
