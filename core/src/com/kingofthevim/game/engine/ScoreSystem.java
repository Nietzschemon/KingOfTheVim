package com.kingofthevim.game.engine;

import com.badlogic.gdx.Gdx;
import com.kingofthevim.game.engine.serialization.ScoreSerialization;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_object.VimObject;

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
            points = points - (10 * actualMoves);
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

        if(points < 0) points = 0;
        Gdx.graphics.requestRendering();

    }

    public void resetAll(){
        points = 10000;
        actualMoves = 0;
        yellowMultiplier = 1;
        redMultiplier = 1;
        maxTime = 0;
        maxMoves = 0;
        timer.clear();
        timer.start();
    }

    public void newLevel(String levelName){
        saveScore();
        this.levelName = levelName;
    }

    public void saveScore(){
        timer.stop();
        ScoreSerialization scoreSeri = new ScoreSerialization();
        scoreSeri.saveScore(this.levelName, points, actualMoves, timer);

        resetAll();
    }

    public int getPoints() {
        return points;
    }

    public int getActualMoves() {
        return actualMoves;
    }

}
