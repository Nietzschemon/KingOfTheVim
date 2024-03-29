package com.kingofthevim.game.engine.vim_modes.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kingofthevim.game.engine.vim_modes.listeners.DeleteModeListener;
import com.kingofthevim.game.engine.vim_modes.listeners.InsertModeListener;
import com.kingofthevim.game.engine.vim_modes.listeners.ModeListener;
import com.kingofthevim.game.engine.vim_modes.listeners.ReplaceModeListener;
import com.kingofthevim.game.engine.vim_modes.VimMove;
import com.kingofthevim.game.engine.vim_modes.VimMovement;
import com.kingofthevim.game.engine.vim_object.Cursor;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.kingofthevim.game.engine.matrix.Tools.tryParseInt;

public class InputManager implements InputProcessor {


    Cursor cursor;
    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    MoveInput moveInput;
    DeleteModeInput deleteModeInput;
    BuildMode buildMode;
    ReplaceModeInput replaceModeInput;
    InsertModeInput insertMode;
    private ArrayList<ReplaceModeListener> replaceModeListeners = new ArrayList<>();
    private ArrayList<InsertModeListener> insertModeListeners = new ArrayList<>();
    private ArrayList<DeleteModeListener> deleteModeListeners = new ArrayList<>();

    private LinkedList<Character> inputHistory;

    int iterationInt = 0;
    String iterationString = "0";
    boolean addToHistory = false;

    ArrayList<VimMove> vimMoveList;


    private boolean buildModeEnabled = false;


    public InputManager(Cursor cursor){
        this.cursor = cursor;
        inputHistory = new LinkedList<>();
        moveInput = new MoveInput(cursor);
        deleteModeInput = new DeleteModeInput(cursor);
        buildMode = new BuildMode(cursor);
        replaceModeInput = new ReplaceModeInput(cursor);
        insertMode = new InsertModeInput(cursor);
        vimMoveList = new ArrayList<>();

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(moveInput);
        Gdx.input.setInputProcessor(inputMultiplexer);
        addModeListener(cursor);
    }

    public InputManager(Cursor cursor, boolean buildMOdeEnabled){
        this(cursor);
        this.buildModeEnabled = buildMOdeEnabled;
    }

    @Override
    public boolean keyDown(int keycode) {
        addToHistory = true;
        iterationSync();

        switch (keycode){

            case Input.Keys.D:
                inputMultiplexer.removeProcessor(moveInput);
                inputMultiplexer.addProcessor(1, deleteModeInput);
                System.out.println("D pressed");
                deleteModeInput.hasExectued = false;
                inDeleteModeChanged(true);
                return true;

            case Input.Keys.F12:
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
                        && Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                    buildModeEnabled = true;
                    return true;
                }
                return false;

            case Input.Keys.TAB:
                if(buildModeEnabled)
                inputMultiplexer.addProcessor(0, buildMode);
                return true;

            case Input.Keys.NUM_1:
                return integerMaker('1');

            case Input.Keys.NUM_2:
                return integerMaker('2');

            case Input.Keys.NUM_3:
                return integerMaker('3');

            case Input.Keys.NUM_4:
                return integerMaker('4');

            case Input.Keys.NUM_5:
                return integerMaker('5');

            case Input.Keys.NUM_6:
                return integerMaker('6');

            case Input.Keys.NUM_7:
                return integerMaker('7');

            case Input.Keys.NUM_8:
                return integerMaker('8');

            case Input.Keys.NUM_9:
                return integerMaker('9');

            case Input.Keys.NUM_0:
                return integerMaker('0');

            case Input.Keys.R:
                if(checkIfNormalMode()){
                    inputMultiplexer.addProcessor(0, replaceModeInput);
                    inputMultiplexer.removeProcessor(moveInput);
                    replaceModeInput.hasExecuted = false;
                    inReplaceModeChanged(true);
                    replaceModeInput.muteInput = true;

                    if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                        replaceModeInput.operatorChar = 'R';
                    }
                    else {
                        replaceModeInput.operatorChar = 'r';
                    }

                    return true;
                }
                return false;


            case Input.Keys.I:
                inputMultiplexer.addProcessor(0, insertMode);
                insertMode.muteInput = true;
                inInsertModeChanged(true);
                return true;

            case Input.Keys.ESCAPE:
                resetIteration();
                resetInputProcessors();
                System.out.println("ESC");
                return true;

        }

        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        addToHistory = false;

        if(deleteModeInput.hasExectued){
            inputMultiplexer.removeProcessor(deleteModeInput);
            inputMultiplexer.addProcessor(1, moveInput);
            inDeleteModeChanged(false);
        }

        if(replaceModeInput.hasExecuted){
            inputMultiplexer.removeProcessor(replaceModeInput);
            inputMultiplexer.addProcessor(1, moveInput);
            inReplaceModeChanged(false);
        }
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        if(addToHistory) inputHistory.add(character);

        return false;
    }


    /**
     * adds a legit VimMove to history
     */
    private void addVimMove(VimMovement vimMove){

        if(vimMove.hasMove()){
            vimMoveList.add(vimMove.getResetVimMove());
        }
    }

    private boolean checkIfNormalMode(){
        SnapshotArray<InputProcessor> processors = inputMultiplexer.getProcessors();

        for (InputProcessor p : processors) {
            if (p.equals(moveInput)) {
                return true;
            }
        }
        return false;
    }

    private void resetInputProcessors(){
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(1, moveInput);
        inReplaceModeChanged(false);
        inInsertModeChanged(false);
        inDeleteModeChanged(false);
    }

    private void iterationSync(){
        if(deleteModeInput.hasExectued
                || moveInput.hasExectued
                || replaceModeInput.hasExecuted){

            addVimMove(moveInput);
            addVimMove(deleteModeInput);


            deleteModeInput.iteration = 0;
            moveInput.iteration = 0;
            iterationInt = 0;
            iterationString = "0";
            deleteModeInput.hasExectued = false;
            moveInput.hasExectued = false;
            replaceModeInput.hasExecuted = false;

        }

        if(iterationInt > 0){
            deleteModeInput.iteration = iterationInt;
            moveInput.iteration = iterationInt;
        }
    }

    /**
     * Gives back the second last char
     * from the input history.
     * @return the second last char typed
     */
    private char oneBeforeLast(){
        if(inputHistory.size() > 0){
            return inputHistory.get(inputHistory.size() - 1);
        }
        return ' ';
    }

    /**
     * checks if a char can be converted to an int
     * and if so adds it to iterationInt, if not iterationInt
     * is reset
     * @param intProspect char to check for int
     *                    conversion possibility
     * @return true if intProspect could be parsed
     */
    public boolean integerMaker(char intProspect){

        if(intProspect > 47 &&
                intProspect < 58){
            int integer;
            iterationString += String.valueOf(intProspect);
            integer = tryParseInt(iterationString);

            if(iterationInt + integer == 0){
                iterationString = "0";
                return false;
            }

            iterationInt = integer;

            System.out.println("it-int: " + iterationInt);
            return true;
        }
        return false;
    }


    //TODO make iteration work with ONE!
    void resetIteration(){
        iterationString = "0";
        iterationInt = 0;
        moveInput.iteration = 0;
        deleteModeInput.iteration = 0;
    }


    public void addModeListener(ModeListener listener){

        insertModeListeners.add(listener);
        replaceModeListeners.add(listener);
        deleteModeListeners.add(listener);
        deleteModeInput.addListener(listener);
    }


    public void removeModeListener(ModeListener listener) {
        insertModeListeners.remove(listener);
        replaceModeListeners.remove(listener);
        deleteModeListeners.remove(listener);
        deleteModeInput.removeListener(listener);
    }

    /**
     * Fires replaceMode enter- or exit-event depending
     * on if modeActive is true or false
     * @param modeActive controls which event is fired
     */
    private void inReplaceModeChanged(boolean modeActive){
        if(modeActive){
            for (ReplaceModeListener c : replaceModeListeners){
                c.onReplaceModeEnter();
            }
        }
        else {
            for (ReplaceModeListener c : replaceModeListeners){
                c.onReplaceModeExit();
            }
        }
    }

    /**
     * Fires insert-mode enter- or exit-event depending
     * on if modeActive is true or false
     * @param modeActive controls which event is fired
     */
    private void inInsertModeChanged(boolean modeActive){
        if(modeActive){
            for (InsertModeListener i : insertModeListeners){
                i.onInsertModeEnter();
            }
        }
        else {
            for (InsertModeListener i : insertModeListeners){
                i.onInsertModeExit();
            }
        }
    }

    /**
     * Fires insert-mode enter- or exit-event depending
     * on if modeActive is true or false
     * @param modeActive controls which event is fired
     */
    private void inDeleteModeChanged(boolean modeActive){
        if(modeActive){
            for (DeleteModeListener d : deleteModeListeners){
                d.onDeleteModeEnter();
            }
        }
        else {
            for (DeleteModeListener d : deleteModeListeners){
                d.onDeleteModeExit();
            }
        }
    }

    public boolean isBuildModeEnabled() {
        return buildModeEnabled;
    }

    public void setBuildModeEnabled(boolean buildModeEnabled) {
        this.buildModeEnabled = buildModeEnabled;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


    public boolean scrolled(int amount) {
        return false;
    }
}
