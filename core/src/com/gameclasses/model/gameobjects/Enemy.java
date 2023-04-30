package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;
import com.gameclasses.controller.laserstrategy.LaserBindings;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.observerlivesandscore.PlayerSystem;

import java.util.List;

//Deliverable 2
abstract public class Enemy {
    public Rectangle hitBox;
    public int hp;
    public int score;
    public boolean isFinalBoss;
    public LaserBindings laserBindings;
    public LaserMovement enemyLaserMovement;
    Texture enemyTexture;

    public Enemy () {
        isFinalBoss = false;
    }

    public void draw (Batch batch, float deltaTime) {
        enemyLaserMovement.moveLaser(deltaTime, this.hitBox);
        batch.draw(enemyTexture, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void fire (float deltaTime, List<EnemyLaser> lasers) {
        laserBindings.fire(deltaTime, this.hitBox, lasers);
    }

    public boolean overlaps (Rectangle other) {
        return this.hitBox.overlaps(other);
    }

    public abstract void die (PlayerSystem playerSystem);

    public boolean isOutOfBounds () {
        return this.hitBox.x + hitBox.width < 0 || this.hitBox.x > GameConstants.WINDOW_WIDTH || this.hitBox.y + hitBox.height < 0
                || this.hitBox.y > GameConstants.WINDOW_HEIGHT;
    }
}

