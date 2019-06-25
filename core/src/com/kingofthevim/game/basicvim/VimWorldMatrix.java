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


    //TODO make fontsize into an enum that enables a selection of
    // window sizes. OR warn when line is out of play area
    public VimWorldMatrix(int rowTotal, int colunmTotal){
        VimWorldMatrix.rowTotal = rowTotal;
        VimWorldMatrix.colunmTotal = colunmTotal;

        cellMatrix = new Cell[rowTotal][colunmTotal];
        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal
                + " - columns: " + colunmTotal
                + " - Cell width: " + fontWidth
                + " - Cell Height: " + fontHeight);
    }

    public VimWorldMatrix(int fontWidth, int fontHeight, int rowTotal, int colunmTotal){

        cellMatrix = new Cell[rowTotal][colunmTotal];
        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal
                + " - columns: " + colunmTotal
                + " - Cell width: " + fontWidth
                + " - Cell Height: " + fontHeight);
    }


    private void setCellSize(int width, int height){

        for (int i = 0; i < rowTotal; i++) {

            for (int j = 0; j < colunmTotal; j++) {

                cellMatrix[i][j] = new Cell(j * width,i * height);

                System.out.println("cellX: " + (j * width) + " cellY: " + (i * height));
            }
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
