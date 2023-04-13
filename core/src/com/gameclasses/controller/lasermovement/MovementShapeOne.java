package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

//Diagonal
public class MovementShapeOne implements LaserMovement {
    public float timeStamp;
    public float speed;

    public MovementShapeOne () {
        timeStamp = 1;
        speed = 45;
    }

    @Override
    public void moveLaser (float deltaTime, Rectangle hitBox) {
        timeStamp += deltaTime;
        hitBox.y -= speed * deltaTime;
        hitBox.x += speed * deltaTime;
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
