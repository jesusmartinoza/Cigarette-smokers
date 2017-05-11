package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Ingredient extends Sprite {

	private IngredientEnum id;
	private static BitmapFont font;

	/**
	 * Create new ingredient with sprite.
	 * @param id
	 */
	public Ingredient(IngredientEnum id) {
		super(new Texture(Gdx.files.internal("ingredients/tobacco.png")));
		this.id = id;
		Texture texture;
		font = new BitmapFont(Gdx.files.internal("regular.fnt"));

		font.getData().setScale(0.85f);

		switch (id) {
			case TOBACCO:
				texture = new Texture(Gdx.files.internal("ingredients/tobacco.png"));
				break;

			case PAPER:
				texture = new Texture(Gdx.files.internal("ingredients/paper.png"));
				break;

			default:
				texture = new Texture(Gdx.files.internal("ingredients/matches.png"));
		}
		setTexture(texture);
		setSize(80, 80);
	}

	/**
	 * Draw sprite and id
	 */
	public void drawWithId(Batch batch) {
		draw(batch);
		font.draw(batch, id.toString(), getX() + getWidth(), getY() + 40);
	}

	/**
	 * Copy constructor. Get texture and create new instance.
	 * @param ingredient
	 */
	public Ingredient(Ingredient ingredient) {
		super(ingredient.getTexture());
		this.id = ingredient.id;

		setSize(80, 80);
	}

	/**
	 * @return ingredient id.
	 */
	public IngredientEnum getId() {
		return id;
	}
}

