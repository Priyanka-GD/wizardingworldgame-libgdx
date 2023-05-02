
package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gamecontrollable.GameControllable;
import com.gameclasses.utils.GameConstants;

import java.util.List;
public class Player implements GameControllable {
    private boolean isSlow, isThrow;
    private final float slowMultiplier = 0.1f;
    public float moveSpeed;
    private final Texture characterMode;
    public Rectangle hitBox, cheatingBox, characterBox;
    private float shootTimestamp = 0;
    private final Texture cheatingMode;
    private Texture bufferMode;
    private final float playerShootInterval = 0.2f;
    private final List<PlayerProjectile> playerBulletList;
    private final List<PlayerSpecialBomb> bomblist;
    private final float bombInterval = 2.5f;

    public Player (Object object, List<PlayerProjectile> playerBulletList, List<PlayerSpecialBomb> bomblist) {
        float width = 100;
        float height = 100;
        float x = (GameConstants.WINDOW_WIDTH - width) / 2;
        float y = 50;
        this.moveSpeed = 10f;
        this.characterBox = new Rectangle(x, y, width, height);
        this.hitBox = new Rectangle(250, 400, 15, 15);
        this.characterMode = new Texture("images/harrypotter.png");
        this.cheatingMode = new Texture("images/cheating.png");
        this.playerBulletList = playerBulletList;
        this.bomblist = bomblist;
        this.cheatingBox = new Rectangle(hitBox.x, hitBox.y, 40, 60);
        bufferMode = this.characterMode;
    }
    public void draw (Batch batch, float deltaTime) {
        update(deltaTime);
        batch.draw(bufferMode, characterBox.x, characterBox.y, characterBox.width, characterBox.height);
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

    @Override
    public void throwBomb () {
        if (shootTimestamp >= bombInterval) {
            shootTimestamp = 0;
            isThrow = true;
            this.bomblist.add(new PlayerSpecialBomb.Builder(new Texture("images/bomb.png"))
                    .hitbox(new Rectangle(hitBox.x - (hitBox.width / 3), hitBox.y + hitBox.height, 30, 30))
                    .speed(250)
                    .direction(0, 1)
                    .build());
        }
    }

    public boolean getIsThrow () {
        return this.isThrow;
    }

    public void setIsThrow (boolean isthrow) {
        this.isThrow = isthrow;
    }

    public boolean overlaps (Rectangle other) {
        return this.characterBox.overlaps(other);
    }

    public void changeMode (boolean isCheating) {
        if (isCheating) {
            this.hitBox = this.cheatingBox;
            this.bufferMode = this.cheatingMode;
        } else {
            this.hitBox = this.characterBox;
            this.bufferMode = this.characterMode;
        }
    }

}

