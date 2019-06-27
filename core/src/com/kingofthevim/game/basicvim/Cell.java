package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Class for sending, keeping and manipulating data on a
 * certain point on the grid. VIM-world objects always
 * interact with the world through these.
 */
public class Cell {


    private char cellChar;
    private Texture cellLook;
    private Vector2 cartesianPosition;
    private Enum<LetterType> letterType = LetterType.GRAY;


    Cell( float x, float y){

        cartesianPosition = new Vector2();
        cartesianPosition.x = x;
        cartesianPosition.y = y;

    }

    public Texture getCellLook(){
        return cellLook;
    }

    public void setCellLook(Texture cellLook, char cellChar, Enum<LetterType> type) {
        this.cellChar = cellChar;
        this.cellLook = cellLook;
        this.letterType = type;
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

    public Enum<LetterType> getLetterType() {
        return letterType;
    }

    //TODO make enum automaticly switch font color
    public void setLetterType(Enum<LetterType> letterType) {
        this.letterType = letterType;
    }

    public void setLetterType(Enum<LetterType> letterType, Texture cellLook) {
        this.letterType = letterType;
        this.cellLook = cellLook;
    }

    public static class Font{

    }
}
