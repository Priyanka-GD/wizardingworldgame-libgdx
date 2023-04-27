package com.gameclasses.model.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.controller.DetectCollision;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.controller.RenderCharacters;
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
    private RenderLaser renderLaser;
    private DetectCollision detectCollision;
    private RenderCharacters renderCharacter;
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
        renderCharacter = new RenderCharacters();
        renderCharacter.loadEnemies(config, enemyToBeReleased, enemyReleaseTime);
    }
    public void render (SpriteBatch sbatch, float deltaTime) {
        updateGame(deltaTime);
        renderCharacter.renderCharacter(sbatch, deltaTime, player);
        // Deliverable 2
        renderCharacter.renderEnemy(sbatch, deltaTime, enemyShipList, enemyLaserList, this.end);
        renderLaser.renderEnemyLasers(sbatch, deltaTime, enemyLaserList);
        renderLaser.renderPlayerShipProjectile(sbatch, deltaTime, playerBulletList);
    }
    private void updateGame (float deltaTime) {
        characterTimestamp += deltaTime;
        renderCharacter.spawnEnemy(enemyToBeReleased, enemyReleaseTime, enemyShipList, enemyCharacterFactory, characterTimestamp);
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
