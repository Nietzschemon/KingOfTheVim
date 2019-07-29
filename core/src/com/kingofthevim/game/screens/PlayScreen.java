package com.kingofthevim.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.states.*;

public class PlayScreen implements Screen {

    KingOfTheVimMain theGame;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    //private Hud hud;
    private GameStateManager gsm;

    public PlayScreen(KingOfTheVimMain theGame){

        this.theGame = theGame;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, gameCam);
        //hud = new Hud(theGame.batch);
        gsm = new GameStateManager();

        gameCam.position.set(gamePort.getWorldHeight() / 2, gamePort.getWorldWidth() / 2, 0);

        gsm.push(new MenuState(gsm));
    }


    public void update(float delta){
        gameCam.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        theGame.batch.setProjectionMatrix(gameCam.combined);
        //theGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);


        gsm.update(delta);
        gsm.render(theGame.batch);

        //hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
