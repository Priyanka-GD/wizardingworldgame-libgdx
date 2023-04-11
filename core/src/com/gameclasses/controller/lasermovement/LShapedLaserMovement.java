package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public class LShapedLaserMovement implements LaserMovement {
    public float timeStamp;
    public float timeToTurn;
    public float speed;

    public LShapedLaserMovement () {
        timeStamp = 0;
        timeToTurn = 10;
        speed = 50;
    }

    @Override
    public void move (float deltaTime, Rectangle hitbox) {
        timeStamp += deltaTime;
        if (timeStamp < timeToTurn)
            hitbox.y -= speed * deltaTime;
        else
            hitbox.x += speed * deltaTime;
    }

    @Override
    public void setDirection (float xDirection, float yDirection) {

    }

    @Override
    public void setSpeed (float speed) {

    }

    @Override
    public void setAcceleration (float acceleration) {

    }
}
