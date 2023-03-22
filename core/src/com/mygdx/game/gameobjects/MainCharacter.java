
package com.mygdx.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.utils.GameConstants;


public class MainCharacter implements GameControllable {
    private boolean isSlow;
    public float moveSpeed;
    private float slowMultiplier= 0.1f;
    private Texture characterMode;
    public Rectangle characterBox;

    public MainCharacter(Object object) {
        float width = 100;
        float height = 100;
        float x = (GameConstants.WINDOW_WIDTH - width) / 2;
        float y = 50;
     this.moveSpeed = 10f;
     this.characterBox = new Rectangle(x, y, width, height);
     this.characterMode = new Texture("images/character.jpg");
    }
    public void draw(Batch batch) {
        batch.draw(characterMode,characterBox.x, characterBox.y, characterBox.width, characterBox.height );
    }
    private float getSpeed() {
     float speed = this.moveSpeed;
     if (isSlow) {
      speed *= this.slowMultiplier;
     }
     return speed;
    }
    @Override
    public void moveUp() {
     characterBox.y = Math.min(characterBox.y + this.getSpeed(), GameConstants.WINDOW_HEIGHT - characterBox.height);
    }
    @Override
    public void moveDown() {
        characterBox.y = Math.max(characterBox.y-this.getSpeed(), 0);
    }

    @Override
    public void moveLeft() {
        characterBox.x = Math.max(characterBox.x - this.getSpeed(), 0);
    }

    @Override
    public void moveRight() {
        characterBox.x = Math.min(characterBox.x + this.getSpeed() , GameConstants.WINDOW_WIDTH - characterBox.width);
    }
    @Override
    public void moveW () {
       characterBox.y+=  this.getSpeed();
    }
    @Override
    public void moveS () {
        characterBox.y-=  this.getSpeed();
    }
    @Override
    public void moveA () {
        characterBox.x-=  this.getSpeed();
    }
    @Override
    public void moveD () {
        characterBox.x+= this.getSpeed();
    }
    @Override
    public void slowMode(boolean isSlow) {
        this.isSlow = isSlow;
    }
}

