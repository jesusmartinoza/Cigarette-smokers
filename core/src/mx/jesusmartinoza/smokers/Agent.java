package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Agent implements Runnable {

	private ArrayList<Ingredient> ingredients;
	private Table table;
	private Thread thread;
	private Sprite awakeSprite;
	private Sprite sleepingSprite;

	/**
	 * The agent has an infinite supply of all three ingredients.
	 * @param table Shared table instance
	 */
	public Agent(Table table) {
		this.table = table;
		ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(IngredientEnum.PAPER));
		ingredients.add(new Ingredient(IngredientEnum.TOBACCO));
		ingredients.add(new Ingredient(IngredientEnum.MATCHES));

		awakeSprite = new Sprite(new Texture(Gdx.files.internal("agent.png")));
		sleepingSprite = new Sprite(new Texture(Gdx.files.internal("agent_sleeping.png")));

		awakeSprite.setSize(120, 120);
		sleepingSprite.setSize(120, 120);

		// Init thread
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * @return ingredients.
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void draw(Batch batch) {
		if(table.isBusy()) {
			sleepingSprite.draw(batch);
		} else {
			awakeSprite.draw(batch);
		}
	}

	/**
	 * Put ingredients when the table is empty
	 */
	@Override
	public void run() {
		while (true) {
			try {
				thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!table.isBusy() && table.isEmpty())
				putRandomIngredients(2);
		}
	}

	/**
	 * Generate 2 random ingredients and add them to the table.
	 * Can not put repeated
	 * @param n number of random ingredients.
	 */
	private void putRandomIngredients(int n) {
		ArrayList<Ingredient> tableIng = table.getIngredients();

		for(int i = 0; i < n; i++) {
			Ingredient ing = ingredients.get(
				MathUtils.random(ingredients.size() - 1));

			if(!tableIng.contains(ing)) {
				ing.setScale(0.8f);
				ing.setPosition(310 + (i * 80), 110);
				tableIng.add(ing);
			} else
				i--;
		}
	}
}
