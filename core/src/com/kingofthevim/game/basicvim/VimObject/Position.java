package com.kingofthevim.game.basicvim.VimObject;

import com.badlogic.gdx.math.Vector2;

public class Position {

    private VimObject vimObject;
    private Vector2 cartesianPosition;
    private int currRow;
    private int currColumn;
    private int rowTotal;
    private int colunmTotal;


    public Position(){ }


    Position(VimObject vimObject, int row, int column, int rowTotal, int colunmTotal){
        this.vimObject = vimObject;
        cartesianPosition = new Vector2();

        //done manually to avoid extra code for nullPointer in set-methods
        cartesianPosition.y = cartesianPosition.y + (vimObject.getSize().getHeight() * row);
        currRow += row;
        cartesianPosition.x = cartesianPosition.x + (vimObject.getSize().getWidth() * column);
        currColumn += column;

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

        if(rowMove != 0 && rowMove + currRow < rowTotal){
            vimObject.doBeforePosiUpdate();

            cartesianPosition.y = cartesianPosition.y + (vimObject.getSize().getHeight() * rowMove);
            currRow += rowMove;

            vimObject.doAfterPosiUpdate();
            return true;
        }

        return false;
    }



    public boolean setRelativeColumn(int columnMove) {

        if(columnMove != 0 && columnMove + currColumn < colunmTotal){
            vimObject.doBeforePosiUpdate();

            cartesianPosition.x = cartesianPosition.x + (vimObject.getSize().getWidth() * columnMove);
            currColumn += columnMove;

            vimObject.doAfterPosiUpdate();
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
            vimObject.doBeforePosiUpdate();

            cartesianPosition.y = vimObject.getSize().getHeight() * row;
            this.currRow = row;

            vimObject.doAfterPosiUpdate();
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
            vimObject.doBeforePosiUpdate();

            cartesianPosition.x = vimObject.getSize().getWidth() * column;
            this.currColumn = column;

            vimObject.doAfterPosiUpdate();
            return true;
        }

        return false;
    }
}
