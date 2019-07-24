package com.kingofthevim.game.basicvim.Actions.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.Actions.Operations;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

public class OperationInput extends Operations implements InputProcessor {

    private Cursor cursor;
    int iteration = 1;
    boolean hasExectued = false;

    public OperationInput(Cursor cursor){

        this.cursor = cursor;
        setObjectPosition(cursor.getPosition());
    }


    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        int deleteNum = (iteration > 0) ? iteration : 1;

        switch (keycode){

            case Input.Keys.J:
                //deleteCharBatch(cursor, deleteNum);
                hasExectued = true;
                return true;

            case Input.Keys.K:
                //deleteCharBatch(cursor, deleteNum);
                hasExectued = true;
                return true;

            case Input.Keys.L:
                deleteCharHorizontal(cursor, true, deleteNum);
                hasExectued = true;
                return true;

            case Input.Keys.H:
                deleteCharHorizontal(cursor, false, deleteNum);
                hasExectued = true;
                return true;

            case Input.Keys.E:

                deleteWordInFront(cursor, false, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), iteration);
                hasExectued = true;
                return true;

            case Input.Keys.W:

                deleteWordInFront(cursor, true, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), iteration);
                hasExectued = true;
                return true;

            case Input.Keys.B:
                //deleteNum = traversePreviousWord_Int(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), iteration);
                hasExectued = true;
                return true;

            case Input.Keys.NUM_0:
                if(iteration > 0) return false;
                //deleteNum = traverseWholeLine_Int(cursor, false);
                hasExectued = true;
                return true;


        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        int deleteNum = (iteration > 0) ? iteration : 1;

        if(character == '$'){
            deleteNum = traverseWholeLine_Int(cursor, true);
            deleteCharBatch(cursor, deleteNum + 1);
            hasExectued = true;
            return true;
        }
        if(character == '^'){
            //goToFirstNonBlankChar(cursor);
            hasExectued = true;
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
