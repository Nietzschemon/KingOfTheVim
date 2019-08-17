package com.kingofthevim.game.engine.Actions;

import com.kingofthevim.game.engine.matrix.VimWorldMatrix;
import com.kingofthevim.game.engine.VimObject.Position;
import com.kingofthevim.game.engine.VimObject.VimObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement {

    private Pattern wordCap = Pattern.compile("([\\w$-/:-?{-~!\"^'\\[\\]#]+)");
    private Pattern wordLetNum = Pattern.compile("(\\w+)");
    private Pattern wordSym = Pattern.compile("([$-/:-?{-~!\"^'\\[\\]#]+)");

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
    protected boolean isLegitVerticalMove(VimObject object, int move){
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
    protected boolean isLegitHorizontalMove(VimObject object, int move){
        int colunmTotal = object.getPosition().getColumnTotal();
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
    public boolean charVerticalMove(VimObject object, boolean down, int iteration){

        int move = (iteration < 1) ? 1 : iteration;
        int endRow =  object.getPosition().getRowTotal() - object.getPosition().getCurrRow() - 1;
        Position position = object.getPosition();


        if(down)
        {

            return position.setRelativeRow((isLegitVerticalMove(object, move)) ? move : endRow);
        }

        return position.setRelativeRow((isLegitVerticalMove(object, - move)) ? ( - move ) : ( - (object.getPosition().getCurrRow())));
    }


    /**
     * Handles per char vertical move events
     * returning a positive or negative number
     * multiplied with iteration if entered.
     * @param object object to move
     * @param forward handles backward/forward-moves
     * @return a positive or negative integer
     */
    public boolean charHorizontalMove(VimObject object, boolean forward, int iteration){
        int move = (iteration < 1) ? 1 : iteration;
        int endColumn = object.getPosition().getColumnTotal() - object.getPosition().getCurrColumn() - 1;
        Position position = object.getPosition();

        if (forward)
        {
            return position.setRelativeColumn((isLegitHorizontalMove(object, move)) ? move : endColumn);
        }

        return position.setRelativeColumn((isLegitHorizontalMove(object, - move)) ? ( - move ) : ( - (object.getPosition().getCurrColumn())));

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
    protected boolean traverseWord(VimObject object, boolean shiftHeld, boolean wordBgn, int iterations){

        VimWorldMatrix matrix = object.getVimMatrix();

        Position position = object.getPosition();
        ArrayList<Integer> allMatches;

        int currColumn = object.getPosition().getCurrColumn();
        int currRow = object.getPosition().getCurrRow();
        int colunmTotal = object.getPosition().getColumnTotal();
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


        step = iterationApplier(allMatches, false, iterations);


        if(step >= 0
                && (currColumn + step) <= colunmTotal){

            return position.setRelativeColumn( (wordBgn) ? step + 1: step);

        }

        return false;
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
    protected boolean traversePreviousWord(VimObject object, boolean shiftHeld, int iterations){

        VimWorldMatrix matrix = object.getVimMatrix();
        ArrayList<Integer> allMatches;

        Position position = object.getPosition();

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

        step = iterationApplier(allMatches, true, iterations);

        if(step >= 0
                && (currColumn - step) >= 0){

            return position.setRelativeColumn( - (currColumn - step));
        }

        return false;
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
    protected ArrayList<Integer> matcherApplier(Matcher matcher, boolean matchEnd){

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
    protected boolean traverseWholeLine(VimObject object, boolean toEnd){
        int currColumn = object.getPosition().getCurrColumn();
        int colunmTotal = object.getPosition().getColumnTotal();
        Position position = object.getPosition();

        if(toEnd){
            return position.setRelativeColumn(colunmTotal - currColumn - 1);
        }
        else {
            return position.setRelativeColumn(-currColumn);
        }
    }


    /**
     * Moves the VimObject to the first non-blank char in
     * the row.
     *
     * @param object VimObject to move
     * @return true if success
     */
    public boolean goToFirstNonBlankChar(VimObject object){

        int currRow = object.getPosition().getCurrRow();
        int currColumn = object.getPosition().getCurrColumn();

        String row = object.getVimMatrix().getStringIndexToRowBeginning(currRow, currColumn, false);
        Matcher firstNonBlankMatcher = wordCap.matcher(row);

        if(firstNonBlankMatcher.find()){
            return objectPosition.setAbsoluteColumn(firstNonBlankMatcher.start());
        }

        return false;
    }


    /**
     * Moves the VimObject to the index of a given pattern if it
     * exists in the matrix.
     *
     * @param vimObject to move
     * @param pattern to search and move to
     * @param shiftHeld if true, it searces forward, false backward,
     *                  from the VimObject
     * @return true if something was found and a move was performed
     */
    public boolean goToPattern(VimObject vimObject, String pattern, boolean shiftHeld){

        int row = vimObject.getPosition().getCurrRow();
        int column = vimObject.getPosition().getCurrColumn();
        String rowString;

        //TODO put in VimMatrix class
        if(shiftHeld){
            rowString = vimObject.getVimMatrix().getStringIndexToRowBeginning(row, column, false);
        }
        else {
            rowString = vimObject.getVimMatrix().getIndexToRowEndString(row, column);
        }
        Pattern pat = Pattern.compile(pattern);
        Matcher customMatch = pat.matcher(rowString);

        ArrayList<Integer> matches = matcherApplier(customMatch, true);

        if(matches.size() > 0){
            int newColumn = matches.get(0);

            if(matches.size() > 1
            && newColumn == 0) {
                newColumn = matches.get(1);
            }

            if(shiftHeld){
                vimObject.getPosition().setAbsoluteColumn(newColumn - 1);
                return true;
            }
            else {
                vimObject.getPosition().setRelativeColumn(newColumn - 1);
                return true;
            }
        }

        return false;
    }


    public int goToLetter_Int(VimObject vimObject, char letter){

        int row = vimObject.getPosition().getCurrRow();
        int column = vimObject.getPosition().getCurrColumn();

        char[] charArray = vimObject.getVimMatrix().getIndexToRowEndString(row, column).toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == letter) return i;
        }

        return 0;
    }

    /**
     * Handles per char vertical move events
     * returning a positive or negative number
     * multiplied with iteration if entered.
     * @param object object to move
     * @param down handles up/down-moves
     * @param iteration number of times to apply move
     * @return a positive or negative integer
     */
    public int charVertical_Int(VimObject object,boolean down, int iteration){

        int move = (iteration < 1) ? 1 : iteration;
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
     * @param iteration number of times to apply move
     * @return a positive or negative integer
     */
    public int charHorizontal_Int(VimObject object,boolean forward, int iteration){
        int move = (iteration < 1) ? 1 : iteration;
        int endColumn = object.getPosition().getColumnTotal() - object.getPosition().getCurrColumn() - 1;

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
     * @param iterations number of times to apply move
     * @return the number of steps to perform asked movement
     */
    protected int traverseWord_Int(VimObject object, boolean shiftHeld,  boolean wordBgn, int iterations){

        VimWorldMatrix matrix = object.getVimMatrix();

        ArrayList<Integer> allMatches;

        int currColumn = object.getPosition().getCurrColumn();
        int currRow = object.getPosition().getCurrRow();
        int colunmTotal = object.getPosition().getColumnTotal();
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


        step = iterationApplier(allMatches, false, iterations);


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
     * @param iterations number of times to apply move
     * @return the number of steps to perform asked movement
     */
    protected int traversePreviousWord_Int(VimObject object, boolean shiftHeld, int iterations){

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

        step = iterationApplier(allMatches, true, iterations);

        if(step >= 0
                && (currColumn - step) >= 0){

            return - (currColumn - step);
        }

        return 0;
    }

    /**
     * Goes to end or beginning of line
     * @param object The object that is to be moved
     * @param toEnd if true, end of line; false, beginning of line
     * @return the integer to add or subtract to go to start or end
     */
    protected int traverseWholeLine_Int(VimObject object, boolean toEnd){
        int currColumn = object.getPosition().getCurrColumn();
        int colunmTotal = object.getPosition().getColumnTotal();

        if(toEnd){
            return colunmTotal - currColumn - 1;
        }
        else {
            return -currColumn;
        }
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
    protected int iterationApplier(ArrayList<Integer> matchList, boolean iterateBackward, int iterations){

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

}
