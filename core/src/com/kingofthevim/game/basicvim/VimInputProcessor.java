package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class VimInputProcessor extends InputAdapter  {

    VimInputProcessor(Pointer aPointer){

        this.pointer = aPointer;

    }

    private Pointer pointer;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:
                pointer.setLeftMove(true);
                break;
            case Input.Keys.RIGHT:
                pointer.setRightMove(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

}
