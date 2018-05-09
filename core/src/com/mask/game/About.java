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
 * Created by Neel on 4/12/2018.
 */

public class About implements Screen, GestureDetector.GestureListener {

    private final MASKgame game;
    private OrthographicCamera camera;

    private float width, height;
    Sprite background;

    boolean lastTouched = false;
    boolean atAllTouched = false;
    float touchX = -1, touchY = -1;
    Sprite back;
    Sprite flagClicker;

    String credits;
    String abouta4g;
    String aboutTC;
    String purpose;

    public About(MASKgame gam) {
        game = gam;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        Vector3 pos = new Vector3(0,0,0);

        Gdx.input.setInputProcessor(new GestureDetector(this));

        flagClicker = new Sprite(Assets.Textures.PLANE.get());
        flagClicker.setScale(0.125f);

        //use new square version of map
        background = new Sprite(Assets.Textures.WORLDMAP2.get());
        background.setCenterX(camera.position.x);
        background.setCenterY(camera.position.y);

        back = new Sprite(new Texture(Gdx.files.internal("geography/mainMenuButtons/backButton.png")));
        back.setScale((float) (width*0.0015));
        back.setPosition((float) (back.getWidth()*width*0.0015), Gdx.graphics.getHeight() * 13/16);

        credits = "The following resources were used to find information for prompts:\n" +
            "\n"+
            "The Telegraph Travel Destinations: \n " +
            "https://www.telegraph.co.uk/travel/destinations/europe/galleries/europe-top-best-places-and-destinations-to-visit/\n" +
            "\n" +
            "WorldAtlas.com:\n" +
            "https://www.worldatlas.com/articles/world-leaders-in-wood-product-exports.html\n" +
            "https://www.worldatlas.com/articles/10-largest-lakes-in-the-world.html\n" +
            "https://www.worldatlas.com/articles/world-leaders-in-jackfruit-production.html\n" +
            "\n" +
            "World’s Top Exports:\n" +
            "http://www.worldstopexports.com/top-aluminum-exporters-by-country/\n" +
            "\n" +
            "BBC Good Food:\n" +
            "https://www.bbcgoodfood.com/howto/guide/top-10-foods-try-spain\n" +
            "https://www.bbcgoodfood.com/howto/guide/top-10-foods-try-mexico\n" +
            "\n" +
            "Land Lopers:\n" +
            "https://landlopers.com/2014/07/08/croatian-desserts\n" +
            "\n" +
            "Eat Out:\n" +
            "http://www.eatout.co.za/article/21-iconic-south-african-foods-ultimate-guide-visitors/\n" +
            "\n" +
            "The Broke Backpacker:\n" +
            "https://www.thebrokebackpacker.com/top-10-venezuelan-foods/\n" +
            "\n" +
            "Buzzfeed:\n" +
            "https://www.buzzfeed.com/chelseypippin/27-turkish-foods-that-will-ruin-you-for-life?utm_term=.guY1DbNRXB#.yr43peMw2m\n" +
            "\n" +
            "Pandotrip\n" +
            "https://www.pandotrip.com/top-10-highest-peaks-in-europe-23392/\n" +
            "\n" +
            "Eniscuola\n" +
            "http://www.eniscuola.net/en/mediateca/10-highest-mountains-in-oceania/\n" +
            "\n" +
            "Answers Africa:\n" +
            "https://answersafrica.com/highest-mountains-africa.html\n" +
            "\n" +
            "Swedish Nomad:\n" +
            "https://www.swedishnomad.com/things-to-do-in-sweden/\n" +
            "\n" +
            "The Zoo Zoom:\n" +
            "http://thezoozoom.com/bizarre/35-rarest-animals-in-the-world\n" +
            "\n" +
            "Encyclopedia.com\n" +
            "https://www.encyclopedia.com/plants-and-animals/plants/plants/durian\n" +
            "\n" +
            "Purdue Horticulture and Landscape Architecture:\n" +
            "https://hort.purdue.edu/newcrop/morton/cherimoya.html\n" +
            "https://hort.purdue.edu/newcrop/CropFactSheets/kiwano.html\n" +
            "https://hort.purdue.edu/newcrop/morton/feijoa.html\n" +
            "\n" +
            "California Rare Fruit Growers Inc.:\n" +
            "https://www.crfg.org/pubs/ff/passionfruit.html\n" +
            "https://www.crfg.org/pubs/ff/tamarillo.html\n" +
            "\n" +
            "Ever In Transit:\n" +
            "http://www.everintransit.com/exotic-fruits/\n" +
            "\n" +
            "Arch Daily:\n" +
            "https://www.archdaily.com/779178/these-are-the-worlds-25-tallest-buildings\n" +
            "\n" +
            "TripAdvisor.com:\n" +
            "https://www.tripadvisor.com/TravelersChoice-Attractions-cAmusementParks-g1\n" +
            "https://www.themeparktourist.com/features/20140228/16441/top-50-theme-parks-world?page=4\n";

        abouta4g = "Apps For Good provides course content for teachers to use in their classrooms. Their goal is to inspire collaboration and self-confidence in people aged 10-18, and prepare them to make a difference in the world.";

        aboutTC = "We are a group of four juniors from the Massachusetts Academy of Math and Science at WPI. We designed this game for our Apps For Good assignment in our Computer Science class, where we were tasked to create an app that addresses a need in our community. \n" +
            "Shashvat: optimist, skilled coder (ssrivastava2@wpi.edu) \n" +
            "Matthew: problem solver, motivated worker (maedwards@wpi.edu)\n" +
            "Kelly: efficient researcher, organizer (kamiller@wpi.edu) \n" +
            "Alex: effective communicator, perseverer (alholmes@wpi.edu) ";

        purpose = "The purpose of our app is to fill the gap in the geography knowledge of American middle school students. We hope to provide players with a rewarding, low risk environment by utilizing a scavenger hunt style game, rather than a stressful environment like those created by quiz-style study apps.";

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

        font.getData().setScale(6 * ((640)/(Gdx.graphics.getWidth()/Gdx.graphics.getDensity())));
        font.draw(game.batch, "About Apps for Good:", 0, Gdx.graphics.getHeight() * 7 / 8, Gdx.graphics.getWidth(), 1, false);
        font.draw(game.batch, "About the Creators:", 0, Gdx.graphics.getHeight() * 4 / 8, Gdx.graphics.getWidth(), 1, false);
        font.draw(game.batch, "Our Purpose:", 0, Gdx.graphics.getHeight() * -2 / 8, Gdx.graphics.getWidth(), 1, false);
        font.draw(game.batch, "Credits:", 0, Gdx.graphics.getHeight() * -6 / 8, Gdx.graphics.getWidth(), 1, false);

        font.getData().setScale(4 * ((640)/(Gdx.graphics.getWidth()/Gdx.graphics.getDensity())));
        font.draw(game.batch, abouta4g, 0, Gdx.graphics.getHeight() * 6 / 8,   Gdx.graphics.getWidth(), -1, true);
        font.draw(game.batch, aboutTC, 0, Gdx.graphics.getHeight() * 3 / 8,   Gdx.graphics.getWidth(), -1, true);
        font.draw(game.batch, purpose, 0, Gdx.graphics.getHeight() * -3 / 8,   Gdx.graphics.getWidth(), -1, true);
        font.draw(game.batch, credits, 0, Gdx.graphics.getHeight() * -7 / 8,   Gdx.graphics.getWidth(), -1, true);

        back.draw(game.batch);

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
                Gdx.app.log("MAINMENYU", "atalltouchedFOR");

                if (Intersector.intersectRectangles(back.getBoundingRectangle(), flagClicker.getBoundingRectangle(), new Rectangle())) {
                    game.setScreen(new MainMenu(game));

                    dispose();
                    Gdx.app.log("MAINMENYU", "touching");

                }

            }
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
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //if(deltaY*camera.zoom<=0){
        camera.translate(0, deltaY * camera.zoom);
        camera.update();//}
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) { return false; }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) { return true; }
}