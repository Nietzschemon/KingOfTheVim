package com.kingofthevim.game.basicvim.VimObject;

import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.Matrix.VimWorldMatrix;

public interface VimObject {

    VimWorldMatrix getVimMatrix();

    Size getSize();

    Position getPosition();

    Cell getCurrentCell();

    void setPosition(Position position);

    void update();

    boolean isOnLetter(char letter);

    boolean isOnType(LetterType type);

    void doAfterPosiUpdate();

    void doBeforePosiUpdate();

}
