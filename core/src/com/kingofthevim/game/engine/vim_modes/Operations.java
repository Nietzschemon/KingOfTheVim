package com.kingofthevim.game.engine.vim_modes;

import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.vimobject.VimObject;

import java.util.ArrayList;

public class Operations extends Movement{


    /**
     * "Deletes" the current occupied cell of the vimobject.
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
     * "Deletes" the current occupied cell of the vimobject.
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

            cellList.get(i).setCellLook(cellList.get( i + 1 ).getCellProperties());
        }

        cellList.get(vimObj.getPosition().getColumnTotal() - 1).clearCell();

    }


    /**
     * "Deletes" the current occupied cell of the vimobject
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
     * by the vimobject and as many left to it as specified
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

    protected void deleteCharBatch(int startColumn, int endColumn, VimObject vimObj){
        for (int i = 0; i < endColumn; i++) {

            deleteChar(vimObj, startColumn);
        }
    }

    /**
     * "Deletes" a specified cell on the row that is occupied by the
     * vimobject and as many left or the right of it as specified
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

    protected void deleteLineHorizontal(VimObject vimObj, boolean inFrontOf){

        int deleteNum = traverseWholeLine_Int(vimObj, inFrontOf);

        if(inFrontOf){

            deleteCharBatch(vimObj, deleteNum + 1);

            // for the one step back
            charHorizontalMove(vimObj, false, 1);
        } else {
            deleteNum = Math.abs(deleteNum);

            traverseWholeLine(vimObj, false);

            deleteCharBatch(vimObj, deleteNum + 1);

        }
    }

    protected void deleteToLineBgn(VimObject vimObj){

        int endColumn = vimObj.getPosition().getCurrColumn() - 1;
        goToFirstNonBlankChar(vimObj);
        int startColumn = vimObj.getPosition().getCurrColumn();

        deleteCharBatch(startColumn, endColumn, vimObj);

    }
}
