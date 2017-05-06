package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Agent extends Sprite implements Runnable {

	private ArrayList<Ingredient> ingredients;
	private Table table;

	/**
	 * The agent has an infinite supply of all three ingredients.
	 * @param table Shared table instance
	 */
	public Agent(Table table) {
		super(new Texture(Gdx.files.internal("agent.png")));
		this.table = table;
		ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(IngredientEnum.PAPER));
		ingredients.add(new Ingredient(IngredientEnum.TOBACCO));
		ingredients.add(new Ingredient(IngredientEnum.MATCHES));

		setSize(120, 120);
	}

	/**
	 * @return ingredients.
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Put ingredients when the table is empty
	 */
	@Override
	public void run() {
		while (true) {
			if(table.isEmpty())
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

			if(!tableIng.contains(ing))
				tableIng.add(ing);
			else
				i--;
		}
	}
}
