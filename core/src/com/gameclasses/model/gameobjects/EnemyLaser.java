package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.lasermovement.Movement;
import com.gameclasses.utils.GameConstants;

public class EnemyLaser {
    private final Texture textureReg;
    public Rectangle hitbox;
    public Movement movement;

    public EnemyLaser (String filename, Rectangle hitbox, Movement movement) {
        this.hitbox = hitbox;
        this.movement = movement;
        this.textureReg = new Texture(filename);
    }

    public boolean canRemove () {
        return hitbox.x < 0 || hitbox.x > GameConstants.WINDOW_WIDTH
                || hitbox.y > GameConstants.WINDOW_HEIGHT || hitbox.y < 0;
    }

    public void move (float deltaTime) {
        movement.move(deltaTime, hitbox);
    }

    public boolean overlaps (Rectangle hitbox) {
        return this.hitbox.overlaps(hitbox);
    }

    public void draw (Batch batch) {
        batch.draw(textureReg, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
