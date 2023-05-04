package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gameclasses.controller.observer.PlayerUpdateRenderer;
import com.gameclasses.model.systems.GameSystem;
import com.gameclasses.model.systems.PlayerSystem;
import com.gameclasses.utils.GameConstants;


public class BackgroundScreen extends PlayerUpdateRenderer {
    private final Camera cameraBackground;

    private final Viewport viewportBackground;
    private final Texture background;
    private final BitmapFont fontHP;
    private final SpriteBatch sbatch;
    private final Skin skin;
    private final Stage stage;
    private final Texture lives;
    private final Texture bomb;
    private int heartCount, score, bombCount;
    private final TextButton cheatingButton;
    private final Texture infinity;
    private boolean isCheating;
    private GameSystem gameSystem;

    public BackgroundScreen (PlayerSystem subject) {
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
        this.subject = subject;
        this.subject.attachBackScreen(this);
        this.heartCount = this.subject.getLives();
        this.score = this.subject.getScore();
        this.bombCount = this.subject.getBombs();
        this.bomb = new Texture("images/bomb.png");
        this.cheatingButton = new TextButton("Cheating", skin, "small");
        this.cheatingButton.setSize(45 * 4, 45);
        this.isCheating = false;
        this.infinity = new Texture("images/infinity.png");
        this.loadButtons();
        sbatch = new SpriteBatch();
    }

    public void attachCheatingObserver (GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }

    private void loadButtons () {
        int sizeUnit = 50;
        cheatingButton.setSize(sizeUnit * 5, sizeUnit);
        cheatingButton.setPosition(GameConstants.WINDOW_WIDTH + 35, 100);
        cheatingButton.getLabel().setFontScale(1.2f, 1.2f);
        cheatingButton.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!isCheating) {
                    isCheating = true;
                    gameSystem.updateCheating();
                } else {
                    isCheating = false;
                    gameSystem.updateCheating();
                }
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!isCheating)
                    cheatingButton.setText("Stop Cheating");
                else
                    cheatingButton.setText("Start Cheating");
                return true;
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                cheatingButton.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                cheatingButton.getLabel().setFontScale(1.2f, 1.2f);
            }
        });

        this.stage.addActor(cheatingButton);
    }

    public void renderBackground () {
        sbatch.setProjectionMatrix(cameraBackground.combined);
        Gdx.gl.glViewport(0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        sbatch.begin();
        sbatch.draw(this.background, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        //observer pattern -- observing player and sends an update -- player as a subject-- reduce coupling
        fontHP.draw(sbatch, "Score: " + String.format("%08d", this.score), GameConstants.WINDOW_WIDTH + 15, GameConstants.WINDOW_HEIGHT - 60);
        fontHP.draw(sbatch, "Remaining Lives: ", GameConstants.WINDOW_WIDTH + 16, GameConstants.WINDOW_HEIGHT - 180);
        this.displayLives();
        this.showBombs();
        sbatch.end();
        stage.act();
        stage.draw();
    }

    public void displayLives () {
        if (!isCheating)
            for (int i = 0; i < this.heartCount; i++)
                sbatch.draw(lives, GameConstants.WINDOW_WIDTH + 10 + ((i % 5) * 40),
                        GameConstants.WINDOW_HEIGHT - (240 + 50 * (i / 5)), 35, 30);
        else
            sbatch.draw(infinity, GameConstants.WINDOW_WIDTH + 100, GameConstants.WINDOW_HEIGHT - 250, 50, 30);
    }

    @Override
    public void updateLives () {
        this.heartCount = subject.getLives();
    }

    @Override
    public void updateScore () {
        this.score = subject.getScore();
    }

    @Override
    public void updateBombs () {
        this.bombCount = subject.getBombs();
    }

    private void showBombs () {
        for (int i = 0; i < this.bombCount; i++)
            sbatch.draw(bomb, GameConstants.WINDOW_WIDTH + 15 + ((i % 6) * 50), GameConstants.WINDOW_HEIGHT - (140 + 50 * (i / 6)), 40, 40);
    }

    public boolean getIsCheating () {
        return this.isCheating;
    }
}
