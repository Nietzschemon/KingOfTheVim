package com.kingofthevim.game.basicvim.Actions.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.Actions.Movement;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

public class MoveInput extends Movement implements InputProcessor {

    Cursor cursor;

    public MoveInput(Cursor cursor){

        this.cursor = cursor;
        setObjectPosition(cursor.getPosition());
    }


    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode){

            case Input.Keys.J:
                return charVerticalMove(cursor, true, getResetIterationInt());

            case Input.Keys.K:
                return charVerticalMove(cursor, false, getResetIterationInt());

            case Input.Keys.L:
                return charHorizontalMove(cursor, true, getResetIterationInt());

            case Input.Keys.H:
                return charHorizontalMove(cursor, false, getResetIterationInt());

            case Input.Keys.E:
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), false, getResetIterationInt());

            case Input.Keys.W:
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), true, getResetIterationInt());

            case Input.Keys.B:
                return traversePreviousWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), getResetIterationInt());

            case Input.Keys.NUM_0:
                if(getIterationInt() > 0) return false;
                return traverseWholeLine(cursor, false);


        }
        if(keyPressedIsChar('$')){
            return traverseWholeLine(cursor, true);
        }
        if(keyPressedIsChar('^')){
            return goToFirstNonBlankChar(cursor);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        setCurrChar(character);
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
