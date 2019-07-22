package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.VimObject.Cursor;
import com.kingofthevim.game.basicvim.LetterType;
import com.kingofthevim.game.basicvim.PointSystem;

import java.util.ArrayList;

public class Level_4 extends Level {

    public Level_4(GameStateManager gsm) {
        super(gsm);

        cursorStartRow = 16;
        cursorStartColumn = 0;
        pointsSys = new PointSystem(10);

        backgroundText();

        levelPath();
        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);

        backgroundMusic();
    }

    @Override
    protected void backgroundText(){

        ArrayList<String> conversionArray;

        conversionArray = backgroundText.makeStringArray("One morning, when Gregor Samsa woke from troubled dreams, he found" +
                "himself transformed in his bed into a horrible vermin.  He lay on" +
                "his armour-like back, and if he lifted his head a little he could" +
                "see his brown belly, slightly domed and divided by arches into stiff" +
                "sections.  The bedding was hardly able to cover it and seemed ready" +
                "to slide off any moment.  His many legs, pitifully thin compared" +
                "with the size of the rest of him, waved about helplessly as he" +
                "looked. " +
                "\"What's happened to me?\" he thought.  It wasn't a dream.  His room," +
                "a proper human room although a little too small, lay peacefully" +
                "between its four familiar walls.  A collection of textile samples" +
                "lay spread out on the table - Samsa was a travelling salesman - and" +
                "above it there hung a picture that he had recently cut out of an" +
                "illustrated magazine and housed in a nice, gilded frame.  It showed" +
                "a lady fitted out with a fur hat and fur boa who sat upright," +
                "raising a heavy fur muff that covered the whole of her lower arm" +
                "towards the viewer. " +
                "Gregor then turned to look out the window at the dull weather." +
                "Drops of rain could be heard hitting the pane, which made him feel" +
                "quite sad. \"How about if I sleep a little bit longer and forget all" +
                "this nonsense\", he thought, but that was something he was unable to" +
                "do because he was used to sleeping on his right, and in his present" +
                "state couldn't get into that position.  However hard he threw" +
                "himself onto his right, he always rolled back to where he was.  He" +
                "must have tried it a hundred times, shut his eyes so that he" +
                "wouldn't have to look at the floundering legs, and only stopped when" +
                "he began to feel a mild, dull pain there that he had never felt" +
                "before.", true);

        backgroundText.setHorizontalStringArray(conversionArray, 0, 0, false, true, LetterType.GRAY);
    }

    @Override
    protected void backgroundMusic() {
        if(backgroundMusic.isPlaying()){
            backgroundMusic.stop();
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(
                    "sound/music/laborintMusic/labMusic2pcm.wav"));
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
        }
    }

    @Override
    protected void levelPath() {
        tagBuiltLevel();

        labyrinthText.batchSetLetterType("O", LetterType.RED, false);
        labyrinthText.batchSetLetterType("X", LetterType.YELLOW, false);

    }

    private void tagBuiltLevel(){

        labyrinthText.createMap(
                "<<rw16>>" +
                        "<rg>####</rg>" +
                        "<up>NON|WAY</up>" +
                        "<rg>#####</rg>" +
                        "<dw>#########</dw>" +
                        "<rg>#####</rg>" +
                        "<up>###########</up>" +
                        "<lf>##########</lf>" +
                        "<up>##</up>" +
                        "<rg>##############</rg>" +
                        "<dw>##############</dw>"+
                        "<rg>##############</rg>"
        );
        labyrinthText.createMap("<rg>green</rg>", true, LetterType.WHITE_GREEN);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }

    @Override
    protected void levelChange() {
        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
            dispose();
            gsm.push(new Level_1(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        //Tells GDX that cam been repositioned.
        cam.update();

        levelChange();
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundMusic.dispose();
        System.out.println("Play State Disposed");
    }
}
