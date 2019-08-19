package com.kingofthevim.game.engine.vim_object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kingofthevim.game.engine.*;
import com.kingofthevim.game.engine.vim_modes.listeners.ModeListener;
import com.kingofthevim.game.engine.vim_modes.input.InputManager;
import com.kingofthevim.game.engine.matrix.Cell;
import com.kingofthevim.game.engine.matrix.LetterType;
import com.kingofthevim.game.engine.matrix.CellMatrix;

import java.util.ArrayList;

public class Cursor implements VimObject, ModeListener {


    //<editor-fold desc="Fields">
    private CellMatrix vimMatrix;

    private int moveCounter = 0;
    private int movesLeft = 10;

    private ArrayList<ArrayList<Cell>> cellMatrix;

    private Position position;

    private Size cursorSize;
    private Texture texture;

    private InputManager inputManager;

    private ScoreSystem scoreSystem;
    private boolean suspendVisual = false;

    //private GameSound gameSound;
    //</editor-fold desc="Fields">


    //TODO inherit from cell
    public Cursor(CellMatrix vimMatrix, int startRow, int startRowCell){

        cursorSize = new Size(22, 44);

        this.vimMatrix = vimMatrix;
        cellMatrix = vimMatrix.getCellMatrix();


        position = new Position(this, startRow, startRowCell, CellMatrix.getRowTotal(), CellMatrix.getColunmTotal());

        texture = new Texture("gamedata/textures/cursors/cursor_44purple.png");


        inputManager = new InputManager(this);

        //gameSound = new GameSound();

        //position.addListener(gameSound);
    }

    public void dispose(){
        texture.dispose();
    }



    @Override
    public void update(){
    }

    /**
     * Gets the current cell of the cursor
     * in the matrix.
     *
     * This is used as a shorthand for
     * a lot of things in the game.
     *
     * @return Current cell of cursor.
     */
    @Override
    public Cell getCurrentCell(){
        return this.cellMatrix.get(this.position.getCurrRow()).get(this.position.getCurrColumn());
    }

    @Override
    public void doBeforePosiUpdate(){
        this.getCurrentCell().setCellLookToDefault();
    }


    @Override
    public void doAfterPosiUpdate(){
        if(! suspendVisual)visualChanges();
        if(scoreSystem != null) scoreSystem.onMove(this);
    }

    /**
     * Handles VISUAL changes associated withe cursor.
     * Some methods in here can do other things, but
     * if they are here they are not used in that way.
     */
    private void visualChanges(){
        ifOnChangeTo(LetterType.RED, LetterType.WHITE_RED);
        ifOnChangeTo(LetterType.YELLOW, LetterType.BLACK_YELLOW);
        ifOnChangeTo(LetterType.WHITE, LetterType.BLACK);
        ifOnChangeTo(LetterType.WHITE_GREEN, LetterType.WHITE_PURPLE);

    }


    /**
     * Changes the underlying LetterType of the
     * cursor/cell when its on a specified LetterType.
     * @param isOn LetterType that triggers the change
     * @param changeTo LetterType to change to
     */
    public void ifOnChangeTo(LetterType isOn, LetterType changeTo){
        if(isOnType(isOn)){
            this.getCurrentCell().setCellLookTemp(changeTo);
        }
    }


    @Override
    public boolean isOnLetter(char letter){
        return this.getCurrentCell().getCellChar() == letter;
    }


    @Override
    public boolean isOnType(LetterType type){
        return this.getCurrentCell().getLetterType() == type;
    }

    //<editor-fold desc="Getters and setters">

    public Texture getTexture(){
        return texture;
    }


    public int getMoveCounter() {
        return moveCounter;
    }

    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    @Override
    public CellMatrix getVimMatrix() {
        return vimMatrix;
    }

    @Override
    public Size getSize() {
        return cursorSize;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {

        this.position = position;
    }

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }

    public void setScoreSystem(ScoreSystem scoreSystem) {
        this.scoreSystem = scoreSystem;
    }

    @Override
    public void onReplaceModeEnter() {
        texture.dispose();
        texture = new Texture("gamedata/textures/cursors/cursor_44white.png");
    }

    @Override
    public void onReplaceModeExit() {
        texture.dispose();
        texture = new Texture("gamedata/textures/cursors/cursor_44purple.png");
    }

    @Override
    public void onInsertModeEnter() {
        texture.dispose();
        suspendVisual = true;
        this.getCurrentCell().setCellLookToDefault();
        texture = new Texture("gamedata/textures/cursors/cursor_44green_insert.png");
    }

    @Override
    public void onInsertModeExit() {
        texture.dispose();
        suspendVisual = false;
        texture = new Texture("gamedata/textures/cursors/cursor_44purple.png");
    }
    //</editor-fold desc="bla">
}
