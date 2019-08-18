package com.kingofthevim.game.engine.sound;

public enum MusicTracks {
    BUNNY("sound/music/laborintMusic/bunny.wav"),
    TRUMPET("sound/music/trumpet.wav"),
    YASHA("sound/music/labMusic1/labMusic1pcm.wav"),
    EROL("sound/music/labMusic2pcm.wav");

    private final String trackPath;

    MusicTracks(String trackPath){
        this.trackPath = trackPath;
    }

    public String getTrackPath() {
        return trackPath;
    }
}
