/*
 * Copyright (c) 2018.
 *         1.1. Все права собственности и авторские права на программу (в том числе любые включенные в нее
 *         управляющие программы (applets), фотографии, анимации, видео- и звукозаписи, музыку и текст),
 *         сопровождающие ее печатные материалы и любые копии программы принадлежат Автору. Все права
 *         Автора на программу защищены законами и международными соглашениями об
 *         авторских правах, а также другими законами и договорами, регулирующими отношения авторского права.
 *         Следовательно, с программой необходимо обращаться, как с любым другим объектом авторского права, с
 *         тем лишь исключением, что программу разрешается установить на одно устройство и сохранить оригинал
 *         при условии, что он будет использоваться только как архив или резервная копия. Копирование
 *         сопровождающих программу печатных материалов запрещено.
 *
 *         1.2. Не разрешается осуществлять вскрытие технологии, декомпиляцию и дизассемблирование
 *         программы, за исключением и только в той степени, в которой такие действия явно разрешены
 *         действующим законодательством, несмотря на наличие в соглашении данного ограничения.
 *
 *         1.3. Разделение программы. Программа лицензируется как единое целое. Ее нельзя разделять на
 *         составляющие части для использования на нескольких устройствах.
 *
 *         1.4. Запрещается продавать данное приложение, предоставлять это приложение в прокат или во временное
 *         пользование имея при этом любую выгоду.
 *
 *         1.5. Автор приложение не несёт ни какой ответственности за какой-либо причинённый вред устройству
 *         данным приложением.
 */

package com.nxgame.jacquesbird.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.nxgame.jacquesbird.others.LoadResources;

public class Mimic {
    private Animation animation;
    private TextureRegion mimic;
    private Rectangle bounds;
    private Vector2 position;

    private float speed;
    private float timer;
    private boolean move;

    private void initializeAnimation() {
        if (LoadResources.getMimic() != null) {
            LoadResources.getMimic().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            TextureRegion[][] regions = TextureRegion.split(LoadResources.getMimic(),
                    LoadResources.getMimic().getWidth() / 3, LoadResources.getMimic().getHeight());
            TextureRegion[] region = new TextureRegion[3];

            byte index = 0;
            timer = 0f;

            for (byte i = 0; i < 1; i++) {
                for (byte k = 0; k < 3; k++) {
                    region[index] = regions[i][k];
                    index++;
                }
            }

            animation = new Animation<TextureRegion>(0.12f, region);
            animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        }
    }

    public Mimic(float x, float y) {
        initializeAnimation();

        position = new Vector2(x, y);
        bounds = new Rectangle(position.x, position.y, 32, 32);
        move = false;
        speed = 60f;
    }

    public void render(SpriteBatch batch) {
        if (move) batch.draw(mimic, position.x, position.y, 35f, 35f);
    }

    public void update(float delta) {
        if (move) {
            position.x -= speed * delta;
            bounds.setPosition(position.x, position.y);
            timer += delta;
            mimic = (TextureRegion) animation.getKeyFrame(timer);

            if (position.x < -50f) {
                deactive();
            }
        }
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void deactive() {
        move = false;
        position.x = 850f;
        bounds.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public void dispose() {
        animation = null;
        bounds = null;
        position = null;
        mimic = null;
        move = Boolean.parseBoolean(null);
    }
}
