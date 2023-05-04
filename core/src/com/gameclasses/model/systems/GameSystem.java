package com.gameclasses.model.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.controller.DetectCollision;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.controller.RenderCharacters;
import com.gameclasses.controller.RenderLaser;
import com.gameclasses.controller.observer.CheatingObserver;
import com.gameclasses.model.factories.EnemyShipFactory;
import com.gameclasses.model.gamecontrollable.CharacterCommand;
import com.gameclasses.model.gameobjects.*;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.gamescreens.BackgroundScreen;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameSystem extends CheatingObserver {
    Player player;
    private CharacterCommand characterCommand;
    private Queue<Float> enemyReleaseTime;
    private Queue<JSONObject> enemyToBeReleased;
    private List<Enemy> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    private List<EnemyLaser> heavyList;
    protected BackgroundScreen subject;
    EnemyShipFactory enemyCharacterFactory;
    private float characterTimestamp;
    private boolean end = false;
    private PlayerSystem playerSystem;
    private List<PlayerProjectile> playerBulletList;
    private RenderLaser renderLaser;
    private DetectCollision detectCollision;
    private RenderCharacters renderCharacter;
    private List<PlayerSpecialBomb> specialBombs;
    private CharacterCommand command;
    private boolean isCheating;

    public GameSystem (BackgroundScreen screen) {
        this.subject = screen;
        this.subject.attachCheatingObserver(this);
        init();
    }

    //initialize variables
    public void init () {
        JsonConfigReader config = GameConstants.config;
        playerBulletList = new ArrayList<>();
        specialBombs = new ArrayList<>();
        player = new Player(config.getPlayerAttribute(), playerBulletList, specialBombs);
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
        command = new CharacterCommand();
        command.add(player);
        renderCharacter.loadEnemies(config, enemyToBeReleased, enemyReleaseTime);
        heavyList = new ArrayList<>();
    }
    public void render (SpriteBatch sbatch, float deltaTime) {
        updateGame(deltaTime);
        renderCharacter.renderCharacter(sbatch, deltaTime, player);
        // Deliverable 2
        renderCharacter.renderEnemy(sbatch, deltaTime, enemyShipList, enemyLaserList, this.end);
        renderLaser.renderEnemyLasers(sbatch, deltaTime, enemyLaserList);
        renderLaser.renderPlayerShipProjectile(sbatch, deltaTime, playerBulletList);
        //Deliverable 3
        renderLaser.renderPlayerShipBomb(sbatch, deltaTime, specialBombs, enemyLaserList, enemyShipList, playerSystem);
    }
    private void updateGame (float deltaTime) {
        characterTimestamp += deltaTime;
        renderCharacter.spawnEnemy(enemyToBeReleased, enemyReleaseTime, enemyShipList, enemyCharacterFactory, characterTimestamp);
        command.run();
        // collision detection
        if (!isCheating)
            detectCollision.playerCollisionWithEnemy(enemyLaserList, player, playerSystem);
        detectCollision.collision(playerBulletList, enemyShipList, enemyLaserList, playerSystem);
        // When player life gets exhausted, then game over screen
        if (playerSystem.getLives() == 0)
            this.end = true;
    }

    // if the game ends
    public boolean canEnd () {
        return characterTimestamp > GameConstants.GAME_LENGTH || this.end || playerSystem.canEnd();
    }

    public void setLivesSystem (PlayerSystem ss) {
        this.playerSystem = ss;
        this.command.setCheckBombs(ss);
    }

    @Override
    public void updateCheating () {
        this.isCheating = subject.getIsCheating();
        this.player.changeMode(this.isCheating);
    }
}
