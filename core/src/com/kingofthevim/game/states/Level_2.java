package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.Cell;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.LetterType;
import com.kingofthevim.game.basicvim.PointSystem;

import java.util.ArrayList;

public class Level_2 extends Level {


    private final int cursorStartRow = 0;
    private final int cursorStartColumn = 0;

    public Level_2(GameStateManager gsm) {
        super(gsm);

        pointsSys = new PointSystem(10);

        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);

        backgroundText();

        levelPath();
    }

    @Override
    protected void levelPath() {

        labyrinthText.createMap(
                "<rg>xxxxxx</rg>" +
                        "<dw>Gxxxxx</dw>" +
                        "<rg>Gxxxxx</rg>" +
                        "<up+01>GxxxxG</up>" +
                        "<rg>now you need \"e\"</rg>" +
                        "<dw>xxxxGx</dw>" +
                        "<rg>xxxxxx</rg>" +
                        "<up>xxxxxG</up>" +
                        "<rg>xxxxxx</rg>" +
                        "<dw>xxxxGxxxxxxxG</dw>" +
                        "<lf>Gxxxxxxx</lf>" +
                        "<up>xxxx</up>" +
                "<lf>\"b\" to get to here</lf>"

        );


        // All letters in the matrix are set to the lettertype of those in the string
        labyrinthText.batchSetLetterType("\"udwGoaltgh", LetterType.YELLOW, false);
        labyrinthText.batchSetLetterType("enonyrb", LetterType.RED, false);
        labyrinthText.batchSetLetterType(" ", LetterType.EMPATHY, false);

        // sets the goal. Extra step needed for right coloring of words
        labyrinthText.createMap("<dw>GOAL|</dw>", true, LetterType.WHITE_GREEN);
    }

    public void backgroundText(){

        String[] message = {"BORED?", "rest assured that my developer WILL make", "this game unbearably hard soon enough", "TIP: most levels can be done in", "one or three vim-moves"};

        backgroundText.setHorizontalStringArray(message, 15, 0, true, false, LetterType.GRAY);

        backgroundText.batchSetLetterType("BOREDWILLTIP?", LetterType.RED, true);
    }

    @Override
    protected void backgroundMusic() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(
                "sound/music/laborintMusic/labMusic1/labMusic1pcm.wav"));
    }
    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();


        if(cursor.isOnType(LetterType.GRAY)
        || cursor.isOnType(LetterType.EMPATHY)){
           cursor.dispose();
           cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);
        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);
        }


        //sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);

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
        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
            cursor.dispose();
            gsm.push(new Level_3(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        cam.update();

        levelChange();
    }

    @Override
    public void dispose() {
        cursor.dispose();

        backgroundMusic.dispose();

        for (int i = 0; i < vimMatrix.getCellMatrix().size() ; i++) {

            for (int j = 0; j < vimMatrix.getCellMatrix().get(i).size(); j++) {

                vimMatrix.getCellMatrix().get(i).get(j).dispose();

            }
        }
        System.out.println("Play State Disposed");
    }


}
