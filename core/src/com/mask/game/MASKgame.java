package com.mask.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Neel on 3/23/2018.
 */

public class MASKgame extends Game {

    public SpriteBatch batch;

    PromptWords prompty;
    private String thePrompt;
    private String theSprite;
    Prompt ploty;


    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);

        Plot.init();
        Assets.init();
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));

        updatePlotsNStuff();

    }

    public void updatePlotsNStuff() {
        ploty = Plot.getAPlot();
        prompty = ploty.getAPrompt();
        thePrompt = prompty.getPromptWord();
        theSprite = prompty.getSpriteName();
    }

    public String getThePrompt() {
        return thePrompt;
    }

    public String getTheSprite() {
        return theSprite;
    }



    public void render() {
        super.render();
    }

    public void dispose() {

    }

}
