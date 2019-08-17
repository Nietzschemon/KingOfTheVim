package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.GameSound;
import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.MatrixSerialization;
import com.kingofthevim.game.basicvim.MusicTracks;
import com.kingofthevim.game.basicvim.ScoreSystem;
import com.kingofthevim.game.states.leveltypes.DevLevel;
import com.kingofthevim.game.states.leveltypes.Labyrinth;
import com.kingofthevim.game.states.leveltypes.Level;
import com.kingofthevim.game.states.leveltypes.Tutorial;

import java.util.ArrayList;

public class Menu extends Level {

    public Menu(GameStateManager gsm) {
        super(gsm);

        pointsSys = new ScoreSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("menu/menu1", vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

        backgroundMusic();
    }

    @Override
    protected void backgroundMusic() {

        gameSound.choseMusic(MusicTracks.TRUMPET);
        gameSound.playMusic();
    }

    @Override
    public void render(SpriteBatch sb) {
        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F5))gsm.push(new DevLevel(gsm));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if(vimMatrix.isOnWord(cursor, "new game")){

                GameSound.scratch1.play();
                gsm.push(new Labyrinth(gsm));
                dispose();
            }

            if(vimMatrix.isOnWord(cursor, "tutorial")){

                GameSound.scratch1.play();
                gsm.push(new Tutorial(gsm));
                dispose();
            }

            if(vimMatrix.isOnWord(cursor, "score")){

                GameSound.scratch1.play();
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
    protected void levelChange() {

    }

    @Override
    public void update(float dt) {

    }
}
