package com.kingofthevim.game.scens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public Integer getScoreInt() {
        return scoreInt;
    }

    public void setScoreInt(Integer scoreInt) {
        this.scoreInt = scoreInt;
    }

    public Integer getMoveInt() {
        return moveInt;
    }

    public void setMoveInt(Integer moveInt) {
        this.moveInt = moveInt;
    }


    //TODO use fonts to update hud

    Label moves;
    Label movesNum;
    Label score;
    Label scoreNum;

    public Hud(SpriteBatch sb){

        scoreInt = 0;
        moveInt = 0;

        viewport = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();

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

    public void dispose(){
        stage.dispose();
    }
}
