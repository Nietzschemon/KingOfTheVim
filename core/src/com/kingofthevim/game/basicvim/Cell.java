package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for sending, keeping and manipulating data on a
 * certain point on the grid. VIM-world objects always
 * interact with the world through these.
 */
public class Cell {


    private char cellChar;
    private Texture cellLook;
    private Vector2 cartesianPosition;

    //TODO change out for enum and more states
    private boolean isGood;
    private boolean isBad;


    Cell( float x, float y){

        cartesianPosition = new Vector2();
        cartesianPosition.x = x;
        cartesianPosition.y = y;

    }

    public Texture getCellLook(){
        return cellLook;
    }

    public void setCellLook(Texture cellLook, char cellChar) {
        this.cellChar = cellChar;
        this.cellLook = cellLook;
    }

    public char getCellChar() {
        return cellChar;
    }

    public void setCellChar(char cellChar) {
        this.cellChar = cellChar;
    }


    public Vector2 getCartesianPosition() {
        return cartesianPosition;
    }

    public void setCartesianPosition(float x, float y) {
        this.cartesianPosition.x = x;
        this.cartesianPosition.y = y;
    }


    public void dispose(){
        cellLook.dispose();
    }

    public boolean isBad() {
        return isBad;
    }

    public void setBad(boolean bad) {
        isBad = bad;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }
}
