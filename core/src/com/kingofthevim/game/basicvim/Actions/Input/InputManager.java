package com.kingofthevim.game.basicvim.Actions.Input;

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

            case Input.Keys.NUM_1:
                return moveInput.integerMaker('1');

            case Input.Keys.NUM_2:
                return moveInput.integerMaker('2');

            case Input.Keys.NUM_3:
                return moveInput.integerMaker('3');

            case Input.Keys.NUM_4:
                return moveInput.integerMaker('4');

            case Input.Keys.NUM_5:
                return moveInput.integerMaker('5');

            case Input.Keys.NUM_6:
                return moveInput.integerMaker('6');

            case Input.Keys.NUM_7:
                return moveInput.integerMaker('7');

            case Input.Keys.NUM_8:
                return moveInput.integerMaker('8');

            case Input.Keys.NUM_9:
                return moveInput.integerMaker('9');

            case Input.Keys.NUM_0:
                return moveInput.integerMaker('0');

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
