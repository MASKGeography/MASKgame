package com.mask.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

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
    int plotNumber = 0;
    private ArrayList<Prompt> thePlots;
    public static boolean switchPlot;
    boolean completed = false;
    boolean almostCompleted = false;

    public Assets assets = new Assets();


    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);
        Plot.init();
        assets.init();
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
        updatePlotsNStuff();
    }

    /**
     * Restarts the app.
     */
    public void restart() {
        completed = false;
        almostCompleted = false;
        score = 0;
        time = 0;

        while (!completed) updatePlotsNStuff();

        completed = false;
        almostCompleted = false;
        score = 0;
        time = 0;
    }

    /**
     * Updates plots and prompts when needed.
     */
    public void updatePlotsNStuff() {
        if (almostCompleted) completed = true;
        thePlots = Plot.getThePlots();
        ploty = thePlots.get(plotNumber);
        prompty = ploty.getAPrompt();
        thePrompt = prompty.getPromptWord();
        theSprite = prompty.getSpriteName();
        overview = ploty.getOverview();
        if (thePrompt.equals(thePlots.get(plotNumber).getLastPrompt().getPromptWord())) {
            plotNumber = (plotNumber + 1) % thePlots.size();
            if (plotNumber == 0) almostCompleted = true;
        }
        return;
    }


    /**
     * Gets the updated Prompt as a String.
     *
     * @return the updated Prompt
     */
    public String getThePrompt() {
        return thePrompt;
    }

    /**
     * Gets the updated Sprite as a String.
     *
     * @return the updated Sprite
     */
    public String getTheSprite() {
        return theSprite;
    }

    /**
     * Gets the overview as a String.
     *
     * @return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * The user's score.
     */
    public int score = 0;

    /**
     * The user's time.
     */
    public float time = 0;

    public void render() {
        super.render();
    }

    public void dispose() {
    }

    /**
     * Returns the user's time as a String.
     *
     * @return the user's time
     */
    public String scoreString() {
        int tmp = (int) time;
        int seconds = tmp % 60;
        int minutes = (tmp - seconds) / 60;
        int milli = (int) ((time - tmp) * 1000);
        String strSeconds = "" + seconds;
        if (strSeconds.length() == 1) {
            strSeconds = "0" + strSeconds;
        }
        String strMilli = "" + milli;
        while (strMilli.length() != 3) strMilli = "0" + strMilli;
        String ans = "Score: " + score + " Time: " + minutes + ":" + strSeconds + ":" + strMilli;
        return ans;
    }
}