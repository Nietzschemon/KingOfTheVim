package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.Matrix.Cell;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.MatrixSerialization;
import com.kingofthevim.game.basicvim.PointSystem;

import java.util.ArrayList;

public class Menu extends Level{

    public Menu(GameStateManager gsm) {
        super(gsm);

        pointsSys = new PointSystem();
        serial = new MatrixSerialization();

        cursor = serial.loadLevel("menu/menu1.txt", vimMatrix);
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

        if(cursor.isOnType(LetterType.WHITE)
        && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

            gsm.push(new Labyrinth(gsm));

        }else{
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
