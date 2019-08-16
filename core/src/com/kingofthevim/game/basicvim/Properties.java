package com.kingofthevim.game.basicvim;

import com.kingofthevim.game.basicvim.Matrix.LetterType;

public class Properties {

    private char keyChar = ' ';
    private LetterType letterType = LetterType.EMPATHY;
    private int row;
    private int column;

    public Properties(){}
    public Properties(char keyChar, LetterType letterType){
        this.keyChar = keyChar;
        this.letterType = letterType;

    }
    public Properties(char keyChar, LetterType letterType, int row, int column){
        this(keyChar, letterType);
        this.row = row;
        this.column = column;
    }

    public char getKeyChar() {
        return keyChar;
    }

    public void setKeyChar(char keyChar) {
        this.keyChar = keyChar;
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
