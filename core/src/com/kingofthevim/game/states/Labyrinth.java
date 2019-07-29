package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.MatrixSerialization;
import com.kingofthevim.game.basicvim.PointSystem;

import java.util.Stack;

public class Labyrinth extends Level{

    private Stack<String> levels;

    public Labyrinth(GameStateManager gsm) {
        super(gsm);

        levels = new Stack<>();
        levels.add("levels/game/Level_5.txt");
        levels.add("levels/game/Level_4.txt");
        levels.add("levels/game/Level_3.txt");
        levels.add("levels/game/Level_2.txt");

        pointsSys = new PointSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("levels/game/Level_1.txt", vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

        backgroundMusic();
    }

    @Override
    protected void backgroundMusic() {
        if(backgroundMusic != null
                && backgroundMusic.isPlaying()) backgroundMusic.stop();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(
                "sound/music/laborintMusic/labMusic1/labMusic1pcm.wav"));
        backgroundMusic.play();

        /*

        if(backgroundMusic != null
                && backgroundMusic.isPlaying()){
            backgroundMusic.stop();
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(
                    "sound/music/laborintMusic/labMusic2pcm.wav"));
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
        }
         */
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
                gsm.push(new MenuState(gsm));
                dispose();
            }
            else {
                String load = levels.pop();

                cursor = serial.loadLevel(load, vimMatrix);
                cursorStartColumn = cursor.getPosition().getCurrColumn();
                cursorStartRow = cursor.getPosition().getCurrRow();

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
