package com.mygdx.game.factories;

import com.mygdx.game.gameobjects.Enemy;
import org.json.simple.JSONObject;

public interface EnemyFactory {

    Enemy produce (JSONObject object);
}
