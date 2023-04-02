package com.mygdx.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.lasermovement.Movement;

public class EnemyCharacterShip extends Enemy {

    public EnemyCharacterShip (Builder builder) {
        super();
        this.hp = builder.hp;
        this.hitbox = builder.hitbox;
        this.score = builder.score;
        this.enemyTexture = builder.texture;
        this.movement = builder.movement;
        this.laserWrapper = builder.laserWrapper;
        this.isFinalBoss = builder.isFinalBoss;
    }

    @Override
    public void die () {

    }


    public static class Builder {
        private int hp;
        private int score;
        private Texture texture;
        private Rectangle hitbox;
        private Movement movement;
        private LaserWrapper laserWrapper;
        private boolean isFinalBoss;

        public Builder () {

        }

        public Builder hp (int hp) {
            this.hp = hp;
            return this;
        }

        public Builder score (int score) {
            this.score = score;
            return this;
        }

        public Builder texture (String filename) {
            this.texture = new Texture(filename);
            return this;
        }

        public Builder hitbox (int x, int y, int width, int height) {
            this.hitbox = new Rectangle(x, y, width, height);
            return this;
        }

        public Builder movement (String movement) {
            try {
                Class cls = Class.forName("com.mygdx.game.lasermovement." + movement);
                this.movement = (Movement) cls.getConstructor().newInstance();
            } catch (Throwable e) {
                System.err.println(e);
            } finally {
                return this;
            }
        }

        public Builder laserWrapper (LaserWrapper laserWrapper) {
            this.laserWrapper = laserWrapper;
            return this;
        }

        public Builder isFinalBoss (boolean isFinalBoss) {
            this.isFinalBoss = isFinalBoss;
            return this;
        }

        public EnemyCharacterShip build () {
            return new EnemyCharacterShip(this);
        }

    }
}
