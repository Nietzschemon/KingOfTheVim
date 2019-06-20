package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kingofthevim.game.KingOfTheVimMain;

public class Pointer extends InputAdapter {


    boolean leftMove;
    boolean rightMove;
    boolean upMove;
    boolean downMove;

    private Vector2 position;

    // to check colition
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

    public Pointer(int x, int y){

        position = new Vector2(x, y);

        texture = new Texture("markers/MarkerPurple.png");

        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

    }

    // sends the delta time to our game world.
    // right now just a movement limiter
    public void update(float dt){

        if(position.x < 0)
        {
            position.x = 0;
        }
        if(position.x > KingOfTheVimMain.WIDTH - 33)
        {
            position.x = KingOfTheVimMain.WIDTH - 33;
        }

        if(position.y < 0)
        {
            position.y = 0;
        }
        if(position.y > KingOfTheVimMain.HEIGHT - 66){
            position.y = KingOfTheVimMain.HEIGHT - 66;
        }

    }


    public void updateMotion()
    {
        if (leftMove)
        {
            position.x -= bounds.width;
        }
        if (rightMove)
        {
            position.x += bounds.width;
        }
        if(upMove)
        {
            position.y += bounds.height;
        }
        if(downMove)
        {
           position.y -= bounds.height;
        }


        bounds.setPosition(position.x, position.y);
    }


    public void dispose(){
        texture.dispose();
    }

}
