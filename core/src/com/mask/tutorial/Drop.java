package com.mask.tutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;


public class Drop extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    private Texture dropImage;
    private Texture bucketImage;

    private OrthographicCamera camera;

    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private long lastDropTime;

    private Music rainMusic;
    private Sound dropSound;

    private BitmapFont font;
    private int score = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("tutorial/badlogic.jpg");

        dropImage = new Texture("tutorial/droplet.png");
        bucketImage = new Texture("tutorial/bucket.png");

        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("tutorial/rain.mp3"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("tutorial/drop.wav"));

        font = new BitmapFont();
        font.getData().setScale(3);

        rainMusic.setLooping(true);
        rainMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bucket = new Rectangle();
        bucket.width = 64;
        bucket.height = 64;
        bucket.x = 800 / 2 - (bucket.width / 2);
        bucket.y = 20;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle raindrop : raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        font.draw(batch, "Score: " + score, 10, 480 - 10);
        batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - (bucket.width / 2);
        }
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
                ++score;
            } else if (raindrop.y + 64 < 0) {
                score = 0;
                iter.remove();
            }
        }
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void dispose() {
        img.dispose();
        dropImage.dispose();
        bucketImage.dispose();
        batch.dispose();
    }

}
