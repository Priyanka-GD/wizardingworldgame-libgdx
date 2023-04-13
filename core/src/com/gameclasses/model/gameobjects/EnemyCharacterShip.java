package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;
import com.gameclasses.controller.laserstrategy.LaserBindings;
import com.gameclasses.view.lives.PlayerLivesSystem;

public class EnemyCharacterShip extends Enemy {

    public EnemyCharacterShip (BuilderEnemy builder) {
        super();
        this.hp = builder.hp;
        this.hitBox = builder.hitBox;
        this.score = builder.score;
        this.enemyTexture = builder.texture;
        this.enemyLaserMovement = builder.laserMovement;
        this.laserBindings = builder.laserBindings;
        this.isFinalBoss = builder.isFinalBoss;
    }

    @Override
    public void die (PlayerLivesSystem playerLivesSystem) {
        // Final boss die ends game
        if (this.isFinalBoss) {
            playerLivesSystem.updateEnd(true);
        }
    }

    public static class BuilderEnemy {
        private int hp;
        private int score;
        private Texture texture;
        private Rectangle hitBox;
        private LaserMovement laserMovement;
        private LaserBindings laserBindings;
        private boolean isFinalBoss;

        public BuilderEnemy () {

        }

        public BuilderEnemy hp (int hp) {
            this.hp = hp;
            return this;
        }

        public BuilderEnemy score (int score) {
            this.score = score;
            return this;
        }

        public BuilderEnemy texture (String filename) {
            this.texture = new Texture(filename);
            return this;
        }

        public BuilderEnemy hitBox (int x, int y, int width, int height) {
            this.hitBox = new Rectangle(x, y, width, height);
            return this;
        }

        public BuilderEnemy movement (String movement) {
            try {
                Class cls = Class.forName("com.gameclasses.controller.lasermovement." + movement);
                this.laserMovement = (LaserMovement) cls.getConstructor().newInstance();
            } catch (Throwable e) {
                System.err.println(e);
            } finally {
                return this;
            }
        }

        public BuilderEnemy laserBindings (LaserBindings laserBindings) {
            this.laserBindings = laserBindings;
            return this;
        }

        public BuilderEnemy isFinalBoss (boolean isFinalBoss) {
            this.isFinalBoss = isFinalBoss;
            return this;
        }

        public EnemyCharacterShip build () {
            return new EnemyCharacterShip(this);
        }

    }
}
