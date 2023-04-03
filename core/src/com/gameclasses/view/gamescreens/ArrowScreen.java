package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gameclasses.utils.GameConstants;

public class ArrowScreen implements Screen {
        private final MainGame game;
        private final SpriteBatch sbatch;
        private final Texture background;
        private final Stage stage;
        private final Skin skin;
        private final BitmapFont font;

        public ArrowScreen (MainGame game) {
            this.game = game;
            background = new Texture("images/firstscreen.jpg");
            stage = new Stage(new ScreenViewport());
            skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
            Gdx.input.setInputProcessor(stage);
            this.font = new BitmapFont();
            this.font.setColor(0, 0, 0, 1);
            this.font.getData().setScale(1f);
            sbatch = new SpriteBatch();
        }
    private void loadButtons() {
        int sizeUnit = 60;
        final Screen self = this;
        final TextButton button3 = new TextButton("Return to the menu", skin, "small");
        button3.setSize(sizeUnit * 4, sizeUnit);
        button3.setPosition((Gdx.graphics.getWidth() - button3.getWidth()) / 2,80);
        button3.getLabel().setFontScale(1.2f, 1.2f);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                backToMenu();
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
        stage.addActor(button3);
    }

    private void backToMenu() {
        this.dispose();
        stage.dispose();
        game.setScreen(new MenuScreen(game));
    }
        private void startGame() {
            this.dispose();
            stage.dispose();
            game.setScreen(new GameScreen(game));
        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {
            font.setColor(Color.BLACK);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.begin();
            Texture image = new Texture("images/firstscreen-overlay.jpg");
            game.batch.draw(image, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
            loadButtons();
            game.batch.end();
            stage.act();
            stage.draw();
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
