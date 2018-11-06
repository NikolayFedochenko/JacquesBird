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

package com.nxgame.jacquesbird.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Neutronx on 17.09.2018.
 *
 */

class StoreBirds {

    StoreBirds(Stage stage, Skin skin) {
        Button.ButtonStyle birdID0 = new Button.ButtonStyle();
        if (Gdx.graphics.getWidth() <= 1279f) {
            birdID0.up = skin.getDrawable("bird_dek");
        }

        if (Gdx.graphics.getWidth() >= 1280f) {
            birdID0.up = skin.getDrawable("bird_dek");
        }

        Button.ButtonStyle birdID1 = new Button.ButtonStyle();
        if (Gdx.graphics.getWidth() <= 1279f) {
            birdID1.up = skin.getDrawable("bird_morg");
        }

        if (Gdx.graphics.getWidth() >= 1280f) {
            birdID1.up = skin.getDrawable("bird_morg");
        }

        Button.ButtonStyle birdID2 = new Button.ButtonStyle();
        if (Gdx.graphics.getWidth() <= 1279f) {
            birdID2.up = skin.getDrawable("bird_saints");
        }

        if (Gdx.graphics.getWidth() >= 1280f) {
            birdID2.up = skin.getDrawable("bird_saints");
        }

        Button.ButtonStyle birdID3 = new Button.ButtonStyle();
        if (Gdx.graphics.getWidth() <= 1279f) {
            birdID3.up = skin.getDrawable("bird_band");
        }

        if (Gdx.graphics.getWidth() >= 1280f) {
            birdID3.up = skin.getDrawable("bird_band");
        }

        Button.ButtonStyle birdID4 = new Button.ButtonStyle();
        if (Gdx.graphics.getWidth() <= 1279f) {
            birdID4.up = skin.getDrawable("bird_cov");
        }

        if (Gdx.graphics.getWidth() >= 1280f) {
            birdID4.up = skin.getDrawable("bird_cov");
        }

        Button.ButtonStyle birdID5 = new Button.ButtonStyle();
        birdID5.up = skin.getDrawable("bird_sc");

        Button[] btnBird = new Button[6];
        for (byte i = 0; i < btnBird.length; i++) {
            btnBird[i] = new Button();
            btnBird[i].setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 8f / 100f);
            switch (i) {
                case 0:
                    btnBird[0].setStyle(birdID0);
                    break;
                case 1:
                    btnBird[1].setStyle(birdID1);
                    break;
                case 2:
                    btnBird[2].setStyle(birdID2);
                    break;
                case 3:
                    btnBird[3].setStyle(birdID3);
                    break;
                case 4:
                    btnBird[4].setStyle(birdID4);
                    break;
                case 5:
                    btnBird[5].setStyle(birdID5);
                    break;
            }
        }

        btnBird[0].setPosition(Gdx.graphics.getWidth() / 2f,
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 19f / 100f), Align.center);
        btnBird[1].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 19f / 100f), Align.center);
        btnBird[2].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 19f / 100f), Align.center);
        btnBird[3].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 30f / 100f, Align.center);
        btnBird[4].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                Gdx.graphics.getHeight() * 30f / 100f, Align.center);
        btnBird[5].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                Gdx.graphics.getHeight() * 30f / 100f, Align.center);

        stage.addActor(btnBird[0]);
        stage.addActor(btnBird[1]);
        stage.addActor(btnBird[2]);
        stage.addActor(btnBird[3]);
        stage.addActor(btnBird[4]);
        stage.addActor(btnBird[5]);
    }

    void dispose() {

    }
}
