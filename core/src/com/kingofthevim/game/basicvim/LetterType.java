package com.kingofthevim.game.basicvim;

public enum LetterType {
    RED("r"),
    GRAY("g"),
    WHITE("w"),
    WHITE_GREEN("w_g"),
    WHITE_PURPLE("w_p"),
    BLACK("b"),
    YELLOW("y"),
    EMPATHY("e");

    private final String tagName;

    LetterType(String tag){
        tagName = tag;
    }

    public String getTagName() {
        return tagName;
    }
}
