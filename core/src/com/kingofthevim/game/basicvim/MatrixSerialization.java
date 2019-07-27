package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.utils.Json;
import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

import java.util.ArrayList;

public class MatrixSerialization {

    Json json = new Json();
    public static String jsonString;
    Save save;

    public MatrixSerialization(){
        save = new Save();
        json.setUsePrototypes(false);
    }


    private static class Save {


        private ArrayList<ArrayList<Properties>> savedMatrix;
        private int cursorRow;
        private int cursorColumn;

        Save(){
            savedMatrix = new ArrayList<>();
        }


        void copyCurrentMatrix(VimObject vimObject){
            ArrayList<ArrayList<Cell>> cellMatrix = vimObject.getVimMatrix().getCellMatrix();

            for (int i = 0; i < cellMatrix.size(); i++) {

                savedMatrix.add(i, new ArrayList<>());

                for (int j = 0; j < cellMatrix.get(i).size(); j++) {
                    Cell column = cellMatrix.get(i).get(j);

                    savedMatrix.get(i).add(column.getCellProperties());

                }
            }
        }
        void copyObjectPosition(VimObject vimObject){
            cursorRow = vimObject.getPosition().getCurrRow();
            cursorColumn = vimObject.getPosition().getCurrColumn();

        }
    }

    public void saveAll(VimObject vimObject){
        save.copyCurrentMatrix(vimObject);
        save.copyObjectPosition(vimObject);

        jsonString = json.toJson(save);
    }

    public void saveObj(VimObject vimObject){
        save.copyObjectPosition(vimObject);

        jsonString = json.toJson(save);
    }

    public void loadMatrix(VimObject vimObject, String string){

        ArrayList<ArrayList<Cell>> cellMatrix = vimObject.getVimMatrix().getCellMatrix();

        Save loadList = json.fromJson(Save.class, string);

        //FileHandle fileHandle = new FileHandle();
        ArrayList<ArrayList<Properties>> propList = loadList.savedMatrix;

        for (int i = 0; i < cellMatrix.size(); i++) {

            for (int j = 0; j < cellMatrix.get(i).size(); j++) {

                System.out.println("old row: " + vimObject.getVimMatrix().getCellMatrix().get(i).get(j).getCellProperties().getRow());
                System.out.println("old column: " + vimObject.getVimMatrix().getCellMatrix().get(i).get(j).getCellProperties().getColumn());

                vimObject.getVimMatrix().getCellMatrix().get(i).get(j).setCellProperties(propList.get(i).get(j));

                System.out.println("new row: " + propList.get(i).get(j).getRow());
                System.out.println("new column: " + propList.get(i).get(j).getColumn());

            }
        }

    }

    public void loadAll(VimObject vimObject, String jsonString){
        loadMatrix(vimObject, jsonString);
        loadObject(vimObject, jsonString);
    }

    public void loadObject(VimObject vimObject, String string){
        Save loadList = json.fromJson(Save.class, string);
        vimObject.getPosition().setAbsoluteColumn(loadList.cursorColumn);
        vimObject.getPosition().setAbsoluteRow(loadList.cursorRow);
    }

}
