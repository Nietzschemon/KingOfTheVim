package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

import java.util.HashMap;

//TODO mark used up yellow letter with colorShift
public class PointSystem {

    private int points = 10000;


    private int maxMoves = 0;
    private int actualMoves = 0;
    private int redPoints = -100;
    private int yellowPoints = 100;
    private int grayPoints = -500;
    private TimeUtils time;
    private long maxTime = 0;
    private int yellowMultiplier = 1;
    private int redMultiplier = 1;
    private HashMap<String, HashMap<String, Integer>> pointHistory;
    private String levelName;

    public PointSystem(){
        time = new TimeUtils();
        pointHistory = new HashMap<>();
    }

    public PointSystem(String levelName){
        this();
        this.levelName = levelName;
    }

    public PointSystem(int maxMoves, int redPoints, int yellowPoints, int grayPoints, long maxTime){
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

        if(vimObject.isOnType(LetterType.RED)){
            points += redPoints * actualMoves * redMultiplier;
            yellowMultiplier = 1;
            redMultiplier++;
        }
        if(vimObject.isOnType(LetterType.YELLOW)){
            points += yellowPoints * yellowMultiplier;
            yellowMultiplier++;
            redMultiplier = 1;
            vimObject.getCurrentCell().setLetterType(LetterType.WHITE);
        }

        if(vimObject.isOnType(LetterType.GRAY)){
            points += grayPoints;
            yellowMultiplier = 1;
            redMultiplier = 1;
        }
        if(vimObject.isOnType(LetterType.WHITE)){
            points = points - (20 * actualMoves);
            yellowMultiplier = 1;
            redMultiplier = 1;
        }

        Gdx.graphics.requestRendering();

        /*
        System.out.println("\n\nActualMoves: " + actualMoves +
                "\nPoints: " + points +
                "\nTime: " + time.toString());

         */
    }

    private void resetAllButPoints(){
        actualMoves = 0;
        yellowMultiplier = 0;
        redMultiplier = 0;
        maxTime = 0;
        maxMoves = 0;
    }

    private void storePreviousData(){
        HashMap<String, Integer> data = new HashMap<>();

        data.put("points", points);
        data.put("actualMoves", actualMoves);
        data.put("maxMoves", maxMoves);
        pointHistory.put(levelName, data);
    }

    public void newLevel(String levelName){
        storePreviousData();
        resetAllButPoints();
        this.levelName = levelName;
    }

    public int getPoints() {
        return points;
    }

    public int getActualMoves() {
        return actualMoves;
    }

    public TimeUtils getTime() {
        return time;
    }

    public HashMap<String, HashMap<String, Integer>> getPointHistory() {
        return pointHistory;
    }

}
