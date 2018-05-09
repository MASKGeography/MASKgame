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
    private String overview;
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
        overview = ploty.getOverview();
    }

    public String getThePrompt() {
        return thePrompt;
    }

    public String getTheSprite() {
        return theSprite;
    }

    public String getOverview() {
        return overview;
    }

    public int score = 0;
    public float time = 0;


    public void render() {
        super.render();
    }

    public void dispose() {

    }

    public String scoreString() {
        int seconds = (int)score;
        int minutes = seconds % 60;

        String ans = "Score: " + score + " Time: " + minutes + ":" + seconds;
        return ans;
    }




}
