package com.gameclasses.view.observerlivesandscore;

public abstract class PlayerUpdateRenderer {
    protected PlayerSystem subject;

    public abstract void updateLives ();

    public abstract void updateScore ();
}
