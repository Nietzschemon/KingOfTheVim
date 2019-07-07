package com.kingofthevim.game.basicvim;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final LetterType[] letterTypes = LetterType.values();

    /**
     *
     * @param string
     * @param colorTagsArray determines if the returned Array should only contains color tags OR
     *                       only position tags with the color tags left in
     * @return An array of strings split by every tags pair.
     */
    private ArrayList<String> createTagArray(String string, boolean colorTagsArray){

        //Matcher tagPairs = (colorTagsArray)? posisionTagPairs : colorTagPairs;

        return null;
    }

    private LinkedHashMap<LetterType, Integer> createColorMap(Matcher colorTagMatcher, ArrayList<String> colorTagArray, LetterType defaultType){

        LinkedHashMap<LetterType, Integer> colorIndexChangeMap = new LinkedHashMap<>();

        //TODO separate tags into tag-class that LetterManager extends

        /*

        Has the regex for all color tags - set as class variable


        create
            method that gets ranges between all start tags.
                - mutlidimensional
                    - array of pos-tag-pair
                    - color tag pairs

            method that creats colors for all ranges



         */


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


}
