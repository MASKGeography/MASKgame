package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Neel on 4/12/2018.
 */

public class HowToPlay implements Screen, GestureDetector.GestureListener {

    private final MASKgame game;
    private OrthographicCamera camera;
    private float width, height;
    Sprite background;
    boolean lastTouched = false;
    boolean atAllTouched = false;
    float touchX = -1, touchY = -1;
    Sprite back;
    Sprite flagClicker;

    public HowToPlay(MASKgame gam) {
        game = gam;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        //use new square version of map
        background = new Sprite(game.assets.WORLDMAP2);
        background.setCenterX(camera.position.x);
        background.setCenterY(camera.position.y);

        //Formatting back button
        back = new Sprite(new Texture(Gdx.files.internal("geography/mainMenuButtons/backButton.png")));
        back.setPosition(Gdx.graphics.getWidth() * 1 / 16, Gdx.graphics.getHeight() * 13 / 16);
        back.setScale((float) (width * 0.0015));

        //Gestures
        Gdx.input.setInputProcessor(new GestureDetector(this));
        Gdx.input.setCatchBackKey(true);

        //Flag clicker
        flagClicker = new Sprite(game.assets.PLANE);
        flagClicker.setScale(0.125f);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //Draws text
        BitmapFont font = game.assets.DEFAULT;
        font.getData().setScale((float) (0.004 * (Gdx.graphics.getWidth())));
        font.draw(game.batch, "Welcome to How To Play", 0, Gdx.graphics.getHeight() * 6 / 8, Gdx.graphics.getWidth(), 1, false);
        font.getData().setScale((float) (0.003 * (Gdx.graphics.getWidth())));
        font.draw(game.batch, "1. Read the prompt and click on the flag of the country that answers the prompt. Pinch with two fingers to zoom in and out, and scroll with one finger.\n" +
            "2. Congratulations, you can now begin playing!", 0, Gdx.graphics.getHeight() * 5 / 8, Gdx.graphics.getWidth(), -1, true);

        //Draws back button
        back.draw(game.batch);

        //Draws the airplane when the screen is touched
        if (atAllTouched) {
            flagClicker.draw(game.batch);
        }

        game.batch.end();

        backButtonPressed |= Gdx.input.isKeyPressed(Input.Keys.BACK);

        //Moves airplane to the last touched location
        if (lastTouched) {
            Vector3 pos = new Vector3(touchX, touchY, 0);
            pos = camera.unproject(pos);
            //flagClicker.setPosition(pos.x, pos.y);
            flagClicker.setCenterX(pos.x);
            flagClicker.setCenterY(pos.y);
        }

        //Returns to main menu when back button pressed
        if (backButtonPressed ||
            (lastTouched && Intersector.intersectRectangles(back.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle()))) {
            game.setScreen(new MainMenu(game));

            dispose();
            return;
        }

        if (atAllTouched) {
            if (lastTouched) {

            }

            lastTouched = false;
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
        atAllTouched = true;
        lastTouched = true;
        touchX = x;
        touchY = y;
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
        return true;
    }

    boolean backButtonPressed = false;

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            // Do your optional back button handling (show pause menu?)
            backButtonPressed = true;
        }
        return false;
    }
}