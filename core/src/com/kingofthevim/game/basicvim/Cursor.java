package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

//TODO TEXTURES look into TextureAtlas and sprites to see if the
// visual effect in spacemacs of a combination of the cursor and
// a letter in spacemacs can be achieved.
public class Cursor implements VimObject {


    //<editor-fold desc="Fields">
    private VimWorldMatrix vimMatrix;

    private int moveCounter = 0;
    private int movesLeft = 10;

    private ArrayList<ArrayList<Cell>> cellMatrix;

    private Position position;

    //TODO remove and put as parameters in constructor
    private Size cursorSize;
    private Texture texture;

    private PointSystem points;

    private InputManager inputManager;
    //</editor-fold desc="bla">


    //TODO inherit from cell

    public Cursor(VimWorldMatrix vimMatrix, int startRow, int startRowCell, PointSystem points){

        cursorSize = new Size(22, 44);

        this.vimMatrix = vimMatrix;
        cellMatrix = vimMatrix.getCellMatrix();


        position = new Position(this, startRow, startRowCell, VimWorldMatrix.rowTotal, VimWorldMatrix.colunmTotal);

        texture = new Texture("markers/marker_44purple.png");

        //mover = new Movement();
        this.points = points;

        inputManager = new InputManager(this);
    }

    public void dispose(){
        texture.dispose();
    }



    @Override
    public void update(){
        //doBeforePosiUpdate();
        //mover.move(this);
        //inputManager.getAction().setObject(this);
        //doAfterPosiUpdate();
    }

    private void doBeforePosiUpdate(){
        int currRow = position.getCurrRow();
        int currColumn = position.getCurrColumn();

        cellMatrix.get(currRow).get(currColumn).setCellLookToDefault();
    }

    //TODO make a general method that looks what color the cursor is
    // and the letter according to a scheme
    private void doAfterPosiUpdate(){

        int currRow = position.getCurrRow();
        int currColumn = position.getCurrColumn();

        if(isOnType(LetterType.RED)){
            cellMatrix.get(currRow).get(currColumn).setCellLookTemp(LetterType.WHITE_RED);
        }

        if(isOnType(LetterType.YELLOW)){
            cellMatrix.get(currRow).get(currColumn).setCellLookTemp(LetterType.BLACK_YELLOW);
        }

        if(isOnType(LetterType.WHITE)){
            cellMatrix.get(currRow).get(currColumn).setCellLookTemp(LetterType.BLACK);
        }

        if(isOnType(LetterType.WHITE_GREEN)){
            cellMatrix.get(currRow).get(currColumn).setCellLookTemp(LetterType.WHITE_PURPLE);
        }
    }


    @Override
    public boolean isOnLetter(char letter){

        int currRow = position.getCurrRow();
        int currColumn = position.getCurrColumn();

        if(cellMatrix.get(currRow).get(currColumn).getCellChar() == letter){
            //System.out.println("is on letter \"" + letter + "\"");
        }
        return cellMatrix.get(currRow).get(currColumn).getCellChar() == letter;
    }


    @Override
    public boolean isOnType(LetterType type){
        int currRow = position.getCurrRow();
        int currColumn = position.getCurrColumn();

        if(cellMatrix.get(currRow).get(currColumn).getLetterType() == type){
            //System.out.println("is on type \"" + type + "\"");
        }
        return cellMatrix.get(currRow).get(currColumn).getLetterType() == type;
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
    public VimWorldMatrix getVimMatrix() {
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
    //</editor-fold desc="bla">
}
