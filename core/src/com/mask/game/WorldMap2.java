package com.mask.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static javax.swing.text.html.HTML.Tag.HEAD;

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

    Sprite[] flagButtons;
    String[] buttonNames;

    int toFind = 1;

    public static HashMap<String, Texture> flagSprites;

    public static ArrayList<CorrectSprite> flagSpriteStr;


    String answerString = "";

    PromptWords prompty;
    String thePrompt;
    String theSprite;
    Prompt ploty;


    public WorldMap2(MASKgame gam) {
        game = gam;

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        Gdx.app.log("Width", "" + width);
        Gdx.app.log("Height", "" + height);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        //use new square version of map
        background = new Sprite(Assets.Textures.WORLDMAP2.get());
        fakebackground = new Sprite(Assets.Textures.WORLDMAP.get());

        //use original map heights
        float mapHeight = Assets.Textures.WORLDMAP.get().getHeight();
        float mapWidth = Assets.Textures.WORLDMAP.get().getWidth();

        float scale = width / mapWidth;
        if (mapHeight * scale > height) {
            scale = height / mapHeight;
        }

        background.setScale(scale);
        fakebackground.setScale(scale);

        Gdx.app.log("Scale", "" + scale);

        background.setCenterX(camera.position.x);
        background.setCenterY(camera.position.y);

        Gdx.app.log("Camera x", "" + camera.position.x);
        Gdx.app.log("Camera y", "" + camera.position.y);


        fakebackground.setCenterX(camera.position.x);
        fakebackground.setCenterY(camera.position.y);

        Gdx.input.setInputProcessor(new GestureDetector(this));

        flagClicker = new Sprite(Assets.Textures.PLANE.get());
        flagClicker.setScale(0.125f);

        flagButtons = new Sprite[Assets.countries2XPos.keySet().size()];
        buttonNames = new String[Assets.countries2XPos.keySet().size()];
        Vector3 pos = new Vector3(0, 0, 0);

        int iter = 0;
        for (String key : Assets.countries2XPos.keySet()) {
            String name = key;
            Double x = Assets.countries2XPos.get(key);
            Double y = Assets.countries2YPos.get(key);

            Gdx.app.log("DEBUG", name + " " + x + " " + y + " " + name.length());

            if (x == null || y == null) {
                ++iter;
                continue;
            }


            Gdx.app.log("Fake Width", "" + fakebackground.getWidth());
            Gdx.app.log("Fake Height", "" + fakebackground.getHeight());

            pos.x = x.floatValue() * fakebackground.getWidth() * scale + (Gdx.graphics.getWidth() - fakebackground.getWidth() * scale) / 2;
            pos.y = y.floatValue() * fakebackground.getHeight() * scale + (Gdx.graphics.getHeight() - fakebackground.getHeight() * scale) / 2;


            pos.z = 0;
            //pos = camera.unproject(pos);

            Gdx.app.log("name", name);

            Sprite flagButton = new Sprite(Assets.flagSprites.get(name));
            flagButtons[iter] = flagButton;
            buttonNames[iter] = name;
            flagSpriteStr = new ArrayList<CorrectSprite>();
            String namePNG = name + ".png";
            Gdx.app.log("helpME:", namePNG);
            CorrectSprite theCorrectSprite = new CorrectSprite(namePNG, flagButton);
            flagSpriteStr.add(theCorrectSprite);
            flagButtons[iter].setPosition(pos.x, pos.y);

            flagButtons[iter].setScale(0.5f);
//            flagButtons[iter].setCenterX(pos.x);
//            flagButtons[iter].setCenterY(pos.y);

            ++iter;
        }


        Gdx.app.log("fakex", "" + fakebackground.getX());
        Gdx.app.log("fakey", "" + fakebackground.getY());


        ploty = Plot.getAPlot();
        prompty = ploty.getAPrompt();
        thePrompt = prompty.getPromptWord();
        theSprite = prompty.getSpriteName();



        Rectangle rect = background.getBoundingRectangle();
        Gdx.app.log("X:", "" + rect.x);
        Gdx.app.log("Y:", "" + rect.y);
        Gdx.app.log("W:", "" + rect.width);
        Gdx.app.log("H:", "" + rect.height);
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

        BitmapFont font = Assets.Fonts.DEFAULT.get();
        font.getData().setScale(3);



        font.draw(game.batch, "Prompt: " + thePrompt + "(" + theSprite + ")", 100, 800);
        font.draw(game.batch, answerString, 100, 600);
        font.draw(game.batch, "x = " + flagClicker.getX(), 500, 400);
        font.draw(game.batch, "y = " + flagClicker.getY(), 500, 500);

//        font.draw(game.batch, "x = " + flagClicker.getX(), 500, 400);
//        font.draw(game.batch, "y = " + flagClicker.getY(), 500, 500);


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
                for (int j = 0; j < flagButtons.length; ++j) {
                    Sprite flag = flagButtons[j];
                    String name = buttonNames[j];

                    if (Intersector.intersectRectangles(flag.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                        if (theSprite.equals(name + ".png")) {
                            answerString = "Congratulations, you found the country!";

                            //get new prompts
                            prompty = ploty.getAPrompt();
                            thePrompt = prompty.getPromptWord();
                            theSprite = prompty.getSpriteName();

                            Gdx.app.log("Switching to new prompt", theSprite);
                            break;
                        } else {
                            Gdx.app.log("Phsyche", "hello world");
                            answerString = "YOU WRONG!!!";
                        }

                        Gdx.app.log("name", "" + name.length());
                        Gdx.app.log("filename", "" + theSprite.length());


                        Gdx.app.log("Intersection detected", name);
                    }

                }
            }

            lastTouched = false;
        }

    }

        @Override
        public void resize ( int width, int height){
        }

        @Override
        public void show () {
        }

        @Override
        public void hide () {
        }

        @Override
        public void pause () {
        }

        @Override
        public void resume () {
        }

        @Override
        public void dispose () {
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
        camera.zoom *= (initialDistance / distance);
        camera.zoom = Math.min(1.0f, camera.zoom);
        camera.zoom = Math.max(1/10.0f, camera.zoom);
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