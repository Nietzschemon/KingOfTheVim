package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.sound.MusicTracks;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.GameStateManager;


public class Labyrinth extends Level {

    public Labyrinth(GameStateManager gsm) {
        super(gsm);

        serial = new MatrixSerialization();
        levels = serial.getFilePathsAsQueue("levels/game/");

        levelName = levels.poll();
        pointsSys = new ScoreSystem(levelName);

        cursor = serial.loadLevel(levelName, vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

        cursor.setScoreSystem(pointsSys);
        cursor.getPosition().addListener(gameSound);
        backgroundMusic();
    }

    @Override
    protected void backgroundMusic() {
        gameSound.choseMusic(MusicTracks.BUNNY);
        gameSound.playMusic();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        // Shows sprite-batch where to draw things on screen.

    }

    @Override
    protected void checkWinCondition() {

        if(isDeleteLevel
                && 0 >= vimMatrix.numberOfLetterTypesInMatrix(LetterType.GREEN)){
            changeLevel();
        }

        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
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
