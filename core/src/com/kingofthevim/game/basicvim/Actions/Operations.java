package com.kingofthevim.game.basicvim.Actions;

import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

import java.util.ArrayList;

public class Operations extends Movement{


    protected void deleteChar(VimObject vimObj){

        ArrayList<Cell> cellList = vimObj.getVimMatrix().getCellMatrix().get(vimObj.getPosition().getCurrRow());

        for (int i = vimObj.getPosition().getCurrColumn(); i < cellList.size() - 1; i++) {

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
