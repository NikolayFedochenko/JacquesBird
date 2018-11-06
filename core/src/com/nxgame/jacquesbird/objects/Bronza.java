/*
 * Copyright © 2018.
 *        1.1. Все права собственности и авторские права на программу (в том числе любые включенные в нее
 *        управляющие программы (applets), фотографии, анимации, видео- и звукозаписи, музыку и текст),
 *        сопровождающие ее печатные материалы и любые копии программы принадлежат Автору. Все права
 *        Автора на программу защищены законами и международными соглашениями об
 *        авторских правах, а также другими законами и договорами, регулирующими отношения авторского права.
 *        Следовательно, с программой необходимо обращаться, как с любым другим объектом авторского права, с
 *        тем лишь исключением, что программу разрешается установить на одно устройство и сохранить оригинал
 *        при условии, что он будет использоваться только как архив или резервная копия. Копирование
 *        сопровождающих программу печатных материалов запрещено.
 *
 *       1.2. Не разрешается осуществлять вскрытие технологии, декомпиляцию и дизассемблирование
 *       программы, за исключением и только в той степени, в которой такие действия явно разрешены
 *       действующим законодательством, несмотря на наличие в соглашении данного ограничения.
 *
 *       1.3. Разделение программы. Программа лицензируется как единое целое. Ее нельзя разделять на
 *       составляющие части для использования на нескольких устройствах.
 *
 *       1.4. Запрещается продавать данное приложение, предоставлять это приложение в прокат или во временное
 *       пользование имея при этом любую выгоду.
 *
 *       1.5. Автор приложение не несёт ни какой ответственности за какой-либо причинённый вред устройству
 *       данным приложением.
 *
 */

package com.nxgame.jacquesbird.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Neutronx on 15.09.2018.
 *
 */

public class Bronza {
    private Vector2 position;
    private Texture texture;
    private TextureRegion region1;
    private Animation animation;

    private float timer;

    private void coinFrame() {
        if (texture == null) {
            texture = new Texture(Gdx.files.internal("objects/sprite/bronza.png"));
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

    public Bronza() {
        coinFrame();
        position = new Vector2(Gdx.graphics.getWidth() * 3f / 100f, (480f / 2f) - (480f * 23.1f / 100f));
    }

    public void render(SpriteBatch batch) {
        batch.draw(region1, position.x, position.y);
    }

    public void update(float delta) {
        timer += delta;
        region1 = (TextureRegion) animation.getKeyFrame(timer, true);
    }

    public void dispose() {
        texture = null;
        position = null;
        region1 = null;
        animation = null;
    }
}
