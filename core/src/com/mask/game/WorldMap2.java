package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

public class WorldMap2 implements Screen, GestureDetector.GestureListener {

    private final MASKgame game;
    private OrthographicCamera camera;

    private float width, height;
    Sprite background, fakebackground;
    Color backgroundColor;

    boolean lastTouched = false;
    boolean atAllTouched = false;
    float touchX = -1, touchY = -1;

    Sprite flagClicker;
    Sprite back;

    Sprite[] flagButtons;
    String[] buttonNames;

    int toFind = 1;

    public static boolean switchPlot;


    String answerString = "";
    String thePrompt;
    String theSprite;

    float initX, initY;


    public WorldMap2(MASKgame gam, String prompt, String sprite) {
        game = gam;
        thePrompt = prompt;
        theSprite = sprite;

        switchPlot = false;

        back = new Sprite(new Texture(Gdx.files.internal("geography/mainMenuButtons/backButton.png")));
        back.setScale(1);
        back.setPosition(Gdx.graphics.getWidth() * 1 / 32, Gdx.graphics.getHeight() * 7 / 16);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        initX = camera.position.x;
        initY = camera.position.y;

        //use new square version of map
        background = new Sprite(game.assets.WORLDMAP2);
        fakebackground = new Sprite(game.assets.WORLDMAP);

        //use original map heights
        float mapHeight = game.assets.WORLDMAP.getHeight();
        float mapWidth = game.assets.WORLDMAP.getWidth();


        float scale = width / mapWidth;
        if (mapHeight * scale > height) {
            scale = height / mapHeight;
        }

        background.setScale(scale);
        fakebackground.setScale(scale);


        background.setCenterX(camera.position.x);
        background.setCenterY(camera.position.y);


        fakebackground.setCenterX(camera.position.x);
        fakebackground.setCenterY(camera.position.y);

        Gdx.input.setInputProcessor(new GestureDetector(this));
        Gdx.input.setCatchBackKey(true);

        flagClicker = new Sprite(game.assets.PLANE);
        flagClicker.setScale(0.125f);

        flagButtons = new Sprite[game.assets.countries2XPos.keySet().size()];
        buttonNames = new String[game.assets.countries2XPos.keySet().size()];
        Vector3 pos = new Vector3(0, 0, 0);

        int iter = 0;
        for (String key : game.assets.countries2XPos.keySet()) {
            String name = key;
            Double x = game.assets.countries2XPos.get(key);
            Double y = game.assets.countries2YPos.get(key);


            if (x == null || y == null) {
                ++iter;
                continue;
            }


            pos.x = x.floatValue() * fakebackground.getWidth() * scale + (Gdx.graphics.getWidth() - fakebackground.getWidth() * scale) / 2;
            pos.y = y.floatValue() * fakebackground.getHeight() * scale + (Gdx.graphics.getHeight() - fakebackground.getHeight() * scale) / 2;


            pos.z = 0;
            //pos = camera.unproject(pos);


            Sprite flagButton = new Sprite(game.assets.flagSprites.get(name));
            flagButtons[iter] = flagButton;
            buttonNames[iter] = name;

            String namePNG = name + ".png";

            flagButtons[iter].setPosition(pos.x, pos.y);

            flagButtons[iter].setScale(0.5f);
//            flagButtons[iter].setCenterX(pos.x);
//            flagButtons[iter].setCenterY(pos.y);

            ++iter;
        }


        Rectangle rect = background.getBoundingRectangle();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        background.draw(game.batch);

        //fakebackground.draw(game.batch);
        for (Sprite button : flagButtons) button.draw(game.batch);

        BitmapFont font = game.assets.DEFAULT;
        font.setColor(Color.BLACK);
        font.getData().setScale((4 * ((640) / (Gdx.graphics.getWidth() / Gdx.graphics.getDensity()))) * camera.zoom);

        float posX = 0 + (camera.position.x - initX);
        float posY = Gdx.graphics.getHeight() * 3 / 16 + (camera.position.y - initY);
        posY = camera.position.y + (posY - camera.position.y) * camera.zoom;

        font.draw(game.batch, game.scoreString(), posX, posY, Gdx.graphics.getWidth(), 1, true);

        posX = 0 + (camera.position.x - initX);
        posY = Gdx.graphics.getHeight() * 31 / 32 + (camera.position.y - initY);
        posY = camera.position.y + (posY - camera.position.y) * camera.zoom;

        font.draw(game.batch, "Prompt: " + thePrompt, posX, posY, Gdx.graphics.getWidth(), 1, true);

        posX = 0 + (camera.position.x - initX);
        posY = Gdx.graphics.getHeight() * 2 / 16 + (camera.position.y - initY);
        posY = camera.position.y + (posY - camera.position.y) * camera.zoom;

        font.draw(game.batch, answerString, posX, posY, Gdx.graphics.getWidth(), 1, true);
        font.setColor(Color.WHITE);
        posX = Gdx.graphics.getWidth() * 4 / 64 + (camera.position.x - initX);
        posX = camera.position.x + (posX - camera.position.x) * camera.zoom;
        posY = Gdx.graphics.getHeight() * 8 / 16 + (camera.position.y - initY);
        posY = camera.position.y + (posY - camera.position.y) * camera.zoom;
        back.setScale(1.5f * camera.zoom);
        back.setCenterX(posX);
        back.setCenterY(posY);

        game.time += Gdx.graphics.getDeltaTime();

        back.draw(game.batch);

        if (atAllTouched) {
            flagClicker.draw(game.batch);
        }

        game.batch.end();

        backButtonPressed |= Gdx.input.isKeyPressed(Input.Keys.BACK);

        if (lastTouched) {
            Vector3 pos = new Vector3(touchX, touchY, 0);
            pos = camera.unproject(pos);
            //flagClicker.setPosition(pos.x, pos.y);
            flagClicker.setCenterX(pos.x);
            flagClicker.setCenterY(pos.y);
        }

        if (backButtonPressed ||
            (lastTouched && Intersector.intersectRectangles(back.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle()))) {
            game.setScreen(new MainMenu(game));

            dispose();
            return;
        }

        if (atAllTouched) {
            if (lastTouched) {

                boolean anyflagtouched = false;
                for (int j = 0; j < flagButtons.length; ++j) {
                    Sprite flag = flagButtons[j];
                    String name = buttonNames[j];

                    if (Intersector.intersectRectangles(flag.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                        anyflagtouched = true;
                        if (theSprite.equals(name + ".png")) {
                            game.score += 10;
                            answerString = "Congratulations, you found the country!";

                            switchPlot = true;

                            //get new prompts
                            game.updatePlotsNStuff();

                            if (game.completed) {
                                game.setScreen(new EndGame(game));
                            } else {
                                game.setScreen(new Cutscene(game));
                            }

                            dispose();
                            return;
                        } else {
                            answerString = "You stranded the person in " + game.assets.makeProper(name) + "! Please help them get to " + game.assets.makeProper(theSprite.substring(0, theSprite.length() - 4)) + "!";
                            switchPlot = false;
                        }


                    }

                }

                if (anyflagtouched) {
                    --game.score;
                }
                if (!anyflagtouched) answerString = "";
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
        camera.zoom *= Math.sqrt(Math.sqrt(initialDistance / distance));
        camera.zoom = Math.min(1.0f, camera.zoom);
        camera.zoom = Math.max(1 / 10.0f, camera.zoom);
        camera.update();
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