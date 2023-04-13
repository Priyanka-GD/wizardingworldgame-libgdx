
package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gamecontrollable.GameControllable;
import com.gameclasses.utils.GameConstants;

import java.util.List;


public class Player implements GameControllable {
    private boolean isSlow;
    private final float slowMultiplier = 0.1f;

    public float moveSpeed;
    private final Texture characterMode;
    private float shootTimestamp = 0;
    public Rectangle characterBox;
    public Rectangle hitBox;
    private final float playerShootInterval = 0.2f;
    private final List<PlayerProjectile> playerBulletList;

    public Player (Object object, List<PlayerProjectile> playerBulletList) {
        float width = 100;
        float height = 100;
        float x = (GameConstants.WINDOW_WIDTH - width) / 2;
        float y = 50;
        this.moveSpeed = 10f;
        this.characterBox = new Rectangle(x, y, width, height);
        this.hitBox = new Rectangle(250, 400, 15, 15);
        this.characterMode = new Texture("images/harrypotter.png");
        this.playerBulletList = playerBulletList;
    }

    public void draw (Batch batch, float deltaTime) {
        update(deltaTime);
        batch.draw(characterMode, characterBox.x, characterBox.y, characterBox.width, characterBox.height);
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
    public void moveLeft () {
        characterBox.x = Math.max(characterBox.x - this.getSpeed(), 0);
    }

    @Override
    public void moveRight () {
        characterBox.x = Math.min(characterBox.x + this.getSpeed(), GameConstants.WINDOW_WIDTH - characterBox.width);
    }

    @Override
    public void playerFire () {
        if (shootTimestamp >= playerShootInterval) {
            shootTimestamp = 0;
            this.playerBulletList.add(new PlayerProjectile.Builder(new Texture("images/snitch.png"))
                    .hitBox(new Rectangle((characterBox.x + (characterBox.width / 2)) - 5, (characterBox.y + characterBox.height) - 5, 15, 30))
                    .speed(300)
                    .direction(0, 1)
                    .build());
            this.playerBulletList.add(new PlayerProjectile.Builder(new Texture("images/snitch.png"))
                    .hitBox(new Rectangle((characterBox.x + (characterBox.width / 2)) + 10, (characterBox.y + characterBox.height) - 5, 15, 30))
                    .speed(300)
                    .direction(0, 1)
                    .build());
        }
    }

    @Override
    public void slowMode (boolean isSlow) {
        this.isSlow = isSlow;
    }

    public boolean overlaps (Rectangle other) {
        return this.characterBox.overlaps(other);
    }
}

