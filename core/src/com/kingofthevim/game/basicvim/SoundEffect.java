/*

 S: tada1.wav by jobro -- https://freesound.org/s/60443/ (BY)
 P: by DWOBoyle -- https://freesound.org/people/DWOBoyle/packs/8861/ (BY)
 P: by DWOBoyle -- https://freesound.org/people/DWOBoyle/packs/8898/ (BY)
 */
package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {

        public static Sound hitYellow = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/146175__dwoboyle__chair-folding-chair-metal-back-hit-04.wav"));
        public static Sound hitRed = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/144263__dwoboyle__impact-misc-tools-0002.wav"));
        public static Sound hitGray = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/143605__dwoboyle__synth-thud.wav"));
        public static Sound hitGoal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/60443__jobro__tada1.wav"));

}
