package com.mygdx.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameobjects.MainCharacter;
import com.mygdx.game.gamescreens.BackgroundScreen;
import com.mygdx.game.utils.PlayerCommand;


public class GameSystem{
    MainCharacter mainCharacter;
    private PlayerCommand command;
    public GameSystem(BackgroundScreen screen) {
        init();
    }

    public void init() {
        mainCharacter = new MainCharacter(new Object());
        command = new PlayerCommand();
        command.add(mainCharacter);

    }
    public void render(SpriteBatch sbatch) {
        renderCharacter(sbatch);
        makeCharacterMove();
    }
    private void renderCharacter(SpriteBatch sbatch) {
        mainCharacter.draw(sbatch);
    }
    private void makeCharacterMove()
    {
        command.run();
    }
}
