package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

//TODO TEXTURES look into TextureAtlas and sprites to see if the
// visual effect in spacemacs of a combination of the cursor and
// a letter in spacemacs can be achieved.
public class Cursor  {

    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

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

    public void setLeftMove(boolean t)
    {
        if(rightMove && t) rightMove = false;
        leftMove = t;
    }
    public void setRightMove(boolean t)
    {
        if(leftMove && t) leftMove = false;
        rightMove = t;
    }
    public void setUpMove(boolean t)
    {
        if(upMove && t) upMove = false;
        downMove = t;
    }
    public void setDownMove(boolean t)
    {
        if(downMove && t) downMove = false;
        upMove = t;
    }
    public Texture getTexture(){
        return texture;
    }


    public Cursor(VimWorldMatrix vimMatrix, int startRow, int startRowCell){

        cellMatrix = vimMatrix.getCellMatrix();
        rowTotal = VimWorldMatrix.rowTotal;
        colunmTotal = VimWorldMatrix.colunmTotal;

        //position = new Vector2(cellMatrix[startRow][startRowCell].getCartesianPosition());

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


    //right now just a movement limiter
    //and currRow(cell)-corrector
    public void update(){

        if(currRowCell < 0){
            currRowCell = 0;

            //TODO write method for updating position
            //position.x = cellMatrix[currRow][currRowCell].getCartesianPosition().x;
            position.x = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().x;
        }
        if(currRowCell > colunmTotal-1){
            currRowCell = colunmTotal-1;
            //position.x = cellMatrix[currRow][currRowCell].getCartesianPosition().x;
            position.x = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().x;
        }

        if(currRow < 0){
            currRow = 0;
            //position.y = cellMatrix[currRow][currRowCell].getCartesianPosition().y;
            position.y = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().y;
        }

        if(currRow > rowTotal-1){
            currRow = rowTotal-1;
            //position.y = cellMatrix[currRow][currRowCell].getCartesianPosition().y;
            position.y = cellMatrix.get(currRow).get(currRowCell).getCartesianPosition().y;
        }

    }


    private boolean isLegitHorizontalMove(int move){

        if(currRowCell+move < 0
        || currRowCell+move > colunmTotal){
            return false;
        }
        //return cellMatrix[currRow][currRowCell + move].getCellLook() != null;
        return cellMatrix.get(currRow).get(currRowCell + move).getCellLook() != null;
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
        //return cellMatrix[currRow + move][currRowCell].getCellLook() != null;
        return cellMatrix.get(currRow + move).get(currRowCell).getCellLook() != null;
    }

    private boolean isGoodLetter(){
        if(cellMatrix.get(currRow).get(currRowCell).getLetterType() == LetterType.YELLOW){
            System.out.println("GOOD!");
            return true;
        }
        return false;
    }

    public boolean isOnGray() {
        if (cellMatrix.get(currRow).get(currRowCell).getLetterType() == LetterType.GRAY){
            System.out.println("It on gray letter \"" + cellMatrix.get(currRow).get(currRowCell).getCellChar() + "\"");


            return true;
        }
        return false;
    }


    //TODO fix bug that takes the texture after reset with the cursor
    // MAKE the above method reset the cursor by disposing it in the renderer
    // in playstate
    public void moveCursorTo(int row, int rowCell){
        currRow = row;
        currRowCell = rowCell;
    }

    public void updateMotion()
    {
        if (leftMove
        && isLegitHorizontalMove(-1))
        {
            position.x -= bounds.width;
            currRowCell -= 1;
        }
        if (rightMove
        && isLegitHorizontalMove(1))
        {
            position.x += bounds.width;
            currRowCell += 1;
        }
        if(upMove
        && isLegitVerticalMove(+1))
        {
            position.y += bounds.height;
            currRow += 1;
        }
        if(downMove
        && isLegitVerticalMove(-1))
        {
           position.y -= bounds.height;
            currRow -= 1;
        }

        bounds.setPosition(position.x, position.y);
        update();


        if(leftMove || rightMove || upMove || downMove){

            isOnGray();
            isGoodLetter();

            //System.out.println(cellMatrix[currRow][currRowCell].getCellChar());
            System.out.println("\n\ncurrRow: " + currRow + " - column: " + currRowCell);
            //System.out.println("x: " + getX() + "\ny: " + getY());
        }
    }


    public void dispose(){
        texture.dispose();
    }

}
