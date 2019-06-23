package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Letter {

    // static as to not accidentally create different sizes
    public static int letter_with;
    public static int letter_height;


    //TODO might need to have a static array of the letters loaded in for performance rather that it being elsewhere?
    //TODO make static and reuse texture for text.
    //TODO LATER make letterArray of all letters so they can be called and handles by LetterManger
    // Pictures
    private Texture texture;

    //position on axis
    private Vector2 position;

    //TODO texture is fonttest are 33 width and 66 height
    //TODO LATER remove this and put in LetterManager
    /**
     * Constructs and places letter on map
     * @param x the x position
     * @param y the y position
     * @param width width of font
     * @param height height of font
     * @param filePath filePath to font letter
     */
    public Letter(float x, float y, int width, int height, String filePath){
        //TODO make a variable
        texture = new Texture(filePath);


        position = new Vector2(x, y);

        letter_with = width;
        letter_height = height;

        //TODO Dont getWidth and hight. Make fixed for performance in LetterManager.
    }

    /**
     *
     * @param x
     * @param y
     * @param letter
     * @param width
     * @param height
     */
    public Letter(float x, float y, Texture letter, int width, int height){

        texture = letter;

        position = new Vector2(x, y);

        letter_with = width;
        letter_height = height;

    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void reposition(float x, float y){
        position.set(x, y);
    }

    public void dispose(){
        texture.dispose();
    }

}
