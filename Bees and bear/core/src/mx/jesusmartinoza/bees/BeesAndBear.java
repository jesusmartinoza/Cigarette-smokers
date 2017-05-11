package mx.jesusmartinoza.bees;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class BeesAndBear implements Screen {

	private SpriteBatch batch;
	private HoneyPot honeyPot;
	private ArrayList<Bee> bees;
	private Bear bear;

	/**
	 * Create circumference with bees
	 */
	private void createBees() {
		int n = MathUtils.random(4, 10);
		int radius = 80;
		float angle = 360f / n;
		float startAngle = n % 2 == 0 ? 90 : 0;

		for(int i = 1; i <= n; i++, startAngle += angle) {
			float cos = MathUtils.cos(startAngle * 0.0174533f);
			float sin = MathUtils.sin(startAngle * 0.0174533f);

			float x = 420 + cos*radius;
			float y = 240 + sin*radius;
			bees.add(new Bee(i, honeyPot, x, y));
		}

		Gdx.app.log("BEES", "Created " + n + " bees");
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		honeyPot = new HoneyPot();
		bees = new ArrayList<Bee>();
		bear = new Bear(honeyPot);

		createBees();
	}

	/**
	 * Draw elements
	 * @param delta
	 */
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.95f, 0.92f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		honeyPot.draw(batch);
		for(Bee b : bees)
			b.draw(batch, delta);
		bear.draw(batch);

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
