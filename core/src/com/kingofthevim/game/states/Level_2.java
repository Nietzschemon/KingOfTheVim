package com.kingofthevim.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.Cell;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.LetterType;

import java.util.ArrayList;

public class Level_2 extends Level {


    private final int cursorStartRow = 4;
    private final int cursorStartColumn = 8;

    public Level_2(GameStateManager gsm) {
        super(gsm);

        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);

        backgroundText();

        levelPath();
    }

    @Override
    protected void levelPath() {

        labyrinthText.createMap("<<cl04,02>>" +
                "<rg>xxxxxx</rg>"
        );


        // All letters in the matrix are set to the lettertype of those in the string
        labyrinthText.setLetterType("hjkl", LetterType.YELLOW, false);
        labyrinthText.setLetterType("X-|", LetterType.RED, false);
    }

    public void backgroundText(){

        String[] message = {"BORED?", "rest assured that my developer WILL make", "this game unbearably hard soon enough", "TIP: most levels can be done in", "one or three vim-moves"};

        backgroundText.setHorizontalStringArray(message, 15, 0, true, LetterType.GRAY);

        backgroundText.setLetterType("BOREDWILLTIP?", LetterType.RED, true);
    }

    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();


        if(cursor.isOnType(LetterType.GRAY)
        || cursor.isOnType(LetterType.EMPATHY)){
           cursor.dispose();
           cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);
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
