package com.kingofthevim.game.basicvim.Actions;

import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

import java.util.ArrayList;

public class Operations extends Movement{


    /**
     * "Deletes" the current occupied cell of the VimObject.
     *
     * This is done by movining all the cell data one cell
     * to the left and clearing the last cell in the row.
     * @param vimObj the object who's cell is to be "deleted"
     */
    protected void deleteChar(VimObject vimObj){

        int position = vimObj.getPosition().getCurrColumn();

        deleteChar(vimObj, position);
    }

    protected void deleteChar(VimObject vimObj, int position){
        ArrayList<Cell> cellList = vimObj.getVimMatrix().getCellMatrix().get(vimObj.getPosition().getCurrRow());


        for (int i = position; i < cellList.size() - 1; i++) {

            cellList.get(i).setCellProperties(cellList.get( i + 1 ).getCellProperties());
        }

        cellList.get(vimObj.getPosition().getColunmTotal() - 1).clearCell();

    }

    protected void deleteCharBatch(VimObject vimObj, int num){
        for (int i = 0; i < num; i++) {

            deleteChar(vimObj);
        }
    }
}
