package com.mask.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Neel on 3/26/2018.
 */

public class Assets {

    public enum Textures {
        DROPLET("droplet.png"),
        BUCKET("bucket.png");

        private final Texture texture;
        Textures(String image) {
            texture = new Texture(Gdx.files.internal(image));
        }

        public Texture get() {
            return texture;
        }

    }

    public enum Sounds {

    }


}
