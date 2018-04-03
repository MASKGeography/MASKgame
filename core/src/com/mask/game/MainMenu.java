package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mask.tutorial.Drop2;
import com.mask.tutorial.GameScreen;

/**
 * Created by Neel on 4/2/2018.
 */

public class MainMenu implements Screen {
    private final MASKgame game;
    private OrthographicCamera camera;

    public MainMenu(final MASKgame gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        Assets.Fonts.DEFAULT.get().draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        Assets.Fonts.DEFAULT.get().draw(game.batch, "Tap anywhere to begin!", 100, 100);

        if (Gdx.input.isTouched()) {
            game.setScreen(new WorldMap(game));
            dispose();
        }

        game.batch.end();

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