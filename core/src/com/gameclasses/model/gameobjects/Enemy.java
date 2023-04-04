package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.laserstrategy.LaserWrapper;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.lasermovement.LaserMovement;

import java.util.List;

//Deliverable 2
abstract public class Enemy {
    public Rectangle hitbox;
    public int hp;
    public int score;
    public boolean isFinalBoss;
    public LaserWrapper laserWrapper;
    public LaserMovement enemyLaserMovement;
    Texture enemyTexture;

    public Enemy () {
        isFinalBoss = false;
    }

    public void draw (Batch batch, float deltaTime) {
        enemyLaserMovement.move(deltaTime, this.hitbox);
        batch.draw(enemyTexture, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public void fire (float deltaTime, List<EnemyLaser> lasers) {
        laserWrapper.fire(deltaTime, this.hitbox, lasers);
    }

    public boolean overlaps (Rectangle other) {
        return this.hitbox.overlaps(other);
    }

    public abstract void die ();

    public boolean isOutOfBounds () {
        return this.hitbox.x + hitbox.width < 0 || this.hitbox.x > GameConstants.WINDOW_WIDTH || this.hitbox.y + hitbox.height < 0
                || this.hitbox.y > GameConstants.WINDOW_HEIGHT;
    }
}

