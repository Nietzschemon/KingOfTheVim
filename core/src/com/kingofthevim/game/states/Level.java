package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.*;
import com.kingofthevim.game.scens.Hud;

import java.util.ArrayList;

public abstract class Level extends State {


    protected int rowTotal = 21;
    protected int columnTotal = 44;
    protected final int fontWidth = 22;
    protected final int fontHeight = 44;
    protected Cursor cursor;
    protected LetterManager backgroundText;
    protected LetterManager labyrinthText;
    protected VimWorldMatrix vimMatrix;

    //Static to cary effectivly between levels
    protected static Music backgroundMusic;

    protected PointSystem pointsSys;

    protected Hud hud;

    protected int cursorStartRow = 4;
    protected int cursorStartColumn = 8;


    //TODO implement
    public Stage stage;
    private Viewport viewport;


    protected Level(GameStateManager gsm) {
        super(gsm);

        // "3" ds

        //TODO use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


        vimMatrix = new VimWorldMatrix(rowTotal, columnTotal, fontWidth, fontHeight);

        backgroundText = new LetterManager(vimMatrix);
        labyrinthText = new LetterManager(vimMatrix);

        hud = new Hud();

        stage = hud.stage;
    }


    //TODO put in methods
    // score()
    // events()
    // cursorRules()

    protected abstract void levelPath();

    protected abstract void backgroundText();

    protected abstract void backgroundMusic();

    protected abstract void levelChange();




    @Override
    public void render(SpriteBatch sb) {
        stage.act();
        stage.draw();

        sb.setProjectionMatrix(cam.combined);

        hud.setMoveInt(updateSteps());
        hud.setScoreInt(updatePoints());

        sb.begin();

        if(cursor.isOnType(LetterType.GRAY)
                || cursor.isOnType(LetterType.EMPATHY)){
            cursor.dispose();
            cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);
        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);
        }

        for(ArrayList<Cell> cellRow : vimMatrix.getCellMatrix()){

            for(Cell cell : cellRow){

                if(cell.getCellLook() != null){
                    sb.draw(cell.getCellLook(),
                            cell.getCartesianPosition().x,
                            cell.getCartesianPosition().y);
                }
            }
        }

        sb.end();
    }

    @Override
    public void handleInput() {

        cursor.update();


    }

    @Override
    public void dispose() {
        hud.dispose();
        cursor.dispose();
    }

    public Integer updateSteps(){

        return pointsSys.getActualMoves();

    }

    public Integer updatePoints(){

        return pointsSys.getPoints();
    }
}
