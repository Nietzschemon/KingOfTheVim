package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement {

    private Pattern wordCap = Pattern.compile("([\\w$-/:-?{-~!\"^'\\[\\]#]+)");
    private Pattern wordLetNum = Pattern.compile("(\\w+)");
    private Pattern wordSym = Pattern.compile("([$-/:-?{-~!\"^'\\[\\]#]+)");



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
                || currColumn + move > colunmTotal - 1){
            System.out.println("MOVE OUT OF BOUNDS!");
            return false;
        }

        return true;
    }


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


    /**
     * Takes care of the vim b/B-movements.
     *
     * This is done with regex-patterns that follow the
     * word/WORD-movement-rules. If not match, zero is returned
     * @param cursor the cursor to be moved in the matrix
     * @param shiftHeld if true WORD-rules are applied
     * @return the number of steps to perform asked movement
     */
    private int traversePreviousWord(Cursor cursor, boolean shiftHeld){

        VimWorldMatrix matrix = cursor.getVimMatrix();

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();

        int symbolMatch = 0;
        int wordMatch = 0;
        int match = -1;

        String row = matrix.getStringIndexToRowBeginning(currRow, currColumn+1, false);

        Matcher wordMatcher = wordLetNum.matcher(row);
        Matcher symbolMatcher = wordSym.matcher(row);
        Matcher capitalMatcher = wordCap.matcher(row);


        if(shiftHeld) {

            while (capitalMatcher.find()){

                if(capitalMatcher.group().isEmpty()){
                    continue;
                }

                if(capitalMatcher.start() != currColumn) {
                    match = capitalMatcher.start();
                }
            }
        }

        else {

            while (symbolMatcher.find()) {

                if (symbolMatcher.group().isEmpty()) {
                    continue;
                }

                if(symbolMatcher.start() != currColumn){
                    symbolMatch = symbolMatcher.start();
                }

            }

            while (wordMatcher.find()) {

                if (wordMatcher.group().isEmpty()) {
                    continue;
                }

                if(wordMatcher.start() != currColumn){
                    wordMatch = wordMatcher.start();
                }

            }

            // sets the matchBgn to an initial value to stop if-deadlock
            match = (wordMatch == 0) ? symbolMatch : wordMatch;


            if (wordMatch >= symbolMatch
                    && wordMatch != 0) match = wordMatch;
            if (wordMatch <= symbolMatch
                    && symbolMatch != 0) match = symbolMatch;
        }

        if(match >= 0
                && (currColumn - match) >= 0){

            return - (currColumn - match);
        }

        return 0;
    }


    /**
     * Goes to end or beginning of line
     * @param cursor The cursor that is to be moved
     * @param toEnd if true, end of line; false, beginning of line
     * @return the integer to add or subtract to go to start or end
     */
    private int traverseWholeLine(Cursor cursor, boolean toEnd){
        int currColumn = cursor.getCurrColumn();
        int colunmTotal = cursor.getColunmTotal();

        if(toEnd){
            return colunmTotal - currColumn - 1;
        }
        else {
            return -currColumn;
        }
    }


    /** TODO do not modify cursor directly
     * TEMPORARY method for jumping to first non-blank char
     * in line. NOTE modifies the cursor passed directly!
     * @param cursor MODIFIES cursor position directly
     * @return true if success
     */
    public boolean goToFirstNonBlankChar(Cursor cursor){

        int currRow = cursor.getCurrRow();
        int currColumn = cursor.getCurrColumn();
        int firstNonBlank = -1;

        String row = cursor.getVimMatrix().getStringIndexToRowBeginning(currRow, currColumn, false);
        //System.out.println("ROW STRING: " + row);
        Matcher firstNonBlankMatcher = wordCap.matcher(row);

        if(firstNonBlankMatcher.find()){
            /*
            System.out.println("ROW MATCHER: " + firstNonBlankMatcher.group() + firstNonBlankMatcher.groupCount());
            System.out.println("ROW MATCHER Start: " + firstNonBlankMatcher.start());
            System.out.println("ROW MATCHER end: " + firstNonBlankMatcher.end());
            System.out.println("currCol: " + currColumn);

             */

            cursor.setAbsoluteColumn(firstNonBlankMatcher.start());
            return true;
        }

        return false;
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

            move = traversePreviousWord(cursor,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){

            move = traverseWord(cursor,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
                    true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)
                && currColumn != colunmTotal){

            move = traverseWord(cursor,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
                    false);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)){

            move = traverseWholeLine(cursor, true);
        }


        //TODO create inputprocessor and get char from keyboard
        //dollar-sign
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
                && Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)){

            move = traverseWholeLine(cursor, false);
        }

        //TODO remove pro-tem key
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)){

            goToFirstNonBlankChar(cursor);
        }

        if(move != 0
        && isLegitHorizontalMove(cursor, move)){

            return move;
        }

        return charHorizontalMove(cursor);
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// SUPPORTER METHODS //////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////



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

    /**
     * VIM-movement-rules based on chars
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

}
