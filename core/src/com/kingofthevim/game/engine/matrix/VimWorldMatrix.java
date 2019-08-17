package com.kingofthevim.game.engine.matrix;

import com.kingofthevim.game.engine.vim_object.VimObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This organizes the world cells into the VIM world. It sets the
 * number of cells and their sizes. All VIM actions are limited to
 * happen within the bounds set here by interaction with the cellMatrix
 */
public class VimWorldMatrix  {

    private static int rowTotal;
    private static int colunmTotal;
    private static int fontHeight = 66;
    private static int fontWidth = 33;

    public static int getRowTotal() {
        return rowTotal;
    }

    public static int getColunmTotal() {
        return colunmTotal;
    }

    public static int getFontHeight() {
        return fontHeight;
    }

    public static int getFontWidth() {
        return fontWidth;
    }


    private enum Font {
        SIZE64,
        SIZE42
    }

    private static ArrayList<ArrayList<Cell>> cellMatrix = new ArrayList<>();

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


                cellMatrix.get(i).add(new Cell(j * width, i * height, i, j));

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

    /**
     * Returns the Cell from the matrix given by
     * the parameters row and column
     * @param row the row of the cell
     * @param column the column of the cell
     * @return a Cell from the matrix
     */
    public Cell getCell(int row, int column){
        return cellMatrix.get(row).get(column);
    }

    /**
     * Creates a list of strings representation of the matrix
     * @return a list of strings
     */
    public List<String> createStringArray(){
        List<String> list = new ArrayList<>();

        for (int i = 0; i < rowTotal ; i++) {
            list.add(getIndexToRowEndString(i, 0));
        }

        return list;
    }

    /**
     * Gets the position of all words that mach the
     * parameter string. The WordPosition-object has
     * a row-number, a start- and end-column.
     * @param word string to search for in matrix
     * @return a list with all the positions that matched
     * the parameter
     */
    public ArrayList<WordPosition> getWordPositions(String word){
        ArrayList<WordPosition> wordPos = new ArrayList<>();

        List<String> matrix = createStringArray();


        for (int i = 0; i < matrix.size(); i++) {
            int startColumn = matrix.get(i).indexOf(word);

            if(startColumn >= 0){
                int endColumn = startColumn + word.length();
                wordPos.add(new WordPosition(i, startColumn, endColumn));
            }

        }


        return wordPos;
    }


    /**
     * Checks if the vimObject is on a specific
     * word in the matrix.
     * @param vimObject to check if it is on given word
     * @param word to check if vimObject is on
     * @return true if it is on the given word
     */
    public boolean isOnWord(VimObject vimObject, String word){

        ArrayList<WordPosition> wordPos = getWordPositions(word);

        int vimObjRow = vimObject.getPosition().getCurrRow();
        int vimObjColumn = vimObject.getPosition().getCurrColumn();

        for(WordPosition ws : wordPos){

            if(ws.row == vimObjRow){
                if(ws.startColumn <= vimObjColumn && ws.endColumn > vimObjColumn) return true;
            }
        }

        return false;
    }

    public void changeAllCellTypes(LetterType ifType, LetterType changeTo){

        for (ArrayList<Cell> arrayList : cellMatrix){
            for(Cell cell : arrayList){

                if(cell.getLetterType() == ifType){
                    cell.setCellLook(changeTo);
                }
            }
        }
    }


    public void changeAllCellTypes(LetterType ifType, char ifChar, LetterType changeTo){

        for (ArrayList<Cell> arrayList : cellMatrix){
            for(Cell cell : arrayList){

                if(cell.getLetterType() == ifType
                && cell.getCellChar() == ifChar){
                    cell.setCellLook(changeTo);
                }
            }
        }
    }


    public class WordPosition{

        private int row;
        private int startColumn;
        private int endColumn;

        WordPosition(int row, int startColumn, int endColumn){
            this.row = row;
            this.startColumn = startColumn;
            this.endColumn = endColumn;
        }

        public int getRow() {
            return row;
        }

        public int getStartColumn() {
            return startColumn;
        }

        public int getEndColumn() {
            return endColumn;
        }
    }

}
