package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.*;

import java.util.ArrayList;

public class Level1 extends State{

    private final int rowTotal = 22;
    private final int columnTotal = 44;
    private final int fontWidth = 22;
    private final int fontHeight = 44;
    private final int cursorStartRow = 0;
    private final int cursorStartColumn = 0;

    private Cursor cursor;

    private VimWorldMatrix vimMatrix;

    private LetterManager backgroundText;
    private LetterManager labyrinthText;


    public Level1(GameStateManager gsm) {
        super(gsm);

        //TODO use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


        vimMatrix = new VimWorldMatrix(rowTotal, columnTotal, fontWidth, fontHeight);
        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn);

        backgroundText = new LetterManager(vimMatrix);
        labyrinthText = new LetterManager(vimMatrix);

        loadBackgroundText();

        labyrinthText.setHorizontalString("X", 0,0,true,LetterType.WHITE);



        labyrinthText.createMap("<rg>O1234a5X</rg>" +
                                            "<dw>O12a3456X</dw>" +
                                            "<rg>O12345X</rg>" +
                                            "<up+01>O12X</up>");

        // All letters in the matrix are set to the lettertype of those in the string

        labyrinthText.setLetterType("O", LetterType.RED, false);
        labyrinthText.setLetterType("X", LetterType.YELLOW, false);
        //labyrinthText.setLetterType("QWERTYUIOPASDFGHJKLZXCVBNM", LetterType.RED, false);
        //labyrinthText.setLetterType("BCRLcursor", LetterType.YELLOW, false);

        // LEVEL EXEMPEL
         // "old" way of creating levels
        /*
        labyrinthText.setHorizontalString("Test", 2, 0, true, LetterType.WHITE );
        labyrinthText.setVerticalString("Das", 3, 3, true, LetterType.WHITE);
        labyrinthText.setHorizontalString("VIM", 5, 4, true, LetterType.WHITE);
        labyrinthText.setHorizontalString("controls", 4, 6, true, LetterType.WHITE);
        labyrinthText.setHorizontalString("and", 5, 13, true, LetterType.WHITE);
        labyrinthText.setHorizontalString("be", 4, 15, true, LetterType.WHITE);
        labyrinthText.setHorizontalString("terribly", 3, 16, true, LetterType.WHITE);
        labyrinthText.setVerticalString("amazed", 4, 23, true, LetterType.WHITE);

        labyrinthText.createMap("<rg>Example</rg>" +
                        "<dw>of</dw>"+
                        "<rg>A</rg>"+
                        "<dw>level</dw>"+
                        "<rg>BUILT</rg>"+
                        "<up>-with</up>"+
                        "<rg>LEVEL-TAGS</rg>"+
                        "<dw>and</dw>"+
                        "<rg>OF</rg>"+
                        "<dw>cursor</dw>"+
                        "<lf>-RESET</lf>"+
                        "<dw>as</dw>" +
                        "<lf>-WELL</lf>"+
                        "<dw>as</dw>" +
                        "<rg>BACKGROUND-TEXT</rg>"+
                        "<dw>and</dw>" +
                        "<lf>COLORED-LETTERS</lf>"
                , true);

       labyrinthText.setLetterType("QWERTYUIOPASDFGHJKLZXCVBNM", LetterType.RED, false);
        labyrinthText.setLetterType("BCRLcursor", LetterType.YELLOW, false);

                        "<rg>also</rg>"+
                        "<rg>forget</rg>"+
                        "<rg>the</rg>"+
                        "<rg>colored</rg>"+
                        "<rg>letters</rg>",
                 true);

         */



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

        conversionArray = backgroundText.makeStringArray("One morning, when Gregor Samsa woke from troubled dreams, he found\n" +
                "himself transformed in his bed into a horrible vermin.  He lay on\n" +
                "his armour-like back, and if he lifted his head a little he could\n" +
                "see his brown belly, slightly domed and divided by arches into stiff\n" +
                "sections.  The bedding was hardly able to cover it and seemed ready\n" +
                "to slide off any moment.  His many legs, pitifully thin compared\n" +
                "with the size of the rest of him, waved about helplessly as he\n" +
                "looked.\n" +
                "\n" +
                "\"What's happened to me?\" he thought.  It wasn't a dream.  His room,\n" +
                "a proper human room although a little too small, lay peacefully\n" +
                "between its four familiar walls.  A collection of textile samples\n" +
                "lay spread out on the table - Samsa was a travelling salesman - and\n" +
                "above it there hung a picture that he had recently cut out of an\n" +
                "illustrated magazine and housed in a nice, gilded frame.  It showed\n" +
                "a lady fitted out with a fur hat and fur boa who sat upright,\n" +
                "raising a heavy fur muff that covered the whole of her lower arm\n" +
                "towards the viewer.\n" +
                "\n" +
                "Gregor then turned to look out the window at the dull weather.\n" +
                "Drops of rain could be heard hitting the pane, which made him feel\n" +
                "quite sad.  \"How about if I sleep a little bit longer and forget all\n" +
                "this nonsense\", he thought, but that was something he was unable to\n" +
                "do because he was used to sleeping on his right, and in his present\n" +
                "state couldn't get into that position.  However hard he threw\n" +
                "himself onto his right, he always rolled back to where he was.  He\n" +
                "must have tried it a hundred times, shut his eyes so that he\n" +
                "wouldn't have to look at the floundering legs, and only stopped when\n" +
                "he began to feel a mild, dull pain there that he had never felt\n" +
                "before.\n");


        backgroundText.setHorizontalStringArray(conversionArray, 0, 0, false, LetterType.GRAY);
    }

    @Override
    public void handleInput() {

        cursor.move();

        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            cursor.setMoveRight_word_bgn(true);
        } else{
            cursor.setMoveRight_word_bgn(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            cursor.setMoveLeft_word(true);
        } else{
            cursor.setMoveLeft_word(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            cursor.setMoveRight_Char(true);
        } else{
            cursor.setMoveRight_Char(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            cursor.setMoveLeft_Char(true);
        } else{
            cursor.setMoveLeft_Char(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            cursor.setMoveDown_Line(true);
        } else{
            cursor.setMoveDown_Line(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            cursor.setMoveUp_Line(true);
        } else{
            cursor.setMoveUp_Line(false);
        }
    }
}
