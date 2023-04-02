package com.mygdx.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameobjects.Enemy;
import com.mygdx.game.gameobjects.EnemyLaser;
import com.mygdx.game.gameobjects.MainCharacter;
import com.mygdx.game.gamescreens.BackgroundScreen;
import com.mygdx.game.utils.GameConstants;
import com.mygdx.game.utils.JsonConfigReader;
import com.mygdx.game.utils.PlayerCommand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class GameSystem {
    MainCharacter mainCharacter;
    private PlayerCommand command;
    private Queue<Float> enemyReleaseTime;
    private Queue<JSONObject> enemyToBeReleased;
    private List<Enemy> enemyShipList;
    private List<EnemyLaser> enemyLaserList;

    public GameSystem (BackgroundScreen screen) {
        init();
    }

    public void init () {
        JsonConfigReader config = GameConstants.config;
        mainCharacter = new MainCharacter(new Object());
        command = new PlayerCommand();
        command.add(mainCharacter);
        enemyToBeReleased = new LinkedList<>();
        enemyLaserList = new LinkedList<>();
        enemyShipList = new LinkedList<>();
        enemyReleaseTime = new LinkedList<>();
        //Deliverable 2
        loadEnemies(config);

    }

    public void loadEnemies (JsonConfigReader config) {
        JSONArray enemyConfigs = config.getEnemies();
        for (Object obj : enemyConfigs) {
            JSONObject enemyObj = (JSONObject) obj;
            float timestamp = ((Long) enemyObj.get("spawnTime")).floatValue();
            int count = ((Long) enemyObj.get("count")).intValue();
            for (int i = 0; i < count; i++) {
                enemyToBeReleased.offer(enemyObj);
                enemyReleaseTime.offer(timestamp);
                timestamp += ((Long) enemyObj.get("interval")).floatValue();
            }
        }
    }

    //Deliverable 2
    private void renderEnemy (SpriteBatch sbatch, float deltaTime) {
        List<Enemy> removeList = new ArrayList<>();
        for (Enemy enemy : enemyShipList) {
            enemy.draw(sbatch, deltaTime);
            enemy.fire(deltaTime, enemyLaserList);
        }
        enemyShipList.removeAll(removeList);
    }

    public void render (SpriteBatch sbatch, float deltaTime) {
        renderCharacter(sbatch, deltaTime);
        makeCharacterMove();
        renderEnemy(sbatch, deltaTime);
    }

    private void renderCharacter (SpriteBatch sbatch, float deltaTime) {
        mainCharacter.draw(sbatch, deltaTime);
    }

    private void makeCharacterMove () {
        command.run();
    }
}
