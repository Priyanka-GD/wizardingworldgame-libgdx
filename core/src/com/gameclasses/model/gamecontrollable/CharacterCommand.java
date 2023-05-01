package com.gameclasses.model.gamecontrollable;

import com.badlogic.gdx.Gdx;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.gamescreens.MenuScreen;
import com.gameclasses.view.observerlivesandscore.PlayerSystem;

import java.util.ArrayList;
import java.util.List;

public class CharacterCommand {
    List<GameControllable> gameControllables;
    public int inputType = MenuScreen.keyBind;
    private PlayerSystem checkBombs;

    public CharacterCommand () {
        gameControllables = new ArrayList<>();
    }

    public void setCheckBombs (PlayerSystem checkBombs) {
        this.checkBombs = checkBombs;
    }

    public void add (GameControllable subscriber) {
        gameControllables.add(subscriber);
    }

    public void run () {
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
            if (Gdx.input.isKeyPressed(GameConstants.PLAYER_FIRE)) {
                sub.playerFire();
            }
            //Bomb throw by Player
            if (Gdx.input.isKeyPressed(GameConstants.BOMB) && checkBombs.getBombs() > 0) {
                sub.throwBomb();
                if (sub.getIsThrow()) {
                    checkBombs.updateBombs(1);
                    sub.setIsThrow(false);
                }
            }


        }
    }
}
