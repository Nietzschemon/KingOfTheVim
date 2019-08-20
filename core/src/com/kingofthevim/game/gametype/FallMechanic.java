package com.kingofthevim.game.gametype;

import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.matrix.CellMatrix;
import com.kingofthevim.game.engine.vim_object.VimObject;

public class FallMechanic {

    private VimObject vimObject;
    private int currRow;
    private int currColumn;
    private LetterType[] stdGround = {LetterType.WHITE, LetterType.RED, LetterType.YELLOW};

    public FallMechanic(VimObject vimObject){
        this.vimObject = vimObject;
        currRow = vimObject.getPosition().getCurrRow();
        currColumn = vimObject.getPosition().getCurrColumn();
    }

    private void updatePosition(){
        currRow = vimObject.getPosition().getCurrRow();
        currColumn = vimObject.getPosition().getCurrColumn();
    }


    /**
     * checks if the vimObject is on the ground.
     * i.e. cannot fall.
     *
     * @return true if on ground
     */
    public boolean onGround(){
        return onGround(stdGround);
    }


    /**
     * checks if the vimObject is on the ground.
     * i.e. cannot fall.
     *
     * @param letterType That count as ground
     * @return true if on ground
     */
    public boolean onGround(LetterType letterType){
        updatePosition();
        return  vimObject.isOnType(letterType);
    }

    /**
     * checks if the vimObject is on the ground.
     * i.e. cannot fall.
     *
     * @param letterTypeArray LetterTypes that count as ground
     * @return true if on ground
     */
    public boolean onGround(LetterType[] letterTypeArray){
        updatePosition();

        for (LetterType letterType : letterTypeArray){
            if(vimObject.isOnType(letterType)) return true;
        }
        return  false;
    }

    /**
     * The object falls in a straight trajectory
     * to the closes legit LetterType changing the
     * intervening cells on the way.
     *
     * @return true if fallen
     */
    public boolean fall(){
        return fall(true);
    }

    /**
     * The object falls in a straight trajectory
     * to the closes legit LetterType.
     * @param changeCells if cells passed should be changed
     * @return true if fallen
     */
    public boolean fall(boolean changeCells){
        return fall(changeCells, LetterType.RED);
    }

    /**
     * The object falls in a straight trajectory
     * to the closes legit LetterType.
     *
     * @param changeCells if cell passed should be changed
     * @param changeTo what the cells should be changed to
     * @return true if fallen
     */
    public boolean fall(boolean changeCells, LetterType changeTo){
        updatePosition();

        if( ! onGround() ){

            CellMatrix matrix = vimObject.getVimMatrix();

            for (int i = 1; i < CellMatrix.getRowTotal() - currRow; i++) {

                Cell cell = matrix.getCell(currRow + i, currColumn);

                if(changeCells)matrix.getCell(currRow + i - 1, currColumn).setCellLookTemp(changeTo);

                if(isGround(cell.getLetterType())){
                    vimObject.getPosition().setAbsoluteRow(currRow + i);
                    return true;
                }
            }
            vimObject.getPosition().setAbsoluteRow(CellMatrix.getRowTotal() - 1);
        }

        return false;
    }

    private boolean isGround(LetterType letterType){
        for (LetterType l : stdGround){
            if(letterType == l)return true;
        }
        return false;
    }

    /**
     * Calls fall() after a given set of seconds.
     *
     * @param dt deltaTime to check against
     * @param timeBeforeFall time until fall in called
     * @return true if fall was called.
     */
    public boolean timeBeforeFall(float dt, float timeBeforeFall){

        if(timeBeforeFall < dt){
            this.fall();
            return true;
        }
        return false;
    }

}
