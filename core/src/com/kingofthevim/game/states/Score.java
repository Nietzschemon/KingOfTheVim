package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.GameSound;
import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.MatrixSerialization;
import com.kingofthevim.game.basicvim.MusicTracks;
import com.kingofthevim.game.basicvim.PointSystem;

import java.util.ArrayList;

public class Score extends Level {

    public Score(GameStateManager gsm) {
        super(gsm);

        pointsSys = new PointSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("gamedata/scoreBoard", vimMatrix);
        cursorStartColumn = cursor.getPosition().getCurrColumn();
        cursorStartRow = cursor.getPosition().getCurrRow();

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
