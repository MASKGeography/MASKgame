package com.mask.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Alexandria on 4/27/2018.
 */

public class CorrectSprite {

    //data
    String spriteNamePNG;
    Sprite correctSprite;

    //constructor
    public CorrectSprite(){
        spriteNamePNG = null;
        correctSprite = null;
    }

    public CorrectSprite(String spriteName, Sprite correctSprite){
        spriteNamePNG = spriteName;
        this.correctSprite = correctSprite;
    }

    //methods
    public String getSpriteName(){
        return spriteNamePNG;
    }

    public Sprite getCorrectSprite(){
        return correctSprite;
    }


}
