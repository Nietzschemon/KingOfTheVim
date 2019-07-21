package com.kingofthevim.game.basicvim;

public interface VimObject {

    VimWorldMatrix getVimMatrix();

    default Cell getCurrentCell(){
        return getVimMatrix().getCellMatrix().get(getCurrRow()).get(getCurrColumn());
    }

    Size getSize();

    Position getLocation();

    void setLocation(Position position);

    int getRowTotal();

    int getColunmTotal();

    int getCurrRow();

    int getCurrColumn();

    void update();

    boolean setRelativeRow(int rowMove);

    boolean setAbsoluteRow(int row);

    boolean setRelativeColumn(int columnMove);

    boolean setAbsoluteColumn(int column);

    boolean isOnLetter(char letter);

    boolean isOnType(LetterType type);

}
