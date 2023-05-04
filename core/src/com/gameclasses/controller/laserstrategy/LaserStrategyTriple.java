package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;
import com.gameclasses.model.gameobjects.EnemyLaser;

import java.util.List;

//Deliverable 2
public class LaserStrategyTriple implements LaserStrategy {
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    String filename;
    String movementClass;

    public LaserStrategyTriple () {
        laserWidth = 20f;
        laserHeight = 20f;
        timeBetweenShots = 0.5f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 190f;
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
    public void laserFire (float deltaTime, Rectangle hitBox, List<EnemyLaser> laserList) {
        timeSinceLastShot += deltaTime;
        try {
            Class cls = Class.forName("com.gameclasses.controller.lasermovement." + movementClass);
            LaserMovement laserMovement = (LaserMovement) cls.getConstructor().newInstance();
            laserMovement.setDirection(0, -1);
            laserMovement.setSpeed(laserMovementSpeed);
            laserMovement.setAcceleration(1);
            if (canFire()) {
                timeSinceLastShot = 0;
                laserList.add(new EnemyLaser(filename,
                        new Rectangle(hitBox.x + hitBox.width * 0.2f,
                                hitBox.y - laserHeight,
                                laserWidth,
                                laserHeight),
                        laserMovement));
                laserList.add(new EnemyLaser(filename,
                        new Rectangle(hitBox.x + hitBox.width * 0.5f,
                                hitBox.y - laserHeight,
                                laserWidth,
                                laserHeight),
                        laserMovement));
                laserList.add(new EnemyLaser(filename,
                        new Rectangle(hitBox.x + hitBox.width * 0.9f,
                                hitBox.y - laserHeight,
                                laserWidth,
                                laserHeight),
                        laserMovement));
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
