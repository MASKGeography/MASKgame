package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class Cutscene implements Screen, GestureDetector.GestureListener {
    private MASKgame game;
    OrthographicCamera camera;
    float height, width;
    String thePrompt;
    String theSprite;
    String overview;
    Sprite sprite;
    int mode = 0;
    float tstart = 0;

    public Cutscene(MASKgame gam) {
        game = gam;
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        Gdx.input.setInputProcessor(new GestureDetector(this));
        Gdx.input.setCatchBackKey(true);
        sprite = new Sprite(game.assets.PERSON);
        sprite.setCenterX(0.15f * width);
        sprite.setCenterY(0.4f * height);
        sprite.setScale(2);
        thePrompt = game.getThePrompt();
        theSprite = game.getTheSprite();
        overview = game.getOverview();
    }

    @Override
    public void render(float delta) {
        tstart += 3 * Gdx.graphics.getDeltaTime();
        boolean drawRed = false;
        if ((int) tstart % 2 == 0) {
            drawRed = true;
        }
        Gdx.gl.glClearColor(100 / 255.0f, 150 / 255.0f, 200 / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        sprite.draw(game.batch);
        if (mode >= 1) {
            BitmapFont font = game.assets.DEFAULT;
            font.getData().setScale((float) (0.003 * (Gdx.graphics.getWidth())));
            font.draw(game.batch, "A person needs your help!" + overview, 0, Gdx.graphics.getHeight() * 27 / 32, Gdx.graphics.getWidth(), 1, true);
            if (drawRed) font.setColor(Color.RED);
            else font.setColor(Color.BLACK);
            font.getData().setScale((float) (0.005 * (Gdx.graphics.getWidth())));
            font.draw(game.batch, game.scoreString(), 0, Gdx.graphics.getHeight() * 31 / 32, Gdx.graphics.getWidth(), 1, true);
            font.setColor(Color.WHITE);
        }
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            --mode;
            if (mode < 0) mode = 0;
        }

        if (mode == 2) {
            game.setScreen(new WorldMap2(game, thePrompt, theSprite));
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
        return false;
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
