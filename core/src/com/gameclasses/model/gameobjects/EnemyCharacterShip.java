package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.laserstrategy.LaserWrapper;
import com.gameclasses.view.lasermovement.LaserMovement;

public class EnemyCharacterShip extends Enemy {

    public EnemyCharacterShip (EnemyBuilder builder) {
        super();
        this.hp = builder.hp;
        this.hitbox = builder.hitbox;
        this.score = builder.score;
        this.enemyTexture = builder.texture;
        this.enemyLaserMovement = builder.laserMovement;
        this.laserWrapper = builder.laserWrapper;
        this.isFinalBoss = builder.isFinalBoss;
    }

    @Override
    public void die () {

    }

    public static class EnemyBuilder {
        private int hp;
        private int score;
        private Texture texture;
        private Rectangle hitbox;
        private LaserMovement laserMovement;
        private LaserWrapper laserWrapper;
        private boolean isFinalBoss;

        public EnemyBuilder () {

        }

        public EnemyBuilder hp (int hp) {
            this.hp = hp;
            return this;
        }

        public EnemyBuilder score (int score) {
            this.score = score;
            return this;
        }

        public EnemyBuilder texture (String filename) {
            this.texture = new Texture(filename);
            return this;
        }

        public EnemyBuilder hitbox (int x, int y, int width, int height) {
            this.hitbox = new Rectangle(x, y, width, height);
            return this;
        }

        public EnemyBuilder movement (String movement) {
            try {
                Class cls = Class.forName("com.gameclasses.model.lasermovement." + movement);
                this.laserMovement = (LaserMovement) cls.getConstructor().newInstance();
            } catch (Throwable e) {
                System.err.println(e);
            } finally {
                return this;
            }
        }

        public EnemyBuilder laserWrapper (LaserWrapper laserWrapper) {
            this.laserWrapper = laserWrapper;
            return this;
        }

        public EnemyBuilder isFinalBoss (boolean isFinalBoss) {
            this.isFinalBoss = isFinalBoss;
            return this;
        }

        public EnemyCharacterShip build () {
            return new EnemyCharacterShip(this);
        }

    }
}
