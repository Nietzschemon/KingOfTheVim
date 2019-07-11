package com.kingofthevim.game.scens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer testNum;

    Label labelTestNum;
    Label labelTest;

    public Hud(SpriteBatch sb){

        testNum = 300;

        viewport = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();

        table.bottom();
        table.setFillParent(true);

        labelTest = new Label("score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        labelTestNum = new Label(String.format("%03d", testNum), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(labelTest).expandX().pad(10);
        table.add(labelTestNum).expandX().pad(10);

        stage.addActor(table);
    }
}
