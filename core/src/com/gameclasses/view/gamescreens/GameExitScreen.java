package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gameclasses.utils.GameConstants;

public class GameExitScreen implements Screen {
    private final MainGame game;
    private final Texture background;
    private final Stage stage;
    private final Skin skin;
    private final BitmapFont font;
    private Music music;
    boolean isWin;

    public GameExitScreen (MainGame game, boolean isWin) {
        this.isWin = isWin;
        this.game = game;
        if (isWin) {
            background = new Texture("images/game-won.png");
            music = Gdx.audio.newMusic(Gdx.files.internal("music/won.mp3"));
            music.setLooping(true);
            music.play();
        } else {
            background = new Texture("images/game-over.png");
        }
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        this.font = new BitmapFont();
        this.font.setColor(0, 0, 0, 1);
        this.font.getData().setScale(2f);
        loadButtons();
    }

    private void loadButtons () {
        int sizeUnit = 60;
        final Screen self = this;

        final TextButton button = new TextButton("Return to menu", skin, "small");
        button.setSize(sizeUnit * 4, sizeUnit);
        button.setPosition((Gdx.graphics.getWidth() - button.getWidth()) / 2, 150);
        button.getLabel().setFontScale(1.2f, 1.2f);
        button.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                backToMenu();
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button.getLabel().setFontScale(1.5f, 1.5f);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button.getLabel().setFontScale(1.2f, 1.2f);
            }
        });
        stage.addActor(button);
    }


    private void backToMenu () {
        this.dispose();
        stage.dispose();
        game.setScreen(new MenuScreen(game));
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
        if (this.isWin) {
            music.stop();
            music.dispose();
        }
    }
}

