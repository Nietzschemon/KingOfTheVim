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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    Label moves;
    Label movesNum;
    Label score;
    Label scoreNum;
    Table table;

    private Skin skin = new Skin(Gdx.files.internal("gamedata/textures/UI/commodore64/skin/uiskin.json"));

    int Help_Guides = 12;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;



    public Hud(){

        viewport = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport);
        table = new Table();


        table.bottom();
        table.setFillParent(true);

        score = new Label("", skin, "commodore-64", "white");
        scoreNum = new Label("", skin, "commodore-64", "white");
        moves = new Label("", skin, "commodore-64", "white");
        movesNum = new Label("", skin, "commodore-64", "white");

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
        this();
        stage = new Stage(viewport, sb);
        stage.addActor(table);
    }

    private void addBackgroundGuide(int columns){
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


    public Table getTable(){
        return table;
    }

    public void setInt(int intToSet, int column){

        setInt(intToSet, column, "%03d");
    }

    public void setInt(int intToSet, int column, String format){

        switch (column){
            case 0:
                this.score.setText(String.format(format, intToSet));
                break;

            case 1:
                this.scoreNum.setText(String.format(format, intToSet));
                break;

            case 2:
                this.moves.setText(String.format(format, intToSet));
                break;

            case 3:
                this.movesNum.setText(String.format(format, intToSet));
                break;

            default:
                break;
        }
    }

    public void setText(String text, int column){

        switch (column){
            case 0:
                score.setText(text);
                break;

            case 1:
                scoreNum.setText(text);
                break;

            case 2:
                moves.setText(text);
                break;

            case 3:
                movesNum.setText(text);
                break;

            default:
                break;
        }
    }

    public void setFontScale(int scale){
        score.setFontScale(scale);
        scoreNum.setFontScale(scale);
        moves.setFontScale(scale);
        movesNum.setFontScale(scale);
    }

    public void clearHud(){
        scoreNum.setText("");
        score.setText("");
        moves.setText("");
        movesNum.setText("");
    }
}
