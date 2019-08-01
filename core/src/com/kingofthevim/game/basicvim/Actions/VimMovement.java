package com.kingofthevim.game.basicvim.Actions;

public interface VimMovement {

    VimMove getVimMove();

    VimMove getResetVimMove();

    void setVimMove(VimMove vimMove);

    boolean hasMove();
}
