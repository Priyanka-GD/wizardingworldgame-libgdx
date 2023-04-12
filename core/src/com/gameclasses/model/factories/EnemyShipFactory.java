package com.gameclasses.model.factories;

import com.gameclasses.controller.laserstrategy.LaserBindings;
import com.gameclasses.model.gameobjects.Enemy;
import com.gameclasses.model.gameobjects.EnemyCharacterShip;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EnemyShipFactory implements EnemyFactory {
    @Override
    public Enemy produce (JSONObject object) {
        LaserBindings laserBindings = new LaserBindings();
        JSONArray lasers = (JSONArray) object.get("laser");
        for (Object obj : lasers) {
            JSONObject laser = (JSONObject) obj;
            laserBindings.addLaser(
                    ((Long) laser.get("effectiveFrom")).intValue(),
                    (String) laser.get("strategy"),
                    (String) laser.get("movement"),
                    (String) laser.get("texture")
            );
        }
        return new EnemyCharacterShip.BuilderEnemy()
                .hp(((Long) object.get("hp")).intValue())
                .score(((Long) object.get("reward")).intValue())
                .texture((String) object.get("texture"))
                .hitbox(((Long) object.get("x")).intValue(),
                        ((Long) object.get("y")).intValue(),
                        ((Long) object.get("width")).intValue(),
                        ((Long) object.get("height")).intValue())
                .movement((String) object.get("movement"))
                .laserBindings(laserBindings)
                .isFinalBoss((boolean) object.get("isFinalBoss"))
                .build();
    }
}
