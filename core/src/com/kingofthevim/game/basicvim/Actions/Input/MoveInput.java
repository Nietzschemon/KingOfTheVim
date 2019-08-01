package com.kingofthevim.game.basicvim.Actions.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.Actions.Movement;
import com.kingofthevim.game.basicvim.MatrixSerialization;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

public class MoveInput extends Movement implements InputProcessor {

    Cursor cursor;
    int iteration = 0;
    boolean hasExectued = false;
    private boolean fMoveActive = false;
    MatrixSerialization matrixSerialization;
    boolean shiftHeld = false;
    String keyString;

    public MoveInput(Cursor cursor){

        this.cursor = cursor;
        matrixSerialization = new MatrixSerialization(cursor);
        setObjectPosition(cursor.getPosition());
    }


    @Override
    public boolean keyDown(int keycode) {
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        shiftHeld = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        keyString = Input.Keys.toString(keycode);

        if(fMoveApplier()) return true;

        switch (keycode){

            case Input.Keys.J:
                hasExectued = true;
                return charVerticalMove(cursor, true, iteration);

            case Input.Keys.K:
                hasExectued = true;
                return charVerticalMove(cursor, false, iteration);

            case Input.Keys.L:
                hasExectued = true;
                return charHorizontalMove(cursor, true, iteration);

            case Input.Keys.H:
                hasExectued = true;
                return charHorizontalMove(cursor, false, iteration);

            case Input.Keys.E:
                hasExectued = true;
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), false, iteration);

            case Input.Keys.W:
                hasExectued = true;
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), true, iteration);

            case Input.Keys.B:
                hasExectued = true;
                return traversePreviousWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), iteration);

            case Input.Keys.NUM_0:
                if(iteration > 0) return false;
                hasExectued = true;
                return traverseWholeLine(cursor, false);

            case Input.Keys.F1:
                matrixSerialization.saveAll();
                return true;

            case Input.Keys.F9:
                matrixSerialization.loadAll();
                return true;

            case Input.Keys.F:
                fMoveActive = true;
                return true;


        }

        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        if(character == '$'){
            hasExectued = true;
            return traverseWholeLine(cursor, true);
        }
        if(character == '^'){
            hasExectued = true;
            return goToFirstNonBlankChar(cursor);
        }
        return false;
    }

    /**
     * Applies the goToLetter method if fMoveActive is true.
     *
     * @return true if an attempt was made.
     */
    private boolean fMoveApplier(){
        if(fMoveActive && keyString.length() < 2) {

            if(! shiftHeld) keyString = keyString.toLowerCase();

            char key = keyString.charAt(0);
            goToLetter(cursor, key);
            fMoveActive = false;
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
