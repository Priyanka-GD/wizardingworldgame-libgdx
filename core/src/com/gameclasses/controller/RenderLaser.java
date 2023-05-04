package com.gameclasses.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.gameobjects.PlayerProjectile;
import com.gameclasses.model.gameobjects.PlayerSpecialBomb;
import com.gameclasses.model.systems.PlayerSystem;

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

    public void renderPlayerShipBomb (SpriteBatch sbatch, float deltaTime, List<PlayerSpecialBomb> specialBombs,
                                      List<EnemyLaser> enemyLaserList, List<Enemy> enemyShipList, PlayerSystem playerSystem) {
        List<PlayerSpecialBomb> removeList = new ArrayList<>();
        List<Enemy> removeEnemyList = new ArrayList<>();
        for (PlayerSpecialBomb bomb : specialBombs) {
            bomb.move(deltaTime);
            bomb.draw(sbatch);
            if (bomb.canRemove()) {
                removeList.add(bomb);
                enemyLaserList.clear();
                for (Enemy enemy : enemyShipList) {
                    enemy.hp -= 10;
                    if (enemy.hp <= 0) {
                        removeEnemyList.add(enemy);
                        enemy.die(playerSystem);
                    }
                }
                enemyShipList.removeAll(removeEnemyList);
            }
        }
        specialBombs.removeAll(removeList);
    }
}
