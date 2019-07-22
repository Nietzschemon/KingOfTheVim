package com.kingofthevim.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.VimObject.Cursor;
import com.kingofthevim.game.basicvim.LetterType;
import com.kingofthevim.game.basicvim.PointSystem;

public class Level_2 extends Level {

    public Level_2(GameStateManager gsm) {
        super(gsm);

        cursorStartRow = 0;
        cursorStartColumn = 0;
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

    }
    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        // Shows sprite-batch where to draw things on screen.

    }

    @Override
    protected void levelChange() {
        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
            dispose();
            gsm.push(new Level_3(gsm));
        }
    }

    @Override
    public void update(float dt) {
        levelChange();

        handleInput();

        cam.update();

    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("Play State Disposed");
    }

}
