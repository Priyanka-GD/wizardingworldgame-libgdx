package com.mygdx.game.laserstrategy;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameobjects.EnemyLaser;

import java.util.List;

//Deliverable 2
public interface LaserStrategy {
    void setLaserMovement (String movement);

    void setTexture (String filename);

    void fire (float deltaTime, Rectangle hitbox, List<EnemyLaser> list);
}
