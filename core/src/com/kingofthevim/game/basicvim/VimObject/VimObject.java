package com.kingofthevim.game.basicvim.VimObject;

import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.Matrix.VimWorldMatrix;

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
