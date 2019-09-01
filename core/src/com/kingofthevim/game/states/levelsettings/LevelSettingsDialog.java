/*
Commodore 64 UI Ver. 1
 Created by Raymond "Raeleus" Buckley
Visit ray3k.com for games, tutorials, and much more!
 Commodore 64 UI can be used under the CC BY license.
http://creativecommons.org/licenses/by/4.0/
 */


package com.kingofthevim.game.states.levelsettings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import java.util.ArrayList;

public class LevelSettingsDialog  {

    private ArrayList<LevelSettingsListener> settingsListeners;
    private LevelSettings levelSettings;
    private Skin skin = new Skin(Gdx.files.internal("gamedata/textures/UI/commodore64/skin/uiskin.json"));

    private CheckBox cursorHasGravity = new CheckBox(" - Cursor", skin, "radio");
    private CheckBox winOnDelete = new CheckBox(" - Delete all green letters", skin, "radio");
    private CheckBox winOnGoal = new CheckBox(" - Reach whiteGreen letters",  skin, "radio");
    private CheckBox resetLevelAtDeath = new CheckBox(" - Reset level at death",  skin, "radio");

    private Dialog dialog = new Dialog("Level Settings", skin, "default") {
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
        dialog.getTitleLabel().setStyle(new Label("Level Settings", skin, "optional").getStyle());
        dialog.setColor(Color.DARK_GRAY);
        dialog.getContentTable().setColor(Color.GREEN);
        dialog.getContentTable().add("Win conditions").left().padTop(20).padBottom(10).padLeft(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add(winOnGoal).left().padLeft(10).padRight(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add(winOnDelete).left().padLeft(10).padRight(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add("Gravity").left().padTop(20).padBottom(10).padLeft(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add(cursorHasGravity).left().padLeft(10).padRight(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add("Resets").left().padTop(20).padBottom(10).padLeft(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add(resetLevelAtDeath).left().padLeft(10).padRight(5);
        dialog.getContentTable().row();
        dialog.getContentTable().add(" ");


        dialog.button("change", true);
        dialog.button("cancel", false);
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
