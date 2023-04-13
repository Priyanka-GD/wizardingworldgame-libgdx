package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gameclasses.utils.GameConstants;
import com.gameclasses.view.lives.PlayerLivesSystem;
import com.gameclasses.view.lives.PlayerUpdateRenderer;


public class BackgroundScreen extends PlayerUpdateRenderer {
    private final Camera cameraBackground;

    private final Viewport viewportBackground;
    private final Texture background;
    private final BitmapFont fontHP;
    private final SpriteBatch sbatch;
    private final Skin skin;
    private final Stage stage;
    private final Texture lives;
    private int heartCount;

    public BackgroundScreen (PlayerLivesSystem subject) {
        this.cameraBackground = new OrthographicCamera();
        ((OrthographicCamera) cameraBackground).setToOrtho(false, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        this.viewportBackground = new StretchViewport(GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT, cameraBackground);
        this.background = new Texture("images/firstscreen.jpg");
        this.lives = new Texture("images/live.png");
        this.fontHP = new BitmapFont();
        this.fontHP.setColor(0, 0, 0, 1);
        this.fontHP.getData().setScale(2f);
        this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        this.stage = new Stage(this.viewportBackground);
        Gdx.input.setInputProcessor(stage);
        sbatch = new SpriteBatch();
        this.subject = subject;
        this.subject.attachBackScreen(this);
        this.heartCount = this.subject.getLives();
    }

    public void renderBackground () {
        sbatch.setProjectionMatrix(cameraBackground.combined);
        Gdx.gl.glViewport(0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        sbatch.begin();
        sbatch.draw(this.background, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        fontHP.draw(sbatch, "Remaining Lives: ", GameConstants.WINDOW_WIDTH + 12, GameConstants.WINDOW_HEIGHT - 110);
        this.displayLives();
        sbatch.end();
        stage.act();
        stage.draw();
    }
    public void displayLives () {
        for (int i = 0; i < this.heartCount; i++)
            sbatch.draw(lives, GameConstants.WINDOW_WIDTH + 15 + ((i % 6) * 50),
                    GameConstants.WINDOW_HEIGHT - (240 + 50 * (i / 6)), 40, 40);
    }
    @Override
    public void updateLives () {
        this.heartCount = subject.getLives();
    }
}
