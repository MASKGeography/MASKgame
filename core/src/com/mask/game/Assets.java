package com.mask.game;

/**
 * Created by Neel on 4/2/2018.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;

public class Assets {

    public static ArrayList<String> countries;

    public static void init() {
        countries = new ArrayList<String>();

        FileHandle file = Gdx.files.internal("geography/ListOfCountries.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            Gdx.app.log("CSV line", line);
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                if (!tokens[i].equals("")) {
                    countries.add(tokens[i]);
                    Gdx.app.log("Country", tokens[i]);
                }
            }

        }

        Gdx.app.log("Number of countries", Integer.toString(countries.size()));

        return;

    }

    public enum Textures {
        AD("geography/flagSprites/ad.png");

        private final Texture texture;
        Textures(String image) {
            texture = new Texture(Gdx.files.internal(image));
        }

        public Texture get() {
            return texture;
        }
    };

    public enum Sounds {

    };

    public enum Fonts {
        DEFAULT();

        private BitmapFont font;
        Fonts() {
            font = new BitmapFont();
        }

        public BitmapFont get() {
            return font;
        }

    };



}
