package com.kingofthevim.game.states.levelsettings;

import com.kingofthevim.game.engine.matrix.LetterType;

import java.util.ArrayList;

public class LevelSettings {

    public boolean cursorHasGravity = false;
    public boolean winOnDelete = false;
    public boolean winOnGoal = true;
    public boolean resetLevelAtDeath = false;
    public boolean resetIfDeathAndDeletion = false;

    public boolean guaranteeLetterTypes = true;
    public ArrayList<LetterType> letterTypesCheckList = new ArrayList<>();

    public boolean guaranteeWords = false;
    public ArrayList<String> wordCheckList = new ArrayList<>();
    
}
