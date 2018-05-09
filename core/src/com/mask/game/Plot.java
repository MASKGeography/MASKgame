package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Allows for the manipulation, randomization, and storage of Prompt objects.
 * @author Alexandria
 *
 */
public class Plot {
	
	//data

    public static ArrayList<Prompt> plots;
	
	private static int size;

	private static int i = 0;
	
	//constructor
	public Plot() {
		plots = new ArrayList<Prompt>();
	}
	
	public Plot(Prompt aPrompt) {
		plots = new ArrayList<Prompt>();
		plots.add(aPrompt);
	}
	
	public Plot(ArrayList<Prompt> promptList) {
		plots = new ArrayList<Prompt>();
		for(int i=promptList.size()-1; i>=0; i--) {
			Prompt input = promptList.get(i);
			plots.add(input);
		}
	}
	
	//methods
	
	/**
	 * Adds a plot to the list of plots.
	 * @param newPrompts a plot to be added to the list of plots
	 */
	public static void addPlot(Prompt newPrompts) {
		plots.add(newPrompts);
			size++;
	}
	
	
	/**
	 * Returns the number of "undealt" plots.
	 * @return number of "undealt" plots
	 */
	public static int size() {
        return size;
	}
	
	/**
	 * Returns whether there are any "undealt" plots.
	 * @return true is empty, false if not empty
	 */
	public static boolean isEmpty() {
		if (plots.size()==0) 
			return true;
		else
			return false;
	}
	
	/**
	 * Shuffles the "deck" of plots. (Might not use.)
	 */
	public static void randomize() {
		Random randy = new Random();
		for(int i = plots.size()-1; i>=0; i--) {
			int a = randy.nextInt(plots.size());
			Prompt first = plots.get(a);
			Prompt second = plots.get(i);
			plots.set(a, second);
			plots.set(i, first);
		}
        size = plots.size();
	}
	
	/**
	 * Returns a plot.
	 * @return a plot
	 */
	public static Prompt getAPlot() {
      return plots.get(i);
    }

	public static Plot init(){
        Plot plots = new Plot();

        FileHandle file = Gdx.files.internal("geography/plots.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            Gdx.app.log("QQQ CSV line prompts", line);
            Prompt prompt = new Prompt();
            String[] tokens = line.split(",");

            prompt.addOverview(tokens[0]);
            Gdx.app.log("QQQ Overview:", tokens[0]);

            for (int i = 1; i < tokens.length - 1; i=i+2) {
                if (!tokens[i].equals("")) {
                    PromptWords promptWords = new PromptWords();
                    promptWords.setPromptWord(tokens[i]);
                    promptWords.setSpriteName(tokens[i+1]);
                    prompt.addPrompt(promptWords);
                    Gdx.app.log("QQQ Prompt:", tokens[i]);
                    Gdx.app.log("QQQ PromptSpriteName:", tokens[i+1]);
                }
            }
            plots.addPlot(prompt);
            Gdx.app.log("QQQ Num Prompts", Integer.toString(plots.size));

        }

        return plots;
    }
}

