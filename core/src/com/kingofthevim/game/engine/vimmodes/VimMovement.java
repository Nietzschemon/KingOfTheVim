package com.kingofthevim.game.engine.vimmodes;

public interface VimMovement {

    VimMove getVimMove();

    VimMove getResetVimMove();

    void setVimMove(VimMove vimMove);

    boolean hasMove();
}
