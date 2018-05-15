package com.mask.game;

/**
 * For placement in a Prompt object.
 *
 * @author Alexandria
 */
public class PromptWords {

    //data
    private String promptWord;

    private String spriteName;

    //constructors

    /**
     * Constructs an empty PromptWords object, with prompt and sprite name as null.
     */
    public PromptWords() {
        promptWord = null;
        spriteName = null;
    }

    /**
     * Constructs a PromptWords object.
     *
     * @param promptWord the prompt
     * @param spriteName the correct country (sprite)
     */
    public PromptWords(String promptWord, String spriteName) {
        this.promptWord = promptWord;
        this.spriteName = spriteName;
    }

    //methods

    /**
     * Returns a prompt.
     *
     * @return the prompt as a String.
     */
    public String getPromptWord() {
        return promptWord;
    }

    /**
     * Returns the correct sprite name.
     *
     * @return the sprite name as a String
     */
    public String getSpriteName() {
        return spriteName;
    }

    /**
     * Sets or overrides the prompt with a new prompt.
     *
     * @param promptWord the new prompt as a String
     */
    public void setPromptWord(String newPromptWord) {
        promptWord = newPromptWord;
    }

    /**
     * Sets or overrides the sprite name with a new sprite name.
     *
     * @param newSpriteName the new sprite name as a String
     */
    public void setSpriteName(String newSpriteName) {
        spriteName = newSpriteName;
    }
}
