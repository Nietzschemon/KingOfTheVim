//code by Stefan Ekblom
//anno 2019

/*
Commodore 64 UI Ver. 1
 Created by Raymond "Raeleus" Buckley
Visit ray3k.com for games, tutorials, and much more!
 Commodore 64 UI can be used under the CC BY license.
http://creativecommons.org/licenses/by/4.0/
 */
package com.kingofthevim.game.scens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer scoreInt;
    private Integer moveInt;

    private Skin skin = new Skin(Gdx.files.internal("gamedata/textures/UI/commodore64/skin/uiskin.json"));

    int Help_Guides = 12;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;


    public Integer getScoreInt() {
        return scoreInt;
    }

    public void setScoreInt(Integer scoreInt) {

        this.scoreNum.setText(String.format("%01d", scoreInt));
    }

    public Integer getMoveInt() {
        return moveInt;
    }

    public void setMoveInt(Integer moveInt) {

        this.movesNum.setText(String.format("%01d", moveInt));
    }

    Label moves;
    Label movesNum;
    Label score;
    Label scoreNum;
    Table table;

    public Hud(){
        scoreInt = 0;
        moveInt = 0;

        viewport = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport);
        table = new Table();


        table.bottom();
        table.setFillParent(true);

        score = new Label("score", skin, "commodore-64", "white");
        scoreNum = new Label("%01d", skin, "commodore-64", "white");
        moves = new Label("moves", skin, "commodore-64", "white");
        movesNum = new Label("%01d", skin, "commodore-64", "white");

        score.setFontScale(2);
        scoreNum.setFontScale(2);
        moves.setFontScale(2);
        movesNum.setFontScale(2);

        table.add(score).expandX().pad(10);
        table.add(scoreNum).expandX().pad(10);
        table.add(moves).expandX().pad(10);
        table.add(movesNum).expandX().pad(10);


        addBackgroundGuide(Help_Guides);

        stage.addActor(table);

    }

    public Hud(SpriteBatch sb){

        scoreInt = 0;
        moveInt = 0;

        viewport = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();

        table.bottom();
        table.setColor(Color.PURPLE);
        table.setFillParent(true);


        score = new Label("score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreNum = new Label(String.format("%03d", scoreInt), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        moves = new Label("moves", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        movesNum = new Label(String.format("%03d", moveInt), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        score.setFontScale(2);
        scoreNum.setFontScale(2);
        moves.setFontScale(2);
        movesNum.setFontScale(2);


        table.add(score).expandX().pad(10);
        table.add(scoreNum).expandX().pad(10);
        table.add(moves).expandX().pad(10);
        table.add(movesNum).expandX().pad(10);

        stage.addActor(table);
    }

    public void addBackgroundGuide(int columns){
        Texture texture = new Texture(Gdx.files.internal("gamedata/textures/PointBar.jpg"));
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,texture.getWidth()*columns,texture.getWidth()*columns);
        Image background = new Image(textureRegion);
        background.setSize(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT/16);
        background.setPosition(0,0);

        stage.addActor(background);
    }

    public void dispose(){
        stage.dispose();
    }
}
