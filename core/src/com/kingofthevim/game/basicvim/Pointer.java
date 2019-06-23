package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kingofthevim.game.KingOfTheVimMain;

//TODO make pointer dependent on keeping within the cell array
// in this way the levels can variate in size more easily, as
// the x and y can be calculated by the position in the "matrix"
public class Pointer extends InputAdapter {


    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

    private static int row;
    private static int rowCell;

    private Vector2 position;

    // to check collision
    private Rectangle bounds;
    private Texture texture;


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

    //TODO x and y need to be automatically determined by row/rowcell
    public Pointer(int x, int y, int row0, int rowCell0){

        position = new Vector2(x, y);

        texture = new Texture("markers/MarkerPurple.png");

        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        row = row0;
        rowCell = rowCell0;
    }

    // right now just a movement limiter
    // and row(cell)-corrector
    public void update(){

        if(position.x < 0)
        {
            position.x = 0;
            rowCell = 0;
        }
        if(position.x > KingOfTheVimMain.WIDTH - 33)//char width
        {
            position.x = KingOfTheVimMain.WIDTH - 33;
            rowCell -= 1;
        }

        if(position.y < 0)
        {
            position.y = 0;
            row -= 1;
        }
        if(position.y > KingOfTheVimMain.HEIGHT - 66){//char height
            position.y = KingOfTheVimMain.HEIGHT - 66;
            row = 0;
        }

    }


    public void updateMotion()
    {
        if (leftMove)
        {
            position.x -= bounds.width;
            rowCell -= 1;
        }
        if (rightMove)
        {
            position.x += bounds.width;
            rowCell += 1;
        }
        if(upMove)
        {
            position.y += bounds.height;
            row -= 1;
        }
        if(downMove)
        {
           position.y -= bounds.height;
            row += 1;
        }

        bounds.setPosition(position.x, position.y);
        update();


        if(leftMove || rightMove || upMove || downMove){
            System.out.println("\n\nrow: " + row + "\ncolumn: " + rowCell);
            System.out.println("x: " + getX() + "\ny: " + getY());
        }
    }


    public void dispose(){
        texture.dispose();
    }

}
