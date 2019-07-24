package com.kingofthevim.game.basicvim.Actions.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.kingofthevim.game.basicvim.Matrix.Tools;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

import java.util.LinkedList;

import static com.kingofthevim.game.basicvim.Matrix.Tools.tryParseInt;

public class InputManager implements InputProcessor {


    Cursor cursor;
    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    MoveInput moveInput;
    OperationInput operationInput;

    private char currChar = 0;

    private char currOperator = ' ';

    private boolean activeOperator = false;

    private LinkedList<Character> inputHistory;

    int iterationInt = 0;
    String iterationString = "0";


    public InputManager(Cursor cursor){
        inputHistory = new LinkedList<>();
        moveInput = new MoveInput(cursor);
        operationInput = new OperationInput(cursor);

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(moveInput);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public boolean keyDown(int keycode) {


        if(operationInput.hasExectued){
            inputMultiplexer.removeProcessor(operationInput);
            inputMultiplexer.addProcessor(0, moveInput);
        }
        iterationSync();
        switch (keycode){

            case Input.Keys.D:
                inputMultiplexer.removeProcessor(moveInput);
                inputMultiplexer.addProcessor(0, operationInput);
                System.out.println("D pressed");
                operationInput.hasExectued = false;
                return true;

            case Input.Keys.NUM_1:
                return integerMaker('1');

            case Input.Keys.NUM_2:
                return integerMaker('2');

            case Input.Keys.NUM_3:
                return integerMaker('3');

            case Input.Keys.NUM_4:
                return integerMaker('4');

            case Input.Keys.NUM_5:
                return integerMaker('5');

            case Input.Keys.NUM_6:
                return integerMaker('6');

            case Input.Keys.NUM_7:
                return integerMaker('7');

            case Input.Keys.NUM_8:
                return integerMaker('8');

            case Input.Keys.NUM_9:
                return integerMaker('9');

            case Input.Keys.NUM_0:
                return integerMaker('0');

            case Input.Keys.ESCAPE:
                inputMultiplexer.removeProcessor(moveInput);
                System.out.println("ESC");
                return true;

        }

        return false;
    }

    private void iterationSync(){
        if(operationInput.hasExectued
        || moveInput.hasExectued){
            operationInput.iteration = 0;
            moveInput.iteration = 0;
            iterationInt = 0;
            iterationString = "0";
            operationInput.hasExectued = false;
            moveInput.hasExectued = false;
        }

        if(iterationInt > 0){
            operationInput.iteration = iterationInt;
            moveInput.iteration = iterationInt;
        }
    }


    private void addToInputHistory(char character){

        if(Tools.isLetterOrNumber(character)
                || Tools.isSymbol(character)){

            inputHistory.add(character);
        }
    }

    /**
     * checks if a char can be converted to an int
     * and if so adds it to iterationInt, if not iterationInt
     * is reset
     * @param intProspect char to check for int
     *                    conversion possibility
     * @return true if intProspect could be parsed
     */
    public boolean integerMaker(char intProspect){

        if(intProspect > 47 &&
                intProspect < 58){
            int integer;
            iterationString += String.valueOf(intProspect);
            integer = tryParseInt(iterationString);

            if(iterationInt + integer == 0){
                iterationString = "0";
                return false;
            }

            iterationInt = integer;

            System.out.println("it-int: " + iterationInt);
            return true;
        }
        return false;
    }

    public int getIterationInt() {
        System.out.println("it-int-get: " + iterationInt);
        return iterationInt;
    }
    protected int getResetIterationInt() {
        int iter = iterationInt;
        iterationString = "0";
        iterationInt = 0;
        return iter;
    }

    public char getCurrOperator() {
        return currOperator;
    }

    public void setCurrOperator(char currOperator) {
        this.currOperator = currOperator;
    }

    public char getResetOperator(){
        char oper = currOperator;
        currOperator = ' ';
        return oper;
    }

    void resetIteration(){iterationString = "0";}

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        addToInputHistory(character);
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
