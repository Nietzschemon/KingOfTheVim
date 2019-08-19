package com.kingofthevim.game.engine.vim_modes.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.engine.vim_modes.InsertMode;
import com.kingofthevim.game.engine.vim_object.VimObject;

public class InsertModeInput extends InsertMode implements InputProcessor {

    VimObject vimObject;
    boolean muteInput = false;
    boolean shiftHeld = false;

    public InsertModeInput(VimObject vimObject){
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

        if(!muteInput) insertLetter(vimObject, character);

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
