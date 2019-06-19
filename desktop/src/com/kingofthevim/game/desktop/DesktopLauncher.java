package com.kingofthevim.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kingofthevim.game.KingOfTheVimMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = KingOfTheVimMain.TITLE;
		config.width = KingOfTheVimMain.WIDTH;
		config.height = KingOfTheVimMain.HEIGHT;

		new LwjglApplication(new KingOfTheVimMain(), config);
	}
}
