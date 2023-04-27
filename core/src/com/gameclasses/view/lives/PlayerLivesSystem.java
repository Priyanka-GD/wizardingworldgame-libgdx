package com.gameclasses.view.lives;

import org.json.simple.JSONObject;

public class PlayerLivesSystem {

    protected PlayerUpdateRenderer backScreen;
    private int lives;
    private boolean win;

    public PlayerLivesSystem (JSONObject object) {
        lives = ((Long) object.get("lives")).intValue();
        win = false;
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
}
