package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Table extends Sprite {

	private static ArrayList<Ingredient> ingredients;
	private boolean busy;

	/**
	 * Constructor.
	 * Init array of ingredients and set sprite's position.
	 */
	public Table() {
		super(new Texture(Gdx.files.internal("table.png")));
		ingredients = new ArrayList<Ingredient>();
		setPosition(70, -40);
		setScale(0.7f);
	}

	/**
	 * @return ingredients empty list
	 */
	public boolean isEmpty() {
		return ingredients.size() == 0;
	}

	/**
	 * @return busy flag
	 */
	public boolean isBusy() {
		return busy;
	}

	/**
	 * @param busy Set flag to true when someone is smoking
	 */
	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	/**
	 * @return Shared ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

}
