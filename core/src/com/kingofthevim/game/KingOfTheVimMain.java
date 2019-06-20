package com.kingofthevim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingofthevim.game.basicvim.Pointer;
import com.kingofthevim.game.states.GameStateManager;
import com.kingofthevim.game.states.MenuState;

public class KingOfTheVimMain extends ApplicationAdapter {

    //TODO Fixed right now for easier borders
	public static final int WIDTH = 990;
	public static final int HEIGHT = 990;

	public static final String TITLE = "King of the VIM";

	private SpriteBatch batch;
	private GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
	    super.dispose();
		batch.dispose();
	}
}
