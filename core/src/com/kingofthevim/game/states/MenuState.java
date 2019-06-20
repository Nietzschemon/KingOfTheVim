package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.KingOfTheVimMain;

public class MenuState extends State{

    Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playBtn = new Texture("fontTest/P.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){ //always check if the user has one anything
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        //Need to subtract the playbutton with itself so it dosent go of center
        sb.draw(playBtn, (KingOfTheVimMain.WIDTH / 2) - (playBtn.getWidth() / 2), KingOfTheVimMain.HEIGHT / 2);
        sb.end();

    }

    @Override
    public void dispose() {
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }

}
