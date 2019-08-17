package com.kingofthevim.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vimobject.Cursor;

import java.util.ArrayList;

public class LevelEditor implements InputProcessor {

    Cursor cursor;
    LetterType letterType;

    public LevelEditor(Cursor cursor){
        this.cursor = cursor;
    }


    @Override
    public boolean keyDown(int keycode) {

        boolean shiftHeld = (Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60));

        if(! shiftHeld) {
            switch (keycode) {

                case Input.Keys.E:
                    cursor.getCurrentCell().clearCell();
                    return true;

                case Input.Keys.B:
                    changeWithinRange('b');
                    return true;

                case Input.Keys.R:
                    changeWithinRange('r');
                    return true;

                case Input.Keys.G:
                    changeWithinRange('g');
                    return true;

                case Input.Keys.Y:
                    changeWithinRange('y');
                    return true;

                case Input.Keys.W:
                    changeWithinRange('w');
                    return true;
            }
        }
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {


        return true;
    }

    /**
     * Gets a subset of LetterTypes with the prefix-char
     * and gets the next in line every time it is called
     * @param typeRange prefix of LetterType subset i.e. in g_w use 'g'
     */
    private void changeWithinRange(char typeRange){

        LetterType letterType = cursor.getCurrentCell().getLetterType();
        ArrayList<LetterType> matched = LetterType.getMatched(typeRange);

        if(matched.contains(letterType)){
            int matchIndex = matched.indexOf(letterType);
            cursor.getCurrentCell().setCellLook((matchIndex < matched.size() - 1) ? matched.get(matchIndex + 1) : matched.get(0));
            return;
        }
        cursor.getCurrentCell().setCellLook(matched.get(0));
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
