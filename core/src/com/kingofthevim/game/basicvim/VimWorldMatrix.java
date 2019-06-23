package com.kingofthevim.game.basicvim;

import com.kingofthevim.game.KingOfTheVimMain;

/**
 * This organizes the world cells into the VIM world. It sets the
 * number of cells and their sizes. All VIM actions are limitid to
 * happen within the bounds set here by iteraction with the cellMatrix
 */
public abstract class VimWorldMatrix {

    private int rowTotal;
    private int colunmTotal;

    private Cell[][] cellMatrix;

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

        for (int i = 0; i < rowTotal; i++) {

            for (int j = 0; j < colunmTotal; j++) {

                cellMatrix[i][j] = new Cell(j * width, i * height);

                System.out.println("cellX: " + (j * width) + "cellY: " + (i * height));
            }
        }
    }



    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

}
