package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kingofthevim.game.KingOfTheVimMain;

public class MenuState extends State{

    Texture developerLevel;
    Texture level_1;
    Texture level_2;

    //TODO write real menu
    public MenuState(GameStateManager gsm) {
        super(gsm);
        developerLevel = new Texture("menu/DevLevel.png");
        level_1 = new Texture("menu/Level1.png");
        level_2 = new Texture("menu/Level2.png");
    }

    //clicks Playbutton
    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

            gsm.set(new DevLevel(gsm));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            gsm.set(new Level_1(gsm));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            gsm.set(new Level_2(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        //Need to subtract the buttons with itself so it dosent go of center
        sb.draw(developerLevel, (KingOfTheVimMain.WIDTH / 2) - (developerLevel.getWidth() / 2), KingOfTheVimMain.HEIGHT / 2);
        sb.draw(level_1, (KingOfTheVimMain.WIDTH / 2) - (level_1.getWidth() / 2), (KingOfTheVimMain.HEIGHT / 2) - level_1.getHeight());

        sb.draw(level_2, (KingOfTheVimMain.WIDTH / 2) - (level_2.getWidth() / 2),
                (KingOfTheVimMain.HEIGHT / 2) - (level_2.getHeight() * 2));

        sb.end();

    }

    @Override
    public void dispose() {
        developerLevel.dispose();
        level_1.dispose();
        level_2.dispose();
        System.out.println("Menu State Disposed");
    }

}
