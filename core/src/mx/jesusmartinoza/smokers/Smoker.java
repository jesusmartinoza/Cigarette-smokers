package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Smoker extends Sprite implements Runnable {

	private Table table;
	private Ingredient ingredient;
	private Thread thread;

	private Animation<TextureRegion> smokeAnimation;
	private boolean smoking;
	private float duration;

	/**
	 * Set new smoker with one ingredient and image.
	 * @param ingredient
	 * @param table Shared table instance
	 * @param spriteImage
	 */
	public Smoker(Ingredient ingredient, Table table, String spriteImage) {
		super(new Texture(Gdx.files.internal(spriteImage)));
		this.table = table;
		this.ingredient = new Ingredient(ingredient);
		duration = 0;

		this.ingredient.setScale(0.7f);
		setSize(170, 340);
		setAnimation();

		// Init thread
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Generete random smoking time between 3 and 8 seconds
	 * and clear table ingredients list when finish
	 */
	private void smoke() {
		int smokingTime = MathUtils.random(3, 8) * 1000; // ms
		smoking = true;
		table.setBusy(true);

		try {
			thread.sleep(1000);
			table.getIngredients().clear();
			smoking = false;
			table.setBusy(false);
		} catch (InterruptedException e) {

		}
	}

	/**
	 * Split smoke.png and create animation
	 */
	private void setAnimation() {
		Texture texture = new Texture(Gdx.files.internal("smoke.png"));
		TextureRegion[][] splited = new TextureRegion(texture).split(128, 128);
		TextureRegion[] frames = new TextureRegion[10];

		for (int i = 0; i < 10; i++)
			frames[i] = splited[0][i];
		smokeAnimation = new Animation(0.15f, frames);
	}

	/**
	 * Draw ingredient
	 * @param batch
	 */
	public void drawIngredient(Batch batch) {
		ingredient.draw(batch);
	}

	/**
	 * Valid if the smoker has the remaining ingredient.
	 */
	private boolean canSmoke() {
		boolean valid = table.getIngredients().size() > 0;

		if(valid)
			for(Ingredient i : table.getIngredients())
				if(i.getId() == ingredient.getId())
					valid = false;

		return valid;
	}

	@Override
	public void draw(Batch batch, float delta) {
		super.draw(batch);

		if(smoking) {
			TextureRegion frame = smokeAnimation.getKeyFrame(duration, true);
			batch.draw(frame, getX() + 25, getY() + 225, 100, 100);
			duration += delta;
		}
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

	/*
	 * Always try to smoke when table has ingredients and smoker has the
	 * remaining ingredient.
	 */
	@Override
	public void run() {
		while(true) {
			if(!table.isBusy() && canSmoke())
				smoke();
		}
	}
}
