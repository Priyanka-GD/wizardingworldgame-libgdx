package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameobjects.GameControllable;
import com.mygdx.game.gamescreens.MenuScreen;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommand {
    List<GameControllable> subscribers;
    public int inputType = MenuScreen.keyBind;
    public PlayerCommand() {
        subscribers = new ArrayList<>();
    }
    public void add(GameControllable subscriber) {
        subscribers.add(subscriber);
    }
    public void run() {
        for (GameControllable sub : subscribers) {
            if(inputType==0) {
                // Character Movements
                sub.slowMode(Gdx.input.isKeyPressed(GameConstants.SLOW_MODE));
                if (Gdx.input.isKeyPressed(GameConstants.UP)) {
                    sub.moveUp();
                }
                if (Gdx.input.isKeyPressed(GameConstants.DOWN)) {
                    sub.moveDown();
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
