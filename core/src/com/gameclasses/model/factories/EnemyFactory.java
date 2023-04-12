package com.gameclasses.model.factories;

import com.gameclasses.model.gameobjects.Enemy;
import org.json.simple.JSONObject;

public interface EnemyFactory {
    Enemy produce (JSONObject object);
}
