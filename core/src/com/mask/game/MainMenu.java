package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mask.tutorial.Drop2;
import com.mask.tutorial.GameScreen;

/**
 * Created by Neel on 4/2/2018.
 */

public class MainMenu implements Screen, GestureDetector.GestureListener {
    private final MASKgame game;
    private OrthographicCamera camera;

    Texture yoButton;
    Sprite yoSprite;

    boolean lastTouched = false;
    boolean atAllTouched = false;
    float touchX = -1, touchY = -1;

    Sprite flagClicker;




    public MainMenu(final MASKgame gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        Vector3 pos = new Vector3(0,0,0);

        yoButton = new Texture(Gdx.files.internal("geography/mainMenuButtons/yo.png"));
        yoSprite = new Sprite(yoButton);

        yoSprite.setPosition(300, 200);

        Gdx.input.setInputProcessor(new GestureDetector(this));

        flagClicker = new Sprite(Assets.Textures.PLANE.get());
        flagClicker.setScale(0.125f);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        Assets.Fonts.DEFAULT.get().draw(game.batch, "Where in the world? ", 300, 400);
        Assets.Fonts.DEFAULT.get().draw(game.batch, "Tap anywhere to begin!", 300, 350);
        Assets.Fonts.DEFAULT.get().draw(game.batch, "Where in the world? ", 300, 300);

        BitmapFont font = Assets.Fonts.DEFAULT.get();
        font.getData().setScale(3);
        font.draw(game.batch, "x = " + flagClicker.getX(), 500, 400);
        font.draw(game.batch, "y = " + flagClicker.getY(), 500, 500);

        yoSprite.draw(game.batch);
        //flagClicker.draw(game.batch);


        if (atAllTouched) {
            flagClicker.draw(game.batch);
        }

        game.batch.end();

        if (lastTouched) {
            Vector3 pos = new Vector3(touchX, touchY, 0);
            pos = camera.unproject(pos);
            //flagClicker.setPosition(pos.x, pos.y);
            flagClicker.setCenterX(pos.x);
            flagClicker.setCenterY(pos.y);
        }

        if (atAllTouched) {
            if (lastTouched) {
                for (int k = 0; k < 10; ++k) {
                    Gdx.app.log("MAINMENYU", "atalltouchedFOR");
                    if (Intersector.intersectRectangles(yoSprite.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                        game.setScreen(new WorldMap2(game));
                        dispose();
                        Gdx.app.log("MAINMENYU", "touching");
                        break;
                    }
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
    public boolean touchDown ( float x, float y, int pointer, int button){
        return false;
    }

    @Override
    public boolean tap ( float x, float y, int count, int button){
        atAllTouched = true;
        lastTouched = true;
        touchX = x;
        touchY = y;
        return false;
    }
    @Override
    public boolean longPress ( float x, float y){
        return false;
    }

    @Override
    public boolean fling ( float velocityX, float velocityY, int button){
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
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        //TODO: No idea where this is from
        float deltaX = pointer2.x - pointer1.x;
        float deltaY = pointer2.y - pointer1.y;

        float angle = (float) Math.atan2((double) deltaY, (double) deltaX) * MathUtils.radiansToDegrees;

        angle += 90f;

        if (angle < 0)
            angle = 360f - (-angle);


        return true;
    }

}
