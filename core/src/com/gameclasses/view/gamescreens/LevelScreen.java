package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gameclasses.controller.JsonConfigReader;
import com.gameclasses.utils.GameConstants;
import org.json.simple.parser.ParseException;

public class LevelScreen implements Screen {
    private final MainGame mainGame;
    private final Texture texture;
    private final Stage levelScreenStage;
    private final Skin skin;

    public LevelScreen (MainGame mainGame) {
        this.mainGame = mainGame;
        texture = new Texture("images/firstscreen.jpg");
        levelScreenStage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Gdx.input.setInputProcessor(levelScreenStage);
        LoadingButtons();
    }

    private void LoadingButtons () {
        int sizeUnit = 45;
        final TextButton levelOne = new TextButton("Level-1", skin, "small");
        levelOne.setSize(sizeUnit * 4, sizeUnit);
        levelOne.setPosition((Gdx.graphics.getWidth() - levelOne.getWidth()) / 2, 400);
        levelOne.getLabel().setFontScale(1.2f, 1.2f);
        levelOne.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    GameConstants.config = new JsonConfigReader();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                startGame();
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelOne.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelOne.getLabel().setFontScale(1.2f, 1.2f);
            }
        });

        final TextButton levelTwo = new TextButton("Level-2", skin, "small");
        levelTwo.setSize(sizeUnit * 4, sizeUnit);
        levelTwo.setPosition((Gdx.graphics.getWidth() - levelTwo.getWidth()) / 2, 300);
        levelTwo.getLabel().setFontScale(1.2f, 1.2f);
        levelTwo.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    GameConstants.config = new JsonConfigReader();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                startGame();
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelTwo.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelTwo.getLabel().setFontScale(1.2f, 1.2f);
            }
        });
        levelScreenStage.addActor(levelOne);
        levelScreenStage.addActor(levelTwo);
    }

    private void startGame () {
        this.dispose();
        levelScreenStage.dispose();
        mainGame.setScreen(new GameScreen(mainGame));
    }

    @Override
    public void show () {

    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainGame.batch.begin();
        mainGame.batch.draw(texture, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        mainGame.batch.end();
        levelScreenStage.act();
        levelScreenStage.draw();
    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void pause () {

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
}
