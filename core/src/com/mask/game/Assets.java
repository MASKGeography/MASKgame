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

    public Assets() {

    }

    Texture WORLDMAP;
    Texture WORLDMAP2;
    Texture PLANE;
    Texture FISHERMAN;
    Texture PERSON;

    BitmapFont DEFAULT;

    public ArrayList<String> countriesProper;
    public ArrayList<String> countries;
    public HashMap<String, Texture> flagSprites;

    public HashMap<String, Double> countries2XPos;
    public HashMap<String, Double> countries2YPos;

    private void initCountries() {
        countriesProper = new ArrayList<String>();

        FileHandle file = Gdx.files.internal("geography/grr.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                if (!tokens[i].equals("")) {
                    countriesProper.add(tokens[i]);
                }
            }

        }

    }

    private void initCountryTextures() {
        FileHandle file = Gdx.files.internal("geography/listOfCountriesFormatted.csv");
        String[] lines = file.readString().split("\n");

        countries = new ArrayList<String>();
        flagSprites = new HashMap<String, Texture>();


        for (String line : lines) {
            line = line.replaceAll("\r", "");
            String[] tokens = line.split(",");

            for (int i = 1; i < tokens.length; ++i) {
                countries.add(tokens[i]);
                flagSprites.put(tokens[i], new Texture(Gdx.files.internal("geography/flagSprites/real/" + tokens[i] + ".png")));
            }

        }
    }

    private void initCountryPos() {
        countries2XPos = new HashMap<String, Double>();
        countries2YPos = new HashMap<String, Double>();

        FileHandle file = Gdx.files.internal("geography/coordinatePercentOfCountries.csv");
        String[] lines = file.readString().split("\n");

        for (String line : lines) {
            String[] tokens = line.split(",");
            String name = tokens[0];
            Double x = Double.parseDouble(tokens[1]);
            Double y = Double.parseDouble(tokens[2]);

            countries2XPos.put(name, x);
            countries2YPos.put(name, y);

        }


    }

    public void init() {
        WORLDMAP = new Texture(Gdx.files.internal("geography/worldMap.png"));
        WORLDMAP2 = new Texture(Gdx.files.internal("geography/worldMap2.png"));
        PLANE = new Texture(Gdx.files.internal("geography/plane.png"));
        FISHERMAN = new Texture(Gdx.files.internal("geography/fisherman.png"));
        PERSON = new Texture(Gdx.files.internal("geography/person.png"));

        DEFAULT = new BitmapFont();

        initCountries();
        initCountryTextures();
        initCountryPos();

        return;

    }

    public String makeProper(String country) {
        String ans = "ERRRRRRROR";
        for (int i = 0; i < countries.size(); ++i) {
            if (country.equals(countries.get(i))) {
                ans = countriesProper.get(i);
            }
        }

        return ans;
    }


}
