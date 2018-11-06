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

class StoreMoney {
    StoreMoney(Stage stage, Skin skin) {
        Button.ButtonStyle gold = new Button.ButtonStyle();
        gold.up = skin.getDrawable("coin_gold");

        Button.ButtonStyle serebro = new Button.ButtonStyle();
        serebro.up = skin.getDrawable("coin_serebro");

        Button.ButtonStyle bronza = new Button.ButtonStyle();
        bronza.up = skin.getDrawable("coin_bronza");

        Button[] coinID = new Button[18];
        for (byte i = 0; i < coinID.length; i++) {
            coinID[i] = new Button();
            if (Gdx.graphics.getWidth() <= 1270f) coinID[i].setSize(Gdx.graphics.getWidth() * 3f / 100f, Gdx.graphics.getHeight() * 4f / 100f);
            if (Gdx.graphics.getWidth() >= 1280f) coinID[i].setSize(Gdx.graphics.getWidth() * 2.8f / 100f, Gdx.graphics.getHeight() * 4f / 100f);

            switch (i) {
                case 0:
                case 3:
                case 6:
                case 9:
                case 12:
                case 15:
                    coinID[i].setStyle(gold);
                    break;
                case 1:
                case 4:
                case 7:
                case 10:
                case 13:
                case 16:
                    coinID[i].setStyle(serebro);
                    break;
                case 2:
                case 5:
                case 8:
                case 11:
                case 14:
                case 17:
                    coinID[i].setStyle(bronza);
                    break;
            }
        }

        coinID[0].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 6.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[1].setPosition(Gdx.graphics.getWidth() / 2f,
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[2].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 6.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[3].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 11f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[4].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[5].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 23.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[6].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 21.8f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[7].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[8].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 9.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 10f / 100f), Align.center);
        coinID[9].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 6.4f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[10].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[11].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 6.4f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[12].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 11f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[13].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[14].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 23.4f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[15].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 21.8f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[16].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);
        coinID[17].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 9.4f / 100f),
                Gdx.graphics.getHeight() * 21f / 100f, Align.center);

        stage.addActor(coinID[0]);
        stage.addActor(coinID[1]);
        stage.addActor(coinID[2]);
        stage.addActor(coinID[3]);
        stage.addActor(coinID[4]);
        stage.addActor(coinID[5]);
        stage.addActor(coinID[6]);
        stage.addActor(coinID[7]);
        stage.addActor(coinID[8]);
        stage.addActor(coinID[9]);
        stage.addActor(coinID[10]);
        stage.addActor(coinID[11]);
        stage.addActor(coinID[12]);
        stage.addActor(coinID[13]);
        stage.addActor(coinID[14]);
        stage.addActor(coinID[15]);
        stage.addActor(coinID[16]);
        stage.addActor(coinID[17]);
    }
}
