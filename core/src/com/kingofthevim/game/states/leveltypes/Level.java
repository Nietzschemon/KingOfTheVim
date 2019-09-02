package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.engine.*;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterManager;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.matrix.CellMatrix;
import com.kingofthevim.game.engine.vim_object.Cursor;
import com.kingofthevim.game.engine.sound.MusicManager;
import com.kingofthevim.game.scens.Hud;
import com.kingofthevim.game.states.GameStateManager;
import com.kingofthevim.game.states.Menu;
import com.kingofthevim.game.states.State;
import com.kingofthevim.game.states.levelsettings.LevelSettings;

import java.util.ArrayList;
import java.util.Queue;

public abstract class Level extends State {


    protected int rowTotal = 21;
    protected int columnTotal = 44;
    protected final int fontWidth = 22;
    protected final int fontHeight = 44;
    protected Cursor cursor;
    protected LetterManager letterAdder;
    protected CellMatrix vimMatrix;
    protected LevelSettings levelSettings;

    //Static to cary effectively between levels
    protected static Music backgroundMusic;

    protected ScoreSystem pointsSys;

    protected Hud hud;

    protected int cursorStartRow;
    protected int cursorStartColumn;
    protected MatrixSerialization serial;

    protected MusicManager musicManager;

    protected String levelName;
    protected Queue<String> levels;

    //TODO implement
    public Stage stage;
    private Viewport viewport;


    protected Level(GameStateManager gsm) {
        super(gsm);

        //use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);

        levelSettings = new LevelSettings();

        vimMatrix = new CellMatrix(rowTotal, columnTotal, fontWidth, fontHeight);

        letterAdder = new LetterManager(vimMatrix);

        hud = new Hud();

        stage = hud.stage;

        musicManager = new MusicManager();
    }


    protected abstract void backgroundMusic();

    protected abstract void checkWinCondition();


    protected void changeLevel(){

        if(levels.isEmpty()){
            MusicManager.scratch1.play();
            pointsSys.saveScore();
            gsm.push(new Menu(gsm));
            dispose();
        } else {

            levelName = levels.poll();

            loadLevel();

            pointsSys.newLevel(levelName);

            Gdx.graphics.requestRendering();
        }
    }

    protected void loadLevel(){

        cursor = serial.loadLevel(levelName, vimMatrix);
        cursor.setScoreSystem(pointsSys);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();
        levelSettings = serial.getLevelSettings();

    }


    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);

        hud.setMoveInt(updateSteps());
        hud.setScoreInt(updatePoints());

        sb.begin();

        if(cursor.isOnType(LetterType.GRAY)
                || cursor.isOnType(LetterType.EMPATHY)){
            cursor.getPosition().setAbsolutePosition(cursorStartRow, cursorStartColumn);

            if(levelSettings.resetLevelAtDeath) loadLevel();

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
        if(cursor.isInMode){
            sb.draw(cursor.getTexture(), cursor.getPosition().getCartesianPosition().x, cursor.getPosition().getCartesianPosition().y);
        }

        sb.end();

        quickKeys();
        stage.act();
        stage.draw();
    }

    public void quickKeys(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.F1)){
            loadLevel();
            pointsSys.resetAll();
        }
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
