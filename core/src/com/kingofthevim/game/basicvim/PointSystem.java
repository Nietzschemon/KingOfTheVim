package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.utils.Timer;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

//TODO mark used up yellow letter with colorShift
public class PointSystem {

    private int points = 10000;


    private int maxMoves = 0;
    private int actualMoves = 0;
    private int redPoints = -100;
    private int yellowPoints = 100;
    private int grayPoints = -500;
    private Timer time;
    private int maxTime = 0;
    private int yellowMultiplier = 1;
    private int redMultiplier = 1;

    public PointSystem(){
        time = new Timer();
        //System.out.println("Standard points system");
    }

    public PointSystem(int maxMoves){
        time = new Timer();
        this.maxMoves = maxMoves;

        //System.out.println("Standard points system");
    }

    public PointSystem(int maxMoves, int maxTime){
        time = new Timer();
        this.maxMoves = maxMoves;
        this.maxTime = maxTime;

        //System.out.println("Standard points system");
    }
    public PointSystem(int maxMoves, int redPoints, int yellowPoints, int grayPoints){
        this.redPoints = redPoints;
        this.yellowPoints = yellowPoints;
        this.grayPoints = grayPoints;
        time = new Timer();
        this.maxMoves = maxMoves;

        /*
        System.out.println("Overrided points system" +
                "\nRed: " + redPoints +
                "\nYellow: " + yellowPoints +
                "\nGray: " + grayPoints);

         */
    }



    public void onMove(Cursor cursor){

        actualMoves++;

        if(cursor.isOnType(LetterType.RED)){
            points += redPoints * actualMoves * redMultiplier;
            yellowMultiplier = 1;
            redMultiplier++;
        }
        if(cursor.isOnType(LetterType.YELLOW)){
            points += yellowPoints * yellowMultiplier;
            yellowMultiplier++;
            redMultiplier = 1;
        }

        if(cursor.isOnType(LetterType.GRAY)){
            points += grayPoints;
            yellowMultiplier = 1;
            redMultiplier = 1;
        }
        if(cursor.isOnType(LetterType.WHITE)){
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

    public Timer getTime() {
        return time;
    }
}
