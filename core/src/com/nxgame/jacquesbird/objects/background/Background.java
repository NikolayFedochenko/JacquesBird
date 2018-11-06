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
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

public class Background {
    private boolean move;

    private class Backgrounds {
        private Sprite sprite;
        private Vector2 position;

        Backgrounds(float x) {
            if (LoadResources.getBackground() != null) {
                LoadResources.getBackground().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

                if (sprite == null) sprite = new Sprite(LoadResources.getBackground());
                sprite.setSize(800f, 480f);
                if (position == null) position = new Vector2(x, 0f);
            }
        }

        void render(SpriteBatch batch, float alpha) {
            sprite.draw(batch, alpha);
        }

        void update(float delta) {
            if (isMove()) position.x -= 120f * delta;
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

    private Backgrounds[] backgroundses;

    public Background() {
        backgroundses = new Backgrounds[2];
        backgroundses[0] = new Backgrounds(0f);
        backgroundses[1] = new Backgrounds(800f);
    }

    public void render(SpriteBatch batch, float alpha) {
        for (Backgrounds backgrounds : backgroundses) {
            backgrounds.render(batch, alpha);
        }
    }

    private boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void update(float delta) {
        if (isMove()) {
            backgroundses[0].update(delta);
            backgroundses[1].update(delta);

            if (backgroundses[0].getPosition().x < -800f) {
                backgroundses[0].reposition(0f);
                backgroundses[1].reposition(800f);
            }
        }
    }

    public void dispose() {
        for (Backgrounds backgrounds : backgroundses) {
            backgrounds.dispose();
        }
        move = Boolean.parseBoolean(null);
    }
}
