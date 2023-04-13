package com.gameclasses.model.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.model.factories.EnemyShipFactory;
import com.gameclasses.model.gamecontrollable.CharacterCommand;
import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.gameobjects.Player;
import com.gameclasses.model.gameobjects.PlayerProjectile;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.gamescreens.BackgroundScreen;
import com.gameclasses.view.lives.PlayerLivesSystem;
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
    EnemyShipFactory enemyCharacterFactory;
    private float characterTimestamp;
    private boolean end = false;
    private PlayerLivesSystem playerLivesSystem;
    private List<PlayerProjectile> playerBulletList;

    public GameSystem (BackgroundScreen screen) {
        this.subject = screen;
        init();
    }

    public void init () {
        JsonConfigReader config = GameConstants.config;
        playerBulletList = new ArrayList<>();
        player = new Player(config.getPlayerAttribute(), playerBulletList);
        GameConstants.PLAYERSHIP = player;
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
        renderPlayerShipProjectile(sbatch, deltaTime);
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
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.moveLaser(deltaTime);
            enemyLaser.draw(sbatch);
        }
    }

    public boolean canEnd () {
        return characterTimestamp > GameConstants.GAME_LENGTH || this.end || playerLivesSystem.canEnd();
    }

    public void setLivesSystem (PlayerLivesSystem ss) {
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

    private void renderPlayerShipProjectile (SpriteBatch sbatch, float deltaTime) {
        List<PlayerProjectile> removeList = new ArrayList<>();
        for (PlayerProjectile bullet : playerBulletList) {
            bullet.move(deltaTime);
            bullet.draw(sbatch);
            if (bullet.canRemove()) {
                removeList.add(bullet);
            }
        }
        playerBulletList.removeAll(removeList);
    }

    private void detectCollision () {
        playerCollisionWithEnemy();
        collision();
    }

    private void collision () {
        List<PlayerProjectile> playerRemoveBulletList = new ArrayList<>();
        List<Enemy> removeEnemyList = new ArrayList<>();
        for (PlayerProjectile bullet : playerBulletList) {
            for (Enemy enemy : enemyShipList) {
                if (enemy.overlaps(bullet.playerBullethitBox)) {
                    enemy.hp -= 1;
                    playerRemoveBulletList.add(bullet);
                    enemyLaserList.removeAll(enemyLaserList);
                    if (enemy.hp <= 0) {
                        removeEnemyList.add(enemy);
                        enemy.die(playerLivesSystem);
                    }
                }
            }
        }
        playerBulletList.removeAll(playerRemoveBulletList);
        enemyShipList.removeAll(removeEnemyList);
    }
}
