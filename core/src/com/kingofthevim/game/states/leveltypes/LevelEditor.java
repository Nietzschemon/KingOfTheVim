package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.kingofthevim.game.engine.*;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterManager;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_object.Cursor;
import com.kingofthevim.game.gametype.FallMechanic;
import com.kingofthevim.game.states.GameStateManager;
import com.kingofthevim.game.states.Menu;

import java.util.ArrayList;

public class LevelEditor extends Level{

    FallMechanic fall;
    MatrixSerialization serialization;
    private boolean testMode = false;
    private boolean fallMode = false;
    private int startColumn = 0;
    private int startRow = 0;
    private LetterManager backgroundText;
    private FileHandle[] texts;
    private int fileIndex = 0;

    public LevelEditor(GameStateManager gsm) {
        super(gsm);

        backgroundText = new LetterManager(vimMatrix);
        cursorStartRow = 2;
        cursorStartColumn = 0;

        pointsSys = new ScoreSystem();


        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);
        fall = new FallMechanic(cursor);

        gameSound.stopMusic();
        serialization = new MatrixSerialization(cursor);

        texts = Gdx.files.internal("gamedata/texts").list();
    }

    @Override
    protected void backgroundMusic() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(
                "sound/music/laborintMusic/labMusic1/labMusic1pcm.wav"));

        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }


    private void vimWordObjectCourse(){
        letterAdder.setHorizontalString("word word word word", 0,0, LetterType.WHITE);
        letterAdder.setHorizontalString("word. word. word. word.", 1,0, LetterType.WHITE);
        letterAdder.setHorizontalString(".word. .word. .word. .word.", 2,0, LetterType.WHITE);
        letterAdder.setHorizontalString("..wo!rd. .wo!rd. .wo!rd. wo!rd.  ", 3,0, LetterType.WHITE);
        letterAdder.setHorizontalString("!!!word !!word !!word !word ", 4,0, LetterType.WHITE);
        letterAdder.setHorizontalString("{{word} {word} {word} word} ", 5,0, LetterType.WHITE);
        letterAdder.setHorizontalString("word () word () word () ord () ", 6,0, LetterType.WHITE);
        letterAdder.setHorizontalString("!!!word!!!! !!!word!!! !!!word!!! !!!word!!!", 7,0, LetterType.WHITE);
        letterAdder.setHorizontalString("word.0!) word.0!) word.0!) word.0!)", 8,0, LetterType.WHITE);
        letterAdder.setHorizontalString("word    word    word    word", 9,0, LetterType.WHITE);
    }


    @Override
    public void render(SpriteBatch sb) {
        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        functionKeys();
        sb.begin();

        if(fallMode){
            if(! fall.onGround()){
                fall.fall();
            }
            if(cursor.isOnType(LetterType.GRAY)){
                cursor.getPosition().setAbsolutePosition(startRow, startColumn);
            }
        }
        if(testMode
        && (cursor.isOnType(LetterType.GRAY)
        || cursor.isOnType(LetterType.EMPATHY))){
            cursor.getPosition().setAbsolutePosition(startRow, startColumn);

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

        if(cursor.isInMode){
            sb.draw(cursor.getTexture(), cursor.getPosition().getCartesianPosition().x, cursor.getPosition().getCartesianPosition().y);
        }

        sb.end();

    }
    private String nextText(){
        if(texts.length <= fileIndex) fileIndex = 0;
        String text = texts[fileIndex].readString();
        fileIndex++;
        return text;
    }

    private boolean functionKeys(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.F1)){
            cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
            int currColumn = cursor.getPosition().getCurrColumn();
            int currRow = cursor.getPosition().getCurrRow();
            cursor.getPosition().setAbsolutePosition(startRow, startColumn);
            serialization.saveAll();
            cursor.getPosition().setAbsolutePosition(currRow, currColumn);
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F3)){
            prose();
            return true;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F4)){
            startColumn = cursor.getPosition().getCurrColumn();
            startRow = cursor.getPosition().getCurrRow();
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F5)){
            gsm.push(new Menu(gsm));
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F6)){
            cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
            cursor.getPosition().setAbsolutePosition(startRow, startColumn);
            serialization.saveAll();
            serialization.listFiles();
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F7)){
            serialization.loadPreviousFile();
            cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
            setCurrPosAsStartPos();
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F8)){
            serialization.loadNextFile();
            cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
            setCurrPosAsStartPos();
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F9)){
            serialization.loadAll();
            setCurrPosAsStartPos();
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F11)){
            fallMode = !fallMode;
            if(fallMode){
                cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
            }
            else {
                cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
            }
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F12)){
            testMode = !testMode;

            if(testMode){
                cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
            }
            else {
                cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
            }
            return true;
        }

        return false;
    }

    /**
     * Mainly used when loading levels to set
     * starting position from save
     */
    private void setCurrPosAsStartPos(){
        startColumn = cursor.getPosition().getCurrColumn();
        startRow = cursor.getPosition().getCurrRow();
    }

    @Override
    protected void levelChange() {
        /*
        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
            dispose();
            gsm.push(new MenuState(gsm));
        }
         */
    }

    @Override
    public void update(float dt) {
        handleInput();

        //fall.timeBeforeFall(dt, 0.4f);
        //Tells GDX that cam been repositioned.
        cam.update();

        levelChange();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void prose(){

        ArrayList<String> conversionArray;

        conversionArray = backgroundText.makeStringArray(nextText(), false);
        vimMatrix.clearAllCells();

        /*
        conversionArray = backgroundText.makeStringArray("One morning, when Stefan Ekblom woke from troubled dreams, he found " +
                "himself transformed in his bed into a horrible coder. He lay on " +
                "his armour-like back, and if he lifted his head a little he could " +
                "see his big belly, slightly domed and divided by arches. " +
                "The bedding was hardly able to cover it and seemed ready " +
                "to slide off any moment. His two legs, pitifully thin compared " +
                "with the size of the rest of him, waved about helplessly as he " +
                "looked. " +
                "\"What's happened to me?\" he thought. It wasn't a new game. His score, " +
                "a proper human score although a little too small, lay peacefully " +
                "between its four familiar walls. A collection of code samples " +
                "lay spread out on the desktop - Stefan loved options - and " +
                "above it there hung a picture that he had recently cut out of an " +
                "illustrated magazine. It showed " +
                "Linus Torvalds fitted out with a fur hat and fur boa who sat upright, " +
                "raising a heavy fur muff that covered the whole of his lower arm " +
                "towards the viewer. " +
                "Gregor then turned to look out the window at the dull weather. " +
                "Drops of rain could be heard hitting the pane, which made him feel " +
                "quite sad. \"How about if I sleep a little bit longer and forget all " +
                "this nonsense\", he thought, but that was something he was unable to " +
                "do because he was used to sleeping on his right, and in his present " +
                "state couldn't get into that position.  However hard he threw " +
                "himself onto his right, he always rolled back to where he was.  He " +
                "must have tried it a hundred times, shut his eyes so that he " +
                "wouldn't have to look at the floundering legs, and only stopped when " +
                "he began to feel a mild, dull pain there that he had never felt " +
                "before.", false);

         */

        backgroundText.setHorizontalStringArray(conversionArray, 0, 0, LetterType.GRAY);
    }
}
