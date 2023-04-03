package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
    public SpriteBatch batch;
        @Override
        public void create () {
            batch = new SpriteBatch();
            this.setScreen(new MenuScreen(this));
        }
    }
