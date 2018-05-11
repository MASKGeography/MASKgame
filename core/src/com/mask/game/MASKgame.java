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

    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);
        Plot.init();
        Assets.init();
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
        updatePlotsNStuff();
    }

    public void restart() {
        completed = false;
        almostCompleted = false;
        score = 0;
        time = 0;

        //kindof a hack, but whatever
        while (!completed) updatePlotsNStuff();

        completed = false;
        almostCompleted = false;
        score = 0;
        time = 0;
    }

    public void updatePlotsNStuff() {
        if (almostCompleted) completed = true;
        thePlots = Plot.getThePlots();
        ploty = thePlots.get(plotNumber);
        prompty = ploty.getAPrompt();
        thePrompt = prompty.getPromptWord();
        theSprite = prompty.getSpriteName();
        overview = ploty.getOverview();
        if (thePrompt.equals(thePlots.get(plotNumber).getLastPrompt().getPromptWord())){
            plotNumber = (plotNumber + 1) % thePlots.size();
            if (plotNumber == 0) almostCompleted = true;
        }
        return;
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
        int tmp = (int)time;
        int seconds = tmp % 60;
        int minutes = (tmp - seconds) / 60;
        int milli = (int)((time - tmp) * 1000);
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