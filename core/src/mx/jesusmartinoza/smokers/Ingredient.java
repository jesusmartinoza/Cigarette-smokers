package mx.jesusmartinoza.smokers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by jesusmartinez on 05/05/17.
 */

public class Ingredient extends Sprite {

	private IngredientEnum id;

	/**
	 * Create new ingredient with sprite.
	 * @param id
	 */
	public Ingredient(IngredientEnum id) {
		super(new Texture(Gdx.files.internal("ingredients/tobacco.png")));
		this.id = id;
		Texture texture;

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
}

