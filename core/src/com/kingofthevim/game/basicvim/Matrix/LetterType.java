package com.kingofthevim.game.basicvim.Matrix;

public enum LetterType {
    BLACK("b"),
    BLACK_YELLOW("b_y"),
    RED("r"),
    GRAY("g"),
    WHITE("w"),
    WHITE_GREEN("w_g"),
    WHITE_RED("w_r"),
    WHITE_PURPLE("w_p"),
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
