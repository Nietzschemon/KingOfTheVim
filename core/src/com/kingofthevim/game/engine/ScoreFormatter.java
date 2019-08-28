package com.kingofthevim.game.engine;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreFormatter {


    private ArrayList<String> makeScoreFormattedList(String[] scores){
        ArrayList<String> levels = new ArrayList<>();

        for (int i = 1; i < scores.length; i++) {
            String s = scores[i];

            String[] strings = splitScoreString(s);

            String str = formatScore(strings[0], strings[1], strings[2], strings[3]);
            levels.add(str);

        }

        Collections.sort(levels);

        return levels;
    }

    private String addSpacedIntToStr(String str, int integer, int spaces){
        String returnStr = str;

        int levelStrLen = returnStr.length();

        levelStrLen = spaces - levelStrLen;

        for (int i = 0; i < levelStrLen ; i++) {
            returnStr = returnStr.concat(" ");
        }

        returnStr = returnStr.concat(String.valueOf(integer));

        return returnStr;
    }

    private String addSpacedIntToStr(String str, String toAdd, int spaces){

        int levelStrLen = str.length();

        levelStrLen = spaces - levelStrLen;

        for (int i = 0; i < levelStrLen ; i++) {
            str = str.concat(" ");
        }

        str = str.concat(toAdd);

        return str;
    }



    private String[] splitScoreString(String scoreString){
        return scoreString.split(",");
    }

    String formatScore(String level, int score, int steps, int time){
        String endString;

        int scorePos = 16;
        int stepsPos = 26;
        int timePos = 36;

        endString = addSpacedIntToStr(level, score, scorePos);
        endString = addSpacedIntToStr(endString, steps, stepsPos);
        endString = addSpacedIntToStr(endString, time, timePos);

        return endString;
    }

    String formatScore(String level, String score, String steps, String time){
        String endString;

        int scorePos = 16;
        int stepsPos = 26;
        int timePos = 36;

        endString = addSpacedIntToStr(level, score, scorePos);
        endString = addSpacedIntToStr(endString, steps, stepsPos);
        endString = addSpacedIntToStr(endString, time, timePos);

        return endString;
    }

}
