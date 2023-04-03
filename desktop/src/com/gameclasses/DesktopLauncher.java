package com.gameclasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.gamescreens.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConstants.EXT_WINDOW_WIDTH;
		config.height = GameConstants.EXT_WINDOW_HEIGHT;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
