package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Main class.
 */
public class CigarretteSmokers extends ApplicationAdapter {

	private SpriteBatch batch;
	private Agent agent;
	private ArrayList<Smoker> smokers;
	private Table table;

	/**
	 * Create smokers list using agents ingredients.
	 */
	private void createSmokers() {
		smokers = new ArrayList<Smoker>();

		for(int i = 0; i < agent.getIngredients().size(); i++) {
			Ingredient in = agent.getIngredients().get(i);
			Smoker s = new Smoker(in, table, "smokers/smoker" + (i+1) +".png");

			s.setPosition(200 + (i*120), 50);
			smokers.add(s);
		}
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		table = new Table();
		agent = new Agent(table);

		createSmokers();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		agent.draw(batch);
		for(Smoker s : smokers)
			s.draw(batch);

		for(Ingredient i : table.getIngredients())
			i.draw(batch);

		// Draw table and every smoker ingredient
		table.draw(batch);
		for(Smoker s : smokers)
			s.drawIngredient(batch);

		batch.end();
	}
}
