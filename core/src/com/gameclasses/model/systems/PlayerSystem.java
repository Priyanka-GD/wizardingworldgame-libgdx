package com.gameclasses.model.systems;

import com.gameclasses.controller.observer.PlayerUpdateRenderer;
import org.json.simple.JSONObject;

public class PlayerSystem {

    protected PlayerUpdateRenderer backScreen;
    private int lives;
    private boolean win;
    private int score;
    private int bombs;

    public PlayerSystem (JSONObject object) {
        lives = ((Long) object.get("lives")).intValue();
        bombs = ((Long) object.get("bombs")).intValue();
        win = false;
        score = 0;
    }

    public void attachBackScreen (PlayerUpdateRenderer backgroundScreen) {
        this.backScreen = backgroundScreen;
    }

    public void updateLives (int change) {
        lives += change;
        this.backScreen.updateLives();
    }
    public int getLives () {
        return lives;
    }

    public void updateEnd (boolean win) {
        this.win = win;
    }

    public boolean canEnd () {
        return this.win || lives <= 0;
    }

    public boolean isWin () {
        return this.win;
    }

    public void updateScore (int change) {
        score += change;
        this.backScreen.updateScore();
    }

    public int getScore () {
        return score;
    }

    public void updateBombs (int num) {
        bombs -= num;
        this.backScreen.updateBombs();
    }

    public int getBombs () {
        return bombs;
    }
}
