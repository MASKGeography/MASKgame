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

    public static ArrayList<String> countriesProper;
    public static ArrayList<String> countries;
    public static HashMap<String, Texture> flagSprites;

    private static void initCountries() {
        countriesProper = new ArrayList<String>();

        FileHandle file = Gdx.files.internal("geography/ListOfCountries.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            Gdx.app.log("CSV line", line);
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                if (!tokens[i].equals("")) {
                    countriesProper.add(tokens[i]);
                    Gdx.app.log("Country", tokens[i]);
                }
            }

        }

    }
    private static void initCountryTextures() {
        FileHandle file = Gdx.files.internal("geography/listOfCountriesFormatted.csv");
        String[] lines = file.readString().split("\n");

        countries = new ArrayList<String>();
        flagSprites = new HashMap<String,Texture>();

        for (String line : lines) {
            Gdx.app.log("CSV line", line);
            line = line.replaceAll("\r", "");
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                countries.add(tokens[i]);
                Gdx.app.log("Country", tokens[i]);
                Gdx.app.log("Country name size", "" + tokens[i].length());
                flagSprites.put(tokens[i], new Texture(Gdx.files.internal("geography/flagSprites/real/" + tokens[i] + ".png")));
            }

        }
    }
    public static void init() {
        initCountries();
        initCountryTextures();

        return;

    }

    public enum Textures {
        WORLDMAP("geography/worldMap.png"),
        WORLDMAP2("geography/worldMap2.png");

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
