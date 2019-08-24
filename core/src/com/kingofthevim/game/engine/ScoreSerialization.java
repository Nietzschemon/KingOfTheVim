package com.kingofthevim.game.engine;

import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.Calendar;

public class ScoreSerialization extends ScoreSystem{

    private String saveFileString;


    public void saveScore(String levelName, int points, int actualMoves, Timer timer){
        writeFile(makeScoreString(levelName, points, actualMoves, timer));
    }

    private void writeFile(String newData){
        FileHandle saveFile = new FileHandle("gamedata/scoreData.csv");
        if(! saveFile.exists()){
            saveFile.writeString("level, score, steps, timer, date,\n", false);
        }
        saveFile.writeString(newData, true);
    }

    private String makeScoreString(String levelName, int points, int actualMoves, Timer timer){

        return removeDirData(levelName) + ", " + points + ", " + actualMoves + ", " + timer.getDiffString()
                + ", " + getDate() + "\n";
    }

    public boolean readScoreFile(){
        FileHandle saveFile = new FileHandle("gamedata/scoreData.csv");

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

    public String getSaveFileString() {
        return saveFileString;
    }
}
