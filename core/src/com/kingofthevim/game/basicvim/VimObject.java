package com.kingofthevim.game.basicvim;

public interface VimObject {

    VimWorldMatrix getVimMatrix();

    default Cell getCurrentCell(){
        return getVimMatrix().getCellMatrix().get(getPosition().getCurrRow()).get(getPosition().getCurrColumn());
    }

    Size getSize();

    Position getPosition();

    void setPosition(Position position);

    void update();

    boolean isOnLetter(char letter);

    boolean isOnType(LetterType type);

}
