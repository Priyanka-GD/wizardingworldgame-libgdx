package com.gameclasses.model.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.model.factories.EnemyFactory;
import com.gameclasses.model.factories.EnemyShipFactory;
import com.gameclasses.model.gamecontrollable.CharacterCommand;
import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.gameobjects.Player;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.gamescreens.BackgroundScreen;
import com.gameclasses.view.score.PlayerLivesSystem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameSystem {
    Player player;
    private CharacterCommand characterCommand;
    private Queue<Float> enemyReleaseTime;
    private Queue<JSONObject> enemyToBeReleased;
    private List<Enemy> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    protected BackgroundScreen subject;
    EnemyFactory enemyCharacterFactory;
    private float characterTimestamp;
    private boolean end = false;
    private PlayerLivesSystem playerLivesSystem;

    public GameSystem (BackgroundScreen screen) {
        this.subject = screen;
        init();
    }

    public void init () {
        JsonConfigReader config = GameConstants.config;
        player = new Player(config.getPlayerAttribute());
        GameConstants.playerShip = player;
        characterCommand = new CharacterCommand();
        characterCommand.add(player);
        enemyToBeReleased = new LinkedList<>();
        enemyLaserList = new ArrayList<>();
        enemyShipList = new LinkedList<>();
        enemyReleaseTime = new LinkedList<>();
        //Deliverable 2
        characterTimestamp = 0;
        enemyCharacterFactory = new EnemyShipFactory();
        loadEnemies(config);

    }

    private void renderCharacter (SpriteBatch sbatch, float deltaTime) {
        player.draw(sbatch, deltaTime);
    }

    public void render (SpriteBatch sbatch, float deltaTime) {
        updateGame(deltaTime);
        renderCharacter(sbatch, deltaTime);
        // Deliverable 2
        renderEnemy(sbatch, deltaTime);
        renderEnemyLasers(sbatch, deltaTime);
    }

    //Deliverable 2
    private void spawnEnemy () {
        if (enemyReleaseTime.size() > 0 && characterTimestamp > enemyReleaseTime.peek()) {
            enemyReleaseTime.poll();
            Enemy enemy = enemyCharacterFactory.produce(enemyToBeReleased.poll());
            enemyShipList.add(enemy);
        }
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
    private void renderEnemy (SpriteBatch sbatch, float deltaTime) {
        List<Enemy> removeList = new ArrayList<>();
        for (Enemy enemy : enemyShipList) {
            enemy.draw(sbatch, deltaTime);
            enemy.fire(deltaTime, enemyLaserList);
            if (enemy.isOutOfBounds()) {
                if (enemy.isFinalBoss) {
                    this.end = true;
                }
                removeList.add(enemy);
            }
        }
        enemyShipList.removeAll(removeList);
    }
    private void updateGame (float deltaTime) {
        characterTimestamp += deltaTime;
        spawnEnemy();
        characterCommand.run();
        detectCollision();
        if (playerLivesSystem.getLives() == 0)
            this.end = true;
    }
    private void renderEnemyLasers (SpriteBatch sbatch, float deltaTime) {
        List<EnemyLaser> removeList1 = new ArrayList<>();
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.move(deltaTime);
            enemyLaser.draw(sbatch);
            if (enemyLaser.canRemove()) {
                removeList1.add(enemyLaser);
            }
        }
        enemyLaserList.removeAll(removeList1);
    }

    public boolean canEnd () {
        return characterTimestamp > GameConstants.GAME_LENGTH || this.end;
    }

    public void setScoreSystem (PlayerLivesSystem ss) {
        this.playerLivesSystem = ss;
    }

    private void playerCollisionWithEnemy () {
        List<EnemyLaser> removeList = new ArrayList<>();
        for (EnemyLaser laser : enemyLaserList) {
            if (player.overlaps(laser.hitBox)) {
                removeList.add(laser);
                playerLivesSystem.updateLives(-1);
            }
        }
        enemyLaserList.removeAll(removeList);
    }

    private void detectCollision () {
        playerCollisionWithEnemy();
    }
}
