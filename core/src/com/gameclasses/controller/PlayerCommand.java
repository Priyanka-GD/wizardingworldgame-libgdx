package com.gameclasses.controller;

import com.badlogic.gdx.Gdx;
import com.gameclasses.model.gameobjects.GameControllable;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.gamescreens.MenuScreen;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommand {
    List<GameControllable> gameControllables;
    public int inputType = MenuScreen.keyBind;
    public PlayerCommand() {
        gameControllables = new ArrayList<>();
    }
    public void add(GameControllable subscriber) {
        gameControllables.add(subscriber);
    }
    public void run() {
        for (GameControllable sub : gameControllables) {
            if (inputType == 0) {
                // Character Movements
                sub.slowMode(Gdx.input.isKeyPressed(GameConstants.SLOW_MODE));
                if (Gdx.input.isKeyPressed(GameConstants.DOWN)) {
                    sub.moveDown();
                }
                if (Gdx.input.isKeyPressed(GameConstants.UP)) {
                    sub.moveUp();
                }
                if (Gdx.input.isKeyPressed(GameConstants.LEFT)) {
                    sub.moveLeft();
                }
                if (Gdx.input.isKeyPressed(GameConstants.RIGHT)) {
                    sub.moveRight();
                }
            }

        }
    }
}
