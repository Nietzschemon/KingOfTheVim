package com.kingofthevim.game.basicvim;

//TODO make this the display class that sends the data to PlayState
// it allways contains a Cell-objects which the other objects.
// manipulate.
public class VimWorld extends VimWorldMatrix {

    Cursor cursor;
    LetterManager lineEditor;

    public VimWorld(int fontWidth, int fontHeight){
        super(fontWidth, fontHeight);

    }


}