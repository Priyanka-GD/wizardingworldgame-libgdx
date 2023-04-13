package com.gameclasses.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.controller.lasermovement.LaserMovement;

public class EnemyLaser {
    private final Texture textureReg;
    public Rectangle hitBox;
    public LaserMovement laserMovement;

    public EnemyLaser (String filename, Rectangle hitBox, LaserMovement laserMovement) {
        this.hitBox = hitBox;
        this.laserMovement = laserMovement;
        this.textureReg = new Texture(filename);
    }

    public void moveLaser (float deltaTime) {
        laserMovement.moveLaser(deltaTime, hitBox);
    }

    public void draw (Batch batch) {
        batch.draw(textureReg, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
}
