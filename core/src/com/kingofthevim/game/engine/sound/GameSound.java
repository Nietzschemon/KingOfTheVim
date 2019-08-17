/*

 */
package com.kingofthevim.game.engine.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.kingofthevim.game.engine.ChangedPosition;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_object.VimObject;

public class GameSound implements ChangedPosition {

        public static Sound hitYellow = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/pop3.wav"));
        public static Sound hitRed = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail2.wav"));
        public static Sound hitGray = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/fail.mp3"));
        public static Sound hitGoal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/success.mp3"));
        public static Sound scratch1 = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/scratch1.wav"));
        private static Music backgroundMusic;

        @Override
        public void onChange(VimObject vimObject) {
                letterSounds(vimObject);
        }

        public void choseMusic(MusicTracks track ){
                if(backgroundMusic != null
                        && backgroundMusic.isPlaying()) backgroundMusic.stop();

                switch (track){
                        case BUNNY:
                            getTrack(MusicTracks.BUNNY);
                            break;

                        case TRUMPET:
                            getTrack(MusicTracks.TRUMPET);
                            break;
                }


                backgroundMusic.setLooping(true);
        }

        public void playMusic(){
                backgroundMusic.play();
        }

        public void stopMusic(){
                if(backgroundMusic != null
                && backgroundMusic.isPlaying()){
                        backgroundMusic.stop();
                }
        }
        /**
         * Handles the sounds the different letters make
         * when a vim_object is over them
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

                if(vimObject.isOnType(LetterType.WHITE_GREEN)){
                        GameSound.hitGoal.play();
                }
        }

        private void getTrack(MusicTracks track){
            String trackStr = track.getTrackPath();
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(trackStr));
        }

}
