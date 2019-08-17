package com.kingofthevim.game.engine.VimObject;

import com.kingofthevim.game.engine.Matrix.Cell;
import com.kingofthevim.game.engine.Matrix.LetterType;
import com.kingofthevim.game.engine.Matrix.VimWorldMatrix;

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
