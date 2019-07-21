package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.math.Vector2;

public class Position {

    private VimObject vimObject;
    private Vector2 cartesianPosition;
    private int currRow;
    private int currColumn;
    private int rowTotal;
    private int colunmTotal;


    Position(VimObject vimObject, int row, int column, int rowTotal, int colunmTotal){
        this.vimObject = vimObject;
        cartesianPosition = new Vector2();
        setAbsoluteRow(row);
        setAbsoluteColumn(column);

        this.rowTotal = rowTotal;
        this.colunmTotal = colunmTotal;
    }

    public Vector2 getCartesianPosition() {
        return cartesianPosition;
    }

    public int getRowTotal() {
        return rowTotal;
    }

    public int getColunmTotal() {
        return colunmTotal;
    }

    public int getCurrRow() {
        return currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public boolean setRelativeRow(int rowMove) {

        if(rowMove != 0){

            cartesianPosition.y = cartesianPosition.y + (vimObject.getSize().getHeight() * rowMove);
            currRow += rowMove;

            return true;
        }

        return false;
    }



    public boolean setRelativeColumn(int columnMove) {

        if(columnMove != 0){

            cartesianPosition.x = cartesianPosition.x + (vimObject.getSize().getWidth() * columnMove);
            currColumn += columnMove;

            return true;
        }

        return false;
    }

    /**
     * Moves the object directly to
     * the specified row
     * @param row to move object to
     * @return true if success, false if not
     */
    public boolean setAbsoluteRow(int row){

        if(row >= 0
                && row < rowTotal){

            cartesianPosition.y = vimObject.getSize().getHeight() * row;
            this.currRow = row;

            return true;
        }

        return false;
    }

    /**
     * Moves the object directly to
     * the specified column
     * @param column to move object to
     * @return true if success, false if not
     */
    public boolean setAbsoluteColumn(int column){

        if(column >= 0
                && column < colunmTotal){

            cartesianPosition.x = vimObject.getSize().getWidth() * column;
            this.currColumn = column;

            return true;
        }

        return false;
    }
}
