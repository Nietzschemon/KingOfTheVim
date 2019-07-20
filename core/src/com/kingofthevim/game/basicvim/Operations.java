package com.kingofthevim.game.basicvim;

import java.util.ArrayList;

public class Operations {


    void deleteChar(VimObject vimObj){

        ArrayList<Cell> cellList = vimObj.getVimMatrix().getCellMatrix().get(vimObj.getCurrRow());

        for (int i = vimObj.getCurrColumn(); i < cellList.size() - 1; i++) {

            cellList.get(i).setCellProperties(cellList.get( i + 1 ).getCellProperties());
        }

        cellList.get(vimObj.getColunmTotal() - 1).clearCell();

    }

    void deleteCharBatch(VimObject vimObj, int num){
        for (int i = 0; i < num; i++) {

            deleteChar(vimObj);
        }
    }
}
