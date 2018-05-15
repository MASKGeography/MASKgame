package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by Neel on 4/2/2018.
 */

public class WorldMap implements Screen, GestureDetector.GestureListener {

    private final MASKgame game;
    private OrthographicCamera camera;
    private TimeUtils timer;
    private long startTime;
    private long lastChanged;

    private Random randy;

    int[] randarr;
    Sprite testFlagSprite;
    float flagX = -1;
    float flagY = -1;

    public WorldMap(final MASKgame gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.assets.WORLDMAP.getWidth(), game.assets.WORLDMAP.getHeight());

        randarr = new int[10];
        randy = new Random();
        for (int i = 0; i < 10; ++i) {
            randarr[i] = Math.abs(randy.nextInt()) % game.assets.countries.size();
        }

        timer = new TimeUtils();
        startTime = timer.millis();
        lastChanged = startTime;

        Gdx.input.setInputProcessor(new GestureDetector(this));

        testFlagSprite = new Sprite(game.assets.flagSprites.get(game.assets.countries.get(2)));
        //testFlagSprite.scale(2);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.translate(-1, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(game.assets.WORLDMAP, 0, 0);
        game.assets.DEFAULT.draw(game.batch, "Welcome to the world map! ", 100, 100);


        for (int i = 0; i < 10; ++i) {
            game.assets.DEFAULT.draw(game.batch, game.assets.countries.get(randarr[i]), 100, 150+20*i);
            game.batch.draw(game.assets.flagSprites.get(game.assets.countries.get(randarr[i])), 1000, 150+20*i);
        }

        if (flagX != -1) {
            testFlagSprite.setCenter(flagX, flagY);
            testFlagSprite.draw(game.batch);

            game.assets.DEFAULT.draw(game.batch, "XPos: " + flagX + " YPos:" + flagY, 1000, 500);
        }

        game.batch.end();


        if (timer.millis() - lastChanged >= 5 * 1000) {
            lastChanged = timer.millis();

            for (int i = 0; i < 10; ++i) {
                randarr[i] = Math.abs(randy.nextInt()) % game.assets.countries.size();
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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        flagX = x;
        flagY = Gdx.graphics.getHeight() - y;

        Vector3 pos = new Vector3(x, y, 0);
        camera.unproject(pos);
        flagX = pos.x;
        flagY = pos.y;
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        Vector3 touchPos = new Vector3(x,y,0);
        x -= Gdx.graphics.getWidth() / 2;
        y -= Gdx.graphics.getHeight() / 2;

        camera.translate(x / 20, y / 20);
        camera.update();

        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    public void pinchStop() {

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom);
        camera.update();

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        //camera.zoom *= (initialDistance / distance) * 0.0001;
        camera.zoom = (initialDistance / distance);
        camera.update();
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
    {
        float deltaX = pointer2.x - pointer1.x;
        float deltaY = pointer2.y - pointer1.y;

        float angle = (float)Math.atan2((double)deltaY,(double)deltaX) * MathUtils.radiansToDegrees;

        angle += 90f;

        if(angle < 0)
            angle = 360f - (-angle);


        return true;
    }
}

