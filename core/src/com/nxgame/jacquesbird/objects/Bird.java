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

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nxgame.jacquesbird.others.LoadResources;

/**
 * Created by Neutronx on 06.09.2018.
 *
 */

public class Bird {
    private Vector2 position;
    private TextureRegion region1;
    private Animation animation;

    private float timer;
    private float speed;
    private float gravity;
    private float vy;
    private float timerJump;

    private void birdFrame() {
        byte FRAME_COLS = 3;
        byte FRAME_ROWS = 1;
        TextureRegion[][] region3 = TextureRegion.split(LoadResources.getBird(),
                LoadResources.getBird().getWidth() / FRAME_COLS, LoadResources.getBird().getHeight() / FRAME_ROWS);
        TextureRegion[] region2 = new TextureRegion[FRAME_COLS];
        byte index = 0;
        timer = 0f;
        for (byte a = 0; a < FRAME_ROWS; a++) {
            for (byte b = 0; b < FRAME_COLS; b++) {
                region2[index] = region3[a][b];
                index++;
            }
        }
        animation = new Animation<TextureRegion>(0.1f, region2);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public Bird() {
        birdFrame();
        position = new Vector2(-100f, MathUtils.random(200f, 300f));
        speed = 120f;
        gravity = -520f;
        vy = 0f;
        timerJump = 0.f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(region1, position.x, position.y, 50f, 40f);
    }

    public void update(float delta) {
        timer += delta;
        timerJump += delta;
        region1 = (TextureRegion) animation.getKeyFrame(timer);
        position.x += speed * delta;

        vy += gravity * delta;
        if (timerJump >= .8f) {
            vy = 200f;
            timerJump = 0.0f;
        }
        position.y += vy * delta;

        if (position.x > 900f) {
            position.x = -100f;
            position.y = (MathUtils.random(100f, 400f));
        }
    }

    public void dispose() {
        position = null;
        animation = null;
        region1 = null;
    }
}
