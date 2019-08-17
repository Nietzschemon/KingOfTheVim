package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.engine.*;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterManager;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.matrix.CellMatrix;
import com.kingofthevim.game.engine.vim_object.Cursor;
import com.kingofthevim.game.engine.sound.GameSound;
import com.kingofthevim.game.scens.Hud;
import com.kingofthevim.game.states.GameStateManager;
import com.kingofthevim.game.states.State;

import java.util.ArrayList;

public abstract class Level extends State {


    protected int rowTotal = 21;
    protected int columnTotal = 44;
    protected final int fontWidth = 22;
    protected final int fontHeight = 44;
    protected Cursor cursor;
    protected LetterManager backgroundText;
    protected LetterManager labyrinthText;
    protected CellMatrix vimMatrix;

    //Static to cary effectively between levels
    protected static Music backgroundMusic;

    protected ScoreSystem pointsSys;

    protected Hud hud;

    protected int cursorStartRow;
    protected int cursorStartColumn;
    protected MatrixSerialization serial;

    protected GameSound gameSound;

    //TODO implement
    public Stage stage;
    private Viewport viewport;


    protected Level(GameStateManager gsm) {
        super(gsm);

        //TODO use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


        vimMatrix = new CellMatrix(rowTotal, columnTotal, fontWidth, fontHeight);

        backgroundText = new LetterManager(vimMatrix);
        labyrinthText = new LetterManager(vimMatrix);

        hud = new Hud();

        stage = hud.stage;

        gameSound = new GameSound();
    }


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
            //cursor.dispose();
            //cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);
            //cursor.doAfterPosiUpdate();
            cursor.getPosition().setAbsolutePosition(cursorStartRow, cursorStartColumn);
            Gdx.graphics.requestRendering();
        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().getCartesianPosition().x, cursor.getPosition().getCartesianPosition().y);
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
