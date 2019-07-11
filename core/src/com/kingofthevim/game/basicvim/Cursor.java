package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

//TODO TEXTURES look into TextureAtlas and sprites to see if the
// visual effect in spacemacs of a combination of the cursor and
// a letter in spacemacs can be achieved.
public class Cursor {


    //<editor-fold desc="Fields">
    private int moveCounter = 0;
    private int movesLeft = 10;



    private int rowTotal;
    private int colunmTotal;
    private ArrayList<ArrayList<Cell>> cellMatrix;

    private int currRow;
    private int currColumn;

    private Vector2 position;

    //TODO remove and put as parameters in constructor
    private int cellWidth = 22;
    private int cellHeight = 44;
    private Texture texture;

    private PointSystem points;

    private Movement mover;

    //</editor-fold desc="bla">


    //TODO inherit from cell

    public Cursor(VimWorldMatrix vimMatrix, int startRow, int startRowCell, PointSystem points){

        cellMatrix = vimMatrix.getCellMatrix();
        rowTotal = VimWorldMatrix.rowTotal;
        colunmTotal = VimWorldMatrix.colunmTotal;



        position = new Vector2(cellMatrix.get(startRow).get(startRowCell).getCartesianPosition());

        texture = new Texture("markers/marker_44purple.png");

        currRow = startRow;
        currColumn = startRowCell;

        mover = new Movement();
        this.points = points;
    }

    public void dispose(){
        texture.dispose();
    }


    public boolean setRow(int rowMove) {

        if(rowMove != 0){
            doBeforePosiUpdate();

            position.y = position.y + (cellHeight * rowMove);
            currRow += rowMove;
            points.onMove(this);

            doAfterPosiUpdate();

            return true;
        }

        return false;
    }

    //TODO make default reset for fonts work
    public boolean setColumn(int columnMove) {

        if(columnMove != 0){
            doBeforePosiUpdate();

            position.x = position.x + (cellWidth * columnMove);
            currColumn += columnMove;
            points.onMove(this);

            doAfterPosiUpdate();
            return true;
        }

        return false;
    }

    public void update(){

        setRow(mover.verticalMove(this));
        setColumn(mover.horizontalMove(this));
    }

    private void doBeforePosiUpdate(){
        cellMatrix.get(currRow).get(currColumn).setCellLookToDefault();
    }

    //TODO make a general method that looks what color the cursor is
    // and the letter according to a scheme
    private void doAfterPosiUpdate(){

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



    public boolean isOnLetter(char letter){
        if(cellMatrix.get(currRow).get(currColumn).getCellChar() == letter){
            System.out.println("is on letter \"" + letter + "\"");
        }
        return cellMatrix.get(currRow).get(currColumn).getCellChar() == letter;
    }

    public boolean isOnType(LetterType type){
        if(cellMatrix.get(currRow).get(currColumn).getLetterType() == type){
            System.out.println("is on type \"" + type + "\"");
        }
        return cellMatrix.get(currRow).get(currColumn).getLetterType() == type;
    }

    //<editor-fold desc="Getters and setters">

    public Texture getTexture(){
        return texture;
    }

    public Vector2 getPosition(){
        return position;
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

    public int getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(int rowTotal) {
        this.rowTotal = rowTotal;
    }

    public int getColunmTotal() {
        return colunmTotal;
    }

    public void setColunmTotal(int colunmTotal) {
        this.colunmTotal = colunmTotal;
    }

    public ArrayList<ArrayList<Cell>> getCellMatrix() {
        return cellMatrix;
    }

    public void setCellMatrix(ArrayList<ArrayList<Cell>> cellMatrix) {
        this.cellMatrix = cellMatrix;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public void setCurrColumn(int currColumn) {
        this.currColumn = currColumn;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Movement getMover() {
        return mover;
    }

    public void setMover(Movement mover) {
        this.mover = mover;
    }
    //</editor-fold desc="bla">
}
