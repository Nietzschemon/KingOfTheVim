package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.*;

public class PlayState extends State{

    //////////////////////////////////////////////////////////
    //TODO make positions the only location metric          //
    // ALL other things should follow automaticly from that //
    // such as SIZE of things and their X and Y             //
    //////////////////////////////////////////////////////////

    private Cursor cursor;

    private VimWorldMatrix vimMatrix;


    private int letterWidth = 33;
    private int letterHeight = 66;

    private LetterManager ltxMgr;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //TODO ta bort magiska nummer

        //TODO use for bigger texts and levels
        //use also for zooming in bigger levels
        cam.setToOrtho(false, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);

        vimMatrix = new VimWorldMatrix();
        cursor = new Cursor( 1, 0);

        ltxMgr = new LetterManager();

        ltxMgr.setWord("Test this!?/^~@", 2, 4);

    }

    @Override
    public void handleInput() {
        //TODO Använd för att ta ett kommando. Ha "IsPressed" för att "hålla in" knappen
        cursor.updateMotion();
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            cursor.setRightMove(true);
        } else{
            cursor.setRightMove(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            cursor.setLeftMove(true);
        } else{
            cursor.setLeftMove(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            cursor.setDownMove(true);
        } else{
            cursor.setDownMove(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            cursor.setUpMove(true);
        } else{
            cursor.setUpMove(false);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cursor.update();

        //TODO The cam should be able to follow the y axis OR the x axis
        //cam.position.x = cursor.getPosition().x + 80;


        ////TODO reset cursor if it falls of.
        //if(cursor.getPosition() logic for on gray letters)
            //gsm.set(new MenuState(gsm));

        //Tells GDX that cam been repositioned.
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(cursor.getTexture(), cursor.getPosition().x, cursor.getPosition().y);

        for(Cell[] cellRow : vimMatrix.getCellMatrix()){

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
    public void dispose() {
        cursor.dispose();

        for(Cell[] cellRow : vimMatrix.getCellMatrix()){

            for(Cell cell : cellRow){

                cell.dispose();
            }
        }
        System.out.println("Play State Disposed");
    }

}
