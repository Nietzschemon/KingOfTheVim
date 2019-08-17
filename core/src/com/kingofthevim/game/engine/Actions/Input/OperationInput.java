package com.kingofthevim.game.engine.Actions.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.engine.Actions.Operations;
import com.kingofthevim.game.engine.Actions.VimMove;
import com.kingofthevim.game.engine.Actions.VimMovement;
import com.kingofthevim.game.engine.VimObject.Cursor;

public class OperationInput extends Operations implements InputProcessor, VimMovement {

    private Cursor cursor;
    int iteration = 1;
    boolean hasExectued = false;
    VimMove vimMove;

    public OperationInput(Cursor cursor){

        this.cursor = cursor;
        setObjectPosition(cursor.getPosition());
        vimMove = new VimMove();
    }


    @Override
    public boolean keyDown(int keycode) {
        int deleteNum = (iteration > 0) ? iteration : 1;
        boolean shiftHeld = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

        switch (keycode){

            case Input.Keys.J:
                //deleteCharBatch(cursor, deleteNum);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.K:
                //deleteCharBatch(cursor, deleteNum);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.L:
                deleteCharHorizontal(cursor, true, deleteNum);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.H:
                deleteCharHorizontal(cursor, false, deleteNum);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.E:

                deleteWordInFront(cursor, false, shiftHeld, iteration);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.W:

                deleteWordInFront(cursor, true, shiftHeld, iteration);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.B:
                deleteWordBehind(cursor, shiftHeld, iteration);
                hasExectued = true;
                vimMove.add(keycode, iteration, 'd', shiftHeld);
                return true;

            case Input.Keys.NUM_0:
                if(iteration > 0) return false;
                deleteLineHorizontal(cursor, false);
                hasExectued = true;
                vimMove.add('0', iteration, 'd');
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

        if(character == '$'){
            deleteLineHorizontal(cursor, true);
            hasExectued = true;
            vimMove.add(character, iteration, 'd');
            return true;
        }
        if(character == '^'){
            deleteToLineBgn(cursor);
            hasExectued = true;
            vimMove.add(character, iteration, 'd');
            return true;
        }
        return false;
    }

    @Override
    public VimMove getVimMove() {
        return vimMove;
    }

    @Override
    public void setVimMove(VimMove vimMove) {
        this.vimMove = vimMove;
    }

    @Override
    public VimMove getResetVimMove() {
        VimMove move = vimMove;
        vimMove = new VimMove();
        return move;
    }

    @Override
    public boolean hasMove() {
        return vimMove.move != '\u0000';
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
