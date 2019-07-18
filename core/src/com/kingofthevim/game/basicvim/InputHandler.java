package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

    VimObject vimObj;

    private char currChar = 0;

    private int iterationInt = 0;
    private String iterationString = "0";

    int getIterationInt() {
        return iterationInt;
    }
    int getResetIterationInt() {
        int iter = iterationInt;
        iterationString = "0";
        iterationInt = 0;
        return iter;
    }
    void resetIteration(){iterationString = "0";}

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

        if(intProspect > 47 &&
            intProspect < 58){
            iterationString += String.valueOf(intProspect);

            iterationInt = tryParseInt(iterationString);
            System.out.println("itString: " + iterationString);
        }
    }

    /**
     * tries to parse integer, if
     * fail, it returns zero
     * @param string that which is to be parsed
     * @return parsed integer or zero
     */
    private int tryParseInt(String string){
        try{
            return Integer.parseInt(string);
        } catch (NumberFormatException e){
            return 0;
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
