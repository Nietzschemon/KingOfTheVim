package com.kingofthevim.game.engine.vim_modes.listeners;

/**
 * Listener for the replace-mode event
 */
public interface InsertModeListener {

    /**
     * Handles events where
     * replace-mode is entered.
     */
    void onInsertModeEnter();

    /**
     * Handles events where
     * replace-mode is exited.
     */
    void onInsertModeExit();
}
