package com.kingofthevim.game.engine.Actions.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.engine.vimobject.VimObject;

public class TextInput implements InputProcessor {

    VimObject vimObject;
    char operatorChar;
    boolean hasExecuted = false;
    char currChar;
    String keyString;
    boolean shiftHeld = false;

    public TextInput(VimObject vimObject){
        this.vimObject = vimObject;
    }

    @Override
    public boolean keyDown(int keycode) {

        shiftHeld = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        keyString = Input.Keys.toString(keycode);

        return writeLetter(keycode);

    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        if(character == operatorChar){
            operatorChar = 'ł';
            return false;
        }

        //changeLetter(character);

        return false;
    }

    private void changeLetter(char character){
        if(character > 32 && character < 126){
            vimObject.getCurrentCell().setCellLook(character);
            hasExecuted = true;
        }
    }

    private boolean writeLetter(int keycode){
        if(keyString.length() < 2) {

            if(! shiftHeld) keyString = keyString.toLowerCase();

            char key = keyString.charAt(0);
            vimObject.getCurrentCell().setCellLook(key);
            vimObject.getPosition().setRelativeColumn(1);
            return true;
        }

        if(Input.Keys.SPACE == keycode){
            vimObject.getCurrentCell().clearCell();
            vimObject.getPosition().setRelativeColumn(1);
            return true;
        }

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
