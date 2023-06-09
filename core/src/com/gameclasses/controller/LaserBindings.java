package com.gameclasses.controller;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.laserstrategy.LaserStrategy;
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

    public void addLaser (float timeStamp, String strategyString, String movementString) {
        try {
            Class cls = Class.forName("com.gameclasses.controller.laserstrategy." + strategyString);
            LaserStrategy strategy = (LaserStrategy) cls.getConstructor().newInstance();
            strategy.setLaserMovement(movementString);
            strategy.setLaserTexture("images/thunder1.png");
            releaseTime.offer(timeStamp);
            laserStrategies.offer(strategy);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
