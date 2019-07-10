package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Movement {



    private boolean isLegitHorizontalMove(Cursor cursor, int move){
        int colunmTotal = cursor.getColunmTotal();
        int currColumn = cursor.getCurrColumn();


        if(currColumn +move < 0
                || currColumn +move > colunmTotal){
            System.out.println("MOVE OUT OF BOUNDS!");
            return false;
        }

        return true;
    }

    /**
     * Checks if veritical move is possible from the
     * current place
     * @param move number of steps
     * @return True if possible, false if not
     */
    private boolean isLegitVerticalMove(Cursor cursor, int move){
        int rowTotal = cursor.getRowTotal();
        int currRow = cursor.getCurrRow();

        if(currRow+move < 0
                || currRow+move > rowTotal-1){
            System.out.println("MOVE OUT OF BOUNDS!");
            return false;
        }

        return true;
    }


    //TODO at end of word if place of matrix, the coursor jumps ahead anyways
    // fix this so it goes to new line instead
    /**
     * Takes care of the vim w/W-movements.
     *
     * This is done by iterating of cellMatrix and checking
     * the cell-chars in pairs against the VIM rules, if a
     * match is found, the loops breaks and returns the count
     * it took to find said pair.
     * @return the number of steps to perform asked movement
     */
    private int traverseWordBeginning(Cursor cursor){

        ArrayList<ArrayList<Cell>> cellMatrix = cursor.getCellMatrix();

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();
        int colunmTotal = cursor.getColunmTotal();


        int count = 0;

        char currChar = cellMatrix.get(currRow).get(currColumn).getCellChar();

        char prevChar = currChar;

        char cellChar = currChar;

        for (int i = currColumn; i <cellMatrix.get(currRow).size(); i++) {

            cellChar = cellMatrix.get(currRow).get(i).getCellChar();

            if(count > 0)prevChar = cellMatrix.get(currRow).get(i - 1).getCellChar();


            count++;

            //System.out.println("prevChar: " + prevChar + " - cellChar: " + cellChar + " - Count " + count);

            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){

                if((cellChar != ' '
                        && prevChar == ' ')){
                    break;
                }
            }
            else {

                if((cellChar != ' '
                        && prevChar == ' ')){
                    break;
                }


                if( isSymbol(prevChar)
                        && isLetterChar(cellChar)){
                    break;
                }

                if( isLetterChar(prevChar)
                        && isSymbol(cellChar)){
                    break;
                }

            }

        }
        //System.out.println("Count: " + (count));

        if(currColumn + count >= colunmTotal){


            // if at space at end of row or
            // space before word at end of row
            if(currColumn + count >= colunmTotal
                    && currChar == ' '
                    && currColumn != colunmTotal - 1){

                return 1;
            }

            return 0;
        }

        return count - 1;
    }

    //TODO at end of word if place of matrix, the coursor jumps ahead anyways
    // fix this so it goes to new line instead
    /**
     * Takes care of the vim e/E-movements.
     *
     * This is done by iterating of cellMatrix and checking
     * the cell-chars in pairs against the VIM rules, if a
     * match is found, the loops breaks and returns the count
     * it took to find said pair.
     * @return the number of steps to perform asked movement
     * @param cursor
     */
    private int traverseWordEnd(Cursor cursor){


        ArrayList<ArrayList<Cell>> cellMatrix = cursor.getCellMatrix();

        int currColumn = cursor.getCurrColumn();
        int currRow = cursor.getCurrRow();
        int colunmTotal = cursor.getColunmTotal();


        int count = 0;

        char currChar = cellMatrix.get(currRow).get(currColumn).getCellChar();

        char prevChar = currChar;

        for (int i = currColumn; i <cellMatrix.get(currRow).size(); i++) {

            char cellChar = cellMatrix.get(currRow).get(i).getCellChar();

            if(count > 0)prevChar = cellMatrix.get(currRow).get(i - 1).getCellChar();


            count++;

            //System.out.println("CHAR: " + cellChar);

            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){

                if((cellChar == ' '
                        && prevChar != ' ')
                        && count > 2){
                    break;
                }
            }
            else {

                if( isLetterChar(prevChar)
                        && cellChar == ' '
                        && count > 2){

                    break;
                }

                if( isLetterChar(prevChar)
                        && isSymbol(cellChar)
                        && count > 2){

                    break;
                }

                if( isLetterChar(currChar)
                        && isSymbol(cellChar)
                ){

                    return count - 1;
                }

            }



        }
        //System.out.println("Count: " + (count));

        if(currColumn + count >= colunmTotal){
            return count - 1;
        }

        return count - 2;
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

    private boolean wordMovementRules(char currCellChar, char prevCellChar){
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


    int verticalMove(Cursor cursor){

        return charVerticalMove(cursor);
    }

    int horizontalMove(Cursor cursor){

        int colunmTotal = cursor.getColunmTotal();
        int currColumn = cursor.getCurrColumn();

        int move = 0;

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){

            move = traversePreviousWord(cursor);


            if(isLegitHorizontalMove( cursor, - move )){
                return - move;
            }

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){

            move = traverseWordBeginning(cursor);

            if(isLegitHorizontalMove(cursor, move)){

                return move;
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.E)
                && currColumn != colunmTotal){

            move = traverseWordEnd(cursor);

            if(isLegitHorizontalMove(cursor, move)){

                return move;
            }
        }



        return charHorizontalMove(cursor);





        /*
        if(){


            //isOnLetter('a');
            //isOnType(LetterType.RED);

            moveCounter++;
            movesLeft--;

            //if(isOnType(LetterType.YELLOW)) movesLeft++;
            //if(isOnType(LetterType.RED)) movesLeft--;

            System.out.println("Moves: " + moveCounter + "\nMoves left: " + movesLeft);

            //System.out.println("\nMoveVar: " + move + "\n");

            System.out.println("\ncurrRow: " + currRow + " - column: " + currColumn + " x: " + position.x + " y: " + position.y);
        }

         */
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
