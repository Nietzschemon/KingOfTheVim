package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.Letter;
import com.kingofthevim.game.basicvim.LetterManager;
import com.kingofthevim.game.basicvim.VimWorld;

public class PlayState extends State{

    //////////////////////////////////////////////////////////
    //TODO make positions the only location metric          //
    // ALL other things should follow automaticly from that //
    // such as SIZE of things and their X and Y             //
    //////////////////////////////////////////////////////////

    private Cursor cursor;

    private VimWorld vimWorld;

    private Array<Letter> letters;

    private int letterWidth = 33;
    private int letterHeight = 66;

    private LetterManager ltxMgr;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //TODO ta bort magiska nummer
        cursor = new Cursor(0, 66, 1, 0);

        //TODO use for bigger texts and levels
        //use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);

        ltxMgr = new LetterManager(1, 2, "CBC");//MÅSTE va ABC
        letters = ltxMgr.getLine();

        vimWorld = new VimWorld(letterWidth, letterHeight);

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
        handleInput(); //
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

        // draws letters every cykle
        for(Letter letter : letters) {
            sb.draw(letter.getTexture(), letter.getPosition().x, letter.getPosition().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        cursor.dispose();
        for(Letter letter : letters)
            letter.dispose();

        System.out.println("Play State Disposed");
    }

}
