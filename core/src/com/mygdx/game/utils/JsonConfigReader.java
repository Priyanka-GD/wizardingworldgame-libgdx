package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonConfigReader {
    private JSONObject obj;

    public JsonConfigReader (int level) {
        try {
            if (level == 1) {
                obj = (JSONObject) new JSONParser().parse(Gdx.files.internal("config-jsons/game-level.json").readString());
            }
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getPlayerAttribute () {
        return (JSONObject) obj.get("character");
    }

    public JSONArray getEnemies () {
        return (JSONArray) obj.get("enemy");
    }
}
