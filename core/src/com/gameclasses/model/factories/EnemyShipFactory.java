package com.gameclasses.model.factories;

import com.gameclasses.controller.laserstrategy.LaserBindings;
import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyCharacterShip;
import org.json.simple.JSONObject;

public class EnemyShipFactory {
    public Enemy produce (JSONObject object) {
        LaserBindings laserBindings = new LaserBindings();
        laserBindings.addLaser();
        return new EnemyCharacterShip.BuilderEnemy()
                .hp(((Long) object.get("hp")).intValue())
                .texture((String) object.get("texture"))
                .hitBox(((Long) object.get("x")).intValue(),
                        ((Long) object.get("y")).intValue(),
                        ((Long) object.get("width")).intValue(),
                        ((Long) object.get("height")).intValue())
                .movement((String) object.get("enemyMovement"))
                .isFinalBoss((boolean) object.get("isFinalBoss"))
                .score(((Long) object.get("reward")).intValue())
                .laserBindings(laserBindings)
                .build();
    }
}
