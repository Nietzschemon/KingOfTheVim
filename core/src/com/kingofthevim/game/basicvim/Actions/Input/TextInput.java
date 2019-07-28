package com.kingofthevim.game.basicvim.Actions.Input;

import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

public class TextInput implements InputProcessor {

    VimObject vimObject;
    char operatorChar;
    boolean hasExecuted = false;
    char currChar;

    public TextInput(VimObject vimObject){
        this.vimObject = vimObject;
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
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

        if(character > 32 && character < 126){
            vimObject.getCurrentCell().setLetter(character);
            hasExecuted = true;
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
