package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Main class.
 */
public class CigarretteSmokers implements Screen {

	private SpriteBatch batch;
	private Agent agent;
	private ArrayList<Smoker> smokers;
	private Table table;
	private Sprite background;

	/**
	 * Create smokers list using agent's ingredients.
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
	public void show() {
		batch = new SpriteBatch();
		table = new Table();
		agent = new Agent(table);

		background = new Sprite(new Texture("background.jpg"));

		createSmokers();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		background.draw(batch);
		for(Smoker s : smokers)
			s.draw(batch, delta);

		// Draw table and every smoker ingredient
		table.draw(batch);
		for(Smoker s : smokers)
			s.drawIngredient(batch);

		if(table.isBusy())
			for(Ingredient i : table.getIngredients())
				i.draw(batch);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
