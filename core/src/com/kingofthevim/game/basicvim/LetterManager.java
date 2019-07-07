//Stefan Ekblom
//anno 2019-06-22
//github RawNietzsche

package com.kingofthevim.game.basicvim;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.text.ParsePosition;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


//TODO LATER use properties to store the file paths.
// https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
public class LetterManager {

    // We only need one of these

    // For larger movements and other things
    private static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    private int rowTotal;
    private int colunmTotal;
    private ArrayList<ArrayList<Cell>> cellMatrix;

    public LetterManager(VimWorldMatrix matrix){

    cellMatrix = matrix.getCellMatrix();

    rowTotal = VimWorldMatrix.rowTotal;
    colunmTotal = VimWorldMatrix.colunmTotal;

    }

    //private void setWordProperties(int row, int rowCell, boolean isBad, boolean isGood)
    //public void setPropertiesAllChars(char[] propertiesChar, PropObject prop)

    /*
    Syntax <direction> <color>  words <<thing>> wor</color>ds </direction>

    direction - up <up>, down <dw>, right <rg> or left <lf> to counted from the end of
                the last string entered. Also coulmn and row can
                be addressed with <cl> and <rw>

    color     - The game colors of letters with their properties red <r>,
                white <w>, yellow <y> and grey <g>.

    thing     - a game object to leave at the current cell. (e.g. a dynamite)


    overriding the default starting posistion of the tag
    is done with + or - and a number in the start tag <tag+/-N>

     */
    public void createMap(String tagString) {

        createMap(tagString, true, LetterType.WHITE);
    }

    //TODO stupid long method to just get the map-automation done. CHANGE!!!
    public void createMap(String tagString, boolean overwrite, LetterType type){

        LinkedHashMap<String, String> impMap = new LinkedHashMap<>();
        ArrayList<String> tagSetsArray = new ArrayList<>();

        // counted from where the end of the last string was set
        int currRow = 0;
        int currCol = 0;
        boolean isOverride = false;
        int overrideNum = 0;

        //"(<cl(\\d){2}(,)+(\\d){2}>(.+?)</cl>)" + // cell
        // whole  start and close tag-combo
        Pattern wholeTagString = Pattern.compile(
                        "(<<cl(\\d){2}(,)+(\\d){2}>>)" + // cell
                        "|(<<rw[-+]{1}(\\d){2}>>)" + // row
                        "|(<<rw(\\d){2}>>)" + // non override version
                        "|(<<co[-+]{1}(\\d){2}>>)" + // column
                        "|(<<co(\\d){2}>>)" +
                        "|(<up[-+]{1}(\\d){2}>(.+?)</up>)" + // up (duh)
                        "|(<up>(.+?)</up>)" +
                        "|(<dw[-+]{1}(\\d){2}>(.+?)</dw>)" +// down
                        "|(<dw>(.+?)</dw>)" +
                        "|(<lf[-+]{1}(\\d){2}>(.+?)</lf>)" +// left
                        "|(<lf>(.+?)</lf>)" +
                        "|(<rg[-+]{1}(\\d){2}>(.+?)</rg>)" +// right
                        "|(<rg>(.+?)</rg>)");

        // detects if start tags contain extra info to override default
        Pattern overrideTags = Pattern.compile(
                        "(<up[-+]{1}(\\d){2}>)" +
                        "|(<dw[-+]{1}(\\d){2}>)" +
                        "|(<lf[-+]{1}(\\d){2}>)" +
                        "|(<rg[-+]{1}(\\d){2}>)");

        // any start tag
        Pattern startTags = Pattern.compile(
                        "(<up[-+]{1}(\\d){2}>)" +
                        "|(<up>(.+?)</up>)" +
                        "|(<dw[-+]{1}(\\d){2}>)" +
                        "|(<dw>)" +
                        "|(<lf[-+]{1}(\\d){2}>)" +
                        "|(<lf>)" +
                        "|(<rg[-+]{1}(\\d){2}>)" +
                        "|(<rg>)");

        Pattern colorTagString = Pattern.compile(
                        "(<b>(.+?)</b>)" + //black
                        "(<e>(.+?)</e>)" + //empty
                        "(<g>(.+?)</g>)" + //gray
                        "(<r>(.+?)</r>)" + //red
                        "(<p>(.+?)</p>)" + //purple
                        "(<y>(.+?)</y>)" + //yellow
                        "(<w>(.+?)</w>)" + //white
                        "(<b\\+[egrpyw]>(.+?)</b>)" + //black with color background
                        "(<g\\+[berpyw]>(.+?)</g>)" +
                        "(<r\\+[begpyw]>(.+?)</r>)" +
                        "(<p\\+[begryw]>(.+?)</p>)" +
                        "(<y\\+[begrpw]>(.+?)</y>)" +
                        "(<w\\+[begrpy]>(.+?)</w>)");

        Pattern colorStartTags = Pattern.compile("(<[begrpyw]\\+[begrpyw]>)");

        /*
        Pattern endTags = Pattern.compile(
                        "</up>)" +
                        "|(</dw>)" +
                        "|(</lf>)" +
                        "|(</rg>)");
        Matcher endTagMatcher = endTags.matcher(tagString);
        */

        Matcher tagSetMatcher = wholeTagString.matcher(tagString);


        while (tagSetMatcher.find()){

            tagSetsArray.add(tagSetMatcher.group());
        }



        for (String string : tagSetsArray){

            String endString;

            String overrideOperator;

            Matcher overrideTagMatcher = overrideTags.matcher(string);
            Matcher startTagMatcher = startTags.matcher(string);
            //Matcher colorTagMatcher = colorTagString.matcher(string);

            /*
            while (colorTagMatcher.find()){

            //TODO Remove color tags here with method and put changes in list/map to execute later


            store default color
            get string.length from end posi-start-tag to start of color-start-tag
            store range with default color

            loop
                get color from start-tag
                rm start-tag
                get length of string to end-tag




            currCol/currRow - endstring.lenght


            use LinkedHashMap, posision = row || column, key = char, value = LetterType

            }

             */

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


            if(string.substring(0,4).equals("<<cl")){
                System.out.println("\nCell position override \nOld position \nrow " + currRow + " - column " + currCol);
                currRow = Integer.parseInt(string.substring(4, 6));
                currCol = Integer.parseInt(string.substring(7, 9));

                System.out.println("new position \nrow " + currRow + " - column " + currCol + "\n");

                continue;
            }

            if(string.substring(0,4).equals("<<co")){

                System.out.println("\nColumn position override. \nOld position \nrow " + currRow + " - column " + currCol);

                if(isOverride){
                    overrideNum = Integer.parseInt(string.substring(5, 7));
                    currCol = ( overrideOperator.equals("+") ? currCol + overrideNum : currCol - overrideNum);
                }
                else
                {
                    currCol = Integer.parseInt(string.substring(4, 6));
                }

                System.out.println("new position \nrow " + currRow + " - column " + currCol + "\n");

                isOverride = false;
                continue;
            }

            if(string.substring(0,4).equals("<<rw")){

                System.out.println("\nRow position override \nOld position \nrow " + currRow + " - column " + currCol);

                if(isOverride){
                    overrideNum = Integer.parseInt(string.substring(5, 7));
                    currRow = ( overrideOperator.equals("+") ? currRow + overrideNum : currRow - overrideNum);
                }
                else
                {
                    currRow = Integer.parseInt(string.substring(4, 6));
                }


                System.out.println("new position \nrow " + currRow + " - column " + currCol + "\n");

                isOverride = false;
                continue;
            }




            //TODO this should be used for checking overrides for the above too
            if(overrideTagMatcher.find()){

                overrideNum = Integer.parseInt(string.substring(4,6));

                if(string.substring(0,3).equals("<up")
                || string.substring(0,3).equals("<dw")){

                    currRow = ( overrideOperator.equals("+") ? currRow + overrideNum : currRow - overrideNum);
                }
                else{

                   currCol = ( overrideOperator.equals("+") ? currCol + overrideNum : currCol - overrideNum);
                }


                isOverride = true;
            }

            if(startTagMatcher.find()){ //remove tags and add to cellMatrix

                // trims the current string depending on if it is overridden or not
                if(isOverride) endString = string.substring(7, string.length() - 5);
                else endString = string.substring(4, string.length() - 5);

                System.out.println("curr word " + endString + " at row " + currRow + " colum " + currCol);

                if(string.substring(0, 3).equals("<up")){

                    currRow = currRow - endString.length();//+ 1; // to make it go "up"
                    setVerticalString(endString, currRow, currCol, overwrite, type);
                }

                if(string.substring(0, 3).equals("<dw")){

                    setVerticalString(endString, currRow, currCol, overwrite, type);
                    currRow += endString.length();
                }

                if(string.substring(0, 3).equals("<lf")){
                    currCol = currCol - endString.length(); // to make it go "left"
                    setHorizontalString(endString, currRow, currCol, overwrite, type);
                }

                if(string.substring(0, 3).equals("<rg")){

                    setHorizontalString(endString, currRow, currCol, overwrite, type);
                    currCol += endString.length();
                }

                isOverride = false;
            }



        }

        System.out.println("TagSetsArray: " + tagSetsArray);
    }



    public void setVerticalString(String string, int startRow, int startCell, boolean overwriteExisting, LetterType type){

        if(string.length() + startRow > cellMatrix.size()){
            throw new IndexOutOfBoundsException("word will be outside cell matrix");
        }

        int iterations = string.length()-1;
        int charNum = 0;


        for(int i = 0; i <= iterations; i++) {
            char charKey = string.charAt(charNum);

            //overwrite existing cell

            System.out.println("row: " + (startRow + i) + "  - startcell " + startCell);

            //TODO make so that letters of the same type are NEVER overwritten
            if((cellMatrix.get(startRow + i).get(startCell).getCellLook() != null
                    && ! overwriteExisting)
                    || cellMatrix.get(startRow + i).get(startCell).getLetterType() == type){
                System.out.println("There is already a char there!");
                iterations++;
                continue;
            }

            cellMatrix.get(startRow + i).get(startCell).setCellLook(charKey, type);

            charNum++;
        }
    }

    /**
     * Writes a string at the given location.
     * @param string The string to be written
     * @param startRow At what row the string should be written
     * @param startCell At what cell in the row the string should start from
     * @param overwriteExisting If existing chars should be jumped or written over
     */
    public void setHorizontalString(String string, int startRow, int startCell, boolean overwriteExisting, LetterType type){
        //TODO remove newline in string if it exists



        if(string.length() + startCell > cellMatrix.get(startRow).size()){
            throw new IndexOutOfBoundsException("word will be outside cell matrix");
        }

        int iterations = string.length()-1;
        int charNum = 0;


        for(int i = 0; i <= iterations; i++) {
            char charKey = string.charAt(charNum);

            //overwrite existing cell

            System.out.println("row: " + startRow + "  - startcell " + (startCell + i));

            if((cellMatrix.get(startRow).get(startCell + i).getCellLook() != null
            && ! overwriteExisting)
            || cellMatrix.get(startRow).get(startCell + i).getLetterType() == type){
                System.out.println("There is already a char there!");
                iterations++;
                continue;
            }

            cellMatrix.get(startRow).get(startCell + i).setCellLook(charKey, type);

            charNum++;
        }
    }

    public void setHorizontalStringArray(String[] stringArray, int startRow, int startColumn, boolean overwriteExisting, LetterType type){

        for (int i = 0; i < stringArray.length; i++) {

            if(stringArray[i] != null)
                setHorizontalString(stringArray[i], startRow + i, startColumn, overwriteExisting, type);
        }
    }

    public void setVerticalStringArray(String[] stringArray, int startRow, int startColumn, boolean overwriteExisting, LetterType type){

        for (int i = 0; i < stringArray.length; i++) {

            if(stringArray[i] != null)
                setVerticalString(stringArray[i], startRow + i, startColumn, overwriteExisting, type);
        }
    }

    /*TODO Level maker
     make methods
        - addBackgroundText()

        - textLevelMaker(String string, boolean fatWordsLevel, boolean )
                string - everything not covered bellow gets grayed
                fatWordsLevel - the level words: chars gets nomralized
                starsBad - letters between stars gets marked as bad (*word*)
                starsGood - letters between two ~ gets marked as good (~~wo~~rd)
        - setPoints(LetterType type, int points)
        - setPoints(LetterType type, int row, int points)
        - setPoints(LetterType type, String chars, int points)
        - setPoints(LetterType type, String chars, int row, int points)
        EVERY EVENT has an analogous plethora of signatures as in setPoints above.
     */

    /**
     * Makes a string array that fits the current
     * cellmatrix
     * @param string string to turn to an array
     * @return a fitted string array
     */
    public String[] makeStringArray(String string){

        String[] returnArray = new String[rowTotal];

        int startIndex = 0;
        int endIndex = cellMatrix.get(0).size();

        for (int i = 0; i < cellMatrix.size(); i++) {

            if(startIndex + endIndex > string.length()){
                returnArray[i] = string.substring(startIndex);
                break;
            }

            returnArray[i] = string.substring(startIndex, startIndex + endIndex);

            startIndex = startIndex + endIndex;
            endIndex = cellMatrix.get(i).size();
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
    public void setLetterType(String string, LetterType type, boolean includeGray){

        setLetterType(string, type, includeGray, 0, cellMatrix.size(), 0, cellMatrix.get(0).size());
    }


    public void setLetterType(String string, LetterType type, boolean includeGray, int startRow, int endRow, int startColumn, int endColumn){

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
    public void setLetterType(LetterType type, int startRow, int endRow, int startColumn, int endColumn){

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
    private void setLetterType(LinkedHashMap<String, String> colorMap){

        colorMap.forEach((colorTag, letters)-> {

            //setLetterType();
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
