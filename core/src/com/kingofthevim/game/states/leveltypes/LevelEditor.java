package com.kingofthevim.game.states.leveltypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.kingofthevim.game.engine.*;
import com.kingofthevim.game.engine.serialization.MatrixSerialization;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterManager;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.vim_modes.input.InputManager;
import com.kingofthevim.game.engine.vim_object.Cursor;
import com.kingofthevim.game.gametype.FallMechanic;
import com.kingofthevim.game.states.GameStateManager;
import com.kingofthevim.game.states.Menu;
import com.kingofthevim.game.states.levelsettings.LevelSettingsDialog;
import com.kingofthevim.game.states.levelsettings.LevelSettingsListener;

import java.util.ArrayList;

public class LevelEditor extends Level implements LevelSettingsListener {

    FallMechanic fall;
    MatrixSerialization serialization;
    private boolean testMode = false;
    private boolean fallMode = false;
    private int startColumn = 0;
    private int startRow = 0;
    private LetterManager backgroundText;
    private FileHandle[] texts;
    private int fileIndex = 0;
    private LevelSettingsDialog dialog;

    public LevelEditor(GameStateManager gsm) {
        super(gsm);

        dialog = new LevelSettingsDialog(levelSettings);

        backgroundText = new LetterManager(vimMatrix);
        cursorStartRow = 2;
        cursorStartColumn = 0;

        pointsSys = new ScoreSystem();


        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);
        fall = new FallMechanic(cursor);

        musicManager.stopMusic();
        serialization = new MatrixSerialization(cursor);

        texts = Gdx.files.internal("gamedata/texts").list();
        vimMatrix.changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
        cursor.muteSoundEffects();

        dialog.addListener(this);
        hud.clearHud();
        hud.setFontScale(1.5f);
        cursor.getInputManager().setBuildModeEnabled(true);
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

        stage.act();
        stage.draw();
    }
    private String nextText(){
        if(texts.length <= fileIndex) fileIndex = 0;
        String text = texts[fileIndex].readString();
        hud.setText("Text: ", 0);
        hud.setText(texts[fileIndex].name(), 1);
        fileIndex++;
        return text;
    }

    private boolean functionKeys(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.F1)){
            cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
            int currColumn = cursor.getPosition().getCurrColumn();
            int currRow = cursor.getPosition().getCurrRow();
            cursor.getPosition().setAbsolutePosition(startRow, startColumn);

            hud.clearHud();

            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
                    || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                serialization.saveAll(serialization.getCurrentFilePath(), levelSettings);
                hud.setText("\"" + serialization.getCurrentFileName() + "\" was overwritten", 0);
            }else{
                 serialization.saveAll(levelSettings);
                 hud.setText("saved as \"" + serialization.getCurrentFileName() + "\"", 0);
            }


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
            hud.setText("start position set to row "
                    + startRow + " column: " + startColumn, 0);
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F5)){
            hud.clearHud();
            gsm.push(new Menu(gsm));
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F7)){
            hud.clearHud();
            serialization.loadPreviousFile();
            levelSettings = serialization.getLevelSettings();
            dialog.setLevelSettings(levelSettings);
            cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
            setCurrPosAsStartPos();
            hud.setText("current file: " + serialization.getCurrentFileName(), 0);
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F8)){
            hud.clearHud();
            serialization.loadNextFile();
            levelSettings = serialization.getLevelSettings();
            dialog.setLevelSettings(levelSettings);
            cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
            setCurrPosAsStartPos();
            hud.setText("current file: " + serialization.getCurrentFileName(), 0);
            return true;
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.F10)){
            dialog.setLevelSettings(levelSettings);
            dialog.showDialog(stage);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F11)){
            fallMode = !fallMode;
            if(fallMode){
                cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
                hud.setText("entered fall-mode", 0);
            }
            else {
                cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
                hud.setText("exited fall-mode", 0);
            }
            return true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F12)){
            testMode = !testMode;

            if(testMode){
                cursor.getVimMatrix().changeAllCellTypes(LetterType.WHITE, ' ', LetterType.EMPATHY);
                hud.setText("entered test-mode", 0);
            }
            else {
                cursor.getVimMatrix().changeAllCellTypes(LetterType.EMPATHY, LetterType.WHITE);
                hud.setText("exited test-mode", 0);
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
    protected void checkWinCondition() {
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

        checkWinCondition();
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

    @Override
    public void settingsChanged() {
        levelSettings = dialog.getLevelSettings();
        new InputManager(cursor).setBuildModeEnabled(true);
        hud.setText("Settings set", 0);
    }
}
