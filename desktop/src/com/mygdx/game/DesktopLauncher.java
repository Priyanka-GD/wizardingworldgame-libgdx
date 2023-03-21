package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.gamescreens.MainGame;
import com.mygdx.game.utils.GameConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConstants.EXT_WINDOW_WIDTH;
		config.height = GameConstants.EXT_WINDOW_HEIGHT;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
