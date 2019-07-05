package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.*;

import java.util.ArrayList;

public class TestLevel extends Level{

    private final int cursorStartRow = 0;
    private final int cursorStartColumn = 0;


    public TestLevel(GameStateManager gsm) {
        super(gsm);


        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);

        //loadBackgroundText();


        levelPath();


        /*
        labyrinthText.createMap("<rg>O123<45X</rg>" +
                                            "<dw>O123456X</dw>" +
                                            "<rg>O1234<5X</rg>" +
                                            "<up+01>O12X</up>");

         */

        // All letters in the matrix are set to the lettertype of those in the string

        labyrinthText.setLetterType("O", LetterType.RED, false);
        labyrinthText.setLetterType("X", LetterType.YELLOW, false);
        //labyrinthText.setLetterType("QWERTYUIOPASDFGHJKLZXCVBNM", LetterType.RED, false);
        //labyrinthText.setLetterType("BCRLcursor", LetterType.YELLOW, false);


    }

    @Override
    protected void levelPath() {
        labyrinthText.setHorizontalString("word word word word", 0,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word. word. word. word.", 1,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString(".word. .word. .word. .word.", 2,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("..wo!rd. .wo!rd. .wo!rd. wo!rd.  ", 3,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("!!!word !!word !!word !word ", 4,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("{{word} {word} {word} word} ", 5,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word () word () word () ord () ", 6,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("!!!word!!!! !!!word!!! !!!word!!! !!!word!!!", 7,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word.0!) word.0!) word.0!) word.0!)", 8,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word    word    word    word", 9,0,true,LetterType.WHITE);
    }


    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        /* //TODO create and put this into resetCursorMethod()
        if(cursor.isOnType(LetterType.GRAY)){
           cursor.dispose();
           cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);
        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);
        }

         */


        sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);

        for(ArrayList<Cell> cellRow : vimMatrix.getCellMatrix()){

            for(Cell cell : cellRow){

                if(cell.getCellLook() != null){
                    sb.draw(cell.getCellLook(),
                            cell.getCartesianPosition().x,
                            cell.getCartesianPosition().y);
                }
            }
        }

        sb.end();
    }

    @Override
    public void update(float dt) {
        handleInput();
        //cursor.update();

        //TODO The cam should be able to follow the y axis OR the x axis
        //cam.position.x = cursor.getPosition().x + 80;


        ////TODO reset cursor if it falls of.
        //if(cursor.getPosition() logic for on gray letters)
        //gsm.set(new MenuState(gsm));

        //Tells GDX that cam been repositioned.
        cam.update();

    }
    @Override
    public void dispose() {
        cursor.dispose();

        for (int i = 0; i < vimMatrix.getCellMatrix().size() ; i++) {

            for (int j = 0; j < vimMatrix.getCellMatrix().get(i).size(); j++) {

                vimMatrix.getCellMatrix().get(i).get(j).dispose();

            }
        }
        System.out.println("Play State Disposed");
    }

    public void loadBackgroundText(){
        String[] conversionArray;

        conversionArray = backgroundText.makeStringArray(
                "word word word word" +
                "word. word. word. word." +
                ".word. .word. .word. .word." +
                "..wo!rd. .wo!rd. .wo!rd. wo!rd.  " +
                "!!!word !!word !!word !word " +
                "{{word} {word} {word} word} " +
                "word () word () word () ord () " +
                "!!!word!!!! !!!!word!!!! !!!!word!!!! !!!word!!!! " +
                "word.0!) word.0!) word.0!) word.0!)" +
                "word    word    word    word");


        backgroundText.setHorizontalStringArray(conversionArray, 0, 0, false, LetterType.GRAY);
    }

}

/*

                       /////////////////////// VIM word-object variations ///////////////

        labyrinthText.setHorizontalString("word word word word", 0,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word. word. word. word.", 1,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString(".word. .word. .word. .word.", 2,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("..wo!rd. .wo!rd. .wo!rd. wo!rd.  ", 3,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("!!!word !!word !!word !word ", 4,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("{{word} {word} {word} word} ", 5,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word () word () word () ord () ", 6,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("!!!word!!!! !!!word!!! !!!word!!! !!!word!!!", 7,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word.0!) word.0!) word.0!) word.0!)", 8,0,true,LetterType.WHITE);
        labyrinthText.setHorizontalString("word    word    word    word", 9,0,true,LetterType.WHITE);



                       //////////////////////// PROSE FOR TEST //////////////////////

        conversionArray = backgroundText.makeStringArray("One morning, when Gregor Samsa woke from troubled dreams, he found" +
                "himself transformed in his bed into a horrible vermin.  He lay on" +
                "his armour-like back, and if he lifted his head a little he could" +
                "see his brown belly, slightly domed and divided by arches into stiff" +
                "sections.  The bedding was hardly able to cover it and seemed ready" +
                "to slide off any moment.  His many legs, pitifully thin compared" +
                "with the size of the rest of him, waved about helplessly as he" +
                "looked." +
                "" +
                "\"What's happened to me?\" he thought.  It wasn't a dream.  His room," +
                "a proper human room although a little too small, lay peacefully" +
                "between its four familiar walls.  A collection of textile samples" +
                "lay spread out on the table - Samsa was a travelling salesman - and" +
                "above it there hung a picture that he had recently cut out of an" +
                "illustrated magazine and housed in a nice, gilded frame.  It showed" +
                "a lady fitted out with a fur hat and fur boa who sat upright," +
                "raising a heavy fur muff that covered the whole of her lower arm" +
                "towards the viewer." +
                "" +
                "Gregor then turned to look out the window at the dull weather." +
                "Drops of rain could be heard hitting the pane, which made him feel" +
                "quite sad.  \"How about if I sleep a little bit longer and forget all" +
                "this nonsense\", he thought, but that was something he was unable to" +
                "do because he was used to sleeping on his right, and in his present" +
                "state couldn't get into that position.  However hard he threw" +
                "himself onto his right, he always rolled back to where he was.  He" +
                "must have tried it a hundred times, shut his eyes so that he" +
                "wouldn't have to look at the floundering legs, and only stopped when" +
                "he began to feel a mild, dull pain there that he had never felt" +
                "before.");



 */