package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public class MovementShapeThree implements LaserMovement {
    public float timeStamp, timeToTurn, speed;

    public MovementShapeThree () {
        timeStamp = 1;
        speed = 45;
        timeToTurn = 6;
    }

    @Override
    public void moveLaser (float deltaTime, Rectangle hitBox) {
        timeStamp += deltaTime;
        if (timeStamp < timeToTurn)
            hitBox.y -= speed * deltaTime;
        else
            hitBox.x -= speed * deltaTime;
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
