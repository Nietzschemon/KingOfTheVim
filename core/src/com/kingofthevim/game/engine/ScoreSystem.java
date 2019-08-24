package com.kingofthevim.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_object.VimObject;

import java.util.Calendar;
import java.util.HashMap;

//TODO mark used up yellow letter with colorShift
public class ScoreSystem {

    private String levelName;
    private int points = 10000;
    private int maxMoves = 0;
    private int actualMoves = 0;
    private int redPoints = -100;
    private int yellowPoints = 100;
    private int grayPoints = -500;
    private long maxTime = 0;
    private int yellowMultiplier = 1;
    private int redMultiplier = 1;

    private Timer timer;

    public ScoreSystem(){
        timer = new Timer();
        timer.start();
    }

    public ScoreSystem(String levelName){
        this();
        this.levelName = levelName;
    }

    public ScoreSystem(int maxMoves, int redPoints, int yellowPoints, int grayPoints, long maxTime){
        this();
        this.redPoints = redPoints;
        this.yellowPoints = yellowPoints;
        this.grayPoints = grayPoints;
        this.maxMoves = maxMoves;
        this.maxTime = maxTime;

        System.out.println("Point-system override" +
                "\nRed: " + redPoints +
                "\nYellow: " + yellowPoints +
                "\nGray: " + grayPoints +
                "\nMaxMoves: " + maxMoves +
                "\nmaxTime: " + maxTime);
    }



    public void onMove(VimObject vimObject){

        actualMoves++;

        if(vimObject.isOnType(LetterType.WHITE)){
            points = points - (20 * actualMoves);
            yellowMultiplier = 1;
            redMultiplier = 1;
        }
        if(vimObject.isOnType(LetterType.RED)){
            points += redPoints * actualMoves * redMultiplier;
            yellowMultiplier = 1;
            redMultiplier++;
        }
        if(vimObject.isOnType(LetterType.YELLOW)){
            points += yellowPoints * yellowMultiplier;
            yellowMultiplier++;
            redMultiplier = 1;
            vimObject.getCurrentCell().setCellLook(LetterType.WHITE);
        }

        if(vimObject.isOnType(LetterType.GRAY)
        || vimObject.isOnType(LetterType.EMPATHY)){
            points += grayPoints;
            yellowMultiplier = 1;
            redMultiplier = 1;
        }

        Gdx.graphics.requestRendering();

    }

    private void resetAllButPoints(){
        actualMoves = 0;
        yellowMultiplier = 1;
        redMultiplier = 1;
        maxTime = 0;
        maxMoves = 0;
        timer.clear();
        timer.start();
    }

    public void newLevel(String levelName){
        timer.stop();
        ScoreSerialization scoreSeri = new ScoreSerialization();
        scoreSeri.saveScore(this.levelName, points, actualMoves, timer);

        resetAllButPoints();
        this.levelName = levelName;
    }

    public int getPoints() {
        return points;
    }

    public int getActualMoves() {
        return actualMoves;
    }

}
