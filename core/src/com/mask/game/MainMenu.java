package com.mask.game;

import com.badlogic.gdx.Gdx;
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
 * Created by Neel on 4/2/2018.
 */

public class MainMenu implements Screen, GestureDetector.GestureListener {
    private final MASKgame game;
    private OrthographicCamera camera;
    Sprite playSprite;
    Sprite htpSprite;
    Sprite a4gSprite;
    boolean lastTouched = false;
    boolean atAllTouched = false;
    float touchX = -1, touchY = -1;
    Sprite flagClicker;
    private float width, height;

    public MainMenu(final MASKgame gam) {
        game = gam;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        Vector3 pos = new Vector3(0, 0, 0);

        //Formats play button
        playSprite = new Sprite(new Texture(Gdx.files.internal("geography/mainMenuButtons/playButton.png")));
        playSprite.setPosition((float) (width * 0.25 - playSprite.getWidth() * 0.5), (float) (height * 0.25));
        playSprite.setScale((float) (width * 0.002));

        //Formats how to play button
        htpSprite = new Sprite(new Texture(Gdx.files.internal("geography/mainMenuButtons/htpButton.png")));
        htpSprite.setPosition((float) (width * 0.5 - htpSprite.getWidth() * 0.5), (float) (height * 0.25));
        htpSprite.setScale((float) (width * 0.002));

        //Formats about button
        a4gSprite = new Sprite(new Texture(Gdx.files.internal("geography/mainMenuButtons/aboutButton.png")));
        a4gSprite.setPosition((float) (width * 0.75 - a4gSprite.getWidth() * 0.5), (float) (height * 0.25));
        a4gSprite.setScale((float) (width * 0.002));

        //Gestures
        Gdx.input.setInputProcessor(new GestureDetector(this));

        //Formats plane clicker
        flagClicker = new Sprite(game.assets.PLANE);
        flagClicker.setScale(0.125f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //Draws buttons
        playSprite.draw(game.batch);
        htpSprite.draw(game.batch);
        a4gSprite.draw(game.batch);

        //Draws airplane clicker
        if (atAllTouched) {
            flagClicker.draw(game.batch);
        }

        //Draws text
        BitmapFont font = game.assets.DEFAULT;
        font.getData().setScale((float) (width * 0.004));
        font.draw(game.batch, "Where in the world? ", 0, (float) (height * 0.75), Gdx.graphics.getWidth(), 1, false);
        font.getData().setScale(1);
        game.batch.end();

        //Moves airplane to the last touched location
        if (lastTouched) {
            Vector3 pos = new Vector3(touchX, touchY, 0);
            pos = camera.unproject(pos);
            flagClicker.setCenterX(pos.x);
            flagClicker.setCenterY(pos.y);
        }

        //Switches to the appropriate screen when a button is pressed
        if (atAllTouched) {
            if (lastTouched) {
                if (Intersector.intersectRectangles(playSprite.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                    game.setScreen(new Cutscene(game));
                    dispose();
                } else if (Intersector.intersectRectangles(htpSprite.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                    game.setScreen(new HowToPlay(game));
                    dispose();
                } else if (Intersector.intersectRectangles(a4gSprite.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                    game.setScreen(new About(game));
                    dispose();
                }
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
}