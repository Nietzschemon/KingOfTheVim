package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.math.Vector2;

public class Position {

    private VimObject vimObject;
    private Vector2 cartesianPosition;
    private int currRow;
    private int currColumn;


    Position(VimObject vimObject, int row, int column){
        this.vimObject = vimObject;
        cartesianPosition = new Vector2();
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
        cartesianPosition.y = vimObject.getSize().getHeight() * currRow;
        this.currRow = currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public void setCurrColumn(int currColumn) {
        cartesianPosition.x = vimObject.getSize().getWidth() * currColumn;
        this.currColumn = currColumn;
    }

}
