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
	
	private ArrayList<Prompt> plots;
	
	private int size;
	
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
		randomize();
	}
	
	//methods
	
	/**
	 * Adds a plot to the list of plots.
	 * @param newPrompts a plot to be added to the list of plots
	 */
	public void addPlot(Prompt newPrompts) {
		plots.add(newPrompts);
			size++;
	}
	
	
	/**
	 * Returns the number of "undealt" plots.
	 * @return number of "undealt" plots
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns whether there are any "undealt" plots.
	 * @return true is empty, false if not empty
	 */
	public boolean isEmpty() {
		if (plots.size()==0) 
			return true;
		else
			return false;
	}
	
	/**
	 * Shuffles the "deck" of plots.
	 */
	public void randomize() {
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
	public Prompt getAPlot() {
			if (size == 0) {
				return null;
			}
			else {
				size=size-1;
				return plots.get(size);
			}
	}

	public static void init(){
	    Plot plots = new Plot();
	    Prompt prompt = new Prompt();
	    PromptWords promptWords = new PromptWords();

        FileHandle file = Gdx.files.internal("geography/plots.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            Gdx.app.log("CSV line prompts", line);
            String[] tokens = line.split(",");

            prompt.addOverview(tokens[0]);

            for (int i = 1; i < tokens.length; i=i+2) {
                if (!tokens[i].equals("")) {
                    promptWords.setPromptWord(tokens[i]);
                    promptWords.setSpriteName(tokens[i+1]);
                    prompt.addPrompt(promptWords);
                    plots.addPlot(prompt);
                    Gdx.app.log("Prompt:", tokens[i]);
                    Gdx.app.log("PromptSpriteName:", tokens[i+1]);
                }
            }



        }
    }
}

