package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.math.Vector2;

public class Location {

    private VimObject vimObject;
    private Vector2 cartesianPosition;
    private int currRow;
    private int currColumn;


    Location(VimObject vimObject, int row, int column){
        this.vimObject = vimObject;
        setCurrRow(row);
        setCurrColumn(column);
    }

    public Vector2 getCartesianPosition() {
        return cartesianPosition;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        cartesianPosition.x = vimObject.getSize().getWidth() * currRow;
        this.currRow = currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public void setCurrColumn(int currColumn) {
        cartesianPosition.y = vimObject.getSize().getHeight() * currColumn;
        this.currColumn = currColumn;
    }

}
