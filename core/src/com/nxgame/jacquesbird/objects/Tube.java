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

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nxgame.jacquesbird.NXGame;

import java.util.Random;

/**
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

public class Tube {
    private static final byte FLUCTUATION = 120;
    private byte TUBE_GAP;
    private static final byte LOWEST_OPENING = 120;

    private Texture topTube, bottomTube;
    private Sprite tTube, bTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop;
    private Rectangle boundsBot;
    private Player player;
    private NXGame game;

    private float speed;
    private boolean scored;
    private byte createMimic;

    private boolean isScored() {
        return scored;
    }

    private void setScored() {
        this.scored = true;
    }

    public Tube(NXGame game, float x, Player player, byte diff) {
        this.player = player;
        this.game = game;

        if ((topTube == null) && (bottomTube == null)) {
            switch (diff) {
                case 1:
                    TUBE_GAP = 90;
                    speed = 180f;
                    break;
                case 2:
                    TUBE_GAP = 75;
                    speed = 160f;
                    break;
                case 3:
                    TUBE_GAP = 65;
                    speed = 140f;
                    break;
                default:
                    TUBE_GAP = 90;
                    speed = 180f;
            }

            topTube = new Texture("objects/toptube.png");
            bottomTube = new Texture("objects/bottomtube.png");
            tTube = new Sprite(topTube);
            bTube = new Sprite(bottomTube);

            rand = new Random();
            scored = false;

            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                posTopTube = new Vector2(x + 500f, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
                posBotTube = new Vector2(x + 500f, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
            }

            if (Gdx.app.getType() == Application.ApplicationType.Android) {
                if (Gdx.graphics.getWidth() <= 1279f) {
                    posTopTube = new Vector2(x + 600f, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
                    posBotTube = new Vector2(x + 600f, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
                }

                if (Gdx.graphics.getWidth() >= 1280f) {
                    posTopTube = new Vector2(x + 800f, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
                    posBotTube = new Vector2(x + 800f, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
                }
            }

            boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
            boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        }
    }

    public void render(SpriteBatch batch) {
        tTube.draw(batch);
        bTube.draw(batch);
    }

    public void update(float delta) {
        posBotTube.x -= speed * delta;
        posTopTube.x -= speed * delta;

        boundsBot.setPosition(posBotTube.x, posBotTube.y);
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        tTube.setPosition(posTopTube.x, posTopTube.y);
        bTube.setPosition(posBotTube.x, posBotTube.y);

        if (!isScored() && boundsTop.getX() + (topTube.getWidth() / 2f) < player.getBounds().getX() + 50f) {
            player.addScore();
            game.label.setText("" + player.getScore());
            setScored();
        }

        if ((posBotTube.x < Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() + 55f)) ||
                (posTopTube.x < Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() + 55f))) {
            reposition();
        }
    }

    private void reposition() {
        posTopTube.set(800f, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(800f, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        scored = false;
        createMimic = (byte) MathUtils.random(0, 127);
    }

    public byte getCreateMimic() {
        return createMimic;
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
        topTube = null;
        bottomTube = null;
        tTube = null;
        bTube = null;
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsBot) || player.overlaps(boundsTop);
    }
}
