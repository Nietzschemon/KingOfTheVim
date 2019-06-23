package com.kingofthevim.game.basicvim;

import com.kingofthevim.game.KingOfTheVimMain;

import java.lang.reflect.Array;


//TODO make this the display class that sends the data to PlayState
// it allways contains a Cell-objects which the other objects.
// manipulate.
public abstract class Matrix {

    public static int fontWidth;
    public static int fontHeight;

    public static int[][] matrix;

    Matrix(int fontWidth, int fontHeight){

        //TODO se om talet är jämt
        //if(fontWidth / KingOfTheVimMain.WIDTH)
        matrix = new int[KingOfTheVimMain.WIDTH/fontWidth][KingOfTheVimMain.HEIGHT/fontHeight];

    }

}
