package com.kingofthevim.game.basicvim;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//TODO LATER use properties to store the file paths.
// https://docs.oracle.com/javase/tutorial/essential/environment/properties.html

public class LetterManager {

    private Map<Character, String> letterFilePaths;

    private Array<Letter> letterArray;

    LetterManager(int rowN, int columN, String word ){

        letterFilePaths = new HashMap<>();
        letterFilePaths.put('A', "fontTest/A.png");
        letterFilePaths.put('B', "fontTest/B.png");
        letterFilePaths.put('C', "fontTest/C.png");

        //TODO move to seperate methods.
        for(int i = 1; i <= word.length(); i++){
            char charKey = word.charAt(i);
            System.out.println("char: " + charKey);
            letterArray.add(new Letter(i * 66, 0, 33, 66, letterFilePaths.get(charKey)));
        }
    }
}
