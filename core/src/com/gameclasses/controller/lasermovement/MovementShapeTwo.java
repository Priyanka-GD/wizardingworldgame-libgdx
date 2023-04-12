package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

//Straight
public class MovementShapeTwo implements LaserMovement {
    public float timeStamp;
    public float speed;

    public MovementShapeTwo () {
        timeStamp = 0;
        speed = 50;
    }

    @Override
    public void moveLaser (float deltaTime, Rectangle hitBox) {
        timeStamp += deltaTime;
        hitBox.y -= speed * deltaTime;
        hitBox.x *= speed * deltaTime;
    }

    @Override
    public void setDirection (float x, float y) {

    }

    @Override
    public void setSpeed (float speed) {

    }

    @Override
    public void setAcceleration (float acceleration) {

    }
}
