package com.kingofthevim.game.engine.vim_modes;

/**
 * Listener for the replace-mode event
 */
public interface ReplaceModeListener {

    /**
     * Handles events where
     * replace-mode is entered.
     */
    void onReplaceModeEnter();

    /**
     * Handles events where
     * replace-mode is exited.
     */
    void onReplaceModeExit();
}
