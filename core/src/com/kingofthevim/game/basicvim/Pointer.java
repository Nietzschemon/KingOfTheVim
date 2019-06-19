package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pointer extends InputAdapter {


    private static final int MOVEMENT = 100;
    boolean leftMove;
    boolean rightMove;

    private Vector2 position;
    //private Vector2 movement;

    // to check colition
    private Rectangle bounds;
    private Texture texture;

    public double getX(){

        return position.x;
    }
    public double getY(){

        return position.y;
    }
    public Texture getTexture(){
        return texture;
    }

    public Pointer(int x, int y){

        position = new Vector2(x, y);
        //movement = new Vector2(x, y);

        texture = new Texture("fontTest/E.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

    }

    // sends the delta time to our game world.
    public void update(float dt){


        // ... in relation to delta time
        //movement.scl(dt);

        // takes the x position on map and updates it with the //movement
        //position.add(MOVEMENT * dt, //movement.y, 0);


        if(position.y < 0)
            position.y = 0;

        // reverses what we scaled previeusly
        //movement.scl(1/dt);
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

        bounds.setPosition(position.x, position.y);
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

    public void dispose(){
        texture.dispose();
    }

}
