package com.kingofthevim.game.engine.vim_object;

import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.matrix.VimWorldMatrix;

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
