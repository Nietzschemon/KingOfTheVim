package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.*;
import com.kingofthevim.game.basicvim.VimObject.Cursor;

public class Level_1 extends Level{

    public Level_1(GameStateManager gsm) {
        super(gsm);

        cursorStartRow = 4;
        cursorStartColumn = 8;

        pointsSys = new PointSystem(10);
        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);

        backgroundText();

        backgroundMusic();
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
                "<lf>in-one-move?</lf>"
        );

        // All letters in the matrix are set to the lettertype of those in the string
        labyrinthText.batchSetLetterType("hjkl", LetterType.YELLOW, false);
        labyrinthText.batchSetLetterType("X-|", LetterType.RED, false);

        // sets the goal. Extra step needed for right coloring of words
        labyrinthText.createMap("<up>GOAL|</up>", true, LetterType.WHITE_GREEN);

    }

    public void backgroundText(){
        String[] welcome = {"welcome to king of the vim!", "this is the starting level where we", "learn the basics of vim" };

        String[] warning = {"WARNING", "gray letters kill you", "and so do empty spaces"};

        backgroundText.setHorizontalStringArray(welcome, 0, 0, false, false, LetterType.GRAY);
        backgroundText.setHorizontalStringArray(warning, 15, 0, true, false, LetterType.GRAY);

        backgroundText.batchSetLetterType("WARNING", LetterType.RED, true);
    }

    @Override
    protected void backgroundMusic() {
        if(backgroundMusic != null
                && backgroundMusic.isPlaying()) backgroundMusic.stop();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(
                "sound/music/laborintMusic/labMusic1/labMusic1pcm.wav"));
        backgroundMusic.play();
    }
    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        // Shows sprite-batch where to draw things on screen.

    }

    @Override
    protected void levelChange() {

        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
            gsm.push(new Level_2(gsm));
            dispose();
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
    }

}

