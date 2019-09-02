package com.kingofthevim.game.engine.serialization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.engine.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ScoreSerialization extends ScoreSystem {

    private String saveFileString;


    public void saveScore(String levelName, int points, int actualMoves, Timer timer){
        writeFile(makeScoreString(levelName, points, actualMoves, timer));
    }

    private void writeFile(String newData){
        FileHandle saveFile = new FileHandle("scoreData.csv");
        if(! saveFile.exists()){
            saveFile.writeString("level, score, steps, timerMilliSec, date\n", false);
        }
        saveFile.writeString(newData, true);
    }

    private String makeScoreString(String levelName, int points, int actualMoves, Timer timer){

        return removeDirData(levelName) + ", " + points + ", " + actualMoves + ", " + timer.getDiffMilli()
                + ", " + getDate() + "\n";
    }

    public boolean readScoreFile(){
        FileHandle saveFile = new FileHandle("scoreData.csv");

        if(saveFile.exists()){
            saveFileString = saveFile.readString();
            return true;
        }
        return false;
    }

    private String removeDirData(String string){
        return string.split(".*/")[1].split("\\.json")[0];
    }


    private String getDate(){
        Calendar calender = Calendar.getInstance();

        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH) + 1;
        int day = calender.get(Calendar.DATE);

        return year + "-" + month + "-" + day;
    }

    public ArrayList<String> getLevelNames(){
        FileHandle[] tutorials = Gdx.files.internal("levels/game").list();
        FileHandle[] levels = Gdx.files.internal("levels/tutorial").list();
        ArrayList<String> levelNames = new ArrayList<>();

        for(FileHandle f : tutorials){
            levelNames.add(f.nameWithoutExtension());
        }

        for(FileHandle f : levels){
            levelNames.add(f.nameWithoutExtension());
        }

        return levelNames;
    }
    public String getSaveFileString() {
        return saveFileString;
    }

    public ArrayList<String> getSaveFileScoreArray(){
        String[] split = saveFileString.split("\\r?\\n");

        return new ArrayList<>(Arrays.asList(split));
    }
}
