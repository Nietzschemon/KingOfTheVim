//Stefan Ekblom
//anno 2019-06-22
//github RawNietzsche

package com.kingofthevim.game.basicvim;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


//TODO LATER use properties to store the file paths.
// https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
public class LetterManager extends TagSystem {


    private int rowTotal;
    private int colunmTotal;
    private ArrayList<ArrayList<Cell>> cellMatrix;

    public LetterManager(VimWorldMatrix matrix){

    cellMatrix = matrix.getCellMatrix();

    rowTotal = VimWorldMatrix.rowTotal;
    colunmTotal = VimWorldMatrix.colunmTotal;

    }

    //public void setPropertiesAllChars(char[] propertiesChar, PropObject prop)

    public void createMap(String tagString) {

        createMap(tagString,  LetterType.WHITE, true, true);
    }

    public void createMap(String tagString, LetterType type) {

        createMap(tagString,  type, true, true);
    }

    public void createMap(String tagString, boolean overwrite, LetterType type) {

        createMap(tagString,  type, overwrite, true);
    }

    //TODO set a variable in text that leaves chars intact but changes everything else
    // as it should. i.e. "####" will change every letter property of "word" but not
    // the chars
    //TODO segment into more methods
    /**
     * Takes a string that contains tags and makes a path out of it
     *
     * The path is made by keeping track of the current row and column
     * of which the path ends. Tags are used to for direction and to
     * shift to a new starting point. The path can overwrite existing
     * text if overwrite is true, keep chars if "#" is written in the
     * tag-string and change lettertype.
     *
     * @param tagString the tag-string to be parsed
     * @param defaultType LetterType to use when there is not color tags info
     * @param overwrite if a cell that is occupied should be overwritten
     * @param charKeep if "#" in the tagString should be supplemented for
     *                 the char already occupying the cell
     */
    public void createMap(String tagString,  LetterType defaultType, boolean overwrite, boolean charKeep){

        ArrayList<String> tagSetsArray = new ArrayList<>();

        // counted from where the end of the last string was set

        tagSetsArray = createTagArray(tagString, false);

        for (String string : tagSetsArray){

            String endString;

            ArrayList<String> colorTagArray = new ArrayList<>();
            HashMap<LetterType, Integer> colorIndexChangeMap = new HashMap<>();

            Matcher overrideTagMatcher = overrideTags.matcher(string);
            Matcher startTagMatcher = startTags.matcher(string);
            Matcher colorTagMatcher = colorTagString.matcher(string);

            // checks if object-tag or tag-pair type
            // and then looks for override operator
            if(string.substring(0, 2).equals("<<")){
                overrideOperator = string.substring(4,5);
            }
            else{

                overrideOperator = string.substring(3,4);
            }
            if(overrideOperator.equals("+")
                    || overrideOperator.equals("-")){
                isOverride = true;
            }

            if(isSelfClosingTag(string)) continue;

            implementOverride(string, overrideTagMatcher);

            if(startTagMatcher.find()){ //remove tags and add to cellMatrix

                // trims the current string depending on if it is overridden or not
                if(isOverride) endString = string.substring(7, string.length() - 5);
                else endString = string.substring(4, string.length() - 5);

                System.out.println("curr word " + endString + " at row " + currRow + " colum " + currCol);

                if(string.substring(0, 3).equals("<up")){

                    currRow = currRow - endString.length();//+ 1; // to make it go "up"
                    setVerticalString(endString, currRow, currCol, overwrite, charKeep, defaultType);
                }

                if(string.substring(0, 3).equals("<dw")){

                    setVerticalString(endString, currRow, currCol, overwrite, charKeep, defaultType);
                    currRow += endString.length();
                }

                if(string.substring(0, 3).equals("<lf")){
                    currCol = currCol - endString.length(); // to make it go "left"
                    setHorizontalString(endString, currRow, currCol, overwrite, charKeep, defaultType);
                }

                if(string.substring(0, 3).equals("<rg")){

                    setHorizontalString(endString, currRow, currCol, overwrite, charKeep, defaultType);
                    currCol += endString.length();
                }

                isOverride = false;
            }

        }

        System.out.println("TagSetsArray: " + tagSetsArray);
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
            || cellMatrix.get(startRow).get(startColumn + i).getLetterType() == type){
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

    public void setHorizontalStringArray(ArrayList<String> stringArray, int startRow, int startColumn, boolean overwriteExisting, boolean replaceChar, LetterType type){

        for (int i = 0; i < stringArray.size(); i++) {

            if(stringArray.get(i) != null)
                setHorizontalString(stringArray.get(i), startRow + i, startColumn, overwriteExisting, replaceChar, type);
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
    public ArrayList<String> makeStringArray(String string, boolean breakWords){

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
                returnArray.set(i, string.substring(startIndex));
                break;
            }


            subString = string.substring(startIndex, endIndex);

            if(breakWords){

                lastSpace = Tools.lastSpaceLocation(subString);

                wordRounding = subString.length() - lastSpace;
                subString = subString.substring(0, lastSpace);

                endIndex -= (wordRounding - 1);
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
                        cellMatrix.get(i).get(j).setLetterType(string.charAt(k), type, includeGray);

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

                cellMatrix.get(i).get(j).setLetterType(type);

            }
        }
    }
    /*
    helper method to createMap() for color tags

    method takes currRow and currColumn
    adds string.lengh to either currRow or currColumn as end-points minus tag-lenghts


            use LinkedHashMap, posision = row || column, key = char, value = LetterType

     */
    //TODO set public when done
    private void batchSetLetterType(LinkedHashMap<String, String> colorMap){

        colorMap.forEach((colorTag, letters)-> {

            //batchSetLetterType();
        });
    }




    ///////////////////////// Ideas and non functioning functions /////////////////////////


    //TODO this dosnt work properly with an identical pattern the horVert
    // variable in setNotationstring() as parameter
    private int checkNumOfPattern(String stringToCheck, String patternToFind){

        Pattern pattern = Pattern.compile(patternToFind);

        Matcher patternMatcher = pattern.matcher(stringToCheck);

        int numOfMatches = 0;

        while (patternMatcher.find()) {
            System.out.println("patterns found: " + patternMatcher.find());

            numOfMatches++;
            System.out.println("num of patters found " + numOfMatches);
        }
        return numOfMatches;
    }


    /**
     * Creates a string array with one element per
     * new line.
     *
     * Mainly useful for creating custom backgrounds
     * by editing them slightly in a text editor and
     * then copying them directly into the method as
     * a string.
     * @param string newline separated string.
     */
    //Todo sett public when done
    private void loadLevelFromEscapeString(String string){

        String[] testArray = new String[colunmTotal];

        String exemple = " \n" +
                "Dag 6\n" +
                "    - Skapa metoder för att enklare kunna skapa banor\n" +
                "      - För att skapa en eller flera horisontella eller vertikala rader\n" +
                "    - Skrev en notation för att kunna skriva banor snabbt för hand och senare editor.\n" +
                "      - Notationen är ett simpelt tag-system likt HTML eller XML och fungerar genom regulära uttryck\n" +
                "      - Tog ett tag att få till ett propert regulärt uttryck för detta.\n";

        System.out.println(string.indexOf("\n"));

        for (int i = 0; i < string.length(); i++) {

        }

    }

    //Todo sett public when done
    private void loadLevelFromFile(Stream stream){

    }

}
