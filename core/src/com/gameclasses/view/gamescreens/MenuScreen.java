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

public class MenuScreen implements Screen {
    private final MainGame game;
    private final Texture background;
    private final Stage stage;
    public static int keyBind = 0;
    private final Skin skin;

    public MenuScreen (MainGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        background = new Texture("images/firstscreen.jpg");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        loadButtons();
    }

    public void loadButtons ()
    {
        int sizeUnit = 60;
        //About the game
        final TextButton button1 = new TextButton("About Game", skin, "small");
        button1.setSize(sizeUnit * 3, sizeUnit);
        button1.setPosition((Gdx.graphics.getWidth() - button1.getWidth()) / 2,450);
        button1.getLabel().setFontScale(1.1f, 1.1f);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                aboutGame();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button1.getLabel().setFontScale(1.5f, 1.5f);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button1.getLabel().setFontScale(1.2f, 1.2f);
            }
        });

        //Start game
        final TextButton button2 = new TextButton("Start", skin, "small");
        button2.setSize(sizeUnit * 3, sizeUnit);
        button2.setPosition((Gdx.graphics.getWidth() - button1.getWidth()) / 2,350);
        button2.getLabel().setFontScale(1.1f, 1.1f);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                startGame();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button1.getLabel().setFontScale(1.5f, 1.5f);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button1.getLabel().setFontScale(1.2f, 1.2f);
            }
        });

        // exit button
        final TextButton button3 = new TextButton("Exit Game", skin, "small");
        button3.setSize(sizeUnit * 3, sizeUnit);
        button3.setPosition((Gdx.graphics.getWidth() - button3.getWidth()) / 2,250);
        button3.getLabel().setFontScale(1.1f, 1.1f);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button3.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button3.getLabel().setFontScale(1.2f, 1.2f);
            }
        });
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
    }
    private void aboutGame() {
        this.dispose();
        stage.dispose();
        game.setScreen(new ArrowScreen(game));
    }
    private void startGame() {
        try {
            GameConstants.config = new JsonConfigReader();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.dispose();
        stage.dispose();
        game.setScreen(new GameScreen(game));
    }
    @Override
    public void show () {

    }

    @Override
    public void render(float delta) {
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
