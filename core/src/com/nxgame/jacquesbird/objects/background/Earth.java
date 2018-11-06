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

package com.nxgame.jacquesbird.objects.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nxgame.jacquesbird.others.LoadResources;

/**
 * Created by Neutronx on 02.10.2018.
 */

public class Earth {
    private boolean move;
    private float speed;
    private float alpha;

    private class EarthWinter {
        private Sprite sprite;
        private Vector2 position;

        EarthWinter(float x) {
            if (LoadResources.getEarth() != null) {
                LoadResources.getEarth().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                if (sprite == null) sprite = new Sprite(LoadResources.getEarth());
                sprite.setSize(800f, 480f);
                if (position == null) position = new Vector2(x, 0f);
            }
        }

        void render(SpriteBatch batch) {
            sprite.draw(batch, getAlpha());
        }

        void update(float delta) {
            if (isMove()) position.x -= getSpeed() * delta;
            sprite.setPosition(position.x, position.y);
        }

        void reposition(float x) {
            if (isMove()) position.x = x;
        }

        Vector2 getPosition() {
            return position;
        }

        void dispose() {
            position = null;
            sprite = null;
        }
    }

    private EarthWinter[] earthWinters;

    public Earth() {
        earthWinters = new EarthWinter[2];
        earthWinters[0] = new EarthWinter(0f);
        earthWinters[1] = new EarthWinter(799f);
    }

    public void render(SpriteBatch batch) {
        for (EarthWinter earthWinter : earthWinters) {
            earthWinter.render(batch);
        }
    }

    private boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    private float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    private float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void update(float delta) {
        if (isMove()) {
            earthWinters[0].update(delta);
            earthWinters[1].update(delta);

            if (earthWinters[0].getPosition().x < -800f) {
                earthWinters[0].reposition(0f);
                earthWinters[1].reposition(800f);
            }
        }
    }

    public void dispose() {
        for (EarthWinter earthWinter : earthWinters) {
            earthWinter.dispose();
        }
        move = Boolean.parseBoolean(null);
    }
}
