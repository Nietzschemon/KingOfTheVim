package com.kingofthevim.game.engine.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.kingofthevim.game.engine.ChangedPosition;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_modes.listeners.ModeListener;
import com.kingofthevim.game.engine.vim_object.VimObject;

public class SoundEffectsManager implements ChangedPosition, ModeListener {

    public static Sound hitYellow = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/pop3.wav"));
    public static Sound hitRed = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail2.wav"));
    public static Sound hitGray = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail.mp3"));
    public static Sound hitGoal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/success.mp3"));
    public static Sound inDeleteMode = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/shotgun_load.wav"));
    public static Sound deletedThing = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/explosion.wav"));

    private boolean isInNormalMode = true;


    @Override
    public void onChange(VimObject vimObject) {
        letterSounds(vimObject);
    }


    /**
     * Handles the sounds the different letters make
     * when a vim_object is over them
     * @param vimObject the object that activates the sounds
     */
    private void letterSounds(VimObject vimObject){

        if(isInNormalMode) {

            if (vimObject.isOnType(LetterType.RED)) {

                SoundEffectsManager.hitRed.play();
            }
            if (vimObject.isOnType(LetterType.YELLOW)) {
                SoundEffectsManager.hitYellow.play();
            }

            if (vimObject.isOnType(LetterType.GRAY)
                    || vimObject.isOnType(LetterType.EMPATHY)) {
                SoundEffectsManager.hitGray.play();
            }

            if (vimObject.isOnType(LetterType.WHITE_GREEN)) {
                SoundEffectsManager.hitGoal.play();
            }
        }
    }

    @Override
    public void onDeleteModeEnter() {
        isInNormalMode = false;
        inDeleteMode.play();
    }

    @Override
    public void onDeleteModeExit() {
        isInNormalMode = true;
    }

    @Override
    public void onInsertModeEnter() {

    }

    @Override
    public void onInsertModeExit() {

    }

    @Override
    public void onReplaceModeEnter() {

    }

    @Override
    public void onReplaceModeExit() {

    }
}
