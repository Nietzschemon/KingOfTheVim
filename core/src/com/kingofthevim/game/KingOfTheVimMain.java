package com.kingofthevim.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.screens.PlayScreen;
import com.kingofthevim.game.states.GameStateManager;

public class KingOfTheVimMain extends Game {

    //TODO Fixed right now for easier borders
	public static final int WIDTH = 990;
	public static final int HEIGHT = 990;

	public static final String TITLE = "King of the VIM";

	// memory intensive and therefore open to every screen
	// so it does not need to be recreated all the time
	public SpriteBatch batch;
	private GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new PlayScreen(this));
		Gdx.graphics.setContinuousRendering(false);


		/*
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		*/

	}

	@Override
	public void render () {
		super.render();

		/*
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

		 */

	}
	
	@Override
	public void dispose () {
	    super.dispose();
		batch.dispose();
	}
}
