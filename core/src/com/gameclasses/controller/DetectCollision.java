package com.gameclasses.controller;

import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyLaser;
import com.gameclasses.model.gameobjects.Player;
import com.gameclasses.model.gameobjects.PlayerProjectile;
import com.gameclasses.model.systems.PlayerSystem;

import java.util.ArrayList;
import java.util.List;

public class DetectCollision {
    // when enemy laser hitbox collides with players hitbox
    public void playerCollisionWithEnemy (List<EnemyLaser> enemyLaserList, Player player, PlayerSystem playerSystem) {
        List<EnemyLaser> removeList = new ArrayList<>();
        for (EnemyLaser laser : enemyLaserList) {
            if (player.overlaps(laser.hitBox)) {
                removeList.add(laser);
                playerSystem.updateLives(-1);
            }
        }
        enemyLaserList.removeAll(removeList);
    }

    public void collision (List<PlayerProjectile> playerBulletList, List<Enemy> enemyShipList, List<EnemyLaser> enemyLaserList, PlayerSystem playerSystem) {
        List<PlayerProjectile> playerRemoveBulletList = new ArrayList<>();
        List<Enemy> removeEnemyList = new ArrayList<>();
        for (PlayerProjectile bullet : playerBulletList) {
            for (Enemy enemy : enemyShipList) {
                if (enemy.overlaps(bullet.playerBullethitBox)) {
                    enemy.hp -= 1;
                    playerRemoveBulletList.add(bullet);
                    enemyLaserList.removeAll(enemyLaserList);
                    if (enemy.hp <= 0) {
                        removeEnemyList.add(enemy);
                        enemy.die(playerSystem);
                    }
                }
            }
        }
        playerBulletList.removeAll(playerRemoveBulletList);
        enemyShipList.removeAll(removeEnemyList);
    }
}
