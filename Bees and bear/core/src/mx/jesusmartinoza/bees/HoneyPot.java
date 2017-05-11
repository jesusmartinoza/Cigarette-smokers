package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jesusmartinez on 08/05/17.
 */

public class HoneyPot {

	private int totalPortions;
	private int currentPortions;
	private boolean busy;

	/**
	 * Init honey pot with random totalPortions
	 */
	public HoneyPot() {
		totalPortions = MathUtils.random(5, 30);
		currentPortions = 0;

		Gdx.app.log("HONEYPOT", "Created with " + totalPortions + " portions");
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public void addPortion(int portion) {
		currentPortions += portion;
	}

	public boolean isFull() {
		return currentPortions >= totalPortions;
	}

	public void clear() {
		currentPortions = 0;
	}
}
