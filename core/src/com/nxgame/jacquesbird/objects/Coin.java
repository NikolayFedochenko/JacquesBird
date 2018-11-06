package com.nxgame.jacquesbird.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nxgame.jacquesbird.others.LoadResources;

/**
 * Created by Neutronx on 06.09.2018.
 *
 */

public class Coin {
    private Vector2 position;
    private Texture texture;
    private TextureRegion region1;
    private Animation animation;
    private Rectangle bounds;

    private float timer;
    private float speed;
    private boolean move;

    private void coinFrame() {
        if (texture == null) {
            if (move) {
                Preferences pref = Gdx.app.getPreferences("JacquesBird");
                switch (pref.getInteger("diff")) {
                    case 1:
                        texture = LoadResources.getBronza();
                        break;
                    case 2:
                        texture = LoadResources.getSerebro();
                        break;
                    case 3:
                        texture = LoadResources.getCoin();
                        break;
                }
            } else texture = LoadResources.getCoin();
        }
        byte FRAME_COLS = 2;
        byte FRAME_ROWS = 2;
        TextureRegion[][] region3 = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);
        TextureRegion[] region2 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        byte index = 0;
        timer = 0.0F;
        for (byte a = 0; a < FRAME_ROWS; a++) {
            for (byte b = 0; b < FRAME_COLS; b++) {
                region2[index] = region3[a][b];
                index++;
            }
        }
        animation = new Animation<TextureRegion>(0.1F, region2);
    }

    public Coin(boolean move) {
        this.move = move;
        coinFrame();

        if (move) {
            position = new Vector2(800f, MathUtils.random(100f, 350f));
            speed = 80f;
        } else {
            position = new Vector2(Gdx.graphics.getWidth() * 3f / 100f, (480f / 2f) - (480f * 9.1f / 100f));
        }
        bounds = new Rectangle(position.x, position.y, 32f, 32f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(region1, position.x, position.y);
    }

    public void recreate() {
        position.x = 800f;
        position.y = MathUtils.random(100f, 350f);
    }

    public void update(float delta) {
        timer += delta;
        region1 = (TextureRegion) animation.getKeyFrame(timer, true);
        if (move) {
            position.x -= speed * delta;
            if (position.x < -50f) recreate();
        }
        bounds.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public void dispose() {
        texture = null;
        bounds = null;
        position = null;
        region1 = null;
        animation = null;
        move = Boolean.parseBoolean(null);
    }
}
