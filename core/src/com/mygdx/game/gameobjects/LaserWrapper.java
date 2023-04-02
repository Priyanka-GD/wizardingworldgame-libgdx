package com.mygdx.game.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LaserWrapper {
    private final Queue<Float> releaseTime;
    private float timestamp;

    public LaserWrapper () {
        timestamp = 0;
        releaseTime = new LinkedList<>();
    }

    public void fire (float deltaTime, Rectangle hitbox, List<EnemyLaser> list) {
        timestamp += deltaTime;
    }

    public void addLaser (float timestamp, String stratStr, String moveStr, String texture) {

        try {
            Class cls = Class.forName("com.mygdx.game.laserstrategy." + stratStr);
            releaseTime.offer(timestamp);

        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
