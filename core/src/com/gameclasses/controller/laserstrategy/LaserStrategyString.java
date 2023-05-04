package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.utils.GameConstants;

import java.util.List;

//String Laser Movement
public class LaserStrategyString implements LaserStrategy {
    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    float timeSwitch;
    boolean fireSwitch;
    String filename;
    String movementClass;

    public LaserStrategyString () {
        laserWidth = 15f;
        laserHeight = 15f;
        timeBetweenShots = 0.05f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 150f;
        fireSwitch = true;
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
    public boolean canFire () {
        if (timeSwitch >= 3) {
            fireSwitch = !fireSwitch;
            timeSwitch = 0;
        }
        return fireSwitch && timeSinceLastShot >= timeBetweenShots;
    }

    @Override
    public void laserFire (float deltaTime, Rectangle hitbox, List<EnemyLaser> list) {
        timeSinceLastShot += deltaTime;
        timeSwitch += deltaTime;
        try {
            if (canFire()) {
                timeSinceLastShot = 0;
                float playerX = GameConstants.PLAYERSHIP.hitBox.x + GameConstants.PLAYERSHIP.hitBox.width / 2;
                float playerY = GameConstants.PLAYERSHIP.hitBox.y + GameConstants.PLAYERSHIP.hitBox.height / 2;
                float enemyX = hitbox.x + hitbox.width / 2;
                float enemyY = hitbox.y + hitbox.height / 2;
                float deltaX = playerX - enemyX;
                float deltaY = playerY - enemyY;
                double angle = Math.atan2(deltaY, deltaX);

                Class cls = Class.forName("com.gameclasses.controller.lasermovement." + movementClass);
                LaserMovement movement = (LaserMovement) cls.getConstructor().newInstance();
                movement.setDirection((float) Math.cos(angle), (float) Math.sin(angle));
                movement.setSpeed(50);
                movement.setAcceleration(1);
                list.add(new EnemyLaser(filename,
                        new Rectangle(hitbox.x + (hitbox.width / 2),
                                hitbox.y + (hitbox.height / 2),
                                laserWidth,
                                laserHeight),
                        movement));
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
