package com.gameclasses.model.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.controller.DetectCollision;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.controller.RenderLaser;
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

// break down module
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
    private RenderLaser renderLaser;
    private DetectCollision detectCollision;


    public GameSystem (BackgroundScreen screen) {
        this.subject = screen;
        init();
    }

    //initialize variables
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
        detectCollision = new DetectCollision();
        renderLaser = new RenderLaser();
        loadEnemies(config);

    }

    //render character on screen
    private void renderCharacter (SpriteBatch sbatch, float deltaTime) {
        player.draw(sbatch, deltaTime);
    }

    public void render (SpriteBatch sbatch, float deltaTime) {
        updateGame(deltaTime);
        renderCharacter(sbatch, deltaTime);
        // Deliverable 2
        renderEnemy(sbatch, deltaTime);
        renderLaser.renderEnemyLasers(sbatch, deltaTime, enemyLaserList);
        renderLaser.renderPlayerShipProjectile(sbatch, deltaTime, playerBulletList);
    }

    //Deliverable 2
    //spawning enemies
    private void spawnEnemy () {
        if (enemyReleaseTime.size() > 0 && characterTimestamp > enemyReleaseTime.peek()) {
            enemyReleaseTime.poll();
            Enemy enemy = enemyCharacterFactory.produce(enemyToBeReleased.poll());
            enemyShipList.add(enemy);
        }
    }

    //loading enemies
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

    //rendering enemies on screen
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
        // collision detection
        detectCollision.playerCollisionWithEnemy(enemyLaserList, player, playerLivesSystem);
        detectCollision.collision(playerBulletList, enemyShipList, enemyLaserList, playerLivesSystem);
        // When player life gets exhausted, then game over screen
        if (playerLivesSystem.getLives() == 0)
            this.end = true;
    }

    // if the game ends
    public boolean canEnd () {
        return characterTimestamp > GameConstants.GAME_LENGTH || this.end || playerLivesSystem.canEnd();
    }
    public void setLivesSystem (PlayerLivesSystem ss) {
        this.playerLivesSystem = ss;
    }

}
