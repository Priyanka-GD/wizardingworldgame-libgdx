package com.mygdx.game.lasermovement;

import com.badlogic.gdx.math.Rectangle;

public class LMovement implements Movement {
    public float timestamp;
    public float timeToTurn;
    public float speed;

    public LMovement () {
        timestamp = 0;
        timeToTurn = 5;
        speed = 50;
    }

    @Override
    public void move (float deltaTime, Rectangle hitbox) {
        timestamp += deltaTime;
        if (timestamp < timeToTurn)
            hitbox.y -= speed * deltaTime;
        else
            hitbox.x += speed * deltaTime;
    }

    @Override
    public void setDirection (float xDirection, float yDirection) {
        // DO NOTHING
    }

    @Override
    public void setSpeed (float speed) {

    }

    @Override
    public void setAcceleration (float acceleration) {
        // DO NOTHING
    }
}
