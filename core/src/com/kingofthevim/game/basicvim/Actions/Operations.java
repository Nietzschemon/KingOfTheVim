package com.kingofthevim.game.basicvim.Actions;

import com.badlogic.gdx.math.MathUtils;
import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

import java.util.ArrayList;

public class Operations extends Movement{


    /**
     * "Deletes" the current occupied cell of the VimObject.
     *
     * This is done by moving all the cell data one cell
     * to the left and clearing the last cell in the row.
     * @param vimObj the object who's cell is to be "deleted"
     */
    private void deleteChar(VimObject vimObj){

        int position = vimObj.getPosition().getCurrColumn();

        deleteChar(vimObj, position);
    }

    /**
     * "Deletes" the current occupied cell of the VimObject.
     *
     * This is done by moving all the cell data one cell
     * to the left and clearing the last cell in the row.
     * @param vimObj the object who is on the row of the
     *               cell that shall be deleted
     * @param position which cell in the row of vimObj that
     *                 shall be deleted
     */
    private void deleteChar(VimObject vimObj, int position){
        ArrayList<Cell> cellList = vimObj.getVimMatrix().getCellMatrix().get(vimObj.getPosition().getCurrRow());


        for (int i = position; i < cellList.size() - 1; i++) {

            cellList.get(i).setCellProperties(cellList.get( i + 1 ).getCellProperties());
        }

        cellList.get(vimObj.getPosition().getColunmTotal() - 1).clearCell();

    }


    /**
     * "Deletes" the current occupied cell of the VimObject
     * and as many left to it as specified
     *
     * This is done by calling deleteCharHorizontal() that moves
     * all the cell data one cell to the left and
     * clearing the last cell in the row.
     * @param vimObj the object who is on the row of the
     *               cell that shall be deleted
     * @param num the number of cells that shall be deleted
     */
    protected void deleteCharBatch(VimObject vimObj, int num){
        for (int i = 0; i < num; i++) {

            deleteChar(vimObj);
        }
    }


    /**
     * "Deletes" a specified cell on the row that is occupied
     * by the VimObject and as many left to it as specified
     *
     * This is done by calling deleteCharHorizontal() that moves
     * all the cell data one cell to the left and
     * clearing the last cell in the row.
     * @param vimObj the object who is on the row of the
     *               cell that shall be deleted
     * @param num the number of cells that shall be deleted
     * @param position from which cell the deletion shall start
     */
    protected void deleteCharBatch(VimObject vimObj, int num, int position){
        for (int i = 0; i < num; i++) {

            deleteChar(vimObj, position);
        }
    }

    /**
     * "Deletes" a specified cell on the row that is occupied by the
     * VimObject and as many left or the right of it as specified
     *
     * This is done by calling deleteCharBatch() that moves
     * all the cell data one cell to the left and
     * clearing the last cell in the row.
     * @param vimObj the object who is on the row of the
     *               cell that shall be deleted
     * @param inFrontOf delete from front or back outward
     * @param iteration the number of cells that shall be deleted
     */
    protected void deleteCharHorizontal(VimObject vimObj, boolean inFrontOf, int iteration){
        if( inFrontOf){
            deleteCharBatch(vimObj, iteration);
        }
        else {
            charHorizontalMove(vimObj, false, iteration);
            deleteCharBatch(vimObj, iteration, vimObj.getPosition().getCurrColumn());

        }

    }


    protected void deleteWordInFront(VimObject vimObj, boolean bgnWord, boolean shiftHeld, int iteration){

        int deleteNum = traverseWord_Int(vimObj, shiftHeld, bgnWord, iteration);
        if(bgnWord) deleteCharBatch(vimObj, deleteNum);
        else deleteCharBatch(vimObj, deleteNum + 1);
    }

    protected void deleteWordBehind(VimObject vimObj, boolean shiftHeld, int iteration){

        int deleteNum = traversePreviousWord_Int(vimObj, shiftHeld, iteration);
        deleteNum = Math.abs(deleteNum);
        traversePreviousWord(vimObj, shiftHeld, iteration);

        deleteCharBatch(vimObj, deleteNum, vimObj.getPosition().getCurrColumn());
    }
}
