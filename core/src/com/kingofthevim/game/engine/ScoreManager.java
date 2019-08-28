package com.kingofthevim.game.engine;

import com.kingofthevim.game.engine.matrix.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {

    private ScoreSerialization scoreSeri;
    private ArrayList<Score> allScores;
    private ScoreFormatter scoreFormatter;

    public ScoreManager(){

        scoreSeri = new ScoreSerialization();
        scoreSeri.readScoreFile();
        allScores = makeScoresList();
        scoreFormatter = new ScoreFormatter();
    }


    /**
     * Finds all high-scores in allScores field
     * @return a ArrayList with the highest score
     * fore each level
     */
    public ArrayList<Score> findAllHighScores(){

        ArrayList<String> levelNames = scoreSeri.getLevelNames();
        ArrayList<Score> highScoreList = new ArrayList<>();

        for(String level : levelNames){
            ArrayList<Score> allScoresForLevel = getAllScoresForLevel(level);

            if(! allScoresForLevel.isEmpty()){
                highScoreList.add(findHighestScore(allScoresForLevel));
            }

        }

        return highScoreList;
    }


    /**
     * Gets all scores for a given level from allScores field
     * @param levelName name of level to return
     * @return an Array with all scores for the given levelName
     */
    private ArrayList<Score> getAllScoresForLevel(String levelName){
        ArrayList<Score> scores = new ArrayList<>();

        for(Score score : allScores){
            if(score.saveName.equals(levelName)) scores.add(score);
        }

        return scores;
    }


    /**
     * Finds the biggest scores of a list of scores with logic
     * to differentiate if two scores are equal.
     * @param scoreList to be sorted
     * @return biggest score
     */
    private Score findHighestScore(ArrayList<Score> scoreList){

        Score score;

        score = scoreList.get(0);

        if(scoreList.size() > 1){

            for (Score s : scoreList) {
                if (s.points > score.points
                        || (s.points >= score.points && s.steps < score.steps)
                        || (s.points >= score.points && s.steps <= score.steps && s.time < score.time)){
                    score = s;
                }
            }
        }



        return score;
    }


    private ArrayList<Score> makeScoresList(){
        ArrayList<Score> scores = new ArrayList<>();

        ArrayList<String> scoreArray = scoreSeri.getSaveFileScoreArray();

        for (String scoreStr : scoreArray ){

            String[] splitScore = scoreStr.split(",");

            scores.add(new Score(splitScore[0],
                    Tools.tryParseInt(splitScore[1]),
                    Tools.tryParseInt(splitScore[2]),
                    Tools.tryParseInt(splitScore[3]),
                    splitScore[4]));
        }


        return scores;
    }

    public ArrayList<String> getHighScoreStringList(){

        ArrayList<Score> allHighScores = findAllHighScores();
        ArrayList<String> scoreList = new ArrayList<>();

        for(Score s : allHighScores){

            String str = scoreFormatter.formatScore(s.saveName, s.points, s.steps, s.time);
            scoreList.add(str);
        }

        Collections.sort(scoreList);

        return scoreList;
    }


    private class Score{

        String saveName;
        int points;
        int steps;
        int time;
        String date;

        Score(String saveName, int points, int steps, int time, String date) {
            this.saveName = saveName;
            this.points = points;
            this.steps = steps;
            this.time = time;
            this.date = date;
        }
    }
}
