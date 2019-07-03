package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.regex.Pattern;

//TODO TEXTURES look into TextureAtlas and sprites to see if the
// visual effect in spacemacs of a combination of the cursor and
// a letter in spacemacs can be achieved.
public class Cursor  {





    // char movement booleans
    private boolean moveLeft_char;
    private boolean moveRight_char;
    private boolean moveUp_line;
    private boolean moveDown_line;

    //TODO fix all so word_bgn and word_end, WORD_bgn, WORD_end
    //word movement booleans
    private boolean moveLeft_word;
    private boolean moveRight_word_bgn;
    private boolean moveRight_word_end;


    //Todo WORD movement booleans
    //private boolean leftWORDMove;
    //private boolean rightWORDMove;

    private int currRow;
    private int currColumn;

    private Vector2 position;

    // to check collision
    private Rectangle bounds;
    private Texture texture;


    private int rowTotal;
    private int colunmTotal;
    private ArrayList<ArrayList<Cell>> cellMatrix;


    public Vector2 getPosition(){
        return position;
    }



    //<editor-fold desc="Setters">

    public void setMoveLeft_word(boolean t)
    {
        if(moveRight_word_bgn && t) moveRight_word_bgn = false;
        moveLeft_word = t;
    }

    public void setMoveRight_word_bgn(boolean t)
    {
        if(moveLeft_word && t) moveLeft_word = false;
        moveRight_word_bgn = t;
    }

    public void setMoveRight_word_end(boolean t)
    {
        if(moveLeft_word && t) moveLeft_word = false;
        moveRight_word_end = t;
    }

    public void setMoveLeft_Char(boolean t)
    {
        if(moveRight_char && t) moveRight_char = false;
        moveLeft_char = t;
    }

    public void setMoveRight_Char(boolean t)
    {
        if(moveLeft_char && t) moveLeft_char = false;
        moveRight_char = t;
    }

    public void setMoveUp_Line(boolean t)
    {
        if(moveUp_line && t) moveUp_line = false;
        moveDown_line = t;
    }
    public void setMoveDown_Line(boolean t)
    {
        if(moveDown_line && t) moveDown_line = false;
        moveUp_line = t;
    }

    //</editor-fold desc="bla">

    public Texture getTexture(){
        return texture;
    }


    public Cursor(VimWorldMatrix vimMatrix, int startRow, int startRowCell){

        cellMatrix = vimMatrix.getCellMatrix();
        rowTotal = VimWorldMatrix.rowTotal;
        colunmTotal = VimWorldMatrix.colunmTotal;


        position = new Vector2(cellMatrix.get(startRow).get(startRowCell).getCartesianPosition());

        texture = new Texture("markers/marker_44purple.png");

        bounds = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        currRow = startRow;
        currColumn = startRowCell;

    }

    public Cursor(VimWorldMatrix vimMatrix, int x, int y, int row0, int rowCell0){

        cellMatrix = vimMatrix.getCellMatrix();
        rowTotal = VimWorldMatrix.rowTotal;
        colunmTotal = VimWorldMatrix.colunmTotal;
        position = new Vector2(x, y);

        texture = new Texture("markers/marker_44purple.png");

        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        currRow = row0;
        currColumn = rowCell0;
    }


    public void update(){

    }


    private boolean isLegitHorizontalMove(int move){

        if(currColumn +move < 0
        || currColumn +move > colunmTotal){
            return false;
        }

        //return cellMatrix.get(currRow).get(currColumn + move).getCellLook() != null;
        return true;
    }

    /**
     * Checks if veritical move is possible from the
     * current place
     * @param move number of steps
     * @return True if possible, false if not
     */
    private boolean isLegitVerticalMove(int move){

        if(currRow+move < 0
                || currRow+move > rowTotal-1){
            return false;
        }

        //return cellMatrix.get(currRow + move).get(currColumn).getCellLook() != null;
        return true;
    }

    public boolean isOnLetter(char letter){
        if(cellMatrix.get(currRow).get(currColumn).getCellChar() == letter){
            System.out.println("is on letter \"" + letter + "\"");
        }
        return cellMatrix.get(currRow).get(currColumn).getCellChar() == letter;
    }

    public boolean isOnType(LetterType type){
        if(cellMatrix.get(currRow).get(currColumn).getLetterType() == type){
            System.out.println("is on type \"" + type + "\"");
        }
        return cellMatrix.get(currRow).get(currColumn).getLetterType() == type;
    }

    //TODO make sensitive to special signs
    private int traverseWordBeginning(){
        int count = 0;

        // on a string like aaa aaa33 33aaa aaa;aaa aa; ;aaa
        // stops at ; and special signs. numbers and words are treated the same

        for (int i = currColumn; i <cellMatrix.get(currRow).size(); i++) {

            count++;

            if(cellMatrix.get(currRow).get(i).getCellChar() == ' '){
                break;
            }
        }
        System.out.println("Count: " + count);

        return count;
    }

    private int traverseWordEnd(){
        int count = 0;

        // on a string like aaa aaa33 33aaa aaa;aaa aa; ;aaa
        // stops at ; and special signs. numbers and words are treated the same

        char currChar = cellMatrix.get(currRow).get(currColumn).getCellChar();

        char prevChar = currChar;

        for (int i = currColumn; i <cellMatrix.get(currRow).size(); i++) {

            char cellChar = cellMatrix.get(currRow).get(i).getCellChar();


            if(count > 0)prevChar = cellMatrix.get(currRow).get(i - 1).getCellChar();


            count++;

            System.out.println("CHAR: " + cellChar);

            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){

                System.out.println("SHIFT PRESS AND COUNT IS " + count);

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

            //"ay - yank into register a
            //*ap - paste from register a

        }
        System.out.println("Count: " + (count));

        if(currColumn + count >= colunmTotal){
            return count - 1;
        }

        return count - 2;
    }

    /**
     * Checks if char is a symbol and thus should only
     * be traversed by WORD-movements. Space is not
     * included
     * @param character char to check
     * @return true if char is a symbol
     */
    private boolean isSymbol(char character ){
        return ((character >= '!' && character <= '/')
                || (character >= ':' && character <= '@')
                || (character >= '[' && character <= '_')
                || (character >= '{' && character <= '~'));
    }

    private boolean isLetterChar(char character ){
        return ((character >= '0' && character <= '9')
                || (character >= 'a' && character <= 'z')
                || (character >= 'A' && character <= 'Z'));
    }

    //TODO make sensative for special signs
    private int traversePreviousWord(){
        int count = 0;

        // on a string like aaa aaa33 33aaa aaa;aaa aa; ;aaa
        // stops at ; and special signs. numbers and words are treated the same

        for (int i = currColumn; i <cellMatrix.get(currRow).size(); i++) {

            count++;


            if(cellMatrix.get(currRow).get(i).getCellChar() == ' '
                    && count > 2){
                break;
            }
        }
        System.out.println("Count: " + (count - 2));

        if(currColumn + count >= colunmTotal){
            return count;
        }

        return count - 2;
    }

    public void move()
    {
        //standard char/line move
        int move = 1;



        if (moveLeft_word){

            /*
            move = traverseWordBeginning();

            //TODO watch out for double negatives
            if(isLegitHorizontalMove(move)){
                position.x = position.x - (bounds.width * move);
                currColumn -= move;
            }

             */
        }

        if (moveRight_word_bgn){

            move = traverseWordBeginning();

            if(isLegitHorizontalMove(move)){
                position.x = position.x + (bounds.width * move);
                currColumn += move;
            }
        }

        if (moveRight_word_end
        && currColumn != colunmTotal){

            move = traverseWordEnd();
            System.out.println("Move: " + move + "\n");

            //TODO if move returns one this will not  work
            if(isLegitHorizontalMove(move)){

                position.x = position.x + (bounds.width * move);
                currColumn += move;

                /*
                if(move <= 2){
                    position.x += bounds.width;
                    currColumn += 1;
                }
                if(move == 200){
                    position.x = position.x + (bounds.width * move);
                    currColumn += move;
                }
                else {
                    move -= 2;
                    position.x = position.x + (bounds.width * move);
                    currColumn += move;
                }

                 */
            }
        }

        if (moveLeft_char
        && isLegitHorizontalMove(-move))
        {
            position.x -= bounds.width;
            currColumn -= move;
        }
        if (moveRight_char
        && isLegitHorizontalMove(move))
        {
            position.x += bounds.width;
            currColumn += move;
        }
        if(moveUp_line
        && isLegitVerticalMove(+move))
        {
            position.y += bounds.height;
            currRow += move;
        }
        if(moveDown_line
        && isLegitVerticalMove(-move))
        {
           position.y -= bounds.height;
            currRow -= move;
        }

        bounds.setPosition(position.x, position.y);
        //update();


        if(moveLeft_char || moveRight_char || moveUp_line || moveDown_line
        || moveLeft_word || moveRight_word_bgn || moveRight_word_end){

            //isOnLetter('a');
            //isOnType(LetterType.RED);

            System.out.println("\ncurrRow: " + currRow + " - column: " + currColumn + " x: " + position.x + " y: " + position.y);
        }
    }


    public void dispose(){
        texture.dispose();
    }

}
