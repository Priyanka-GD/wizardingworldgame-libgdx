package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gameobjects.EnemyLaser;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LaserBindings {
    private final Queue<Float> laserReleaseTime;
    private final Queue<LaserStrategy> laserStrategies;
    private float timestamp;
    private LaserStrategy currentLaserStrategy;

    public LaserBindings () {
        timestamp = 0;
        laserReleaseTime = new LinkedList<>();
        laserStrategies = new LinkedList<>();
    }

    public void fire (float deltaTime, Rectangle laserHitbox, List<EnemyLaser> enemyLaserList) {
        timestamp += deltaTime;
        if (currentLaserStrategy == null || (!laserReleaseTime.isEmpty() && timestamp >= laserReleaseTime.peek())) {
            laserReleaseTime.poll();
            currentLaserStrategy = laserStrategies.poll();
        }
        currentLaserStrategy.laserFire(deltaTime, laserHitbox, enemyLaserList);
    }

    public void addLaser (float timeStamp, String stringStrategy, String stringMovement, String texture) {

        try {
            Class cls = Class.forName("com.gameclasses.controller.laserstrategy." + stringStrategy);
            LaserStrategy strategy = (LaserStrategy) cls.getConstructor().newInstance();
            strategy.setLaserMovement(stringMovement);
            strategy.setLaserTexture(texture);
            laserReleaseTime.offer(timeStamp);
            laserStrategies.offer(strategy);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
