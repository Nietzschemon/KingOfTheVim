package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.engine.sound.MusicManager;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.sound.MusicTracks;
import com.kingofthevim.game.engine.ScoreSystem;
import com.kingofthevim.game.states.leveltypes.LevelEditor;
import com.kingofthevim.game.states.leveltypes.Labyrinth;
import com.kingofthevim.game.states.leveltypes.Level;
import com.kingofthevim.game.states.leveltypes.Tutorial;

import java.util.ArrayList;

public class Menu extends Level {

    public Menu(GameStateManager gsm) {
        super(gsm);

        pointsSys = new ScoreSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("menu/menu1_noOptSco.json", vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

        cursor.muteSoundEffects();
        backgroundMusic();
    }

    @Override
    protected void backgroundMusic() {

        musicManager.choseMusic(MusicTracks.TRUMPET);
        musicManager.playMusic();
    }

    @Override
    public void render(SpriteBatch sb) {
        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F5))gsm.push(new LevelEditor(gsm));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if(vimMatrix.isOnWord(cursor, "new game")){

                MusicManager.scratch1.play();
                gsm.push(new Labyrinth(gsm));
                dispose();
            }

            if(vimMatrix.isOnWord(cursor, "tutorial")){

                MusicManager.scratch1.play();
                gsm.push(new Tutorial(gsm));
                dispose();
            }

            if(vimMatrix.isOnWord(cursor, "score")){

                MusicManager.scratch1.play();
                gsm.push(new Score(gsm));
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
    protected void checkWinCondition() {

    }

    @Override
    public void update(float dt) {

    }
}
