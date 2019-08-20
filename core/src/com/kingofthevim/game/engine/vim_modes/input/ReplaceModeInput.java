package com.kingofthevim.game.engine.vim_modes.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.engine.vim_modes.ReplaceMode;
import com.kingofthevim.game.engine.vim_object.VimObject;

/**
 * InputProcessor for ReplaceMode class
 */
public class ReplaceModeInput extends ReplaceMode implements InputProcessor {

    VimObject vimObject;
    char operatorChar;
    boolean hasExecuted = false;
    boolean muteInput;

    public ReplaceModeInput(VimObject vimObject){
        this.vimObject = vimObject;
    }

    @Override
    public boolean keyDown(int keycode) {

        return Input.Keys.ESCAPE != keycode;

    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        if(!muteInput) replaceChar(vimObject, character);

        muteInput = false;

        return true;
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
