package com.mask.game;

/**
 * Created by Neel on 4/2/2018.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

public class Assets {

    public static ArrayList<String> countriesProper;
    public static ArrayList<String> countries;
    public static HashMap<String, Texture> flagSprites;

    public static HashMap<String, Double> countries2XPos;
    public static HashMap<String, Double> countries2YPos;

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
    private static void initCountryPos() {
        countries2XPos = new HashMap<String, Double>();
        countries2YPos = new HashMap<String, Double>();

        FileHandle file = Gdx.files.internal("geography/coordinatePercentOfCountries.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            String[] tokens = line.split(",");
            String name = tokens[0];
            Double x = Double.parseDouble(tokens[1]);
            Double y = Double.parseDouble(tokens[2]);

            Gdx.app.log("CountryPos", name + " " + x + " " + y + " " + name.length());

            countries2XPos.put(name, x);
            countries2YPos.put(name, y);

        }


    }
    public static void init() {
        countriesProper = new ArrayList<String>();
        //initCountries();
        initCountryTextures();
        initCountryPos();

        return;

    }

    public enum Textures {
        WORLDMAP("geography/worldMap.png"),
        WORLDMAP2("geography/worldMap2.png"),
        PLANE("geography/plane.png"),
        FISHERMAN("geography/fisherman.png");

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
