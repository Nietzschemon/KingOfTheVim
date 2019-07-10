package com.kingofthevim.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.*;

import java.util.ArrayList;

public class Level_1 extends Level{


    private final int cursorStartRow = 4;
    private final int cursorStartColumn = 8;

    public Level_1(GameStateManager gsm) {
        super(gsm);

        pointsSys = new PointSystem(10);
        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);

        backgroundText();

        levelPath();
    }

    @Override
    protected void levelPath() {

        //TODO make goal in font with background-color (and level names with same styling)
        labyrinthText.createMap("<<cl04,08>>" +
                "<rg>Press-l-to-move-RIGHT-j</rg>" +
                "<dw>|for|DOWN|</dw>" +
                "<lf+01>h-for-LEFT</lf>" +
                "<up>k|is|UP|</up>" +
                "<lf>xxx-</lf>" +
                "<dw>|xxxx</dw>" +
                "<lf>xxxxx-</lf>" +
                "<up>xx</up>" +
                "<lf>in-one-move?</lf>" +
                "<up>GOAL|</up>"

        );


        // All letters in the matrix are set to the lettertype of those in the string
        labyrinthText.setLetterType("hjkl", LetterType.YELLOW, false);
        labyrinthText.setLetterType("X-|", LetterType.RED, false);
    }

    public void backgroundText(){
        String[] welcome = {"welcome to king of the vim!", "this is the starting level where we", "learn the basics of vim" };

        String[] warning = {"WARNING", "gray letters kill you", "and so do empty spaces"};

        backgroundText.setHorizontalStringArray(welcome, 0, 0, false, LetterType.GRAY);
        backgroundText.setHorizontalStringArray(warning, 15, 0, true, LetterType.GRAY);

        backgroundText.setLetterType("WARNING", LetterType.RED, true);
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

