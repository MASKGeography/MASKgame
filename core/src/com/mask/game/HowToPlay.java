package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
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

    public HowToPlay(MASKgame gam) {
        game = gam;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        //use new square version of map
        background = new Sprite(Assets.Textures.WORLDMAP2.get());
        background.setCenterX(camera.position.x);
        background.setCenterY(camera.position.y);
        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        BitmapFont font = Assets.Fonts.DEFAULT.get();
        font.getData().setScale(3);
        font.draw(game.batch, "Welcome to How To Play", 0, Gdx.graphics.getHeight() * 7 / 8, Gdx.graphics.getWidth(), 1, false);
        font.draw(game.batch, "Welcome to How To Play. To play, follow steps blank blank blank blank and blank. Welcome to How To Play. To play, follow steps blank blank blank blank and blank. Welcome to How To Play. To play, follow steps blank blank blank blank and blank.Welcome to How To Play. To play, follow steps blank blank blank blank and blank.Welcome to How To Play. To play, follow steps blank blank blank blank and blank.Welcome to How To Play. To play, follow steps blank blank blank blank and blank.Welcome to How To Play. To play, follow steps blank blank blank blank and blank.", 0, Gdx.graphics.getHeight() * 6 / 8, Gdx.graphics.getWidth(), 1, true);
        game.batch.end();
        if (lastTouched) {
            Vector3 pos = new Vector3(touchX, touchY, 0);
            pos = camera.unproject(pos);
        }
        if (atAllTouched) {
            lastTouched = false;
        }
    }

    @Override
    public void resize (int width, int height){ }

    @Override
    public void show () { }

    @Override
    public void hide () { }

    @Override
    public void pause () { }

    @Override
    public void resume () { }

    @Override
    public void dispose () { }

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

    public void pinchStop() { }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) { return false; }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) { return false; }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) { return true; }
}