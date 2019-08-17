package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.GameSound;
import com.kingofthevim.game.engine.Matrix.Cell;
import com.kingofthevim.game.engine.Matrix.LetterType;
import com.kingofthevim.game.engine.MatrixSerialization;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.leveltypes.DevLevel;
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

        labyrinthText.setHorizontalStringArray(levels, 4, 2, 2, true, true, LetterType.WHITE );

    }
    @Override
    protected void backgroundMusic() {

    }

    @Override
    public void render(SpriteBatch sb) {
        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F5))gsm.push(new DevLevel(gsm));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            /*
            if(vimMatrix.isOnWord(cursor, "new game")){

                GameSound.scratch1.play();
                gsm.push(new Labyrinth(gsm));
                dispose();
            }

             */


            if(vimMatrix.isOnWord(cursor, "Back")){

                GameSound.scratch1.play();
                gsm.push(new Menu(gsm));
                dispose();
            }
        }


        else{
            sb.draw(cursor.getTexture(), cursor.getPosition().getCartesianPosition().x, cursor.getPosition().getCartesianPosition().y);
        }

        for(ArrayList<Cell> cellRow : vimMatrix.getCellMatrix()){

            for(Cell cell : cellRow){

                if(cell.getCellLook() != null){
                    sb.draw(cell.getCellLook(),
                            cell.getCartesianPosition().x,
                            cell.getCartesianPosition().y);
                }
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
