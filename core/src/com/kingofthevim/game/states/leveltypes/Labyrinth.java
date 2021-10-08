package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.sound.MusicTracks;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.GameStateManager;

import java.util.LinkedList;


public class Labyrinth extends Level {

    public Labyrinth(GameStateManager gsm) {
        super(gsm);

        serial = new MatrixSerialization();

        /*TODO make two files for listing levels.
            One for in-game and one for appending files made by the level editor
         */
        //levels = serial.getFilePathsAsQueue("./levels/game/");

        levels = new LinkedList<String>();
        levels.add("levels/game/Level_1.1.json");
        levels.add("levels/game/Level_1.2.json");
        levels.add("levels/game/Level_3.1.json");
        levels.add("levels/game/Level_3.2.json");
        levels.add("levels/game/Puzzle_1.0.json");
        levels.add("levels/game/Puzzle_1.1.json");

        levelName = levels.poll();
        pointsSys = new ScoreSystem(levelName);

        cursor = serial.loadLevel(levelName, vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();
        levelSettings = serial.getLevelSettings();

        cursor.setScoreSystem(pointsSys);
        backgroundMusic();
    }

    @Override
    protected void backgroundMusic() {
        musicManager.choseMusic(MusicTracks.BUNNY);
        musicManager.playMusic();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        // Shows sprite-batch where to draw things on screen.

    }

    @Override
    protected void checkWinCondition() {

        if(levelSettings.winOnDelete
                && 0 >= vimMatrix.numberOfLetterTypesInMatrix(LetterType.GREEN)){
            changeLevel();
        }

        if(levelSettings.winOnGoal
                && cursor.isOnType(LetterType.WHITE_GREEN)) {
            changeLevel();
        }

    }

    @Override
    public void update(float dt) {

        checkWinCondition();

        handleInput();

        cam.update();
    }

}
