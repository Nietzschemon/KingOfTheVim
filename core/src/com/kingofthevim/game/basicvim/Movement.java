package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement extends InputHandler {

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

        ArrayList<Integer> allMatches;

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();
        int colunmTotal = cursor.getColunmTotal();
        int step;

        String row = matrix.getIndexToRowEndString(currRow, currColumn+1);

        Matcher wordMatcher = wordLetNum.matcher(row);
        Matcher symbolMatcher = wordSym.matcher(row);
        Matcher capitalMatcher = wordCap.matcher(row);

        if(shiftHeld) {

            if(wordBgn){
                allMatches = matcherApplier(capitalMatcher, false);
            }
            else {
                allMatches = matcherApplier(capitalMatcher, true);
            }
        }

        else {

            if(wordBgn){
                allMatches = matcherApplier(wordMatcher, false);
                allMatches.addAll(matcherApplier(symbolMatcher, false));
            }
            else {
                allMatches = matcherApplier(wordMatcher, true);
                allMatches.addAll(matcherApplier(symbolMatcher, true));
            }
        }


        step = iterationApplier(allMatches, false);


        if(step >= 0
                && (currColumn + step) <= colunmTotal){

            return (wordBgn) ? step + 1: step;
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
        ArrayList<Integer> allMatches;

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();
        int step;

        String row = matrix.getStringIndexToRowBeginning(currRow, currColumn, false);

        Matcher wordMatcher = wordLetNum.matcher(row);
        Matcher symbolMatcher = wordSym.matcher(row);
        Matcher capitalMatcher = wordCap.matcher(row);


        if(shiftHeld) {

            allMatches = matcherApplier(capitalMatcher, false);
        }

        else {

            allMatches = matcherApplier(symbolMatcher, false);
            allMatches.addAll(matcherApplier(wordMatcher, false));
        }

        step = iterationApplier(allMatches, true);

        if(step >= 0
                && (currColumn - step) >= 0){

            return - (currColumn - step);
        }

        return 0;
    }

    /**
     * checks that the current iteration-input from
     * the user is within the given array, if not it
     * will return the last value of the array
     * Always reset the current iterationInt.
     * @param matchList list from which an int shall be
     *                  returned a
     * @param iterateBackward used for matchLists that need
     *                        to iterated backwards
     * @return the appropriate value in the array or zero
     */
    private int iterationApplier(ArrayList<Integer> matchList, boolean iterateBackward){

        int iterations = (getIterationInt() > 0) ? getIterationInt() - 1 : 0;

        // resets iterations
        setIterationInt(0);

        if(! iterateBackward) matchList.removeIf(p -> p == 0);

        if (matchList.size() > 0){



            iterations = (iterations < matchList.size()) ? iterations : matchList.size() - 1;

            Collections.sort(matchList);
            if(iterateBackward)Collections.reverse(matchList);


            return matchList.get(iterations);
        }

        return 0;
    }

    /**
     * Applies a given initialized matcher and puts
     * all integers of either .start or .end matches
     * into an ArrayList that is returned
     * @param matcher an initialized matcher to iterate over
     * @param matchEnd if .end or .start of matcher should
     *                 be used
     * @return an ArrayList with integers made up of either
     * all .start- or .end-subroutines of the given matcher
     */
    private ArrayList<Integer> matcherApplier(Matcher matcher, boolean matchEnd){

        ArrayList<Integer> matchList = new ArrayList<>();

        while (matcher.find()) {

            if (matcher.group().isEmpty()) continue;

            if(matchEnd) matchList.add(matcher.end());
            else matchList.add(matcher.start());


        }

        return matchList;
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

            move = traverseWholeLine(cursor, false);
        }

        if (keyPressedIsChar('$')){

            move = traverseWholeLine(cursor, true);
        }

        if (keyPressedIsChar('^')){

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
