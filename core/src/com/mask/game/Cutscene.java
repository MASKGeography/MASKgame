package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Screen;

public class Cutscene implements Screen, GestureDetector.GestureListener {
    private MASKgame game;

    OrthographicCamera camera;
    float height, width;

    String thePrompt;
    String theSprite;

    Sprite sprite;

    int mode = 0;



    public Cutscene(MASKgame gam) {
        game=gam;

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        Gdx.input.setInputProcessor(new GestureDetector(this));
        sprite = new Sprite(Assets.Textures.FISHERMAN.get());

        sprite.setCenterX(0.2f * width);
        sprite.setCenterY(0.5f * height);
        sprite.setScale(2);

        game.updatePlotsNStuff();
        thePrompt = game.getThePrompt();
        theSprite = game.getTheSprite();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        sprite.draw(game.batch);

        if (mode >= 1) {
            BitmapFont font = Assets.Fonts.DEFAULT.get();

            font.getData().setScale(3);

            font.draw(game.batch, thePrompt, 100, 800);
        }

        game.batch.end();


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
        Gdx.app.log("Cutscene", "switching back");
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
