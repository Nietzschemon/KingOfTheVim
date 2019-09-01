package com.kingofthevim.game.states.levelsettings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

public class LevelSettingsDialog  {

    private ArrayList<LevelSettingsListener> settingsListeners;
    private LevelSettings levelSettings;
    private Skin skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

    private Button cursorHasGravity = new CheckBox("Cursor", skin);
    private Button winOnDelete = new CheckBox("Delete all green letters", skin);
    private Button winOnGoal = new CheckBox("Reach the goal (white_green)",  skin);
    private Button resetLevelAtDeath = new CheckBox("Reset level at death",  skin);

    private Dialog dialog = new Dialog("Level Settings", skin, "dialog") {
        public void result(Object obj) {
            System.out.println("result " + obj);
            boolean ok = (boolean)obj;
            if(ok)checkRadioButtons();
            settingsChanged();
        }
    };

    public LevelSettingsDialog(LevelSettings levelSettings){
        settingsListeners = new ArrayList<>();
        this.levelSettings = levelSettings;
        loadButtons();
    }

    private void checkRadioButtons(){
        levelSettings.winOnDelete = winOnDelete.isChecked();
        levelSettings.winOnGoal = winOnGoal.isChecked();
        levelSettings.cursorHasGravity = cursorHasGravity.isChecked();
        levelSettings.resetLevelAtDeath = resetLevelAtDeath.isChecked();
    }
    private void loadButtons(){
        dialog.getContentTable().add("Win conditions").left().padTop(10);
        dialog.getContentTable().row();
        dialog.getContentTable().add(winOnGoal).left();
        dialog.getContentTable().row();
        dialog.getContentTable().add(winOnDelete).left();
        dialog.getContentTable().row();
        dialog.getContentTable().add("Gravity").left().padTop(10);
        dialog.getContentTable().row();
        dialog.getContentTable().add(cursorHasGravity).left();
        dialog.getContentTable().row();
        dialog.getContentTable().add("Resets").left().padTop(10);
        dialog.getContentTable().row();
        dialog.getContentTable().add(resetLevelAtDeath).left();

        dialog.button("change", true).getContentTable().left();
        dialog.button("cancel", false).getContentTable().right();
        dialog.key(Input.Keys.ENTER, true);
    }

    public void showDialog(Stage stage){
        syncSettingWithDialog();
        Gdx.input.setInputProcessor(stage);
        dialog.setVisible(true);
        dialog.toFront();
        dialog.show(stage);
    }

    private void syncSettingWithDialog(){
        winOnGoal.setChecked(levelSettings.winOnGoal);
        winOnDelete.setChecked(levelSettings.winOnDelete);
        cursorHasGravity.setChecked(levelSettings.cursorHasGravity);
        resetLevelAtDeath.setChecked(levelSettings.resetLevelAtDeath);
    }


    public LevelSettings getLevelSettings() {
        return levelSettings;
    }

    public void setLevelSettings(LevelSettings levelSettings) {
        this.levelSettings = levelSettings;
    }

    public void addListener(LevelSettingsListener listener){
        settingsListeners.add(listener);
    }
    public void removeListener(LevelSettingsListener listener){
        settingsListeners.remove(listener);
    }

    private void settingsChanged(){
        for(LevelSettingsListener listener : settingsListeners){
            listener.settingsChanged();
        }
    }

}
