//Stefan Ekblom
//anno 2019-06-22
//github RawNietzsche

package com.kingofthevim.game.engine.matrix;

import java.util.*;


public class LetterManager {


    private int rowTotal;
    private int colunmTotal;
    private ArrayList<ArrayList<Cell>> cellMatrix;

    public LetterManager(CellMatrix matrix){

    cellMatrix = matrix.getCellMatrix();

    rowTotal = CellMatrix.getRowTotal();
    colunmTotal = CellMatrix.getColunmTotal();

    }



    /**
     * Writes a VERTICAL string at the given location
     *
     * @param string string to be split into chars and written to location
     * @param startRow row at which to start writing
     * @param startColumn column at which to start writing
     * @param overwriteExisting if to jump over occipoed cells or overwrite them
     * @param replaceChar if to keep current cell char when "#" is in string
     * @param type LetterType of the chars written
     */
    public void setVerticalString(String string, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        if(string.length() + startRow > cellMatrix.size()){
            throw new IndexOutOfBoundsException("word will be outside cell matrix");
        }

        int iterations = string.length()-1;
        int charNum = 0;


        for(int i = 0; i <= iterations; i++) {
            char charKey = string.charAt(charNum);

            System.out.println("row: " + (startRow + i) + "  - startcell " + startColumn);

            //TODO make so that letters of the same type are NEVER overwritten
            if((cellMatrix.get(startRow + i).get(startColumn).getCellLook() != null
                    && ! overwriteExisting)
                    || cellMatrix.get(startRow + i).get(startColumn).getLetterType() == type){
                System.out.println("There is already a char there!");
                iterations++;
                continue;
            }

            cellMatrix.get(startRow + i).get(startColumn).setCellLook(charKey, type, true, replaceChar);

            charNum++;
        }
    }

    /**
     * Writes a HORIZONTAL string at the given location
     *
     * @param string string to be split into chars and written to location
     * @param startRow row at which to start writing
     * @param startColumn column at which to start writing
     * @param overwriteExisting if to jump over occipoed cells or overwrite them
     * @param replaceChar if to keep current cell char when "#" is in string
     * @param type LetterType of the chars written
     */
    public void setHorizontalString(String string, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){
        //TODO remove newline in string if it exists

        if(string.length() + startColumn > cellMatrix.get(startRow).size()){
            throw new IndexOutOfBoundsException("word will be outside cell matrix");
        }

        int iterations = string.length()-1;
        int charNum = 0;


        for(int i = 0; i <= iterations; i++) {
            char charKey = string.charAt(charNum);

            //overwrite existing cell
            System.out.println("row: " + startRow + "  - startcell " + (startColumn + i));

            if((cellMatrix.get(startRow).get(startColumn + i).getCellLook() != null
            && ! overwriteExisting)
            && cellMatrix.get(startRow).get(startColumn + i).getLetterType() == type){
                System.out.println("There is already a char there!");
                iterations++;
                continue;
            }

            cellMatrix.get(startRow).get(startColumn + i).setCellLook(charKey, type, true, replaceChar);

            charNum++;
        }
    }

    public void setHorizontalStringArray(String[] stringArray, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        for (int i = 0; i < stringArray.length; i++) {

            if(stringArray[i] != null)
                setHorizontalString(stringArray[i], startRow + i, startColumn, overwriteExisting, replaceChar, type);
        }
    }

    public void setHorizontalStringArray(ArrayList<String> stringArray, int startRow, int startColumn, LetterType type){

        setHorizontalStringArray(stringArray, startRow, startColumn, true, true, type);
    }

    public void setHorizontalStringArray(ArrayList<String> stringArray, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        for (int i = 0; i < stringArray.size(); i++) {

            if(stringArray.get(i) != null)
                setHorizontalString(stringArray.get(i), startRow + i, startColumn, overwriteExisting, replaceChar, type);
        }
    }

    public void setHorizontalStringArray(ArrayList<String> stringArray, int startRow, int rowSpaceBetween, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        for (int i = 0; i < stringArray.size(); i++) {

            if(stringArray.get(i) != null)
                setHorizontalString(stringArray.get(i), startRow + i * rowSpaceBetween, startColumn, overwriteExisting, replaceChar, type);
        }
    }
    public void setVerticalStringArray(String[] stringArray, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        for (int i = 0; i < stringArray.length; i++) {

            if(stringArray[i] != null)
                setVerticalString(stringArray[i], startRow + i, startColumn, overwriteExisting, replaceChar, type);
        }
    }

    public void setVerticalStringArray(ArrayList<String> stringArray, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        for (int i = 0; i < stringArray.size(); i++) {

            if(stringArray.get(i) != null)
                setVerticalString(stringArray.get(i), startRow + i, startColumn, overwriteExisting, replaceChar, type);
        }
    }

    /**
     * Makes a string array that fits the current
     * cellMatrix
     * @param string string to turn to an array
     * @return a fitted string array
     */
    public ArrayList<String> makeStringArray(String string, boolean splitWords){

        ArrayList<String> returnArray = new ArrayList<>();
        String subString;

        int startIndex = 0;
        int endIndex;

        int wordRounding;
        int lastSpace;

        // because we can decide where to break
        string = string.replaceAll("\n", " ");

        for (int i = 0; i < cellMatrix.size(); i++) {


            endIndex = startIndex + cellMatrix.get(i).size();

            if(endIndex > string.length()){
                //returnArray.set(i, string.substring(startIndex));
                returnArray.add(string.substring(startIndex));
                break;
            }


            subString = string.substring(startIndex, endIndex);

            if(! splitWords){

                lastSpace = Tools.lastSpaceLocation(subString);

                if(lastSpace > 0) {

                    wordRounding = subString.length() - lastSpace;
                    subString = subString.substring(0, lastSpace);

                    endIndex -= (wordRounding - 1);
                }
            }


            returnArray.add(subString);
            startIndex = endIndex;
        }


        return returnArray;
    }



    //TODO make public when made ready
    private String[] makeStringArray(String string, int stopAtColumn){

        return null;
    }
    private String[] makeStringArray(String string, int stopAtColumn, int startAtColumn){//startAt puts in spaces

        return null;
    }

    /**
     * Sets letter types for all rows matching any char in
     * the given string.
     * @param string chars to be colored
     * @param type color to change cars to
     * @param includeGray if gray letters should be colored too
     */
    public void batchSetLetterType(String string, LetterType type, boolean includeGray){

        batchSetLetterType(string, type, includeGray, 0, cellMatrix.size(), 0, cellMatrix.get(0).size());
    }


    public void batchSetLetterType(String string, LetterType type, boolean includeGray, int startRow, int endRow, int startColumn, int endColumn){

        if(cellMatrix.size() < endRow) throw new IndexOutOfBoundsException("endRow of " + string + " is outside matrix");

        for (int i = startRow; i < endRow ; i++) {

            if(cellMatrix.get(i).size() < endColumn) throw new IndexOutOfBoundsException("endColumn of " + string + " is outside matrix");

            for (int j = startColumn; j < endColumn ; j++) {

                for (int k = 0; k < string.length() ; k++) {

                    if(cellMatrix.get(i).get(j).getCellChar() == string.charAt(k) ){
                        cellMatrix.get(i).get(j).setCellLook(string.charAt(k), type, includeGray);

                    }
                }
            }
        }
    }

    /**
     * Sets letter types for all chars within the
     * limits given. This method effectivly can
     * batch color any shape of tetragon in the text
     * @param type color to change cars to
     * @param startRow color to type from this row
     * @param endRow color until this row
     * @param startColumn color from this column in every given row
     * @param endColumn until this column
     */
    public void batchSetLetterType(LetterType type, int startRow, int endRow, int startColumn, int endColumn){

        if(cellMatrix.size() < endRow) throw new IndexOutOfBoundsException("endRow of is outside matrix");

        for (int i = startRow; i <= endRow ; i++) {

            if(cellMatrix.get(i).size() < endColumn) throw new IndexOutOfBoundsException("endColumn of is outside matrix");

            for (int j = startColumn; j <= endColumn ; j++) {

                cellMatrix.get(i).get(j).setCellLook(type);

            }
        }
    }
}
