package com.kingofthevim.game.engine.vim_modes.listeners;

public interface DeleteModeListener {

    /**
     * Handles events where
     * delete-mode is entered.
     */
    void onDeleteModeEnter();

    /**
     * Handles events where
     * delete-action occurred.
     */
    void onDeleteModeAction();

    /**
     * Handles events where
     * delete-mode is exited.
     */
    void onDeleteModeExit();
}
