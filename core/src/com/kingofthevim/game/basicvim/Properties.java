package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.kingofthevim.game.basicvim.Matrix.LetterType;
import com.kingofthevim.game.basicvim.VimObject.Size;

import java.util.ArrayList;

public class Properties {

    //TODO rename to letterChar
    private char cellChar = ' ';
    private LetterType letterType = LetterType.EMPATHY;
    private int row;
    private int column;

    public Properties(){}
    public Properties(char cellChar, LetterType letterType){
        this.cellChar = cellChar;
        this.letterType = letterType;

    }
    public Properties(char cellChar, LetterType letterType, int row, int column){
        this(cellChar, letterType);
        this.row = row;
        this.column = column;
    }

    public char getCellChar() {
        return cellChar;
    }

    public void setCellChar(char cellChar) {
        this.cellChar = cellChar;
    }

    public LetterType getLetterType() {
        return letterType;
    }

    public void setLetterType(LetterType letterType) {
        this.letterType = letterType;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
