package com.kingofthevim.game.engine;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer {


    private long startTime;
    private long stopTime;
    private boolean timerRunning = false;

    public Timer(){ }

    public boolean start(){

        if(! timerRunning){
            startTime = TimeUtils.millis();
            timerRunning = true;
            return true;
        }

        else {
            return false;
        }
    }

    public boolean stop(){

        if(timerRunning){
            stopTime = TimeUtils.millis();
            timerRunning = false;
            return true;
        }

        else {
            return false;
        }
    }

    public void clear(){
        startTime = 0;
        stopTime = 0;
    }

    public String getDiffString(){
        return String.format("%02d:%02d", getDiffInMin(), getDiffInSec());
    }

    private int getDiffInSec(){
        return (int) (getDiffMilli() / 1000) % 60 ;
    }

    private int getDiffInMin(){
        return (int) ((getDiffMilli() / (1000*60)) % 60);
    }

    public long getDiffMilli(){
        return stopTime - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }
}
