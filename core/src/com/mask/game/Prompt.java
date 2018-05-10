package com.mask.game;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

/**
 * Allows for the manipulation of PromptWords objects.
 * @author Alexandria
 *
 */
public class Prompt {
	
	//data
	public ArrayList<PromptWords> prompts;
	
	private String overview;
	
	private int size;

	int PROMPTi = -1;
	
	//constructor
	/**
	 * Constructs an empty Prompt object.
	 */
	public Prompt() {
		prompts = new ArrayList<PromptWords>();
		overview = null;
	}
	
	/**
	 * Constructs a Prompt object with a given prompt.
	 * @param prompt a prompt to be stored in the Prompt object
	 */
	public Prompt(PromptWords prompt) {
		prompts = new ArrayList<PromptWords>();
		prompts.add(prompt);
		overview = null;
	}
	
	/**
	 * Constructs a Prompt object with a given prompt and an overview.
	 * @param prompt a prompt to be stored in the Prompt object
	 * @param overview an overview for a Prompt object
	 */
	public Prompt(PromptWords prompt, String overview) {
		prompts = new ArrayList<PromptWords>();
		prompts.add(prompt);
		this.overview = overview;
	}
	
	/**
	 * Constructs a Prompt object with several given prompts.
	 * @param someStrings an Array of several prompts to be stores in the Prompt object
	 */
	public Prompt(ArrayList<PromptWords> somePromptWords) {
		prompts = new ArrayList<PromptWords>();
		for(int i=somePromptWords.size()-1; i>=0; i--) {
			PromptWords input = somePromptWords.get(i);
			prompts.add(input);
		}
	}
	
	/**
	 * Constructs a Prompt object with several given prompts and an overview.
	 * @param someStrings an Array of several prompts to be stores in the Prompt object
	 * @param overview an overview for a Prompt object
	 */
	public Prompt(ArrayList<PromptWords> somePromptWords, String overview) {
		prompts = new ArrayList<PromptWords>();
		this.overview = overview;
		for(int i=somePromptWords.size()-1; i>=0; i--) {
			PromptWords input = somePromptWords.get(i);
			prompts.add(input);
		}
	}
	
	//methods
	/**
	 * Adds or overwrites an overview for the list of prompts.
	 * @param overview an overview of a list of prompts.
	 */
	public void addOverview(String overview) {
		this.overview = overview;
	}
	
	/**
	 * Returns the overview of the total plot.
	 * @return the overview in the form of a String
	 */
	public String getOverview() {
		return overview;
	}
	
	/**
	 * Adds a prompt to the list of prompts.
	 * @param newPrompt a prompt to be added to the list of prompts
	 */
	public void addPrompt(PromptWords newPrompt) {
		prompts.add(newPrompt);
			size++;
	}
	
	
	/**
	 * Returns the number of "undealt" prompts.
	 * @return number of "undealt" prompts
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns whether there are any "undealt" prompts.
	 * @return true is empty, false if not empty
	 */
	public boolean isEmpty() {
		if (prompts.size()==0) 
			return true;
		else
			return false;
	}
	
	/**
	 * Shuffles the "deck" of prompts. (Not used.)
	 */
	public void randomize() {
		Random randy = new Random();
		for(int i = prompts.size()-1; i>=0; i--) {
			int a = randy.nextInt(prompts.size());
			PromptWords first = prompts.get(a);
			PromptWords second = prompts.get(i);
			prompts.set(a, second);
			prompts.set(i, first);
		}
		size = prompts.size();
	}
	
	/**
	 * Returns a prompt.
	 * @return a prompt
	 */
	public PromptWords getAPrompt() {
        PROMPTi = (PROMPTi+1)%getSize();
      return prompts.get(PROMPTi);
	}
	
	/**
	 * Displays the contents of the class as a String.
	 */
	public String toString() {
		String str = "Overview: " + overview + "\n";
		int k = 1;
		for (int i = prompts.size()-1; i>=0; i--) {
			str = str + "Prompt " + (k) + ": " +  prompts.get(i).getPromptWord() + "\n";
			k++;
		}
		return str;
	}
	
}
