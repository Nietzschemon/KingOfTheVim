package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.serialization.ScoreManager;
import com.kingofthevim.game.engine.sound.MusicManager;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.leveltypes.LevelEditor;
import com.kingofthevim.game.states.leveltypes.Level;


public class Score extends Level {

    ScoreManager scoreMgr;

    public Score(GameStateManager gsm) {
        super(gsm);

        pointsSys = new ScoreSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("gamedata/scoreBoard", vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

        cursor.muteSoundEffects();
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

                MusicManager.scratch1.play();
                gsm.push(new Menu(gsm));
                dispose();
            }
        }

        sb.end();
    }

    @Override
    protected void checkWinCondition() {

    }

    @Override
    public void update(float dt) {

    }
}
