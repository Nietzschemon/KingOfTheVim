package com.kingofthevim.game.engine.vim_modes;

import com.kingofthevim.game.engine.matrix.Tools;
import com.kingofthevim.game.engine.vim_object.VimObject;

/**
 * Class that aims to emulate the vim replace mode.
 */
public class ReplaceMode {

    /**
     * Replaces the char of cell the VimObject is currently on.
     * @param vimObject whos current cell is to be replaced
     * @param character to replace cell-character with
     * @return true if successful
     */
    public boolean replaceChar(VimObject vimObject, char character){
        if(Tools.isLetterNumberSymbol(character)) {

            vimObject.getCurrentCell().setCellLook(character);
            vimObject.getPosition().setRelativeColumn(1);
            return true;
        }

        if(character == ' '){
            vimObject.getCurrentCell().clearCell();
            vimObject.getPosition().setRelativeColumn(1);
            return true;
        }

        return false;
    }

}
