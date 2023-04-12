package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.utils.GameConstants;

public class PlayerProjectile {
    public Rectangle playerBulletHitbox;
    private float movementSpeed;
    private final float xDirection;
    private final float yDirection;
    private final float acceleration;
    private final Texture playerBulletTexture;

    public PlayerProjectile (Builder builder) {
        this.playerBulletHitbox = builder.hitbox;
        this.movementSpeed = builder.movementSpeed;
        this.xDirection = builder.xDirection;
        this.yDirection = builder.yDirection;
        this.acceleration = builder.acceleration;
        this.playerBulletTexture = builder.textureReg;
    }

    public boolean canRemove () {
        return playerBulletHitbox.x < 0 || playerBulletHitbox.x > GameConstants.WINDOW_WIDTH
                || playerBulletHitbox.y > GameConstants.WINDOW_HEIGHT || playerBulletHitbox.y < 0;
    }

    public void move (float deltaTime) {
        playerBulletHitbox.x += movementSpeed * xDirection * deltaTime;
        playerBulletHitbox.y += movementSpeed * yDirection * deltaTime;
        movementSpeed = movementSpeed * acceleration;
    }

    public void draw (Batch batch) {
        batch.draw(playerBulletTexture, playerBulletHitbox.x, playerBulletHitbox.y, playerBulletHitbox.width, playerBulletHitbox.height);
    }

    public static class Builder {
        private Rectangle hitbox;
        private float movementSpeed;
        private float xDirection;
        private float yDirection;
        private float acceleration;
        private final Texture textureReg;

        public Builder (Texture texture) {
            this.textureReg = texture;
            xDirection = 0;
            yDirection = 1;
            acceleration = 1;
        }

        public Builder hitbox (Rectangle hitbox) {
            this.hitbox = hitbox;
            return this;
        }

        public Builder hitbox (float x, float y, float width, float height) {
            this.hitbox = new Rectangle(x, y, width, height);
            return this;
        }

        public Builder direction (float x, float y) {
            this.xDirection = x;
            this.yDirection = y;
            return this;
        }

        public Builder speed (float speed) {
            this.movementSpeed = speed;
            return this;
        }

        public Builder acceleration (float acc) {
            this.acceleration = acc;
            return this;
        }

        public PlayerProjectile build () {
            return new PlayerProjectile(this);
        }
    }
}
