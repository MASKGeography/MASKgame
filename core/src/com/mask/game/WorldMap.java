package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by Neel on 4/2/2018.
 */

public class WorldMap implements Screen {

    private final MASKgame game;
    private OrthographicCamera camera;
    private TimeUtils timer;
    private long startTime;
    private long lastChanged;

    private Random randy;

    int[] randarr;

    public WorldMap(final MASKgame gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Assets.Textures.WORLDMAP.get().getWidth(), Assets.Textures.WORLDMAP.get().getHeight());

        randarr = new int[10];
        randy = new Random();
        for (int i = 0; i < 10; ++i) {
            randarr[i] = Math.abs(randy.nextInt()) % Assets.countries.size();
        }

        timer = new TimeUtils();
        startTime = timer.millis();
        lastChanged = startTime;

        //load images for sprites
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(Assets.Textures.WORLDMAP.get(), 0, 0);
        Assets.Fonts.DEFAULT.get().draw(game.batch, "Welcome to the world map! ", 100, 100);

        for (int i = 0; i < 10; ++i) {
            Assets.Fonts.DEFAULT.get().draw(game.batch, Assets.countries.get(randarr[i]), 100, 150+20*i);
        }

        game.batch.end();


        if (timer.millis() - lastChanged >= 5 * 1000) {
            lastChanged = timer.millis();

            for (int i = 0; i < 10; ++i) {
                randarr[i] = Math.abs(randy.nextInt()) % Assets.countries.size();
            }
        }


    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
