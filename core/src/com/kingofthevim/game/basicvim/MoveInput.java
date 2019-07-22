package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

public class MoveInput extends Movement implements InputProcessor {

    private char currChar = 0;
    Cursor cursor;

    MoveInput(Cursor cursor){

        this.cursor = cursor;
        setObjectPosition(cursor.getPosition());
    }


    public boolean keyPressedIsChar(char dasChar){

        if(currChar == dasChar){
            currChar = '0';
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode){

            case Input.Keys.J:
                return charVerticalMove(cursor, true, 1);

            case Input.Keys.K:
                return charVerticalMove(cursor, false, 1);

            case Input.Keys.L:
                return charHorizontalMove(cursor, true, 1);

            case Input.Keys.H:
                return charHorizontalMove(cursor, false, 1);

            case Input.Keys.E:
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), false, 1);

            case Input.Keys.W:
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), true, 1);

            case Input.Keys.B:
                return traversePreviousWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), 1);

            case Input.Keys.NUM_0:
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
        currChar = character;
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
