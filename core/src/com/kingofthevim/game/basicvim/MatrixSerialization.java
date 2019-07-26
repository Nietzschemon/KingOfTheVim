package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.utils.Json;
import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

import java.util.ArrayList;

public class MatrixSerialization {

    public ArrayList<Properties> savedMatrix;

    public MatrixSerialization(){
        savedMatrix = new ArrayList<>();
    }

    public void saveCurrentMatrix(VimObject vimObject){
        ArrayList<ArrayList<Cell>> cellMatrix = vimObject.getVimMatrix().getCellMatrix();
        Json json = new Json();
        json.setUsePrototypes(false);

        for(ArrayList<Cell> row : cellMatrix){
            for(Cell column : row){

                savedMatrix.add(column.getCellProperties());

            }
        }

        json.toJson(this);

        System.out.println(json.prettyPrint(this));
    }
}
