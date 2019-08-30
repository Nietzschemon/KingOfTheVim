/*

 */
package com.kingofthevim.game.engine.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.kingofthevim.game.engine.ChangedPosition;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_object.VimObject;

public class MusicManager {

        public static Sound scratch1 = Gdx.audio.newSound(Gdx.files.internal("sound/sound_effects/scratch1.wav"));
        private static Music backgroundMusic;


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

        private void getTrack(MusicTracks track){
            String trackStr = track.getTrackPath();
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(trackStr));
        }

}
