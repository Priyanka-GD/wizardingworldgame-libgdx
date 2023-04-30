package com.gameclasses.view.observerlivesandscore;

import org.json.simple.JSONObject;

public class PlayerSystem {

    protected PlayerUpdateRenderer backScreen;
    private int lives;
    private boolean win;
    private int score;

    public PlayerSystem (JSONObject object) {
        lives = ((Long) object.get("lives")).intValue();
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
}
