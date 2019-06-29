//Stefan Ekblom
//anno 2019-06-22
//github RawNietzsche

package com.kingofthevim.game.basicvim;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

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

    //Pattern bla = Pattern.compile("<S>");
    //Match ds.;
    public static void printMatches(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        // Check all occurrences
        while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end());
            System.out.println(" Found: " + matcher.group());
        }
    }


    private HashMap<String, String> makeStringDirectionMap(String levelString){
        HashMap<String, String> directionMap = new HashMap<>();

        Pattern vertical = Pattern.compile("[VERT]");
        Pattern horizontal = Pattern.compile("[HORI]");

        Matcher vertMatch = vertical.matcher(levelString);
        Matcher horiMatch = horizontal.matcher(levelString);

        int startIndex = levelString.indexOf("<")+1;
        int endIndex = levelString.indexOf(">");

        Matcher isMatch;


        return null;
    }

        /* better for human notation to use [UPP] [DOWN] [LEFT] and [RIGHT]
        use integers to indent with negative one moving it back and positive
        moving it forward. I.E. [UPP-2] or [UPP+2]
        The editor can use [DOWN] and [LEFT] with above number notation exclusively */
        //Todo sett public when done
    private void setHumanNotationstring(String string){

        //TODO set tag in beginnig, remove it in the loop and add at once to
        // cell matrix. i.e [up+2] words words [down-1] words  (use small letters
        // ease of writing).

    }

    //Todo sett public when done
    private void loadLevelFromFile(Stream stream){

    }

    //Todo sett public when done
    private void loadLevelFromEscapeString(String string){

        String exemple = " \n" +
                "Dag 6\n" +
                "    - Skapa metoder för att enklare kunna skapa banor\n" +
                "      - För att skapa en eller flera horisontella eller vertikala rader\n" +
                "    - Skrev en notation för att kunna skriva banor snabbt för hand och senare editor.\n" +
                "      - Notationen är ett simpelt tag-system likt HTML eller XML och fungerar genom regulära uttryck\n" +
                "      - Tog ett tag att få till ett propert regulärt uttryck för detta.\n";
    }


    //TODO make private and part of formatedLevelString() method
    //TODO make newline remover for regular expression to work proper
    public void setNotationString(String string){

        //TODO make pattern set to match and just have one array that cuts
        // up the strings and let the order in the array keep the order
        // in which the adding the strings and the ordering numbers in the
        // tags should be interpreted.
        ArrayList<String> horVertStrings = new ArrayList<>();

        Pattern horVert = Pattern.compile("(<vt[-+]{0,1}(\\d){0,2}>[\\w\\s]*</vt>)|(<hr[-+]{0,1}(\\d){0,2}>[\\w\\s]*</hr>)");

        Matcher horVertMatch = horVert.matcher(string);

        while (horVertMatch.find()){
            System.out.println("HorVert: " + horVertMatch.start());
            System.out.println("HorVert: " + horVertMatch.end());

            horVertStrings.add(string.substring(horVertMatch.start(),
                    horVertMatch.end()));
        }

        System.out.println("ARRAY Combo: " + horVertStrings);


        for (int i = 0; i < horVertStrings.size(); i++) {


        }
    }

    public void setFormatedLevelString(String levelString){

        int[] startIndex = new int[levelString.length()/2];
        int[] endIndex = new int[levelString.length()/2];

        int startIndexCount = 0;
        int endIndexCount = 0;

            System.out.println("indexOf >   " + levelString.indexOf(">"));

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

            System.out.println("row: " + startRow + "  - startcell " + (startCell + i));

            if(cellMatrix.get(startRow + i).get(startCell).getCellLook() != null
                    && ! overwriteExisting){
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



        if(string.length() + startCell > cellMatrix.get(startRow).size()){
            throw new IndexOutOfBoundsException("word will be outside cell matrix");
        }

        int iterations = string.length()-1;
        int charNum = 0;


        for(int i = 0; i <= iterations; i++) {
            char charKey = string.charAt(charNum);

            //overwrite existing cell

            System.out.println("row: " + startRow + "  - startcell " + (startCell + i));

            if(cellMatrix.get(startRow).get(startCell + i).getCellLook() != null
            && ! overwriteExisting){
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
        int endIndex = cellMatrix.get(0).size()-1;

        for (int i = 0; i < cellMatrix.size(); i++) {

            if(startIndex + endIndex > string.length()){
                returnArray[i] = string.substring(startIndex);
                break;
            }

            returnArray[i] = string.substring(startIndex, startIndex + endIndex);

            startIndex = startIndex + endIndex;
            endIndex = cellMatrix.get(i).size()-1;
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
     * @param string
     * @param type
     */
    public void setLetterType(String string, LetterType type, boolean includeGray){

        for (int i = 0; i < cellMatrix.size() ; i++) {

            for (int j = 0; j < cellMatrix.get(i).size() ; j++) {

                for (int k = 0; k < string.length() ; k++) {

                    if(cellMatrix.get(i).get(j).getCellChar() == string.charAt(k) ){
                        cellMatrix.get(i).get(j).setLetterType(string.charAt(k), type, includeGray);

                    }
                }
            }
        }
    }
}
