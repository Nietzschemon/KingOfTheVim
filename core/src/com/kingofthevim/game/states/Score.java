package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.sound.GameSound;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.MatrixSerialization;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.leveltypes.LevelEditor;
import com.kingofthevim.game.states.leveltypes.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Score extends Level {

    HashMap<String, HashMap<String, Integer>> score;

    public Score(GameStateManager gsm) {
        super(gsm);

        pointsSys = new ScoreSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("gamedata/scoreBoard", vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

        score = serial.getScore();
        listScore();
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


    private String formatScore(String level, int score, int steps, int time){
        String endString;

        int scorePos = 16;
        int stepsPos = 26;
        int timePos = 36;

        endString = addSpacedIntToStr(level, score, scorePos);
        endString = addSpacedIntToStr(endString, steps, stepsPos);
        endString = addSpacedIntToStr(endString, time, timePos);

        return endString;
    }
    private void listScore(){
        ArrayList<String> levels = new ArrayList<>();

        score.forEach((k, v) -> {
            String str = k;
            int x = str.lastIndexOf("/") + 1;
            str = str.substring(x);

            str = formatScore(str, v.get("points"), v.get("actualMoves"), 0);

            levels.add(str);
        });

        Collections.sort(levels);

        letterAdder.setHorizontalStringArray(levels, 4, 2, 2, true, true, LetterType.WHITE );

    }
    @Override
    protected void backgroundMusic() {

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F5))gsm.push(new LevelEditor(gsm));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            if(vimMatrix.isOnWord(cursor, "Back")){

                GameSound.scratch1.play();
                gsm.push(new Menu(gsm));
                dispose();
            }
        }

        sb.end();
    }

    @Override
    protected void levelChange() {

    }

    @Override
    public void update(float dt) {

    }
}
