package com.kingofthevim.game.engine.vim_modes;

import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.Tools;
import com.kingofthevim.game.engine.vim_object.VimObject;

import java.util.ArrayList;

public class InsertMode {


    /**
     * Inserts a character into the current row
     * @param vimObject that does the insertion
     * @param character the char to insert
     * @return true if success
     */
    public boolean insertLetter(VimObject vimObject, char character){

        if(Tools.isLetterNumberSymbol(character)){
            ArrayList<Cell> cellMatrixRow = vimObject.getVimMatrix().getCellMatrix().get(vimObject.getPosition().getCurrRow());

            for (int i = cellMatrixRow.size() - 2; i > vimObject.getPosition().getCurrColumn() - 1; i--) {
                cellMatrixRow.get(i + 1).setCellLook(cellMatrixRow.get(i).getCellProperties());
            }


            if(character == ' '){
                vimObject.getCurrentCell().clearCell();
                vimObject.getPosition().setRelativeColumn(1);
                return true;
            }

            vimObject.getCurrentCell().setCellLook(character);
            vimObject.getPosition().setRelativeColumn(1);
            return true;
        }

        return false;
    }

}
