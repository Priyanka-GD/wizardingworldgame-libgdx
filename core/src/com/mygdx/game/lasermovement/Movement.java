package com.mygdx.game.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public interface Movement {
    void move (float deltaTime, Rectangle hitbox);

    void setDirection (float xDirection, float yDirection);

    void setSpeed (float speed);

    void setAcceleration (float acceleration);
}
