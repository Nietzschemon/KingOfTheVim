package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kingofthevim.game.KingOfTheVimMain;

import java.lang.reflect.Array;

//TODO TEXTURES look into TextureAtlas and sprites to see if the
// visual effect in spacemacs of a combination of the cursor and
// a letter in spacemacs can be achieved.

//TODO make pointer dependent on keeping within the cell array
// in this way the levels can variate in size more easily, as
// the x and y can be calculated by the position in the "cellMatrix"
public class Cursor extends VimWorldMatrix {

    //TODO fix so cursor works within matrix
    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

    private static int currRow;
    private static int currRowCell;

    private Vector2 position;

    // to check collision
    private Rectangle bounds;
    private Texture texture;

    public int getRow(){
        return currRow;
    }

    public int getRowCell(){
        return currRowCell;
    }


    public double getX(){
        return position.x;
    }

    public double getY(){
        return position.y;
    }

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


    public Cursor(int startRow, int startRowCell){

        position = new Vector2(cellMatrix[startRow][startRowCell].getCartesianPosition());

        texture = new Texture("markers/MarkerPurple.png");

        bounds = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        currRow = startRow;
        currRowCell = startRowCell;

    }

    public Cursor(int x, int y, int row0, int rowCell0){
        super();

        position = new Vector2(x, y);

        texture = new Texture("markers/MarkerPurple.png");

        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        currRow = row0;
        currRowCell = rowCell0;
    }


    // right now just a movement limiter
    // and currRow(cell)-corrector
    public void update(){

        if(position.x < 0){
            position.x = 0;
            currRowCell = 0;
        }
        if(position.x > KingOfTheVimMain.WIDTH - 33){//char width
            position.x = KingOfTheVimMain.WIDTH - 33;
            currRowCell -= 1;
        }

        if(currRow < 0){
            position.y = KingOfTheVimMain.HEIGHT - 66;//char height
            currRow = 0;
        }
        if(currRow > rowTotal){
            position.y = 0;
            currRow -= 1;
        }

    }


    public void updateMotion()
    {
        if (leftMove)
        {
            position.x -= bounds.width;
            currRowCell -= 1;
        }
        if (rightMove)
        {
            position.x += bounds.width;
            currRowCell += 1;
        }
        if(upMove)
        {
            position.y += bounds.height;
            currRow -= 1;
        }
        if(downMove)
        {
           position.y -= bounds.height;
            currRow += 1;
        }

        bounds.setPosition(position.x, position.y);
        update();


        if(leftMove || rightMove || upMove || downMove){
            System.out.println("\n\ncurrRow: " + currRow + "\ncolumn: " + currRowCell);
            System.out.println("x: " + getX() + "\ny: " + getY());
        }
    }


    public void dispose(){
        texture.dispose();
    }

}
