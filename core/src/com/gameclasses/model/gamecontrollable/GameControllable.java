package com.gameclasses.model.gamecontrollable;

public interface GameControllable {
    void moveUp ();

    void moveDown ();

    void moveLeft ();

    void moveRight ();

    void slowMode (boolean slow);
}