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

public class KingOfTheVimMain extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Bird";

	private SpriteBatch batch;
	private Pointer pointer;


	@Override
	public void create () {
		batch = new SpriteBatch();
		pointer = new Pointer(0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		pointer.updateMotion();
		if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
			pointer.setRightMove(true);
		} else{
			pointer.setRightMove(false);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
			pointer.setLeftMove(true);
		} else{
			pointer.setLeftMove(false);
		}


		batch.begin();
		batch.draw(pointer.getTexture(), (int)pointer.getX(), (int)pointer.getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		pointer.dispose();
	}
}
