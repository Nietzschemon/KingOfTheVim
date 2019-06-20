package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Letter {

    public static int tube_with;
    public static int tube_height;

    //TODO make static and reuse texture for text.
    //TODO LATER make letterArray of all letters so they can be called and handles by LetterManger
    // Pictures
    private Texture texture;

    //position on axis
    private Vector2 position;

    //Colition box
    //TODO LATER make the LetterManager handle the coaliton box
    // and only asign it to idiviual letters if needed.
    // ALTERNATIVE since the number of positions per line
    // is fixed a array of chars might be all that is needed.
    private Rectangle bounds;

    //TODO texture is fonttest are 33 width and 66 height
    //TODO LATER remove this and put in LetterManager
    public Letter(float x, float y, int width, int height){
        //TODO make a variable
        texture = new Texture("fontTest/A.png");

        position = new Vector2(x, y);

        tube_with = width;
        tube_height = height;

        //TODO Dont getWidth and hight. Make fixed for performance in LetterManager.
        //Sets the bounds with image postion and size
        bounds = new Rectangle(position.x, position.y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    // puts tubes passed in front
    public void reposition(float x, float y){
        position.set(x, y);
        bounds.setPosition(position.x, position.y);
    }

    // whats on the box.
    public boolean collides(Rectangle pointer){
        return pointer.overlaps(bounds);
    }

    public void dispose(){
        texture.dispose();
    }

}
