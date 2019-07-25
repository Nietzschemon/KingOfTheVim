package com.kingofthevim.game.basicvim.Matrix;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kingofthevim.game.basicvim.Matrix.Tools.charIsLetter;
import static com.kingofthevim.game.basicvim.Matrix.Tools.tryParseInt;

/*
Syntax <direction> <color>  words <<thing>> wor</color>ds </direction>

direction - up <up>, down <dw>, right <rg> or left <lf> to counted from the end of
            the last string entered. Also coulmn and row can
            be addressed with <<cl>> and <<rw>>

color     - The game colors of letters with their properties red <r>,
            white <w>, yellow <y> and grey <g>.

thing     - a game object to leave at the current cell. (e.g. a dynamite)


overriding the default starting position of the tag
is done with + or - and a number in the start tag <tag+/-N>

 */
public class TagSystem {

    int currRow = 0;
    int currCol = 0;
    boolean isOverride = false;
    int overrideNum = 0;
    String overrideOperator;

    static final LetterType[] letterTypes = LetterType.values();

    //<editor-fold desc="Patterns">

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
                    "|(<e>(.+?)</e>)" + //empty
                    "|(<g>(.+?)</g>)" + //gray
                    "|(<r>(.+?)</r>)" + //red
                    "|(<p>(.+?)</p>)" + //purple
                    "|(<y>(.+?)</y>)" + //yellow
                    "|(<w>(.+?)</w>)" + //white
                    "|(<b\\+[egrpyw]>(.+?)</b>)" + //black with color background
                    "|(<g\\+[berpyw]>(.+?)</g>)" +
                    "|(<r\\+[begpyw]>(.+?)</r>)" +
                    "|(<p\\+[begryw]>(.+?)</p>)" +
                    "|(<y\\+[begrpw]>(.+?)</y>)" +
                    "|(<w\\+[begrpy]>(.+?)</w>)");

    Pattern colorStartTags = Pattern.compile("(<[begrpyw]\\+[begrpyw]>)");

    Pattern endTags = Pattern.compile(
            "(</up>)" +
                    "|(</dw>)" +
                    "|(</lf>)" +
                    "|(</rg>)");

    //</editor-fold desc="Patterns for tag-methods">

         // TODO implement
        /*

        Has the regex for all color tags - set as class variable

        create
            method that gets ranges between all start tags.
                - mutlidimensional
                    - array of pos-tag-pair
                    - color tag pairs

            method that creats colors for all ranges
         */
    /**
     *
     * @param tagString a string containing position and self-closing tags or color-tags
     * @param isColorTagsArray determines if the returned Array should only contains color tags OR
     *                       only position tags with the color tags left in
     * @return An array of strings split by every tags pair.
     */
    protected ArrayList<String> createTagArray(String tagString, boolean isColorTagsArray){

        ArrayList<String> tagSetsArray = new ArrayList<>();

        Matcher tagSetMatcher = (isColorTagsArray) ? colorTagString.matcher(tagString) : wholeTagString.matcher(tagString);

        while (tagSetMatcher.find()){

            tagSetsArray.add(tagSetMatcher.group());
        }

        return tagSetsArray;
    }


    //TODO maybe an array of numbers?
    protected LinkedHashMap<Integer, String> createRangeMap(){

        return  null;
    }

    protected LinkedHashMap<LetterType, Integer> createColorMap(Matcher colorTagMatcher,
                                                                ArrayList<String> colorTagArray,
                                                                LetterType defaultType){

        LinkedHashMap<LetterType, Integer> colorIndexChangeMap = new LinkedHashMap<>();

        while (colorTagMatcher.find()){

            colorTagArray.add(colorTagMatcher.group());
        }
        //Todo get first range bewtween posistion-tag and first color-tag
        // and end-tags to starttags i.e </endTag> ... <startTag>

        for (String colorTagStr : colorTagArray) {

            String bgColor = colorTagStr.substring(2,3);
            String strippedColorStr;
            boolean hasBgColor = false;
            LetterType color = defaultType;

            //store ranges between tags and remove tags

            //TODO implement
            if(bgColor.equals("+")){
                hasBgColor = true;
            }

            if(hasBgColor){

                    /*
                    check for first letter in enum name
                    match <char> against first letter/name
                     */
                //LetterType.GRAY.name();

            }
            else{
                strippedColorStr = colorTagStr.substring(3, colorTagStr.length()-4);
            }


            for (LetterType letter : letterTypes){

                System.out.println("LetterType tag: " + letter.getTagName());

                //matches color-tag
                if(letter.getTagName().equals(colorTagStr.substring(1,2))){

                    System.out.println("Match for " + letter);
                    color = letter;
                    break;
                }
            }

            colorIndexChangeMap.put(color, colorTagStr.length()-4);

            //remove tags method

                /*
                <sPosTag> defaultColorRange <colorTag> TagColorRange </colorTag> defaultColorRange <colorTag>

                put ranges in Map<LetterType, Integer> colorMap


                colorstr.lenght

                subString(0,3);
                subString(colorStr.lenght-4);
                 */


            hasBgColor = false;

        }

        return colorIndexChangeMap;
    }


    /**
     * Identifes a self closing-tag in a string, updates the row and/or column
     * index and returns true. If no tag is found it does nothing and returns false
     * @param string A string that contains a self-closing tag
     * @return true if a self-closing tag is found and false if not
     */
    protected boolean isSelfClosingTag(String string){
        if(string.substring(0,4).equals("<<cl")){
            System.out.println("\nCell position override \nOld position \nrow " + currRow + " - column " + currCol);
            currRow = Integer.parseInt(string.substring(4, 6));
            currCol = Integer.parseInt(string.substring(7, 9));

            System.out.println("new position \nrow " + currRow + " - column " + currCol + "\n");

            return true;
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
            return true;
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
            return true;
        }

        return false;
    }

    protected void implementOverride(String tagString, Matcher overrideTagMatcher){

        if(overrideTagMatcher.find()){

            overrideNum = Integer.parseInt(tagString.substring(4,6));

            if(tagString.substring(0,3).equals("<up")
                    || tagString.substring(0,3).equals("<dw")){

                currRow = ( overrideOperator.equals("+") ? currRow + overrideNum : currRow - overrideNum);
            }
            else{

                currCol = ( overrideOperator.equals("+") ? currCol + overrideNum : currCol - overrideNum);
            }


            isOverride = true;
        }

    }

    public ArrayList<String> makeCellTagsOfCurrentMatrix(VimWorldMatrix matrix){
        ArrayList<ArrayList<Cell>> cellMatrix = matrix.getCellMatrix();

        ArrayList<String> tagArray = new ArrayList<>();

        for (int i = 0; i < cellMatrix.size() - 1; i++) {

            for (int j = 0; j < cellMatrix.get(i).size() - 1; j++) {

                char cellChar = cellMatrix.get(i).get(j).getCellProperties().cellChar;
                String letterType = cellMatrix.get(i).get(j).getCellProperties().letterType.getTagName();

                //System.out.println("<<|" + i + "|" + j + "|" + cellChar + "|" + letterType + "|>>");

                tagArray.add("<<||r:" + i + "|c:" + j + "|l:" + cellChar +"|t:" + letterType + "||>>");

            }
        }

        tagArray.add("<<||r:" + cellMatrix.size() + "|c:" + cellMatrix.get(0).size() + "|f:fontsize||>>");

        //buildMapFromCellTags(tagArray);

        System.out.println("TagArray: " + tagArray);
        return tagArray;
    }

    //TODO delete if unused
    public CellTag readCellTag(String cellTagStr){

        ArrayList<ArrayList<Cell>> cellMatrix = new ArrayList<>();
        String iterationString = "0";
        int totalRows = 0;
        int totalColums = 0;
        CellTag cellTag = new CellTag();

        char[] charArray = cellTagStr.toCharArray();

        boolean identifier = false;

        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char c = charArray[i];

            if (c > 47 &&
                    c < 58) {
                iterationString += String.valueOf(c);

            }
            if (charArray[i] == ':' && charArray[i + 1] == 'r') {
                totalRows = tryParseInt(iterationString);
                iterationString = "0";
            }
            if (charArray[i] == ':' && charArray[i + 1] == 'c') {
                totalColums  = tryParseInt(iterationString);
                iterationString = "0";
            }

        }

        return cellTag;
    }

    public ArrayList<CellSubTag> createSubTagArray(String cellTag){

        char[] cellTagChars = cellTag.toCharArray();
        char identifier = ' ';
        int startPosition = 0;
        int endPosition = 0;
        ArrayList<CellSubTag> subTags = new ArrayList<>();

        for (int i = 0; i < cellTagChars.length; i++) {

            if(cellTagChars[i + 1] == ':'
                    && charIsLetter(cellTagChars[i])){
                identifier = cellTagChars[i];
                startPosition = i + 2;
            }

            if(cellTagChars[i] == '|'
                    && cellTagChars[i - 1] != '|'){

                endPosition = i + 1;
            }

            if(identifier != ' '
                    && startPosition != 0
                    && endPosition != 0){

                subTags.add(new CellSubTag(identifier, startPosition, endPosition));

                identifier = ' ';
                startPosition = 0;
                endPosition = 0;
            }

        }

        return subTags;
    }

    public CellTag createCellTag(String cellTagStr){

        int row = 0;
        int column = 0;
        LetterType letterType = LetterType.EMPATHY;
        char letter = ' ';

        ArrayList<CellSubTag> cellSubTags = createSubTagArray(cellTagStr);

        for(CellSubTag sTag : cellSubTags){

            if(sTag.identifier == 'r'){

                row = Tools.tryParseInt(
                        cellTagStr.substring(
                                sTag.startPosition,
                                sTag.endPosition));
            }

            if(sTag.identifier == 'c'){

                column = Tools.tryParseInt(
                        cellTagStr.substring(
                                sTag.startPosition,
                                sTag.endPosition));
            }
        }



        return new CellTag(row, column, letterType, letter);
    }



    public ArrayList<ArrayList<Cell>> buildCellArray(ArrayList<String> cellTagArray){


        ArrayList<ArrayList<Cell>> cellMatrix = new ArrayList<>();
        String iterationString = "0";
        int totalRows = 0;
        int totalColums = 0;

        char[] charArray = cellTagArray.get(cellTagArray.size() - 1).toCharArray();

        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char c = charArray[i];

            if (c > 47 &&
                    c < 58) {
                iterationString += String.valueOf(c);

            }
            if (charArray[i] == ':' && charArray[i + 1] == 'r') {
                totalRows = tryParseInt(iterationString);
                iterationString = "0";
            }
            if (charArray[i] == ':' && charArray[i + 1] == 'c') {
                totalColums  = tryParseInt(iterationString);
                iterationString = "0";
            }

        }

        System.out.println("total row: " + totalRows + " - Total columns: " + totalColums);

        cellMatrix.ensureCapacity(totalRows);//TODO get value from last tag that contains all metadata

        for (ArrayList<Cell> p : cellMatrix) {
            p.ensureCapacity(totalColums);
        }

        return cellMatrix;
    }

    //TODO searchForSubTag() -> when subtag found break and return value

    public void buildMapFromCellTags(ArrayList<String> cellTagArray){


        ArrayList<ArrayList<Cell>> cellMatrix = buildCellArray(cellTagArray);


        for (int i = 0; i < cellMatrix.size(); i++) {

            char[] charTag = cellTagArray.get(i).toCharArray();

            char cellChar = '0';
            LetterType letterType = LetterType.EMPATHY;

            for (int j = 0; j < cellMatrix.get(i).size(); j++) {


                if (charTag[j] == ':' && charTag[j + 1] == 'l') {
                    cellChar = charTag[j - 1];
                }

                if (charTag[j] == ':' && charTag[j + 1] == 't') {
                    //letterType = LetterType.valueOf(charTag[j - 1]);
                }


                cellMatrix.get(i).set(j, new Cell(cellChar, letterType));

                //System.out.println("<<|" + i + "|" + j + "|" + cellChar + "|" + letterType + "|>>");

            }
        }

        System.out.println("cellMatrix: " + cellMatrix);
    }


    //TODO put the cell speficic tag-interpreting methods in cell
    // and make the constructor take a tag-string and depoly them
    // in constructor
    private class CellTag{

        int totalRows;


        int totalColumns;
        int rows;
        int columns;
        LetterType letterType;
        char letter;

        public CellTag(){}

        public CellTag(int totalRows, int totalColumns){
            this.totalRows = totalRows;
            this.totalColumns = totalColumns;
        }
        public CellTag(int rows, int columns, LetterType letterType, char letter) {
            this.rows = rows;
            this.columns = columns;
            this.letterType = letterType;
            this.letter = letter;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private class CellSubTag{
        char identifier;
        int startPosition;
        int endPosition;

        public CellSubTag(char identifier, int startPosition, int endPosition) {
            this.identifier = identifier;
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

    }
}
