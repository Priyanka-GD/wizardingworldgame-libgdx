package com.gameclasses.controller.observer;

import com.gameclasses.model.systems.PlayerSystem;

public abstract class PlayerUpdateRenderer {
    protected PlayerSystem subject;

    public abstract void updateLives ();

    public abstract void updateScore ();

    public abstract void updateBombs ();
}
