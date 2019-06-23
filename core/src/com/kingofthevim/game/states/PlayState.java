package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.Letter;
import com.kingofthevim.game.basicvim.LetterManager;
import com.kingofthevim.game.basicvim.Pointer;

public class PlayState extends State{

    private Pointer pointer;

    private Array<Letter> letters;

    //////////////////////////////////
    //TODO make positions the only location metric
    // ALL other things should follow automaticly from that
    // such as SIZE of things and their X and Y
    /////////////////////////////////////////////////////////
    private int letterWidth = 33;
    private int letterHeight = 66;

    private LetterManager ltxMgr;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //TODO ta bort magiska nummer
        pointer = new Pointer(0, 66, 13, 0);

        //TODO use for bigger texts and levels
        //use also for zooming in bigger levels
        cam.setToOrtho(false, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);

        ltxMgr = new LetterManager(1, 2, "CBC");//MÅSTE va ABC
        letters = ltxMgr.getLine();

    }

    @Override
    public void handleInput() {
        //TODO Använd för att ta ett kommando. Ha "IsPressed" för att "hålla in" knappen
        pointer.updateMotion();
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            pointer.setRightMove(true);
        } else{
            pointer.setRightMove(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            pointer.setLeftMove(true);
        } else{
            pointer.setLeftMove(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            pointer.setDownMove(true);
        } else{
            pointer.setDownMove(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            pointer.setUpMove(true);
        } else{
            pointer.setUpMove(false);
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); //
        pointer.update();

        //TODO The cam should be able to follow the y axis OR the x axis
        //cam.position.x = pointer.getPosition().x + 80;


        ////TODO reset pointer if it falls of.
        //if(pointer.getPosition() logic for on gray letters)
            //gsm.set(new MenuState(gsm));

        //Tells GDX that cam been repositioned.
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {

        // Shows sprite-batch where to draw things on screen.
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(pointer.getTexture(), pointer.getPosition().x, pointer.getPosition().y);

        // draws letters every cykle
        for(Letter letter : letters) {
            sb.draw(letter.getTexture(), letter.getPosition().x, letter.getPosition().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        pointer.dispose();
        for(Letter letter : letters)
            letter.dispose();

        System.out.println("Play State Disposed");
    }

}
