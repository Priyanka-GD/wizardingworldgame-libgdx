package com.gameclasses.view.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gameclasses.utils.GameConstants;


public class BackgroundScreen  {
    private final Camera cameraBackground;

    private final Viewport viewportBackground;
    private final Texture background;
    private final BitmapFont font0, font1;
    private final SpriteBatch sbatch;
    private final String mode;
    private final Skin skin;
    private final Stage stage;
    public BackgroundScreen() {
        this.cameraBackground = new OrthographicCamera();
        ((OrthographicCamera) cameraBackground).setToOrtho(false, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        this.viewportBackground = new StretchViewport(GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT,cameraBackground);
        this.background = new Texture("images/firstscreen.jpg");
        this.font0 = new BitmapFont();
        this.font1 = new BitmapFont();
        this.font0.setColor(0, 0, 0, 1);
        this.font0.getData().setScale(2f);
        this.font1.setColor(1,1,1,1);
        this.font1.getData().setScale(2f);
        this.mode = "Normal speed";
        this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        this.stage = new Stage(this.viewportBackground);
        Gdx.input.setInputProcessor(stage);
        sbatch = new SpriteBatch();
    }

    public void renderBackground(){
        sbatch.setColor(Color.WHITE);
        font0.setColor(Color.WHITE);
        sbatch.setProjectionMatrix(cameraBackground.combined);
        Gdx.gl.glViewport(0,0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        sbatch.begin();
        sbatch.draw(this.background, 0, 0, GameConstants.EXT_WINDOW_WIDTH, GameConstants.EXT_WINDOW_HEIGHT);
        sbatch.end();
        stage.act();
        stage.draw();
    }


}
