package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.VimObject.Cursor;
import com.kingofthevim.game.basicvim.VimObject.VimObject;

//TODO mark used up yellow letter with colorShift
public class PointSystem {

    private int points = 10000;


    private VimObject vimObject;
    private int maxMoves = 0;
    private int actualMoves = 0;
    private int redPoints = -100;
    private int yellowPoints = 100;
    private int grayPoints = -500;
    private TimeUtils time;
    private long maxTime = 0;
    private int yellowMultiplier = 1;
    private int redMultiplier = 1;

    public PointSystem(){
        time = new TimeUtils();
    }

    public PointSystem(VimObject vimObject){
        this.vimObject = vimObject;
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


        /*
        System.out.println("\n\nActualMoves: " + actualMoves +
                "\nPoints: " + points +
                "\nTime: " + time.toString());

         */
    }



    private boolean cursorOnX(){
        return false;
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

    public VimObject getVimObject() {
        return vimObject;
    }

    public void setVimObject(VimObject vimObject) {
        this.vimObject = vimObject;
    }
}
