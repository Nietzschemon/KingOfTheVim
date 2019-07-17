package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class Control extends InputAdapter {

    private char currChar = 0;

    Control(){
        Gdx.input.setInputProcessor(this);
    }


    public boolean keyPressedIsChar(char dasChar){

        if(currChar == dasChar){
            currChar = '0';
            return true;
        }
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        currChar = character;
        return false;
    }

}
