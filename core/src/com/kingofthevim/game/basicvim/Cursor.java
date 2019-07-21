package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

//TODO TEXTURES look into TextureAtlas and sprites to see if the
// visual effect in spacemacs of a combination of the cursor and
// a letter in spacemacs can be achieved.
public class Cursor implements VimObject {


    //<editor-fold desc="Fields">
    private VimWorldMatrix vimMatrix;

    private int moveCounter = 0;
    private int movesLeft = 10;

    private int rowTotal;
    private int colunmTotal;
    private ArrayList<ArrayList<Cell>> cellMatrix;

    private Position position;

    //TODO remove and put as parameters in constructor
    private Size cursorSize;
    private Texture texture;

    private PointSystem points;

    private Movement mover;

    //</editor-fold desc="bla">


    //TODO inherit from cell

    public Cursor(VimWorldMatrix vimMatrix, int startRow, int startRowCell, PointSystem points){

        cursorSize = new Size(22, 44);

        this.vimMatrix = vimMatrix;
        cellMatrix = vimMatrix.getCellMatrix();
        rowTotal = VimWorldMatrix.rowTotal;
        colunmTotal = VimWorldMatrix.colunmTotal;


        position = new Position(this, startRow, startRowCell);

        //position = new Vector2(cellMatrix.get(startRow).get(startRowCell).getCartesianPosition());

        texture = new Texture("markers/marker_44purple.png");

        mover = new Movement();
        this.points = points;
    }

    public void dispose(){
        texture.dispose();
    }


    @Override
    public boolean setRelativeRow(int rowMove) {

        if(rowMove != 0){
            doBeforePosiUpdate();

            position.setCurrRow(position.getCurrRow() + rowMove);
            points.onMove(this);

            doAfterPosiUpdate();

            return true;
        }

        return false;
    }


    @Override
    public boolean setRelativeColumn(int columnMove) {

        if(columnMove != 0){
            doBeforePosiUpdate();

            position.setCurrColumn(position.getCurrColumn() + columnMove);
            points.onMove(this);

            doAfterPosiUpdate();
            return true;
        }

        return false;
    }

    /**
     * Moves the cursor directly to
     * the specified row
     * @param row to move cursor to
     * @return true if success, false if not
     */
    @Override
    public boolean setAbsoluteRow(int row){

        if(row >= 0
                && row < colunmTotal){
            doBeforePosiUpdate();

            position.setCurrRow(row);
            points.onMove(this);

            doAfterPosiUpdate();
            return true;
        }

        return false;
    }

    /**
     * Moves the cursor directly to
     * the specified column
     * @param column to move cursor to
     * @return true if success, false if not
     */
    @Override
    public boolean setAbsoluteColumn(int column){

        if(column >= 0
        && column < colunmTotal){
            doBeforePosiUpdate();

            position.setCurrColumn(column);
            points.onMove(this);

            doAfterPosiUpdate();
            return true;
        }

        return false;
    }

    @Override
    public void update(){

        setRelativeRow(mover.verticalMove(this));
        setRelativeColumn(mover.horizontalMove(this));
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
    public int getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(int rowTotal) {
        this.rowTotal = rowTotal;
    }

    @Override
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

    @Override
    public int getCurrRow() {
        return position.getCurrRow();
    }

    public void setCurrRow(int currRow) {
        this.position.setCurrRow(currRow);
    }

    @Override
    public int getCurrColumn() {
        return position.getCurrColumn();
    }

    public void setCurrColumn(int currColumn) {
        this.position.setCurrColumn(currColumn);
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

    }
    //</editor-fold desc="bla">
}
