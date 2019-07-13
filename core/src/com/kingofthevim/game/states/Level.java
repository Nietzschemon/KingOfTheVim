package com.kingofthevim.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kingofthevim.game.KingOfTheVimMain;
import com.kingofthevim.game.basicvim.Cursor;
import com.kingofthevim.game.basicvim.LetterManager;
import com.kingofthevim.game.basicvim.PointSystem;
import com.kingofthevim.game.basicvim.VimWorldMatrix;
import com.kingofthevim.game.scens.Hud;

public abstract class Level extends State {


    protected int rowTotal = 22;
    protected int columnTotal = 44;
    protected final int fontWidth = 22;
    protected final int fontHeight = 44;
    protected Cursor cursor;
    protected LetterManager backgroundText;
    protected LetterManager labyrinthText;
    protected VimWorldMatrix vimMatrix;

    protected Music backgroundMusic;

    protected PointSystem pointsSys;

    //////////////////////////


    public Stage stage;
    private Viewport viewport;

    private Integer scoreInt;
    private Integer moveInt;


    Label moves;
    Label movesNum;
    Label score;
    Label scoreNum;

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



    protected Level(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());

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

        //viewport = new StretchViewport(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT, new OrthographicCamera());
        //stage = new Stage();


        //////////////////////////////////////////////

        //TODO use for bigger texts and levels use also for zooming in bigger levels
        cam.setToOrtho(true, KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


        vimMatrix = new VimWorldMatrix(rowTotal, columnTotal, fontWidth, fontHeight);

        backgroundText = new LetterManager(vimMatrix);
        labyrinthText = new LetterManager(vimMatrix);


    }


    //TODO put in methods
    // score()
    // events()
    // cursorRules()

    protected abstract void levelPath();

    protected abstract void backgroundText();

    protected abstract void backgroundMusic();

    protected abstract void levelChange();

    public Integer updateSteps(){

        return pointsSys.getActualMoves();

    }

    public Integer updatePoints(){

        return pointsSys.getPoints();
    }

    @Override
    public void render(SpriteBatch sb) {

        stage.act();
        stage.draw();

    }

    @Override
    public void handleInput() {

        cursor.update();

    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();

    }
}
