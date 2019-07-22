package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

public class InputManager implements InputProcessor {


    Cursor cursor;
    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    MoveInput moveInput;



    public InputManager(Cursor cursor){
        moveInput = new MoveInput(cursor);

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(moveInput);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){

            case Input.Keys.M:
                //inputMultiplexer.addProcessor(0, action);
                return true;
            case Input.Keys.ESCAPE:
                inputMultiplexer.removeProcessor(moveInput);
                System.out.println("ESC");
                return true;

        }

        return false;
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
