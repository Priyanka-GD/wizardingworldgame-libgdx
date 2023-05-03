package com.gameclasses.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.model.factories.EnemyShipFactory;
import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.gameobjects.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RenderCharacters {

    //render character on screen
    public void renderCharacter (SpriteBatch sbatch, float deltaTime, Player player) {

        player.draw(sbatch, deltaTime);
    }

    //rendering enemies on screen
    public void renderEnemy (SpriteBatch sbatch, float deltaTime, List<Enemy> enemyShipList, List<EnemyLaser> enemyLaserList, boolean end, List<EnemyLaser> heavyList) {
        List<Enemy> removeList = new ArrayList<>();
        for (Enemy enemy : enemyShipList) {
            enemy.draw(sbatch, deltaTime);
            enemy.fire(deltaTime, enemyLaserList, heavyList);
            if (enemy.isOutOfBounds()) {
                if (enemy.isFinalBoss) {
                    end = true;
                }
                removeList.add(enemy);
            }
        }
        enemyShipList.removeAll(removeList);
    }

    //loading enemies
    public void loadEnemies (JsonConfigReader config, Queue<JSONObject> enemyToBeReleased, Queue<Float> enemyReleaseTime) {
        JSONArray enemyConfigs = config.getEnemies();
        for (Object obj : enemyConfigs) {
            JSONObject enemyObj = (JSONObject) obj;
            float timestamp = ((Long) enemyObj.get("spawnTime")).floatValue();
            int count = ((Long) enemyObj.get("count")).intValue();
            for (int i = 0; i < count; i++) {
                enemyToBeReleased.offer(enemyObj);
                enemyReleaseTime.offer(timestamp);
            }
        }
    }

    //Deliverable 2
    //spawning enemies
    public void spawnEnemy (Queue<JSONObject> enemyToBeReleased, Queue<Float> enemyReleaseTime, List<Enemy> enemyShipList,
                            EnemyShipFactory enemyCharacterFactory, float characterTimestamp) {
        if (enemyReleaseTime.size() > 0 && characterTimestamp > enemyReleaseTime.peek()) {
            enemyReleaseTime.poll();
            Enemy enemy = enemyCharacterFactory.produce(enemyToBeReleased.poll());
            enemyShipList.add(enemy);
        }
    }
}
