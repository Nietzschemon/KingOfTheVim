package com.kingofthevim.game.basicvim;

//TODO make this the display class that sends the data to PlayState
// it allways contains a Cell-objects which the other objects.
// manipulate.
public class VimWorld  {

    VimWorldMatrix vimMatrix;

    Cursor cursor;
    LetterManager lineEditor;

    //TODO location

    /**cursor.getRow
     * cursor.getColum
     *
     *
     * WITH: static matrix that is extended to cursor and the others
     * JUST: cursor.update
     * THEN: matrix.update -> matrix.getMatrix and draw it. -> matrix.dispose
     *
     *
     */

    public VimWorld(VimWorldMatrix vimMatrix){
        this.vimMatrix = vimMatrix;

    }
    public VimWorld(int fontWidth, int fontHeight){
    }




}