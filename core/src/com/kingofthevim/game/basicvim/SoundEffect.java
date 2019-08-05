/*

 */
package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {

        public static Sound hitYellow = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/pop3.wav"));
        public static Sound hitRed = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail2.wav"));
        public static Sound hitGray = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail.mp3"));
        public static Sound hitGoal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/success.mp3"));

}
