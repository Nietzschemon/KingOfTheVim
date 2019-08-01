package com.kingofthevim.game.basicvim.Actions;

import com.badlogic.gdx.Input;

public class VimMove {
    public char move;
    public int iteration;
    public char operator;

    public void add(char move, int iteration){
        this.move = move;
        this.iteration = iteration;
    }

    public void add(int iteration, char operator){
        this.iteration = iteration;
        this.operator = operator;
    }

    public void add(char move, char operator){
        this.move = move;
        this.operator = operator;
    }

    public void add(boolean shiftHeld, int iteration, int operatorKeyCode){
        String keyString = Input.Keys.toString(operatorKeyCode);

        this.move = (shiftHeld) ? keyString.charAt(0) : keyString.toLowerCase().charAt(0);
        this.iteration = iteration;
    }

    public void add(int moveKeyCode, int iteration, boolean shiftHeld){
        String keyString = Input.Keys.toString(moveKeyCode);

        this.move = (shiftHeld) ? keyString.charAt(0) : keyString.toLowerCase().charAt(0);
        this.iteration = iteration;
    }

    public void add(char move, int iteration, char operator){
        this.move = move;
        this.iteration = iteration;
        this.operator = operator;
    }
}
