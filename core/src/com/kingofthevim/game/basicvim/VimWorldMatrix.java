package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

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
    private enum Font {
        SIZE64,
        SIZE42
    }

    static ArrayList<ArrayList<Cell>> cellMatrix = new ArrayList<>();

    //TODO make fontsize into an enum that enables a selection of
    // window sizes. OR warn when line is out of play area
    private VimWorldMatrix(){
        VimWorldMatrix.rowTotal =
        VimWorldMatrix.colunmTotal = colunmTotal;

        //cellMatrix = new Cell[rowTotal][colunmTotal];

        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal
                + " - columns: " + colunmTotal
                + " - Cell width: " + fontWidth
                + " - Cell Height: " + fontHeight);
    }

    public VimWorldMatrix(int rowTotal, int colunmTotal){
        VimWorldMatrix.rowTotal = rowTotal;
        VimWorldMatrix.colunmTotal = colunmTotal;

        //cellMatrix = new Cell[rowTotal][colunmTotal];

        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal
                + " - columns: " + colunmTotal
                + " - Cell width: " + fontWidth
                + " - Cell Height: " + fontHeight);
    }

    public VimWorldMatrix(int rowTotal, int colunmTotal, int fontWidth, int fontHeight){
        VimWorldMatrix.rowTotal = rowTotal;
        VimWorldMatrix.colunmTotal = colunmTotal;
        VimWorldMatrix.fontWidth = fontWidth;
        VimWorldMatrix.fontHeight = fontHeight;

        //cellMatrix = new Cell[rowTotal][colunmTotal];
        setCellSize(fontWidth, fontHeight);

        System.out.println("\nVimMatrix size - rows: " + rowTotal
                + " - columns: " + colunmTotal
                + " - Cell width: " + fontWidth
                + " - Cell Height: " + fontHeight);
    }


    //TODO look into column count vs filled column discrepancy
    private static void setCellSize(int width, int height){

        for (int i = 0; i < rowTotal; i++) {

            cellMatrix.add(new ArrayList<Cell>());

            for (int j = 0; j < colunmTotal; j++) {


                cellMatrix.get(i).add(new Cell(j * width, i * height));

                System.out.println("cellX: " + (j * width) + " cellY: " + (i * height));
            }
        }

    }

    public ArrayList<ArrayList<Cell>> getCellMatrix() {
        return cellMatrix;
    }


}
