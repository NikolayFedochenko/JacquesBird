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

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nxgame.jacquesbird.media.Media;
import com.nxgame.jacquesbird.view.StoreScreen;

public class StoreButton extends ScreenAdapter {
    private Preferences pref;
    private Button[] btnBuy;
    private Button[] btnSelect;

    private boolean[] activeBtnBuy;
    private boolean[] activeBtnSel;

    private StoreBirds storeBirds;

    public StoreButton(final Stage stage, final Skin fontSkin, final boolean sound) {
        pref = Gdx.app.getPreferences("JacquesBird");

        activeBtnBuy = new boolean[6];
        for (byte i = 0; i < activeBtnBuy.length; i++) {
            activeBtnBuy[i] = true;
        }

        activeBtnSel = new boolean[6];
        for (byte i = 0; i < activeBtnSel.length; i++) {
            activeBtnSel[i] = false;
        }

        storeBirds = new StoreBirds(stage, LoadResources.getResources_store());
        new StoreMoney(stage, LoadResources.getResources_store());

        Button.ButtonStyle styleBuy = new Button.ButtonStyle();
        styleBuy.up = LoadResources.getResources_store().getDrawable("btnBuy_100");
        styleBuy.down = LoadResources.getResources_store().getDrawable("btnBuy_down_100");

        Button.ButtonStyle styleSelect = new Button.ButtonStyle();
        styleSelect.up = LoadResources.getResources_store().getDrawable("btnSelectFalse_100");
        styleSelect.down = LoadResources.getResources_store().getDrawable("btnSelectFalse_down_100");
        styleSelect.checked = LoadResources.getResources_store().getDrawable("btnSelectTrue_100");

        btnBuy = new Button[6];
        for (byte i = 0; i < btnBuy.length; i++) {
            btnBuy[i] = new Button(styleBuy);
            btnBuy[i].setSize(Gdx.graphics.getWidth() * 15 / 100, Gdx.graphics.getHeight() * 7f / 100f);
        }

        btnBuy[0].setPosition(Gdx.graphics.getWidth() / 2f,
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 2f / 100f), Align.center);
        btnBuy[1].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 2f / 100f), Align.center);
        btnBuy[2].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 2f / 100f), Align.center);
        btnBuy[3].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 9f / 100f, Align.center);
        btnBuy[4].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                Gdx.graphics.getHeight() * 9f / 100f, Align.center);
        btnBuy[5].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                Gdx.graphics.getHeight() * 9f / 100f, Align.center);

        btnSelect = new Button[6];
        for (byte i = 0; i < btnSelect.length; i++) {
            btnSelect[i] = new Button(styleSelect);
            btnSelect[i].setSize(Gdx.graphics.getWidth() * 15 / 100, Gdx.graphics.getHeight() * 7f / 100f);
        }

        btnSelect[0].setPosition(Gdx.graphics.getWidth() / 2f,
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 2f / 100f), Align.center);
        btnSelect[1].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 2f / 100f), Align.center);
        btnSelect[2].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 2f / 100f), Align.center);
        btnSelect[3].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 9f / 100f, Align.center);
        btnSelect[4].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                Gdx.graphics.getHeight() * 9f / 100f, Align.center);
        btnSelect[5].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                Gdx.graphics.getHeight() * 9f / 100f, Align.center);

        for (Button aBtnSelect : btnSelect) {
            aBtnSelect.setVisible(false);
        }

        final MyDialog[] dialog = new MyDialog[2];
        dialog[0] = new MyDialog("Магазин Jacques\n", fontSkin);
        dialog[1] = new MyDialog("Магазин Jacques\n", fontSkin);
        if (Gdx.graphics.getWidth() <= 1279f) {
            dialog[0].scaleBy(-0.6f);
            dialog[1].scaleBy(-0.6f);
        }
        if (Gdx.graphics.getWidth() >= 1280f) {
            dialog[0].scaleBy(0.1f);
            dialog[1].scaleBy(0.1f);
        }
        dialog[0].text("Успешная покупка!", fontSkin.get("green", Label.LabelStyle.class)).align(Align.center);
        dialog[1].text("Недостаточно монет!", fontSkin.get("red", Label.LabelStyle.class)).align(Align.center);
        dialog[0].button("ok", Align.center).align(Align.center);
        dialog[1].button("ok", Align.center).align(Align.center);

        btnBuy[0].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnBuy[0]) {
                    if ((pref.getInteger("serebro") >= StoreScreen.getPriceBird()[1]) &&
                            (pref.getInteger("bronza") >= StoreScreen.getPriceBird()[2])) {

                        int ser = pref.getInteger("serebro") - StoreScreen.getPriceBird()[1];
                        int bro = pref.getInteger("bronza") - StoreScreen.getPriceBird()[2];

                        pref.putInteger("serebro", ser);
                        pref.putInteger("bronza", bro);
                        pref.putBoolean("itemID0", true);
                        pref.flush();
                        if (sound) Media.playBuy();
                        dialog[0].show(stage);
                    } else {
                        if (sound) Media.playErrorBuy();
                        dialog[1].show(stage);
                    }
                    load();
                }
            }
        });

        btnBuy[1].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnBuy[1]) {
                    if ((pref.getInteger("serebro") >= StoreScreen.getPriceBird()[4]) &&
                            (pref.getInteger("bronza") >= StoreScreen.getPriceBird()[5]) &&
                            (pref.getInteger("gold") >= StoreScreen.getPriceBird()[3])) {

                        int gol = pref.getInteger("gold") - StoreScreen.getPriceBird()[3];
                        int ser = pref.getInteger("serebro") - StoreScreen.getPriceBird()[4];
                        int bro = pref.getInteger("bronza") - StoreScreen.getPriceBird()[5];

                        pref.putInteger("gold", gol);
                        pref.putInteger("serebro", ser);
                        pref.putInteger("bronza", bro);
                        pref.putBoolean("itemID1", true);
                        pref.flush();
                        if (sound) Media.playBuy();
                        dialog[0].show(stage);
                    } else {
                        if (sound)  Media.playErrorBuy();
                        dialog[1].show(stage);
                    }
                    load();
                }
            }
        });

        btnBuy[2].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnBuy[2]) {
                    if ((pref.getInteger("gold") >= StoreScreen.getPriceBird()[6]) &&
                            (pref.getInteger("serebro") >= StoreScreen.getPriceBird()[7]) &&
                            (pref.getInteger("bronza") >= StoreScreen.getPriceBird()[8])) {

                        int gol = pref.getInteger("gold") - StoreScreen.getPriceBird()[6];
                        int ser = pref.getInteger("serebro") - StoreScreen.getPriceBird()[7];
                        int bro = pref.getInteger("bronza") - StoreScreen.getPriceBird()[8];

                        pref.putInteger("gold", gol);
                        pref.putInteger("serebro", ser);
                        pref.putInteger("bronza", bro);
                        pref.putBoolean("itemID2", true);
                        pref.flush();
                        if (sound) Media.playBuy();
                        dialog[0].show(stage);
                    } else {
                        if (sound) Media.playErrorBuy();
                        dialog[1].show(stage);
                    }
                    load();
                }
            }
        });

        btnBuy[3].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnBuy[3]) {
                    if ((pref.getInteger("gold") >= StoreScreen.getPriceBird()[9]) &&
                            (pref.getInteger("serebro") >= StoreScreen.getPriceBird()[10]) &&
                            (pref.getInteger("bronza") >= StoreScreen.getPriceBird()[11])) {

                        int gol = pref.getInteger("gold") - StoreScreen.getPriceBird()[9];
                        int ser = pref.getInteger("serebro") - StoreScreen.getPriceBird()[10];
                        int bro = pref.getInteger("bronza") - StoreScreen.getPriceBird()[11];

                        pref.putInteger("gold", gol);
                        pref.putInteger("serebro", ser);
                        pref.putInteger("bronza", bro);
                        pref.putBoolean("itemID3", true);
                        pref.flush();
                        if (sound) Media.playBuy();
                        dialog[0].show(stage);
                    } else {
                        if (sound) Media.playErrorBuy();
                        dialog[1].show(stage);
                    }
                    load();
                }
            }
        });

        btnBuy[4].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnBuy[4]) {
                    if ((pref.getInteger("gold") >= StoreScreen.getPriceBird()[12]) &&
                            (pref.getInteger("serebro") >= StoreScreen.getPriceBird()[13]) &&
                            (pref.getInteger("bronza") >= StoreScreen.getPriceBird()[14])) {

                        int gol = pref.getInteger("gold") - StoreScreen.getPriceBird()[12];
                        int ser = pref.getInteger("serebro") - StoreScreen.getPriceBird()[13];
                        int bro = pref.getInteger("bronza") - StoreScreen.getPriceBird()[14];

                        pref.putInteger("gold", gol);
                        pref.putInteger("serebro", ser);
                        pref.putInteger("bronza", bro);
                        pref.putBoolean("itemID4", true);
                        pref.flush();
                        if (sound) Media.playBuy();
                        dialog[0].show(stage);
                    } else {
                        if (sound) Media.playErrorBuy();
                        dialog[1].show(stage);
                    }
                    load();
                }
            }
        });

        btnBuy[5].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnBuy[5]) {
                    if ((pref.getInteger("gold") >= StoreScreen.getPriceBird()[15]) &&
                            (pref.getInteger("serebro") >= StoreScreen.getPriceBird()[16]) &&
                            (pref.getInteger("bronza") >= StoreScreen.getPriceBird()[17])) {

                        int gol = pref.getInteger("gold") - StoreScreen.getPriceBird()[15];
                        int ser = pref.getInteger("serebro") - StoreScreen.getPriceBird()[16];
                        int bro = pref.getInteger("bronza") - StoreScreen.getPriceBird()[17];

                        pref.putInteger("gold", gol);
                        pref.putInteger("serebro", ser);
                        pref.putInteger("bronza", bro);
                        pref.putBoolean("itemID5", true);
                        pref.flush();
                        if (sound) Media.playBuy();
                        dialog[0].show(stage);
                    } else {
                        if (sound) Media.playErrorBuy();
                        dialog[1].show(stage);
                    }
                    load();
                }
            }
        });

        btnSelect[0].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnSel[0]) {
                    if (btnSelect[0].isChecked()) {
                        pref.putString("skin", "bird_00");
                        pref.putBoolean("itemIDisChecked[0]", true);
                        pref.putBoolean("itemIDisChecked[1]", false);
                        pref.putBoolean("itemIDisChecked[2]", false);
                        pref.putBoolean("itemIDisChecked[3]", false);
                        pref.putBoolean("itemIDisChecked[4]", false);
                        pref.putBoolean("itemIDisChecked[5]", false);
                    } else {
                        pref.putString("skin", "bird");
                        pref.putBoolean("itemIDisChecked[0]", false);
                    }
                    pref.flush();
                    load();
                    LoadResources.updatePlayer();
                }
            }
        });

        btnSelect[1].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnSel[1]) {
                    if (btnSelect[1].isChecked()) {
                        pref.putString("skin", "67bird0");
                        pref.putBoolean("itemIDisChecked[0]", false);
                        pref.putBoolean("itemIDisChecked[1]", true);
                        pref.putBoolean("itemIDisChecked[2]", false);
                        pref.putBoolean("itemIDisChecked[3]", false);
                        pref.putBoolean("itemIDisChecked[4]", false);
                        pref.putBoolean("itemIDisChecked[5]", false);
                        System.out.println("bird_morg - true");
                    } else {
                        pref.putString("skin", "bird");
                        pref.putBoolean("itemIDisChecked[1]", false);
                    }
                    pref.flush();
                    load();
                    LoadResources.updatePlayer();
                }
            }
        });

        btnSelect[2].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnSel[2]) {
                    if (btnSelect[2].isChecked()) {
                        pref.putString("skin", "e5bird3fd");
                        pref.putBoolean("itemIDisChecked[0]", false);
                        pref.putBoolean("itemIDisChecked[1]", false);
                        pref.putBoolean("itemIDisChecked[2]", true);
                        pref.putBoolean("itemIDisChecked[3]", false);
                        pref.putBoolean("itemIDisChecked[4]", false);
                        pref.putBoolean("itemIDisChecked[5]", false);
                        System.out.println("bird_saints - true");
                    } else {
                        pref.putString("skin", "bird");
                        pref.putBoolean("itemIDisChecked[2]", false);
                    }
                    pref.flush();
                    load();
                    LoadResources.updatePlayer();
                }
            }
        });

        btnSelect[3].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnSel[3]) {
                    if (btnSelect[3].isChecked()) {
                        pref.putString("skin", "bird3dba");
                        pref.putBoolean("itemIDisChecked[0]", false);
                        pref.putBoolean("itemIDisChecked[1]", false);
                        pref.putBoolean("itemIDisChecked[2]", false);
                        pref.putBoolean("itemIDisChecked[3]", true);
                        pref.putBoolean("itemIDisChecked[4]", false);
                        pref.putBoolean("itemIDisChecked[5]", false);
                    } else {
                        pref.putString("skin", "bird");
                        pref.putBoolean("itemIDisChecked[3]", false);
                    }
                    pref.flush();
                    load();
                    LoadResources.updatePlayer();
                }
            }
        });

        btnSelect[4].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnSel[4]) {
                    if (btnSelect[4].isChecked()) {
                        pref.putString("skin", "bird3o20");
                        pref.putBoolean("itemIDisChecked[0]", false);
                        pref.putBoolean("itemIDisChecked[1]", false);
                        pref.putBoolean("itemIDisChecked[2]", false);
                        pref.putBoolean("itemIDisChecked[3]", false);
                        pref.putBoolean("itemIDisChecked[4]", true);
                        pref.putBoolean("itemIDisChecked[5]", false);
                    } else {
                        pref.putString("skin", "bird");
                        pref.putBoolean("itemIDisChecked[4]", false);
                    }
                    pref.flush();
                    load();
                    LoadResources.updatePlayer();
                }
            }
        });

        btnSelect[5].addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtnSel[5]) {
                    if (btnSelect[5].isChecked()) {
                        pref.putString("skin", "scbird666on");
                        pref.putBoolean("itemIDisChecked[0]", false);
                        pref.putBoolean("itemIDisChecked[1]", false);
                        pref.putBoolean("itemIDisChecked[2]", false);
                        pref.putBoolean("itemIDisChecked[3]", false);
                        pref.putBoolean("itemIDisChecked[4]", false);
                        pref.putBoolean("itemIDisChecked[5]", true);
                    } else {
                        pref.putString("skin", "bird");
                        pref.putBoolean("itemIDisChecked[5]", false);
                    }
                    pref.flush();
                    load();
                    LoadResources.updatePlayer();
                }
            }
        });

        stage.addActor(btnBuy[0]);
        stage.addActor(btnBuy[1]);
        stage.addActor(btnBuy[2]);
        stage.addActor(btnBuy[3]);
        stage.addActor(btnBuy[4]);
        stage.addActor(btnBuy[5]);
        stage.addActor(btnSelect[0]);
        stage.addActor(btnSelect[1]);
        stage.addActor(btnSelect[2]);
        stage.addActor(btnSelect[3]);
        stage.addActor(btnSelect[4]);
        stage.addActor(btnSelect[5]);

        load();
    }

    private void load() {
        if (pref.getBoolean("itemID0")) {
            btnBuy[0].setVisible(false);
            btnSelect[0].setVisible(true);
            activeBtnBuy[0] = false;
            activeBtnSel[0] = true;

            if (pref.getBoolean("itemIDisChecked[0]")) {
                btnSelect[0].setChecked(true);
                btnSelect[1].setChecked(false);
                btnSelect[2].setChecked(false);
                btnSelect[3].setChecked(false);
                btnSelect[4].setChecked(false);
                btnSelect[5].setChecked(false);
            } else {
                btnSelect[0].setChecked(false);
            }
        }

        if (pref.getBoolean("itemID1")) {
            btnBuy[1].setVisible(false);
            btnSelect[1].setVisible(true);
            activeBtnBuy[1] = false;
            activeBtnSel[1] = true;

            if (pref.getBoolean("itemIDisChecked[1]")) {
                btnSelect[0].setChecked(false);
                btnSelect[1].setChecked(true);
                btnSelect[2].setChecked(false);
                btnSelect[3].setChecked(false);
                btnSelect[4].setChecked(false);
                btnSelect[5].setChecked(false);
            } else {
                btnSelect[1].setChecked(false);
            }
        }

        if (pref.getBoolean("itemID2")) {
            btnBuy[2].setVisible(false);
            btnSelect[2].setVisible(true);
            activeBtnBuy[2] = false;
            activeBtnSel[2] = true;

            if (pref.getBoolean("itemIDisChecked[2]")) {
                btnSelect[0].setChecked(false);
                btnSelect[1].setChecked(false);
                btnSelect[2].setChecked(true);
                btnSelect[3].setChecked(false);
                btnSelect[4].setChecked(false);
                btnSelect[5].setChecked(false);
            } else {
                btnSelect[2].setChecked(false);
            }
        }

        if (pref.getBoolean("itemID3")) {
            btnBuy[3].setVisible(false);
            btnSelect[3].setVisible(true);
            activeBtnBuy[3] = false;
            activeBtnSel[3] = true;

            if (pref.getBoolean("itemIDisChecked[3]")) {
                btnSelect[0].setChecked(false);
                btnSelect[1].setChecked(false);
                btnSelect[2].setChecked(false);
                btnSelect[3].setChecked(true);
                btnSelect[4].setChecked(false);
                btnSelect[5].setChecked(false);
            } else {
                btnSelect[3].setChecked(false);
            }
        }

        if (pref.getBoolean("itemID4")) {
            btnBuy[4].setVisible(false);
            btnSelect[4].setVisible(true);
            activeBtnBuy[4] = false;
            activeBtnSel[4] = true;

            if (pref.getBoolean("itemIDisChecked[4]")) {
                btnSelect[0].setChecked(false);
                btnSelect[1].setChecked(false);
                btnSelect[2].setChecked(false);
                btnSelect[3].setChecked(false);
                btnSelect[4].setChecked(true);
                btnSelect[5].setChecked(false);
            } else {
                btnSelect[4].setChecked(false);
            }
        }

        if (pref.getBoolean("itemID5")) {
            btnBuy[5].setVisible(false);
            btnSelect[5].setVisible(true);
            activeBtnBuy[5] = false;
            activeBtnSel[5] = true;

            if (pref.getBoolean("itemIDisChecked[5]")) {
                btnSelect[0].setChecked(false);
                btnSelect[1].setChecked(false);
                btnSelect[2].setChecked(false);
                btnSelect[3].setChecked(false);
                btnSelect[4].setChecked(false);
                btnSelect[5].setChecked(true);
            } else {
                btnSelect[5].setChecked(false);
            }
        }
        pref.flush();
    }

    public void dispose() {
        storeBirds.dispose();
    }
}
