package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;
import com.gameclasses.utils.GameConstants;

public class EnemyLaser {
    private final Texture textureReg;
    public Rectangle hitBox;
    public LaserMovement laserMovement;

    public EnemyLaser (String filename, Rectangle hitbox, LaserMovement laserMovement) {
        this.hitBox = hitbox;
        this.laserMovement = laserMovement;
        this.textureReg = new Texture(filename);
    }

    public boolean canRemove () {
        return hitBox.x < 0 || hitBox.x > GameConstants.WINDOW_WIDTH
                || hitBox.y > GameConstants.WINDOW_HEIGHT || hitBox.y < 0;
    }

    public void move (float deltaTime) {
        laserMovement.move(deltaTime, hitBox);
    }

    public boolean overlaps (Rectangle hitBox) {
        return this.hitBox.overlaps(hitBox);
    }

    public void draw (Batch batch) {
        batch.draw(textureReg, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
}
