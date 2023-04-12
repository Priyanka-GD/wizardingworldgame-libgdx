package com.gameclasses.controller.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public interface LaserMovement {
    void moveLaser (float deltaTime, Rectangle hitBox);

    void setDirection (float x, float y);

    void setSpeed (float speed);

    void setAcceleration (float acceleration);
}
