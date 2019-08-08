package com.kingofthevim.game.basicvim;

public enum MusicTracks {
    BUNNY("sound/music/laborintMusic/bunny.wav"),
    TRUMPET("sound/music/trumpet.wav");
    //lab();

    private final String trackPath;

    MusicTracks(String trackPath){
        this.trackPath = trackPath;
    }

    public String getTrackPath() {
        return trackPath;
    }
}
