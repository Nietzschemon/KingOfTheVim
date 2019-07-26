package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.graphics.Texture;
import com.kingofthevim.game.basicvim.Matrix.LetterType;

import java.util.ArrayList;

public class Properties {

    public char cellChar = ' ';
    public LetterType letterType = LetterType.EMPATHY;

    public Properties(){}
    public Properties(char cellChar, LetterType letterType){
        this.cellChar = cellChar;
        this.letterType = letterType;

    }

}
