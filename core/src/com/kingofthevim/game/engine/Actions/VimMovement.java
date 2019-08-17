package com.kingofthevim.game.engine.Actions;

public interface VimMovement {

    VimMove getVimMove();

    VimMove getResetVimMove();

    void setVimMove(VimMove vimMove);

    boolean hasMove();
}
