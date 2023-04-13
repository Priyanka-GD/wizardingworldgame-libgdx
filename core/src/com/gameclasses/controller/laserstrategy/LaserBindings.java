package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gameobjects.EnemyLaser;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LaserBindings {
    private final Queue<Float> releaseTime;
    private final Queue<LaserStrategy> laserStrategies;
    private float timestamp;
    private LaserStrategy currentLaserStrategy;

    public LaserBindings () {
        timestamp = 0;
        releaseTime = new LinkedList<>();
        laserStrategies = new LinkedList<>();
    }

    public void fire (float deltaTime, Rectangle laserHitBox, List<EnemyLaser> enemyLaserList) {
        timestamp += deltaTime;
        if (currentLaserStrategy == null || (!releaseTime.isEmpty() && timestamp >= releaseTime.peek())) {
            releaseTime.poll();
            currentLaserStrategy = laserStrategies.poll();
        }
        currentLaserStrategy.laserFire(deltaTime, laserHitBox, enemyLaserList);
    }

    public void addLaser (String texture) {

        try {
            Class cls = Class.forName("com.gameclasses.controller.laserstrategy." + "LaserStrategyOne");
            LaserStrategy strategy = (LaserStrategy) cls.getConstructor().newInstance();
            strategy.setLaserMovement("StraightLineShapedLaserMovement");
            strategy.setLaserTexture(texture);
            releaseTime.offer(0.0f);
            laserStrategies.offer(strategy);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
