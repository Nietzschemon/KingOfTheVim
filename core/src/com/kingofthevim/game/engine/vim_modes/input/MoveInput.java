package com.kingofthevim.game.engine.vim_modes.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.engine.vim_modes.Movement;
import com.kingofthevim.game.engine.vim_modes.VimMove;
import com.kingofthevim.game.engine.vim_modes.VimMovement;
import com.kingofthevim.game.engine.vimobject.Cursor;

public class MoveInput extends Movement implements InputProcessor, VimMovement {

    Cursor cursor;
    int iteration = 0;
    boolean hasExectued = false;
    private boolean fMoveActive = false;
    boolean shiftHeld = false;
    String keyString;

    boolean addToHistory = false;
    private VimMove vimMove;

    public MoveInput(Cursor cursor){

        this.cursor = cursor;
        setObjectPosition(cursor.getPosition());
        vimMove = new VimMove();
    }


    @Override
    public boolean keyDown(int keycode) {
        addToHistory = false;

        shiftHeld = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        keyString = Input.Keys.toString(keycode);


        if(fMoveApplier()) return true;

        switch (keycode){

            case Input.Keys.J:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return charVerticalMove(cursor, true, iteration);

            case Input.Keys.K:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return charVerticalMove(cursor, false, iteration);

            case Input.Keys.L:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return charHorizontalMove(cursor, true, iteration);

            case Input.Keys.H:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return charHorizontalMove(cursor, false, iteration);

            case Input.Keys.E:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), false, iteration);

            case Input.Keys.W:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return traverseWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), true, iteration);

            case Input.Keys.B:
                hasExectued = true;
                vimMove.add(keycode, iteration, shiftHeld);
                return traversePreviousWord(cursor, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT), iteration);

            case Input.Keys.NUM_0:
                if(iteration > 0) return false;
                hasExectued = true;
                vimMove.add('0', iteration);
                return traverseWholeLine(cursor, false);


            case Input.Keys.F:
                vimMove.add(iteration, 'f');
                fMoveActive = true;
                shiftWasHeld = shiftHeld;
                return true;

        }


        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        addToHistory = true;


        return false;
    }


    @Override
    public boolean keyTyped(char character) {

        if(character == '$'){
            hasExectued = true;
            vimMove.add(character, iteration);
            traverseWholeLine(cursor, true);
        }
        if(character == '^'){
            hasExectued = true;
            vimMove.add(character, iteration);
            goToFirstNonBlankChar(cursor);
        }


        return hasExectued;
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
            goToPattern(cursor, keyString.substring(0), shiftWasHeld);
            fMoveActive = false;
            hasExectued = true;

            vimMove.move = key;
            shiftWasHeld = false;
            return true;
        }
        return false;
    }

    private boolean shiftWasHeld = false;

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
