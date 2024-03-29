package com.kingofthevim.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.kingofthevim.game.KingOfTheVimMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle(KingOfTheVimMain.TITLE);
		config.setWindowedMode(KingOfTheVimMain.WIDTH, KingOfTheVimMain.HEIGHT);


		new Lwjgl3Application(new KingOfTheVimMain(), config);
	}
}
