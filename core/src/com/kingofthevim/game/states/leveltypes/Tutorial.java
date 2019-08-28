package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.sound.GameSound;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.sound.MusicTracks;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.GameStateManager;
import com.kingofthevim.game.states.Menu;

import java.util.Stack;

public class Tutorial extends Level {


    private Stack<String> levels;

    public Tutorial(GameStateManager gsm) {
        super(gsm);

        levels = new Stack<>();
        //levels.add("levels/tutorial/Tutorial_4.1.json");
        levels.add("levels/tutorial/Tutorial_3.1.json");
        levels.add("levels/tutorial/Tutorial_2.4.json");
        levels.add("levels/tutorial/Tutorial_2.3.json");
        levels.add("levels/tutorial/Tutorial_2.2.json");
        levels.add("levels/tutorial/Tutorial_2.1.json");
        levels.add("levels/tutorial/Tutorial_1.2.json");

        pointsSys = new ScoreSystem("levels/tutorial/Tutorial_1.1.json");
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("levels/tutorial/Tutorial_1.1.json", vimMatrix);
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
    protected void levelChange() {

        if(cursor.isOnType(LetterType.WHITE_GREEN)) {

            if(levels.empty()){
                GameSound.scratch1.play();
                gsm.push(new Menu(gsm));
                dispose();
            }
            else {
                String load = levels.pop();

                cursor = serial.loadLevel(load, vimMatrix);
                cursor.setScoreSystem(pointsSys);
                pointsSys.newLevel(load);
                cursorStartColumn = cursor.getPosition().getCurrColumn();
                cursorStartRow = cursor.getPosition().getCurrRow();

                cursor.getPosition().addListener(gameSound);
                Gdx.graphics.requestRendering();
            }
        }
    }

    @Override
    public void update(float dt) {

        levelChange();

        handleInput();

        cam.update();
    }

}
