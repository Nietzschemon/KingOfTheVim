package com.kingofthevim.game.engine.vim_object;

public class Size {


    private int width = 22;
    private int height = 44;

    public Size(){

    }

    public Size(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
