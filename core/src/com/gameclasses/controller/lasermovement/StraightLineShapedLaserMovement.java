package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public class StraightLineShapedLaserMovement implements LaserMovement {

    private float xDirection = 0;
    private float yDirection = 0;

    private float movementSpeed = 110;
    private float acceleration = 2;

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
