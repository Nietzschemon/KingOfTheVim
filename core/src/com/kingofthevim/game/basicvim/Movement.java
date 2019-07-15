package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement {

    Pattern wordCap = Pattern.compile("([\\w$-/:-?{-~!\"^'\\[\\]#]*)");
    Pattern wordLetNum = Pattern.compile("(\\w*)");
    Pattern wordSym = Pattern.compile("([$-/:-?{-~!\"^'\\[\\]#]*)");
    /**
     * Checks if horizontal move is possible from the
     * current place by checking the current position
     * in the matrix of the cursor against themove
     * where it would be if the move parameter is added
     * @param move number of steps - positive or negative
     * @return True if possible, false if not
     */
    private boolean isLegitHorizontalMove(Cursor cursor, int move){
        int colunmTotal = cursor.getColunmTotal();
        int currColumn = cursor.getCurrColumn();

        if(currColumn + move < 0
                || currColumn + move > colunmTotal){
            System.out.println("MOVE OUT OF BOUNDS!");
            return false;
        }

        return true;
    }


    /**
     * Checks if vertical move is possible from the
     * current place by checking the current position
     * in the matrix of the cursor against the move
     * where it would be if the move parameter is added
     * @param move number of steps - positive or negative
     * @return True if possible, false if not
     */
    private boolean isLegitVerticalMove(Cursor cursor, int move){
        int rowTotal = cursor.getRowTotal();
        int currRow = cursor.getCurrRow();

        if(currRow + move < 0
                || currRow + move > rowTotal-1){
            System.out.println("MOVE OUT OF BOUNDS!");
            return false;
        }

        return true;
    }


    /**
     * Takes care of the vim w/W- and e/E-movements.
     *
     * This is done with regex-patterns that follow the
     * word/WORD-movement-rules. If not match, zero is returned
     * @param cursor the cursor to be moved in the matrix
     * @param shiftHeld if true WORD-rules are applied
     * @param wordBgn if true w/W-rules applies, else e/E-rules
     * @return the number of steps to perform asked movement
     */
    private int traverseWord(Cursor cursor, boolean shiftHeld, boolean wordBgn){

        VimWorldMatrix matrix = cursor.getVimMatrix();

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();
        int colunmTotal = cursor.getColunmTotal();

        int symbolMatch = 0;
        int wordMatch = 0;
        int finalMatch = -1;

        String row = matrix.getIndexToRowEndString(currRow, currColumn);

        Matcher wordMatcher = wordLetNum.matcher(row);
        Matcher symbolMatcher = wordSym.matcher(row);
        Matcher capitalMatcher = wordCap.matcher(row);

        if(shiftHeld) {

            while (capitalMatcher.find()){

                if(capitalMatcher.group().isEmpty()){
                    continue;
                }

                if(wordBgn){
                    if(capitalMatcher.start() > 0){
                        finalMatch = capitalMatcher.start();
                        break;
                    }
                }
                else {

                    if(capitalMatcher.end() > 1){
                        finalMatch = capitalMatcher.end();
                        break;
                    }
                }
            }
        }

        else {


            while (symbolMatcher.find()) {

                if (symbolMatcher.group().isEmpty()) {
                    continue;
                }

                if(wordBgn){
                    if(symbolMatcher.start() > 0){
                        symbolMatch = symbolMatcher.start();
                        break;
                    }
                }

                else {

                    if(symbolMatcher.end() > 1){
                        symbolMatch = symbolMatcher.end();
                        break;
                    }
                }
            }


            while (wordMatcher.find()) {

                if (wordMatcher.group().isEmpty()) {
                    continue;
                }

                if(wordBgn){
                    if(wordMatcher.start() > 0){
                        wordMatch = wordMatcher.start();
                        break;
                    }
                }
                else {
                    if(wordMatcher.end() > 1){
                        wordMatch = wordMatcher.end();
                        break;
                    }
                }

            }

            finalMatch = (wordMatch == 0) ? symbolMatch : wordMatch;

            // sets the matchBgn to an initial value to stop if-deadlock

            if (wordMatch <= symbolMatch
                    && wordMatch != 0) finalMatch = wordMatch;
            if (wordMatch >= symbolMatch
                    && symbolMatch != 0) finalMatch = symbolMatch;

        }

        if(finalMatch > 0
                && (finalMatch + currColumn) <= colunmTotal ){

            return (wordBgn) ? finalMatch : finalMatch - 1;
        }

        return 0;
    }


    // Experimental method to get all word-movement over to regex
    private int traversePREVWordREGEX(Cursor cursor, boolean shiftHeld){

        VimWorldMatrix matrix = cursor.getVimMatrix();

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();
        int colunmTotal = cursor.getColunmTotal();

        int symbolMatch = 0;
        int wordMatch = 0;
        int match = -1;

        String row = matrix.getStringIndexToRowBeginning(currRow, currColumn, true);

        Matcher wordMatcher = wordLetNum.matcher(row);
        Matcher symbolMatcher = wordSym.matcher(row);
        Matcher capitalMatcher = wordCap.matcher(row);

        ArrayList<String > matchList = new ArrayList<>();

        if(shiftHeld) {

            while (capitalMatcher.find()){

                if(capitalMatcher.group().isEmpty()){
                    continue;
                }


                if(capitalMatcher.start() > 0) {
                    match = capitalMatcher.start();
                    break;
                }
            }
        }

        else {


            while (symbolMatcher.find()) {

                if (symbolMatcher.group().isEmpty()) {
                    continue;
                }

                matchList.add(symbolMatcher.group());

                if(symbolMatcher.end() > 0){

                    symbolMatch = symbolMatcher.end();
                    break;
                }


            }
            System.out.println("symbolArray: " + matchList);
            matchList.clear();

            while (wordMatcher.find()) {

                if (wordMatcher.group().isEmpty()) {
                    continue;
                }

                matchList.add(wordMatcher.group());

                if(wordMatcher.end() > 0){
                    wordMatch = wordMatcher.end();
                    break;
                }

            }

            System.out.println("symbolArray: " + matchList);

            // sets the matchBgn to an initial value to stop if-deadlock
            match = (wordMatch == 0) ? symbolMatch : wordMatch;


            if (wordMatch <= symbolMatch
                    && wordMatch != 0) match = wordMatch;
            if (wordMatch >= symbolMatch
                    && symbolMatch != 0) match = symbolMatch;

            System.out.println("\nMatch: " + match +
                                "\nsymbol: " + symbolMatch +
                                "\nword: " + wordMatch);

        }

        if(match > 0
                && (match + currColumn) <= colunmTotal ){

            return match -1;
        }

        return 0;
    }


    /**
     * Takes care of the vim b/B-movements.
     *
     * This is done by iterating of cellMatrix and checking
     * the cell-chars in pairs against the VIM rules, if a
     * match is found, the loops breaks and returns the count
     * it took to find said pair.
     * @return the number of steps to perform asked movement
     */
    private int traversePreviousWord(Cursor cursor){

        ArrayList<ArrayList<Cell>> cellMatrix = cursor.getCellMatrix();

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();

        int count = 0;

        char currChar = cellMatrix.get(currRow).get(currColumn).getCellChar();

        char prevChar = currChar;

        char cellChar = currChar;

        for (int i = currColumn; i >= 0; i--) {

            cellChar = cellMatrix.get(currRow).get(i).getCellChar();

            if(count > 0)prevChar = cellMatrix.get(currRow).get(i + 1).getCellChar();

            count++;

            //System.out.println("prevChar: " + prevChar + " - cellChar: " + cellChar + " - Count " + count);

            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){

                if((cellChar == ' '
                        && prevChar != ' ')
                        && count > 2){

                    return count - 2;
                }
            }
            else {

                if(wordMovementRules(cellChar, prevChar)
                        &&  count > 2){

                    return count - 2;
                }
            }
        }

        //System.out.println("Count: " + (count) + "\n");

        return count - 1;
    }

    /** TODO discontinue and use regex instead
     * support method to traversePreviouWord()
     *
     * the method aspires to contain all the word-movement
     * rules to be used in the word-movement-methods. it
     * checks between the current and privius char according
     * to rules divided by if-statements. If any rule is true,
     * it returns true.
     * @param currCellChar the char of the current cell
     * @param prevCellChar the char of the previus cell
     * @return true if a ruled if followed.
     */
    private boolean wordMovementRules(char currCellChar, char prevCellChar){
        if(isLetterChar(prevCellChar)
        && isLetterChar(currCellChar)){
            return false;
        }
        if(isSymbol(prevCellChar)
                && isSymbol(currCellChar)){
            return false;
        }

        if( isLetterChar(prevCellChar)
                && currCellChar == ' '){
            return true;
        }

        if( isSymbol(prevCellChar)
                && currCellChar == ' '){
            return true;
        }

        if( isSymbol(prevCellChar)
                && isLetterChar(currCellChar)){
            return true;
        }

        if( isLetterChar(prevCellChar)
                && isSymbol(currCellChar)){
            return true;
        }


        return false;
    }

    //dsdsdd####sadad

    /**
     * Handles one char vertical move events
     * by either returning a positive or negative
     * 1 or zero
     * @param cursor The cursor to be moved
     * @return positive or negative one if legit
     * and zero if not.
     */
    private int charVerticalMove(Cursor cursor){

        if(Gdx.input.isKeyJustPressed(Input.Keys.J)
                && isLegitVerticalMove(cursor, 1))
        {
            return 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)
                && isLegitVerticalMove(cursor, -1))
        {
            return -1;
        }
        return 0;
    }

    /**
     * Handles one char horizontal move events
     * by either returning a positive or negative
     * 1 or zero
     * @param cursor The cursor to be moved
     * @return positive or negative one if legit
     * and zero if not.
     */
    private int charHorizontalMove(Cursor cursor){

        if (Gdx.input.isKeyJustPressed(Input.Keys.H)
                && isLegitHorizontalMove(cursor, -1))
        {
            return -1;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)
                && isLegitHorizontalMove(cursor, 1))
        {
            return 1;

        }

        return 0;
    }


    /**
     * The main method for all vertical moves. It passes
     * Cursor to the appropriate methods and if any key is
     * pressed will return the number of steps in the
     * matrix that was returned by that method. If no
     * valid moves are made it returns zero via
     * charVerticalMove()
     * @param cursor the cursor to be moved
     * @return An integer representing the number of
     * steps that is to be taken between rows in the matrix
     */
    int verticalMove(Cursor cursor){

        return charVerticalMove(cursor);
    }

    /**
     * The main method for all horizontal moves. It passes
     * Cursor to the appropriate methods and if any key is
     * pressed will return the number of steps in the
     * matrix that was returned by that method. If no
     * valid moves are made it returns zero via
     * charVerticalMove()
     * @param cursor the cursor to be moved
     * @return An integer representing the number of
     * steps that is to be taken between rows in the matrix
     */
    int horizontalMove(Cursor cursor){

        int colunmTotal = cursor.getColunmTotal();
        int currColumn = cursor.getCurrColumn();

        int move = 0;

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){

            move = traversePreviousWord(cursor);


            /* //TODO make work!
            move = traversePREVWordREGEX(cursor,
                    false);
             */
            if(isLegitHorizontalMove( cursor, - move )){
                return - move;
            }

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){

            move = traverseWord(cursor,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
                    true);

            if(isLegitHorizontalMove(cursor, move)){

                return move;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)
                && currColumn != colunmTotal){


            move = traverseWord(cursor,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
                    false);


            if(isLegitHorizontalMove(cursor, move)){

                return move;
            }
        }

        return charHorizontalMove(cursor);
    }


    /**
     * Checks if char is a symbol and thus next to each
     * other constitutes a word. Is used together with
     * isSymbol() to separate between words and WORDS.
     * Space is not included
     * @param character char to check
     * @return true if char is a symbol
     */
    private boolean isSymbol(char character ){
        return ((character >= '!' && character <= '/')
                || (character >= ':' && character <= '@')
                || (character >= '[' && character <= '_')
                || (character >= '{' && character <= '~'));
    }


    /**
     * Checks if char is a letter or number and thus
     * next to each other constitutes a word. Is used
     * together with isSymbol() to separate between
     * words and WORDS. Space is not included
     * @param character char to check
     * @return true if char is a symbol
     */
    private boolean isLetterChar(char character ){
        return ((character >= '0' && character <= '9')
                || (character >= 'a' && character <= 'z')
                || (character >= 'A' && character <= 'Z'));
    }

}
