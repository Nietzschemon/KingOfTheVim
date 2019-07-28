package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.VimObject.Cursor;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

public class Builder implements InputProcessor {

    Cursor cursor;

    public Builder(Cursor cursor){
        this.cursor = cursor;
    }


    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){

            case Input.Keys.R:
                cursor.getCurrentCell().setLetterType(LetterType.RED);
                return true;

            case Input.Keys.Y:
                cursor.getCurrentCell().setLetterType(LetterType.YELLOW);
                return true;

            case Input.Keys.G:
                cursor.getCurrentCell().setLetterType(LetterType.GRAY);
                return true;

            case Input.Keys.W:
                cursor.getCurrentCell().setLetterType(LetterType.WHITE);
                return true;

            case Input.Keys.F:
                cursor.getCurrentCell().setLetterType(LetterType.WHITE_GREEN);
                return true;
        }

        return false;
    }

    public LetterType nextSubType(){
        return null;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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
