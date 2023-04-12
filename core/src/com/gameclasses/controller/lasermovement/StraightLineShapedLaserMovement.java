package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public class StraightLineShapedLaserMovement implements LaserMovement {

    private float x = 0;
    private float y = 0;
    private float movementSpeed = 90;
    private float acceleration = 1;

    @Override
    public void moveLaser (float deltaTime, Rectangle hitBox) {
        hitBox.x += movementSpeed * x * deltaTime;
        hitBox.y += movementSpeed * y * deltaTime;
        movementSpeed = movementSpeed * acceleration;
    }

    @Override
    public void setDirection (float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSpeed (float speed) {
        this.movementSpeed = speed;
    }

    @Override
    public void setAcceleration (float acceleration) {
        this.acceleration = acceleration;
    }
}
