package com.kingofthevim.game.basicvim.Actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kingofthevim.game.basicvim.Matrix.Tools;

import java.util.LinkedList;

public class Action {

    private char currChar = 0;

    private char currOperator = ' ';

    private boolean activeOperator = false;

    private LinkedList<Character> inputHistory;

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

    public Action(){
        inputHistory = new LinkedList<>();
    }


    public boolean keyPressedIsChar(char dasChar){

        if(currChar == dasChar){
            currChar = '0';
            return true;
        }
        return false;
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
     */
    private void integerMaker(char intProspect){

        if(intProspect > 47 &&
            intProspect < 58){
            iterationString += String.valueOf(intProspect);
            iterationInt = tryParseInt(iterationString);
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


    public boolean keyTyped(char character) {
        currChar = character;
        integerMaker(character);
        addToInputHistory(character);
        return false;
    }

    public char getCurrChar() {
        return currChar;
    }

    public void setCurrChar(char currChar) {
        this.currChar = currChar;
    }

    public boolean isActiveOperator() {
        return activeOperator;
    }

    public void setActiveOperator(boolean activeOperator) {
        this.activeOperator = activeOperator;
    }

    public LinkedList<Character> getInputHistory() {
        return inputHistory;
    }

    public void setInputHistory(LinkedList<Character> inputHistory) {
        this.inputHistory = inputHistory;
    }

    public void setIterationInt(int iterationInt) {
        this.iterationInt = iterationInt;
    }

    public String getIterationString() {
        return iterationString;
    }

    public void setIterationString(String iterationString) {
        this.iterationString = iterationString;
    }
}
