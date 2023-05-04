package com.gameclasses.controller.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.gameclasses.model.gameobjects.EnemyLaser;

import java.util.List;

//Deliverable 2
public interface LaserStrategy {
    void setLaserMovement (String movement);

    void setLaserTexture (String filename);

    void laserFire (float deltaTime, Rectangle hitBox, List<EnemyLaser> list);

    boolean canFire ();
}
