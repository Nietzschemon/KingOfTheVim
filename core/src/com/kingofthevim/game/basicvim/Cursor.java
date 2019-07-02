package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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
    private int currRowCell;

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
        currRowCell = startRowCell;

    }

    public Cursor(VimWorldMatrix vimMatrix, int x, int y, int row0, int rowCell0){

        cellMatrix = vimMatrix.getCellMatrix();
        rowTotal = VimWorldMatrix.rowTotal;
        colunmTotal = VimWorldMatrix.colunmTotal;
        position = new Vector2(x, y);

        texture = new Texture("markers/marker_44purple.png");

        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        currRow = row0;
        currRowCell = rowCell0;
    }


    //TODO fix unneccery (dublicate?) code between here (update()) and move()
    //right now just a movement limiter
    //and currRow(cell)-corrector
    public void update(){

        /*
        if(currRowCell < 0){
            currRowCell = 0;

            //TODO write method for updating position
            position.x = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().x;
        }
        if(currRowCell > colunmTotal-1){
            currRowCell = colunmTotal-1;
            position.x = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().x;
        }

        if(currRow < 0){
            currRow = 0;
            position.y = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().y;
        }

        if(currRow > rowTotal-1){
            currRow = rowTotal-1;
            position.y = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().y;
        }
        */


    }


    private boolean isLegitHorizontalMove(int move){

        if(currRowCell+move < 0
        || currRowCell+move > colunmTotal){
            return false;
        }

        //return cellMatrix.get(currRow).get(currRowCell + move).getCellLook() != null;
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

        //return cellMatrix.get(currRow + move).get(currRowCell).getCellLook() != null;
        return true;
    }

    public boolean isOnLetter(char letter){
        if(cellMatrix.get(currRow).get(currRowCell).getCellChar() == letter){
            System.out.println("is on letter \"" + letter + "\"");
        }
        return cellMatrix.get(currRow).get(currRowCell).getCellChar() == letter;
    }

    public boolean isOnType(LetterType type){
        if(cellMatrix.get(currRow).get(currRowCell).getLetterType() == type){
            System.out.println("is on type \"" + type + "\"");
        }
        return cellMatrix.get(currRow).get(currRowCell).getLetterType() == type;
    }


    public void move()
    {
        if (moveLeft_word
                && isLegitHorizontalMove(-1))
        {
            position.x -= bounds.width;
            currRowCell -= 1;
        }
        if (moveRight_word_bgn
                && isLegitHorizontalMove(1))
        {
            position.x += bounds.width;
            currRowCell += 1;
        }

        if (moveLeft_char
        && isLegitHorizontalMove(-1))
        {
            position.x -= bounds.width;
            currRowCell -= 1;
        }
        if (moveRight_char
        && isLegitHorizontalMove(1))
        {
            position.x += bounds.width;
            currRowCell += 1;
        }
        if(moveUp_line
        && isLegitVerticalMove(+1))
        {
            position.y += bounds.height;
            currRow += 1;
        }
        if(moveDown_line
        && isLegitVerticalMove(-1))
        {
           position.y -= bounds.height;
            currRow -= 1;
        }

        bounds.setPosition(position.x, position.y);
        update();


        if(moveLeft_char || moveRight_char || moveUp_line || moveDown_line){

            //isOnLetter('a');
            //isOnType(LetterType.RED);

            //System.out.println("\n\ncurrRow: " + currRow + " - column: " + currRowCell);
        }
    }


    public void dispose(){
        texture.dispose();
    }

}
