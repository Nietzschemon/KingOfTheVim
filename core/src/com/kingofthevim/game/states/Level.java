package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.LetterManager;
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

    protected Level(GameStateManager gsm) {
        super(gsm);
                //TODO use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


        vimMatrix = new VimWorldMatrix(rowTotal, columnTotal, fontWidth, fontHeight);

        backgroundText = new LetterManager(vimMatrix);
        labyrinthText = new LetterManager(vimMatrix);
    }


    //TODO
    // put in methods
    // levelPath()
    // score()
    // events()
    // cursorRules()

    protected abstract void levelPath();

    protected abstract void loadBackgroundText();

    @Override
    public void handleInput() {

        cursor.move();

        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            cursor.setMoveRight_word_bgn(true);
        } else{
            cursor.setMoveRight_word_bgn(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            cursor.setMoveRight_word_end(true);
        } else{
            cursor.setMoveRight_word_end(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            cursor.setMoveLeft_word(true);
        } else{
            cursor.setMoveLeft_word(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            cursor.setMoveRight_Char(true);
        } else{
            cursor.setMoveRight_Char(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            cursor.setMoveLeft_Char(true);
        } else{
            cursor.setMoveLeft_Char(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            cursor.setMoveDown_Line(true);
        } else{
            cursor.setMoveDown_Line(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            cursor.setMoveUp_Line(true);
        } else{
            cursor.setMoveUp_Line(false);
        }
    }
}
