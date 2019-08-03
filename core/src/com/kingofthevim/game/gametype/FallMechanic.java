package com.kingofthevim.game.gametype;

import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.Matrix.VimWorldMatrix;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

public class FallMechanic {

    VimObject vimObject;
    int currRow;
    int currColumn;

    public FallMechanic(VimObject vimObject){
        this.vimObject = vimObject;
        currRow = vimObject.getPosition().getCurrRow();
        currColumn = vimObject.getPosition().getCurrColumn();
    }

    private void updatePosition(){
        currRow = vimObject.getPosition().getCurrRow();
        currColumn = vimObject.getPosition().getCurrColumn();
    }

    public boolean canFall(){
        updatePosition();
        Cell ground = vimObject.getVimMatrix().getCell(currRow, currColumn);
        return ground.getLetterType() == LetterType.EMPATHY;
    }

    public boolean canFall(LetterType letterType){
        updatePosition();
        Cell ground = vimObject.getVimMatrix().getCell(currRow, currColumn);
        return ground.getLetterType() == letterType;
    }

    /**
     * The object falls in a straight trajectory
     * to the closes legit LetterType.
     * @return true if fallen
     */
    public boolean fall(){
        updatePosition();

        if( ! vimObject.isOnType(LetterType.WHITE) ){

            VimWorldMatrix matrix = vimObject.getVimMatrix();

            for (int i = 1; i < VimWorldMatrix.getRowTotal() - currRow; i++) {

                Cell cell = matrix.getCell(currRow + i, currColumn);

                matrix.getCell(currRow + i - 1, currColumn).setCellLookTemp(LetterType.RED);

                if(cell.getLetterType() == LetterType.WHITE){
                    vimObject.getPosition().setAbsoluteRow(currRow + i);
                    return true;
                }
            }
            vimObject.getPosition().setAbsoluteRow(VimWorldMatrix.getRowTotal() - 1);
        }

        return false;
    }

    public boolean fall(boolean changeChars){
        return false;
    }

    public boolean fallTime(float deltaTime){
        return false;
    }
}
