package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.kingofthevim.game.KingOfTheVimMain;

/**
 * This organizes the world cells into the VIM world. It sets the
 * number of cells and their sizes. All VIM actions are limited to
 * happen within the bounds set here by interaction with the cellMatrix
 */
public class VimWorldMatrix {

    static int rowTotal;
    static int colunmTotal;
    static int fontHeight = 66;
    static int fontWidth = 33;

    static Cell[][] cellMatrix;

    //TODO Make a class that sets everything as below
    // that takes font-width/height as a parameters
    public VimWorldMatrix(){
        rowTotal = (KingOfTheVimMain.HEIGHT/fontHeight) - 1;
        colunmTotal = (KingOfTheVimMain.WIDTH/fontWidth) - 1;


        cellMatrix = new Cell[rowTotal][colunmTotal];
        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal + " - columns: " + colunmTotal);
    }


    //TODO make fontsize into an enum that enables a selection of
    // window sizes. OR warn when line is out of play area
    VimWorldMatrix(int fontWidth, int fontHeight){

        rowTotal = (KingOfTheVimMain.HEIGHT/fontHeight) - 1;
        colunmTotal = (KingOfTheVimMain.WIDTH/fontWidth) - 1;


        cellMatrix = new Cell[rowTotal][colunmTotal];
        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal + " - columns: " + colunmTotal);
    }

    VimWorldMatrix(int fontWidth, int fontHeight, int rowTotal, int colunmTotal){

        cellMatrix = new Cell[rowTotal][colunmTotal];
        setCellSize(fontWidth, fontHeight);

    }


    private void setCellSize(int width, int height){

        //makes the inverted axis work with the row
        // ordering in the array.
        int cellYfix = rowTotal;

        for (int i = 0; i < rowTotal; i++) {

            for (int j = 0; j < colunmTotal; j++) {

                cellMatrix[i][j] = new Cell(j * width, cellYfix * height);

                //TODO ändra så y 800 börjar på x 0
                System.out.println("cellX: " + (j * width) + " cellY: " + (cellYfix * height));
            }
            cellYfix--;
        }
    }


    //TODO make get changed textures method for performance
    public Texture[] getMatrixTextures(){

        return null;
    }

    public Cell[] getRow(int row){

        return cellMatrix[row];
    }

    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

}
