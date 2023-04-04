package com.gameclasses.view.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public interface LaserMovement {
    void move (float deltaTime, Rectangle hitbox);

    void setDirection (float xDirection, float yDirection);

    void setSpeed (float speed);

    void setAcceleration (float acceleration);
}
