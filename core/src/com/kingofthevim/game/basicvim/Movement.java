package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement extends InputHandler {

    private Pattern wordCap = Pattern.compile("([\\w$-/:-?{-~!\"^'\\[\\]#]+)");
    private Pattern wordLetNum = Pattern.compile("(\\w+)");
    private Pattern wordSym = Pattern.compile("([$-/:-?{-~!\"^'\\[\\]#]+)");

    private Operations operation = new Operations();

    private Position objectPosition;

    public Position getObjectPosition() {
        return objectPosition;
    }

    public void setObjectPosition(Position objectPosition) {
        this.objectPosition = objectPosition;
    }


    /**
     * Checks if vertical move is possible from the
     * current place by checking the current position
     * in the matrix of the object against the move
     * where it would be if the move parameter is added
     * @param move number of steps - positive or negative
     * @return True if possible, false if not
     */
    private boolean isLegitVerticalMove(VimObject object, int move){
        int rowTotal = object.getPosition().getRowTotal();
        int currRow = object.getPosition().getCurrRow();

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
     * in the matrix of the object against themove
     * where it would be if the move parameter is added
     * @param move number of steps - positive or negative
     * @return True if possible, false if not
     */
    private boolean isLegitHorizontalMove(VimObject object, int move){
        int colunmTotal = object.getPosition().getColunmTotal();
        int currColumn = object.getPosition().getCurrColumn();

        if(currColumn + move < 0
                || currColumn + move > colunmTotal - 1){
            System.out.println("MOVE OUT OF BOUNDS!");
            return false;
        }

        return true;
    }


    /**
     * Handles per char vertical move events
     * returning a positive or negative number
     * multiplied with iteration if entered.
     * @param object object to move
     * @param down handles up/down-moves
     * @return a positive or negative integer
     */
    private int charVerticalMove(VimObject object, boolean down){

       int move = (getIterationInt() < 1) ? 1 : getResetIterationInt();
       int endRow =  object.getPosition().getRowTotal() - object.getPosition().getCurrRow() - 1;

        if(down)
        {
            return (isLegitVerticalMove(object, move)) ? move : endRow;
        }

        return (isLegitVerticalMove(object, - move)) ? ( - move ) : ( - (object.getPosition().getCurrRow()));
    }


    /**
     * Handles per char vertical move events
     * returning a positive or negative number
     * multiplied with iteration if entered.
     * @param object object to move
     * @param forward handles backward/forward-moves
     * @return a positive or negative integer
     */
    private int charHorizontalMove(VimObject object, boolean forward){
        int move = (getIterationInt() < 1) ? 1 : getResetIterationInt();
        int endColumn = object.getPosition().getColunmTotal() - object.getPosition().getCurrColumn() - 1;

        if (forward)
        {
            return (isLegitHorizontalMove(object, move)) ? move : endColumn;
        }

        return (isLegitHorizontalMove(object, - move)) ? ( - move ) : ( - (object.getPosition().getCurrColumn()));

    }


    /**
     * Takes care of the vim w/W- and e/E-movements.
     *
     * This is done with regex-patterns that follow the
     * word/WORD-movement-rules. If not match, zero is returned
     * @param object the object to be moved in the matrix
     * @param shiftHeld if true WORD-rules are applied
     * @param wordBgn if true w/W-rules applies, else e/E-rules
     * @return the number of steps to perform asked movement
     */
    private int traverseWord(VimObject object, boolean shiftHeld, boolean wordBgn){

        VimWorldMatrix matrix = object.getVimMatrix();

        ArrayList<Integer> allMatches;

        int currColumn = object.getPosition().getCurrColumn();
        int currRow = object.getPosition().getCurrRow();
        int colunmTotal = object.getPosition().getColunmTotal();
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
     * @param object the object to be moved in the matrix
     * @param shiftHeld if true WORD-rules are applied
     * @return the number of steps to perform asked movement
     */
    private int traversePreviousWord(VimObject object, boolean shiftHeld){

        VimWorldMatrix matrix = object.getVimMatrix();
        ArrayList<Integer> allMatches;

        int currColumn = object.getPosition().getCurrColumn();
        int currRow = object.getPosition().getCurrRow();
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

        int iterations = getResetIterationInt();
        iterations = (iterations > 0) ? iterations - 1 : 0;

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
     * @param object The object that is to be moved
     * @param toEnd if true, end of line; false, beginning of line
     * @return the integer to add or subtract to go to start or end
     */
    private int traverseWholeLine(VimObject object, boolean toEnd){
        int currColumn = object.getPosition().getCurrColumn();
        int colunmTotal = object.getPosition().getColunmTotal();

        if(toEnd){
            return colunmTotal - currColumn - 1;
        }
        else {
            return -currColumn;
        }
    }


    /** TODO do not modify object directly
     * TEMPORARY method for jumping to first non-blank char
     * in line. NOTE modifies the object passed directly!
     * @param object MODIFIES object position directly
     * @return true if success
     */
    public boolean goToFirstNonBlankChar(VimObject object){

        int currRow = object.getPosition().getCurrRow();
        int currColumn = object.getPosition().getCurrColumn();
        int firstNonBlank = -1;

        String row = object.getVimMatrix().getStringIndexToRowBeginning(currRow, currColumn, false);
        //System.out.println("ROW STRING: " + row);
        Matcher firstNonBlankMatcher = wordCap.matcher(row);

        if(firstNonBlankMatcher.find()){
            /*
            System.out.println("ROW MATCHER: " + firstNonBlankMatcher.group() + firstNonBlankMatcher.groupCount());
            System.out.println("ROW MATCHER Start: " + firstNonBlankMatcher.start());
            System.out.println("ROW MATCHER end: " + firstNonBlankMatcher.end());
            System.out.println("currCol: " + currColumn);

             */

            objectPosition.setAbsoluteColumn(firstNonBlankMatcher.start());
            return true;
        }

        return false;
    }

    /**
     * The main method for all vertical moves. It passes
     * object to the appropriate methods and if any key is
     * pressed will return the number of steps in the
     * matrix that was returned by that method. If no
     * valid moves are made it returns zero via
     * charVerticalMove()
     * @param object the object to be moved
     * @return An integer representing the number of
     * steps that is to be taken between rows in the matrix
     */
    void verticalMove(VimObject object){

        int move = 0;
        objectPosition = object.getPosition();

        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
           move = charVerticalMove(object, false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            move = charVerticalMove(object, true);
        }

        if(isLegitVerticalMove(object,move)
        && move != 0){
            activeOperator = false;
            objectPosition.setRelativeRow( move);
        }

    }


    //TODO make backwards delete work
    /**
     * The main method for all horizontal moves. It passes
     * object to the appropriate methods and if any key is
     * pressed will return the number of steps in the
     * matrix that was returned by that method. If no
     * valid moves are made it returns zero via
     * charVerticalMove()
     * @param object the object to be moved
     * @return An integer representing the number of
     * steps that is to be taken between rows in the matrix
     */
    void horizontalMove(VimObject object){

        int colunmTotal = object.getPosition().getColunmTotal();
        int currColumn = object.getPosition().getCurrColumn();

        int move = 0;

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            activeOperator = true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            operation.deleteChar(object);

            activeOperator = false;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            move = charHorizontalMove(object, false);

            activeOperator = false;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            move = charHorizontalMove(object, true);

            if(activeOperator){

                operation.deleteChar(object);
                activeOperator = false;
                move = 0;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){

            move = traversePreviousWord(object,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT));

            activeOperator = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){

            move = traverseWord(object,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
                    true);

            if(activeOperator){

                operation.deleteCharBatch(object, move);
                activeOperator = false;
                move = 0;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)
                && currColumn != colunmTotal){

            move = traverseWord(object,
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
                    false);

            if(activeOperator){

                operation.deleteCharBatch(object, move + 1);
                activeOperator = false;
                move = 0;
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)
            && (getIterationInt() <= 0)){

            move = traverseWholeLine(object, false);

                activeOperator = false;
        }

        if (keyPressedIsChar('$')){

            move = traverseWholeLine(object, true);

            if(activeOperator){

                operation.deleteCharBatch(object, move + 1);
                activeOperator = false;
                move = 0;
            }
        }

        if (keyPressedIsChar('^')){

            goToFirstNonBlankChar(object);
            activeOperator = false;
        }



        if(move != 0
        && isLegitHorizontalMove(object, move)) {
            objectPosition.setRelativeColumn(move);
        }

    }

    void move(VimObject object){
        objectPosition = object.getPosition();

        verticalMove(object);
        horizontalMove(object);

    }


}
