package com.mygdx.game.gamescreens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.systems.GameSystem;
import com.mygdx.game.utils.GameConstants;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


public class GameScreen implements Screen {
    private final Camera cameraForeground;
    private final Viewport viewport;
    private MainGame game;
    private int foregroundOffset;
    private GameSystem gameSystem;

    private Texture texture;
    private SpriteBatch spriteBatch;
    private BackgroundScreen backgroundScreen;


    public GameScreen(MainGame game) {
        this.gameSystem = new GameSystem(this.backgroundScreen);
        spriteBatch = new SpriteBatch();
        this.cameraForeground = new OrthographicCamera();
        ((OrthographicCamera) cameraForeground).setToOrtho(false, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        this.viewport = new FitViewport(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT,cameraForeground);
        foregroundOffset = 0;
        texture = new Texture("images/gamescreen.jpg");
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(cameraForeground.combined);
        Gdx.gl.glViewport(10,10, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        spriteBatch.begin();
        spriteBatch.draw(this.texture, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        gameSystem.render(spriteBatch);
        spriteBatch.end();
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }
}
