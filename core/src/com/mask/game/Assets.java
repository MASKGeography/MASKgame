package com.mask.game;

/**
 * Created by Neel on 4/2/2018.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.HashMap;

public class Assets {

    public static ArrayList<String> countries;
    public static ArrayList<String> countries2;
    public static HashMap<String, Texture> flagSprites;

    private static void initCountries() {
        countries = new ArrayList<String>();

        FileHandle file = Gdx.files.internal("geography/ListOfCountries.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            Gdx.app.log("CSV line", line);
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                if (!tokens[i].equals("")) {
                    if (tokens[i].contains("\n")) {
                        tokens[i] = tokens[i].substring(0, tokens[i].length() - 1);
                    }
                    countries.add(tokens[i]);
                    Gdx.app.log("Country", tokens[i]);
                }
            }

        }
    }
    private static void initCountryTextures() {
        FileHandle file = Gdx.files.internal("geography/listOfCountriesFormatted.csv");
        String[] lines = file.readString().split("\n");

        countries2 = new ArrayList<String>();
        flagSprites = new HashMap<String,Texture>();

        for (String line : lines) {
            Gdx.app.log("CSV line", line);
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                if (!tokens[i].equals("a\r")) {

                    if (tokens[i].contains("\n")) {
                        tokens[i] = tokens[i].substring(0, tokens[i].length() - 1);
                    }
                    countries2.add(tokens[i]);
                    Gdx.app.log("Country", tokens[i]);
                    Gdx.app.log("Country name size", "" + tokens[i].length());
                    Gdx.app.log("Last Characater", "" + (int)tokens[i].charAt(tokens[i].length() - 1));
                    flagSprites.put(tokens[i], new Texture(Gdx.files.internal("geography/flagSprites/real/" + tokens[i] + ".png")));
                }
            }

        }
    }
    public static void init() {
        initCountries();
        initCountryTextures();

        Gdx.app.log("Number of countries", Integer.toString(countries.size()));

        int count = 0;
        for (String country : countries) {
            if (country.equals("")) ++count;
        }
        Gdx.app.log("Number empty", Integer.toString(count));

        return;

    }

    public enum Textures {
        WORLDMAP("geography/worldMap.png");

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
