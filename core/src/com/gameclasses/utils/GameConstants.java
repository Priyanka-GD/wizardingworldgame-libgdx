package com.gameclasses.utils;

import com.badlogic.gdx.Input;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.model.gameobjects.Player;

public class GameConstants {
    public static final int EXT_WINDOW_WIDTH = 836;
    public static final int EXT_WINDOW_HEIGHT = 820;
    public static final int WINDOW_WIDTH = 536;
    public static final int WINDOW_HEIGHT = 800;
    public static int UP = Input.Keys.UP;
    public static int DOWN = Input.Keys.DOWN;
    public static int LEFT = Input.Keys.LEFT;
    public static int RIGHT = Input.Keys.RIGHT;
    public static int SLOW_MODE = Input.Keys.SHIFT_LEFT;
    public static JsonConfigReader config;
    public static final int GAME_LENGTH = 85;
    public static Player PLAYERSHIP;
    public static int PLAYER_FIRE = Input.Keys.SPACE;
    public static int BOMB = Input.Keys.B;
}
