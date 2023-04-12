package com.gameclasses.view.gamescreens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.model.systems.GameSystem;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.score.PlayerLivesSystem;
import org.json.simple.JSONObject;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


public class GameScreen implements Screen {
    private final Camera cameraForeground;
    private final Viewport viewport;
    private final MainGame game;
    private final GameSystem gameSystem;

    private final Texture texture;
    private final SpriteBatch spriteBatch;
    private BackgroundScreen backgroundScreen;
    private final PlayerLivesSystem playerLivesSystem;

    public GameScreen (MainGame game) {
        //Deliverable 2
        JsonConfigReader config = GameConstants.config;
        JSONObject playerConfigs = config.getPlayerAttribute();
        //Deliverable 1
        this.gameSystem = new GameSystem(this.backgroundScreen);
        spriteBatch = new SpriteBatch();
        this.cameraForeground = new OrthographicCamera();
        ((OrthographicCamera) cameraForeground).setToOrtho(false, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        this.viewport = new FitViewport(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT, cameraForeground);
        texture = new Texture("images/gamescreen.jpg");
        playerLivesSystem = new PlayerLivesSystem(playerConfigs);
        this.backgroundScreen = new BackgroundScreen(playerLivesSystem);
        gameSystem.setLivesSystem(playerLivesSystem);
        this.game = game;
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        this.backgroundScreen.renderBackground();
        spriteBatch.setProjectionMatrix(cameraForeground.combined);
        Gdx.gl.glViewport(10, 10, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        spriteBatch.begin();
        spriteBatch.draw(this.texture, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        gameSystem.render(spriteBatch, deltaTime);
        spriteBatch.end();
        if (gameSystem.canEnd()) {
            this.gameEnd();
        }
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void dispose () {

    }

    private void gameEnd () {
        this.dispose();
        game.setScreen(new GameExitScreen(game, playerLivesSystem.isWin()));
    }
}
