package com.mask.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Neel on 3/23/2018.
 */

public class MASKgame extends Game {

    public SpriteBatch batch;

    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
    }
    public void render() {
        super.render();
    }

    public void dispose() {

    }

}
