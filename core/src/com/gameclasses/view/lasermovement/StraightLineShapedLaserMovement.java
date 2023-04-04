package com.gameclasses.view.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public class StraightLineShapedLaserMovement implements LaserMovement {


    // direction between 0 - 1
    private float xDirection = 0; // right positive, left negative
    private float yDirection = 0; // up positive, down negative

    private float movementSpeed = 100;
    private float acceleration = 1;

    @Override
    public void move (float deltaTime, Rectangle hitbox) {
        hitbox.x += movementSpeed * xDirection * deltaTime;
        hitbox.y += movementSpeed * yDirection * deltaTime;
        movementSpeed = movementSpeed * acceleration;
    }

    @Override
    public void setDirection (float xDirection, float yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
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
