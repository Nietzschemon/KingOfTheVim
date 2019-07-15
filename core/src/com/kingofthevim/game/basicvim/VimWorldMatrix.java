package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * This organizes the world cells into the VIM world. It sets the
 * number of cells and their sizes. All VIM actions are limited to
 * happen within the bounds set here by interaction with the cellMatrix
 */
public class VimWorldMatrix implements VimObject {

    static int rowTotal;
    static int colunmTotal;
    static int fontHeight = 66;
    static int fontWidth = 33;

    @Override
    public VimWorldMatrix getVimMatrix() {
        return this;
    }

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

        cellMatrix.clear();

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

    /**
     * creates a string of the given rows chars
     * from the startIndex to the end of the row
     * @param row from which row to create the string
     * @param startIndex from where in the row to start the string creation
     * @return a precut string from the index to the end of the given row
     */
    public String getIndexToRowEndString(int row, int startIndex){

        StringBuilder rowString = new StringBuilder();

        for (Cell letter : cellMatrix.get(row)){

            if(letter.getCellChar() > 0){
                rowString.append((letter.getCellChar()));
            }
        }

        rowString = new StringBuilder(rowString.substring(startIndex));

        return rowString.toString();
    }

    /**
     * creates a string of the given rows chars
     * from the startIndex to the end of the row
     * @param row from which row to create the string
     * @param startIndex from where in the row to start the string creation
     * @return a precut string from the index to the end of the given row
     */
    public String getStringIndexToRowBeginning(int row, int startIndex, boolean reverseString){

        StringBuilder rowString = new StringBuilder();

        if(reverseString){

            for (int i = startIndex; i >= 0; i--) {
                if(cellMatrix.get(row).get(i).getCellChar() > 0){
                    rowString.append((cellMatrix.get(row).get(i).getCellChar()));
                }
            }
        }
        else{
            for (Cell letter : cellMatrix.get(row)){

                if(letter.getCellChar() > 0){
                    rowString.append((letter.getCellChar()));
                }
            }
        }

        rowString = new StringBuilder(rowString.substring(0, startIndex));

        return rowString.toString();
    }


}
