package com.gameclasses.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.gameobjects.PlayerProjectile;

import java.util.ArrayList;
import java.util.List;

public class RenderLaser {
    //rendering enemy lasers
    public void renderEnemyLasers (SpriteBatch sbatch, float deltaTime, List<EnemyLaser> enemyLaserList) {
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.moveLaser(deltaTime);
            enemyLaser.draw(sbatch);
        }
    }

    // player shooting projectile towards enemies
    public void renderPlayerShipProjectile (SpriteBatch sbatch, float deltaTime, List<PlayerProjectile> playerBulletList) {
        List<PlayerProjectile> removeList = new ArrayList<>();
        for (PlayerProjectile bullet : playerBulletList) {
            bullet.move(deltaTime);
            bullet.draw(sbatch);
            if (bullet.canRemove()) {
                removeList.add(bullet);
            }
        }
        playerBulletList.removeAll(removeList);
    }
}
