/*

 */
package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

public class GameSound implements ChangedPosition {

        public static com.badlogic.gdx.audio.Sound hitYellow = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/pop3.wav"));
        public static com.badlogic.gdx.audio.Sound hitRed = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail2.wav"));
        public static com.badlogic.gdx.audio.Sound hitGray = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail.mp3"));
        public static com.badlogic.gdx.audio.Sound hitGoal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/success.mp3"));

        @Override
        public void onChange(VimObject vimObject) {
                letterSounds(vimObject);
        }

        /**
         * Handles the sounds the different letters make
         * when a VimObject is over them
         * @param vimObject the object that activates the sounds
         */
        private void letterSounds(VimObject vimObject){

                if(vimObject.isOnType(LetterType.RED)){
                                GameSound.hitRed.play();
                        }
                if(vimObject.isOnType(LetterType.YELLOW)){
                        GameSound.hitYellow.play();
                }

                if(vimObject.isOnType(LetterType.GRAY)
                || vimObject.isOnType(LetterType.EMPATHY)){
                        GameSound.hitGray.play();
                }
        }
}
