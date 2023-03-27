package com.mygdx.game.gameobjects;

public interface GameControllable {
    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();
    public void slowMode(boolean slow);
}