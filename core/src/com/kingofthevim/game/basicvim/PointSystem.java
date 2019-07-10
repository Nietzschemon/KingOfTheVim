package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;

public class PointSystem {

    private int points = 0;
    private int maxMoves = 0;
    private int actualMoves = 0;
    private int gameMoves = 0;
    private Timer time;
    private int maxTime = 0;


    public PointSystem(){
        time = new Timer();
    }

    public PointSystem(int maxMoves){
        time = new Timer();
        this.maxMoves = maxMoves;
    }

    public PointSystem(int maxMoves, int maxTime){
        time = new Timer();
        this.maxMoves = maxMoves;
        this.maxTime = maxTime;
    }

    public PointSystem(int maxMoves, int maxTime, LetterType[] letterTypes){
        time = new Timer();
        this.maxMoves = maxMoves;
        this.maxTime = maxTime;
    }

    public void onMove(Cursor cursor){

        if(cursor.isOnType(LetterType.RED)){
            points -= 100;
            gameMoves--;
        }
        if(cursor.isOnType(LetterType.YELLOW)){
            points += 100;
            gameMoves++;
        }

        points -= 10;
        gameMoves--;
        actualMoves--;

        System.out.println("\n\nActualMoves: " + actualMoves +
                "\nGameMoves: " + gameMoves +
                "\nPoints: " + points);
    }

    private boolean cursorOnX(){
        return false;
    }

    public void addPoints(int points){


    }

    public void subtractPoints(int points){

    }
}
