package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;
import com.gameclasses.model.gameobjects.EnemyLaser;

import java.util.List;

// Heavy Laser
public class LaserStrategyHeavy implements LaserStrategy {
    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    String filename;
    String movementClass;

    public LaserStrategyHeavy () {
        laserWidth = 30f;
        laserHeight = 30f;
        timeBetweenShots = 1f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 200f;
    }

    @Override
    public boolean canFire () {
        return timeSinceLastShot >= timeBetweenShots;
    }

    @Override
    public void setLaserMovement (String movement) {
        this.movementClass = movement;
    }

    @Override
    public void setLaserTexture (String filename) {
        this.filename = filename;
    }

    @Override
    public void laserFire (float deltaTime, Rectangle hitbox, List<EnemyLaser> list, List<EnemyLaser> heavyList) {
        timeSinceLastShot += deltaTime;
        try {
            if (canFire()) {
                for (int angle = 0; angle < 360; angle += 45) {
                    timeSinceLastShot = 0;
                    Class cls = Class.forName("com.gameclasses.controller.lasermovement." + movementClass);
                    LaserMovement movement = (LaserMovement) cls.getConstructor().newInstance();
                    movement.setDirection((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle)));
                    movement.setSpeed(laserMovementSpeed);
                    movement.setAcceleration(1);
                    heavyList.add(new EnemyLaser(filename,
                            new Rectangle(hitbox.x + (hitbox.width / 2) + hitbox.width * 0.5f * (float) Math.cos(Math.toRadians(angle)),
                                    hitbox.y + (hitbox.height / 2) + hitbox.height * 0.5f * (float) Math.sin(Math.toRadians(angle)),
                                    laserWidth,
                                    laserHeight),
                            movement));
                }
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
