package com.kingofthevim.game.engine.vim_modes;

public interface VimMovement {

    VimMove getVimMove();

    VimMove getResetVimMove();

    void setVimMove(VimMove vimMove);

    boolean hasMove();
}
