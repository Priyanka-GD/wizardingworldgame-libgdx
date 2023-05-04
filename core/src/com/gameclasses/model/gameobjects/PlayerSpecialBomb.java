package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.utils.GameConstants;

public class PlayerSpecialBomb {

    public Rectangle hitbox;

    private float movementSpeed;
    private final float bombingSpeed;
    private final float xDirection;
    private final float yDirection;
    private final float acceleration;
    private boolean isBombing;
    private float launchDistance;

    private Texture textureReg;

    public PlayerSpecialBomb (Builder builder) {
        this.hitbox = builder.hitbox;
        this.movementSpeed = builder.movementSpeed;
        this.xDirection = builder.xDirection;
        this.yDirection = builder.yDirection;
        this.acceleration = builder.acceleration;
        this.textureReg = builder.textureReg;
        this.bombingSpeed = 400;
        this.isBombing = false;
        this.launchDistance = 0;
    }

    public boolean canRemove () {
        return hitbox.width > GameConstants.WINDOW_WIDTH
                || hitbox.height > GameConstants.WINDOW_HEIGHT || hitbox.y < 0;
    }
// Bomb moves
    public void move (float deltaTime) {
        hitbox.x += movementSpeed * xDirection * deltaTime;
        if (this.launchDistance < 250 && !this.isBombing) {
            this.launchDistance += movementSpeed * yDirection * deltaTime;
            hitbox.y += movementSpeed * yDirection * deltaTime;
        } else if (this.launchDistance >= 250 && !this.isBombing) {
            this.textureReg = new Texture("images/bombScenes.png");
            this.hitbox = new Rectangle(hitbox.x - 33, hitbox.y, 85, 150);
            this.isBombing = true;
        } else {
            bombing(deltaTime);
        }
        movementSpeed = movementSpeed * acceleration;
    }

    public void draw (Batch batch) {
        batch.draw(textureReg, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void bombing (float deltaTime) {
        hitbox.width += this.bombingSpeed * deltaTime;
        hitbox.height += this.bombingSpeed * deltaTime;
        hitbox.x = hitbox.x - (this.bombingSpeed * deltaTime / 2);
    }

    public static class Builder {
        private Rectangle hitbox;
        private float movementSpeed;
        private float xDirection, yDirection;
        private final float acceleration;
        private final Texture textureReg;

        public Builder (Texture texture) {
            this.textureReg = texture;
            this.xDirection = 0;
            this.yDirection = 1;
            this.acceleration = 1;
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

        public PlayerSpecialBomb build () {
            return new PlayerSpecialBomb(this);
        }
    }
}
