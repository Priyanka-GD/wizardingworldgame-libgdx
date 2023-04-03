package com.gameclasses.model.gameobjects;

public interface GameControllable {
    void moveUp ();

    void moveDown ();

    void moveLeft ();

    void moveRight ();

    void slowMode (boolean slow);
}