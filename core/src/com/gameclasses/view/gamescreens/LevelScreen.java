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
    private final MainGame game;
    //private MenuScreen menu;
    private final Texture background;
    private final Stage stage;
    private final Skin skin;

    public LevelScreen (MainGame game) {
        this.game = game;
        //this.menu = menu;
        background = new Texture("images/firstscreen.jpg");
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        loadButtons();
    }

    private void loadButtons () {
        int sizeUnit = 60;
        final TextButton optionButton1 = new TextButton("Level-1", skin, "small");
        optionButton1.setSize(sizeUnit * 4, sizeUnit);
        optionButton1.setPosition((Gdx.graphics.getWidth() - optionButton1.getWidth()) / 2, 400);
        optionButton1.getLabel().setFontScale(1.2f, 1.2f);
        optionButton1.addListener(new InputListener() {
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
                optionButton1.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                optionButton1.getLabel().setFontScale(1.2f, 1.2f);
            }
        });

        final TextButton optionButton2 = new TextButton("Level-2", skin, "small");
        optionButton2.setSize(sizeUnit * 4, sizeUnit);
        optionButton2.setPosition((Gdx.graphics.getWidth() - optionButton2.getWidth()) / 2, 300);
        optionButton2.getLabel().setFontScale(1.2f, 1.2f);
        optionButton2.addListener(new InputListener() {
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
                optionButton2.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                optionButton2.getLabel().setFontScale(1.2f, 1.2f);
            }
        });
        stage.addActor(optionButton1);
        stage.addActor(optionButton2);
    }

    private void startGame () {
        this.dispose();
        stage.dispose();
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void show () {

    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        game.batch.end();
        stage.act();
        stage.draw();
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
