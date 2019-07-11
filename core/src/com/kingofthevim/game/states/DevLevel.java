package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.*;

import java.util.ArrayList;

public class DevLevel extends Level{

    private final int cursorStartRow = 6;
    private final int cursorStartColumn = 0;

    public DevLevel(GameStateManager gsm) {
        super(gsm);

        pointsSys = new PointSystem(10);


        //backgroundText();

        levelPath();
        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);
        backgroundMusic();
    }

    @Override
    protected void backgroundText(){

    }

    @Override
    protected void levelPath() {
        tagBuiltLevel();
        //vimWordObjectCourse();

        labyrinthText.setLetterType("O", LetterType.RED, false);
        labyrinthText.setLetterType("X", LetterType.YELLOW, false);

    }

    private void tagBuiltLevel(){

        labyrinthText.createMap(
                "<<rw06>>" +
                "<rg>O123<r>4</r>56789X</rg>" +
                        "<dw+02>O123X</dw>" +
                        "<rg>O123X</rg>"
                );
        labyrinthText.createMap("<rg>green</rg>", true, LetterType.WHITE_GREEN);
    }

    private void vimWordObjectCourse(){
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

    private void prose(){

        String[] conversionArray;

        conversionArray = backgroundText.makeStringArray("One morning, when Gregor Samsa woke from troubled dreams, he found" +
                "himself transformed in his bed into a horrible vermin.  He lay on" +
                "his armour-like back, and if he lifted his head a little he could" +
                "see his brown belly, slightly domed and divided by arches into stiff" +
                "sections.  The bedding was hardly able to cover it and seemed ready" +
                "to slide off any moment.  His many legs, pitifully thin compared" +
                "with the size of the rest of him, waved about helplessly as he" +
                "looked. " +
                "\"What's happened to me?\" he thought.  It wasn't a dream.  His room," +
                "a proper human room although a little too small, lay peacefully" +
                "between its four familiar walls.  A collection of textile samples" +
                "lay spread out on the table - Samsa was a travelling salesman - and" +
                "above it there hung a picture that he had recently cut out of an" +
                "illustrated magazine and housed in a nice, gilded frame.  It showed" +
                "a lady fitted out with a fur hat and fur boa who sat upright," +
                "raising a heavy fur muff that covered the whole of her lower arm" +
                "towards the viewer. " +
                "Gregor then turned to look out the window at the dull weather." +
                "Drops of rain could be heard hitting the pane, which made him feel" +
                "quite sad. \"How about if I sleep a little bit longer and forget all" +
                "this nonsense\", he thought, but that was something he was unable to" +
                "do because he was used to sleeping on his right, and in his present" +
                "state couldn't get into that position.  However hard he threw" +
                "himself onto his right, he always rolled back to where he was.  He" +
                "must have tried it a hundred times, shut his eyes so that he" +
                "wouldn't have to look at the floundering legs, and only stopped when" +
                "he began to feel a mild, dull pain there that he had never felt" +
                "before.");

        backgroundText.setHorizontalStringArray(conversionArray, 0, 0, false, LetterType.GRAY);
    }

    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        if(cursor.isOnType(LetterType.WHITE_GREEN)){
           //cursor.dispose();
           //cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);

        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);
        }


        //sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);


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
    protected void levelChange() {
        if(cursor.isOnType(LetterType.WHITE_GREEN)) {
            cursor.dispose();
            gsm.push(new Level_1(gsm));
        }
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

        levelChange();
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

}
