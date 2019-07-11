package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.LetterManager;
import com.kingofthevim.game.basicvim.PointSystem;
import com.kingofthevim.game.basicvim.VimWorldMatrix;

public abstract class Level extends State {


    protected int rowTotal = 22;
    protected int columnTotal = 44;
    protected final int fontWidth = 22;
    protected final int fontHeight = 44;
    protected Cursor cursor;
    protected LetterManager backgroundText;
    protected LetterManager labyrinthText;
    protected VimWorldMatrix vimMatrix;

    protected Music backgroundMusic;

    protected PointSystem pointsSys;

    protected Level(GameStateManager gsm) {
        super(gsm);

        //TODO use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


        vimMatrix = new VimWorldMatrix(rowTotal, columnTotal, fontWidth, fontHeight);

        backgroundText = new LetterManager(vimMatrix);
        labyrinthText = new LetterManager(vimMatrix);
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
    public void handleInput() {

        cursor.update();

    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();

    }
}
