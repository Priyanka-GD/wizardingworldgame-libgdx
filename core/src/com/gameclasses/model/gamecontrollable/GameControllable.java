package com.gameclasses.model.gamecontrollable;

public interface GameControllable {
    void moveUp ();

    void moveDown ();

    void moveLeft ();

    void moveRight ();

    void playerFire ();

    void slowMode (boolean slow);
}