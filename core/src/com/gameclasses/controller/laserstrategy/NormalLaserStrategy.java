package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.lasermovement.Movement;

import java.util.List;

//Deliverable 2
public class NormalLaserStrategy implements LaserStrategy {
    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    String filename;
    String movementClass;

    public NormalLaserStrategy () {
        laserWidth = 4.0f;
        laserHeight = 20f;
        timeBetweenShots = 0.6f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 200f;
    }

    private boolean canFire () {
        return timeSinceLastShot >= timeBetweenShots;
    }

    @Override
    public void setLaserMovement (String movement) {
        this.movementClass = movement;
    }

    @Override
    public void setTexture (String filename) {
        this.filename = filename;
    }


    @Override
    public void fire (float deltaTime, Rectangle hitbox, List<EnemyLaser> list) {
        timeSinceLastShot += deltaTime;
        try {
            Class cls = Class.forName("com.gameclasses.model.lasermovement." + movementClass);
            Movement movement = (Movement) cls.getConstructor().newInstance();
            movement.setDirection(0, -1);
            movement.setSpeed(laserMovementSpeed);
            movement.setAcceleration(1);
            if (canFire()) {
                timeSinceLastShot = 0;
                list.add(new EnemyLaser(filename,
                        new Rectangle(hitbox.x + hitbox.width * 0.18f,
                                hitbox.y - laserHeight,
                                laserWidth,
                                laserHeight),
                        movement));

                list.add(new EnemyLaser(filename,
                        new Rectangle(hitbox.x + hitbox.width * 0.82f,
                                hitbox.y - laserHeight,
                                laserWidth,
                                laserHeight),
                        movement));
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
