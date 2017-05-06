package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Smoker extends Sprite implements Runnable {

	private Table table;
	private Ingredient ingredient;

	/**
	 * Set new smoker with one ingredient and image.
	 * @param ingredient
	 * @param table Shared table instance
	 * @param spriteImage
	 */
	public Smoker(Ingredient ingredient, Table table, String spriteImage) {
		super(new Texture(Gdx.files.internal(spriteImage)));
		this.table = table;
		this.ingredient = ingredient;

		this.ingredient.setScale(0.7f);
		setSize(170, 340);
	}

	/**
	 * Generete random smoking time between 3 and 8 seconds
	 * and clear table ingredients list when finish
	 */
	private void smoke() {
		int smokingTime = MathUtils.random(3, 8) * 1000; // ms

		try {
			Thread.sleep(smokingTime);
			table.getIngredients().clear();
		} catch (InterruptedException e) {

		}
	}

	/**
	 * Draw ingredient
	 * @param batch
	 */
	public void drawIngredient(Batch batch) {
		ingredient.draw(batch);
	}

	/**
	 * Set smoker and ingredient position.
	 * @param x
	 * @param y
	 */
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);

		ingredient.setPosition(x + 20, y + 90);
	}

	/*+
		 * Always try to smoke when table has ingredients and smoker has the
		 * remaining ingredient.
		 */
	@Override
	public void run() {
		while(true) {
			if(!table.isEmpty() && !table.getIngredients().contains(ingredient))
				smoke();
		}
	}
}
