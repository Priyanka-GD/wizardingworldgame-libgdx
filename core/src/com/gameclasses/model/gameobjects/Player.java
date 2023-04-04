
package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gamecontrollable.GameControllable;
import com.gameclasses.utils.GameConstants;


public class Player implements GameControllable {
    private boolean isSlow;
    private final float slowMultiplier = 0.1f;

    public float moveSpeed;
    private final Texture characterMode;
    private final Texture hitMode;
    private float shootTimestamp = 0;
    public Rectangle characterBox;
    public Rectangle hitBox;

    public Player (Object object) {
        float width = 100;
        float height = 100;
        float x = (GameConstants.WINDOW_WIDTH - width) / 2;
        float y = 50;
        this.moveSpeed = 10f;
        this.characterBox = new Rectangle(x, y, width, height);
        this.hitBox = new Rectangle(250, 400, 15, 15);
        this.characterMode = new Texture("images/harrypotter.png");
        this.hitMode = new Texture("images/hitbox.png");
    }

    public void draw (Batch batch, float deltaTime) {
        update(deltaTime);
        batch.draw(characterMode, characterBox.x, characterBox.y, characterBox.width, characterBox.height);
        if (isSlow)
            batch.draw(hitMode, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    private float getSpeed () {
        float speed = this.moveSpeed;
        if (isSlow) {
            speed *= this.slowMultiplier;
        }
        return speed;
    }

    public void update (float deltaTime) {
        this.shootTimestamp += deltaTime;
    }

    @Override
    public void moveUp () {
        characterBox.y = Math.min(characterBox.y + this.getSpeed(), GameConstants.WINDOW_HEIGHT - characterBox.height);
    }

    @Override
    public void moveDown () {
        characterBox.y = Math.max(characterBox.y - this.getSpeed(), 0);
    }

    @Override
    public void moveLeft() {
        characterBox.x = Math.max(characterBox.x - this.getSpeed(), 0);
    }

    @Override
    public void moveRight () {
        characterBox.x = Math.min(characterBox.x + this.getSpeed(), GameConstants.WINDOW_WIDTH - characterBox.width);
    }

    @Override
    public void slowMode (boolean isSlow) {
        this.isSlow = isSlow;
    }

    public boolean overlaps (Rectangle other) {
        return this.characterBox.overlaps(other);
    }
}

