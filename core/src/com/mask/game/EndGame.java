package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public class EndGame implements Screen, GestureDetector.GestureListener {
    private MASKgame game;

    OrthographicCamera camera;
    float height, width;

    int mode = 0;
    float time = 0;

    public EndGame(MASKgame gam) {
        game = gam;

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        Gdx.input.setInputProcessor(new GestureDetector(this));

    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(100/255.0f, 150/255.0f, 200/255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        BitmapFont font = Assets.Fonts.DEFAULT.get();
        font.getData().setScale((float) (0.003 * (Gdx.graphics.getWidth())));
        font.draw(game.batch, "Congratulations! You finished the game!\n" + game.scoreString(), 0, Gdx.graphics.getHeight() * 9/16, Gdx.graphics.getWidth(), 1, false);

        game.batch.end();

        if (mode >= 1 && time >= 3) {
            game.restart();
            game.setScreen(new MainMenu(game));
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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        ++mode;
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    public void pinchStop() {

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

}
