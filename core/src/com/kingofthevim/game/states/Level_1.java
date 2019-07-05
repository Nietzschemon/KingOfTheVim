package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.*;

import java.util.ArrayList;

public class Level_1 extends Level{


    private final int cursorStartRow = 6;
    private final int cursorStartColumn = 0;

    public Level_1(GameStateManager gsm) {
        super(gsm);

        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);

        loadBackgroundText();

        levelPath();
    }

    @Override
    protected void levelPath() {

        labyrinthText.setHorizontalString("Start", 6, 0, false, LetterType.WHITE);

        // All letters in the matrix are set to the lettertype of those in the string
        labyrinthText.setLetterType("O", LetterType.RED, false);
        labyrinthText.setLetterType("X", LetterType.YELLOW, false);
    }

    public void loadBackgroundText(){
        String[] welcome = {"Welcome to King of the VIM!", "This is the starting level where we", "learn the basics of VIM" };

        String[] warning = {"Rest assured that my developer WILL make", "this game unbearably hard soon enough", "may God have mercy on your soul"};

        String[] info = {"To skip ahead: ", "every tutorial can be skipped by performing", "the level under a certain move count"};

        backgroundText.setHorizontalStringArray(welcome, 0, 0, false, LetterType.GRAY);
        backgroundText.setHorizontalStringArray(warning, 15, 0, false, LetterType.GRAY);
        backgroundText.setHorizontalStringArray(info, 19, 0, false, LetterType.RED);
    }

    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        /* //TODO create and put this into resetCursorMethod()
        if(cursor.isOnType(LetterType.GRAY)){
           cursor.dispose();
           cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);
        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);
        }

         */


        sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);

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
    public void update(float dt) {
        handleInput();

        cam.update();

    }
    @Override
    public void dispose() {
        cursor.dispose();

        for (int i = 0; i < vimMatrix.getCellMatrix().size() ; i++) {

            for (int j = 0; j < vimMatrix.getCellMatrix().get(i).size(); j++) {

                vimMatrix.getCellMatrix().get(i).get(j).dispose();

            }
        }
        System.out.println("Play State Disposed");
    }


}

