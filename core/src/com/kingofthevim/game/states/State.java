package com.kingofthevim.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.kingofthevim.game.KingOfTheVimMain;

public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
