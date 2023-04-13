package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.utils.GameConstants;

public class PlayerProjectile {
    private final float x;
    private float movementSpeed;
    private final float y;
    public Rectangle playerBullethitBox;
    private final float acceleration;
    private final Texture playerBulletTexture;

    public PlayerProjectile (Builder builder) {
        this.playerBullethitBox = builder.hitBox;
        this.movementSpeed = builder.movementSpeed;
        this.x = builder.x;
        this.y = builder.y;
        this.acceleration = builder.acceleration;
        this.playerBulletTexture = builder.texture;
    }

    public boolean canRemove () {
        return playerBullethitBox.x < 0 || playerBullethitBox.x > GameConstants.WINDOW_WIDTH
                || playerBullethitBox.y > GameConstants.WINDOW_HEIGHT || playerBullethitBox.y < 0;
    }

    public void move (float deltaTime) {
        playerBullethitBox.x += movementSpeed * x * deltaTime;
        playerBullethitBox.y += movementSpeed * y * deltaTime;
        movementSpeed = movementSpeed * acceleration;
    }

    public void draw (Batch batch) {
        batch.draw(playerBulletTexture, playerBullethitBox.x, playerBullethitBox.y, playerBullethitBox.width, playerBullethitBox.height);
    }

    public static class Builder {
        private final Texture texture;
        private float movementSpeed;
        private Rectangle hitBox;
        private float x;
        private final float acceleration;
        private float y;

        public Builder (Texture texture) {
            this.texture = texture;
            x = 0;
            y = 1;
            acceleration = 1;
        }

        public Builder hitBox (Rectangle hitBox) {
            this.hitBox = hitBox;
            return this;
        }

        public Builder hitBox (float x, float y, float width, float height) {
            this.hitBox = new Rectangle(x, y, width, height);
            return this;
        }

        public Builder direction (float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder speed (float speed) {
            this.movementSpeed = speed;
            return this;
        }

        public PlayerProjectile build () {
            return new PlayerProjectile(this);
        }
    }
}
