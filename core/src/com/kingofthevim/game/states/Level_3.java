package com.kingofthevim.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.basicvim.Cell;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.LetterType;
import com.kingofthevim.game.basicvim.PointSystem;

import java.util.ArrayList;

public class Level_3 extends Level{

    private final int cursorStartRow = 0;
    private final int cursorStartColumn = 0;

    public Level_3(GameStateManager gsm) {
        super(gsm);


        pointsSys = new PointSystem(10);
        cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);

        backgroundText();

        levelPath();
    }

    @Override
    protected void levelPath() {

        labyrinthText.createMap(
                "<rg>xxxxxx</rg>" +
                        "<dw>xxxxxx</dw>" +
                        "<rg>xxxxxx</rg>" +
                        "<up+01>xxxxxx</up>" +
                        "<rg>now you need \"e\"</rg>" +
                        "<dw>xxxxxx</dw>" +
                        "<rg>xxxxxx</rg>" +
                        "<up>xxxxxx</up>" +
                        "<rg>xxxxxx</rg>" +
                        "<dw>xxxxxxxxxxxxx</dw>" +
                        "<lf>xxxxxxxx</lf>" +
                        "<up>xxxx</up>" +
                        "<lf>\"b\" to get to here</lf>" +
                        "<dw>Goal</dw>"
        );


        // All letters in the matrix are set to the lettertype of those in the string
        labyrinthText.setLetterType("\"udwGoaltgh", LetterType.YELLOW, false);
        labyrinthText.setLetterType("enonyrb", LetterType.RED, false);
    }

    public void backgroundText(){

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


        if(cursor.isOnType(LetterType.GRAY)
                || cursor.isOnType(LetterType.EMPATHY)){
            cursor.dispose();
            cursor = new Cursor(vimMatrix, cursorStartRow, cursorStartColumn, pointsSys);
        }else{
            sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);
        }


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



}
