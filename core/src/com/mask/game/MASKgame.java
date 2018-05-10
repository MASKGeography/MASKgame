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
    int promptNumber = 0;
    private ArrayList<Prompt> thePlots;
    public static boolean switchPlot;



    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);

        Plot.init();
        Assets.init();
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));

        updatePlotsNStuff();

    }

    //ALEX'S NOTES
    //app is skipping very first prompt because somehow gets called right when app opens, then again after play is clicked
    //app is skipping last prompt of each plotline because that's how i'm changing the plotline
    //tried making it skip when the app looped back to the first prompt, but couldn't distinguish whether it was the first
    //  or second time the first prompt was being called
    //app is crashing after very last prompt, probably because there is no where to go after but we will add an end game screen
    //  and allow game to reset, redirect to main menu, so when play is pressed game restarts
    //one way to fix skipping problem would be to add fake prompts in all the places where the app is skipping them
    //  that would be dangerous though because it's like cheat coding
    //haven't been able to test boolean switchPlot yet but could solve problem, need a bit of help tho bc idk how to transfer
    //  data between classes without a getter method? do u even need one?
    //pls no touch this class until we are together tomorrow because it is sensitive and likes to crash the whole app :(

    public void updatePlotsNStuff() {
        thePlots = Plot.getThePlots();
        ploty = thePlots.get(promptNumber);
        prompty = ploty.getAPrompt();
        thePrompt = prompty.getPromptWord();

        Gdx.app.log("WhyNoWork", "" + thePrompt);

        theSprite = prompty.getSpriteName();
        overview = ploty.getOverview();
        if (thePrompt.equals(thePlots.get(promptNumber).getLastPrompt().getPromptWord())){
         //  if (switchPlot==true) {
               promptNumber++;
          // }
        }
        if (thePrompt.equals(thePlots.get(thePlots.size()-1).getLastPrompt().getPromptWord())){
            if (switchPlot==true) {
                //game wont end rn because boolean switchPlot doesn't actually work!
                //end game
            }
        }
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
