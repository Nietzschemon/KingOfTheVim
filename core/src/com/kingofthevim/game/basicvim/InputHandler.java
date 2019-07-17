package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

    VimObject vimObj;

    private char currChar = 0;

    private int iterationInt = 0;

    public int getIterationInt() {
        return iterationInt;
    }
    public void setIterationInt(int iterations) {
        iterationInt = iterations;
    }

    InputHandler(){
        Gdx.input.setInputProcessor(this);
    }

    InputHandler(VimObject object){
        new InputHandler();

        vimObj = object;


    }


    public boolean keyPressedIsChar(char dasChar){

        if(currChar == dasChar){
            currChar = '0';
            return true;
        }
        return false;
    }


    /**
     * checks if a char can be converted to an int
     * and if so adds it to iterationInt, if not iterationInt
     * is reset
     * @param intProspect char to check for int
     *                    conversion possibility
     */
    private void integerMaker(char intProspect){

        if(intProspect > 48 &&
            intProspect < 58){
            iterationInt += Character.getNumericValue(intProspect);
            System.out.println("iterationInt: " + iterationInt);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    //TODO make reset iterationInt
    @Override
    public boolean keyUp(int keycode) {

        switch (keycode){

            case Input.Keys.B:
                System.out.println("Is B");
                break;

            case Input.Keys.E:
                System.out.println("Is E");
                break;

            case Input.Keys.W:
                System.out.println("Is W");
                break;

            case Input.Keys.H:
                System.out.println("Is H");
                break;

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        currChar = character;
        integerMaker(character);
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
