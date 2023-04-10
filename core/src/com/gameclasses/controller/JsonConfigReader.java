package com.gameclasses.controller;

import com.badlogic.gdx.Gdx;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonConfigReader {
    private final JSONObject obj;

    public JsonConfigReader () {
        try {
            obj = (JSONObject) new JSONParser().parse(Gdx.files.internal("config-jsons/game-level.json").readString());
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public JSONObject getPlayerAttribute () {
        return (JSONObject) obj.get("character");
    }

    public JSONArray getEnemies () {
        return (JSONArray) obj.get("enemy");
    }
}
