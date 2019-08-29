package com.kingofthevim.game.engine.matrix;

import java.util.ArrayList;

public enum LetterType {
    BLACK("b"),
    BLACK_YELLOW("b_y"),
    RED("r"),
    GREEN("g_1"),
    GRAY("g_2"),
    WHITE("w"),
    WHITE_GREEN("w_g"),
    WHITE_RED("w_r"),
    WHITE_PURPLE("w_p"),
    YELLOW("y"),
    EMPATHY("e");

    private final String tagName;

    public static ArrayList<LetterType> getMatched(char tagPrefixChar){

        LetterType[] typeArray = LetterType.values();
        ArrayList<LetterType> returnList = new ArrayList<>();

        for(LetterType type : typeArray){
            if(type.tagName.charAt(0) == tagPrefixChar){
                returnList.add(type);
            }
        }

        return returnList;
    }

    LetterType(String tag){
        tagName = tag;
    }

    public String getTagName() {
        return tagName;
    }
}
