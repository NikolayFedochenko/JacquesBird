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

package com.nxgame.jacquesbird.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nxgame.jacquesbird.NXGame;
import com.nxgame.jacquesbird.objects.Bronza;
import com.nxgame.jacquesbird.objects.Coin;
import com.nxgame.jacquesbird.objects.Serebro;
import com.nxgame.jacquesbird.others.LoadResources;
import com.nxgame.jacquesbird.others.StoreButton;

/**
 * Created by Neutronx on 15.09.2018.
 *
 */

public class StoreScreen implements Screen {
    private NXGame game;
    private OrthographicCamera camera;
    private Preferences pref;

    private Coin gold;
    private Bronza bronza;
    private Serebro serebro;

    private Stage stage;
    private StoreButton store;
    private Label[] label;

    private static short[] priceBird;

    public static short[] getPriceBird() {
        return priceBird;
    }

    private static void initPriceBird() {
        priceBird = new short[18];
        priceBird[0] = 0;
        priceBird[1] = 10;
        priceBird[2] = 60;
        priceBird[3] = 5;
        priceBird[4] = 10;
        priceBird[5] = 30;
        priceBird[6] = 10;
        priceBird[7] = 90;
        priceBird[8] = 30;
        priceBird[9] = 50;
        priceBird[10] = 100;
        priceBird[11] = 100;
        priceBird[12] = 50;
        priceBird[13] = 200;
        priceBird[14] = 500;
        priceBird[15] = 200;
        priceBird[16] = 500;
        priceBird[17] = 1000;
    }

    StoreScreen(final NXGame game) {
        this.game = game;

        pref = Gdx.app.getPreferences("JacquesBird");
        if (stage == null) stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800f, 480f);

        gold = new Coin(false);
        serebro = new Serebro();
        bronza = new Bronza();

        Button.ButtonStyle back = new Button.ButtonStyle();
        back.up = LoadResources.getUi().getDrawable("12");
        back.down = LoadResources.getUi().getDrawable("13");

        Button btnBack = new Button(back);
        btnBack.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
        btnBack.setPosition(Gdx.graphics.getWidth() * 3f / 100f, Gdx.graphics.getHeight() * 4f / 100f);

        btnBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        label = new Label[5];
        for (byte i = 0; i < label.length; i++) {
            label[i] = new Label("", LoadResources.getFontSkin().get("green", Label.LabelStyle.class));
        }
        label[0].setText("Средства");
        label[0].setFontScale(Gdx.graphics.getHeight() / 1300f);
        label[0].setPosition(Gdx.graphics.getWidth() * 4f / 100f, Gdx.graphics.getHeight() / 1.9f);

        label[1].setText("" + pref.getInteger("bronza"));
        label[1].setFontScale(Gdx.graphics.getHeight() / 1600f);
        label[1].setPosition(Gdx.graphics.getWidth() * 12f / 100f, Gdx.graphics.getHeight() / 3.15f);

        label[2].setText("" + pref.getInteger("serebro"));
        label[2].setFontScale(Gdx.graphics.getHeight() / 1600f);
        label[2].setPosition(Gdx.graphics.getWidth() * 12f / 100f, Gdx.graphics.getHeight() / 2.6f);

        label[3].setText("" + pref.getInteger("gold"));
        label[3].setFontScale(Gdx.graphics.getHeight() / 1600f);
        label[3].setPosition(Gdx.graphics.getWidth() * 12f / 100f, Gdx.graphics.getHeight() / 2.2f);

        label[4].setText("Магазин Jacques");
        label[4].setAlignment(Align.center);
        label[4].setFontScale(Gdx.graphics.getHeight() / 900f);
        label[4].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 10f / 100f));

        initPriceBird();
        Label[] price = new Label[18];
        for (byte i = 0; i < price.length; i++) {
            price[i] = new Label("" + priceBird[i], LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
            price[i].setFontScale(Gdx.graphics.getHeight() / 2000f);
            price[i].setAlignment(Align.center);
        }

        price[0].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 6.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[1].setPosition(Gdx.graphics.getWidth() / 2f,
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[2].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 6.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[3].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 11f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[4].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[5].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 23.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[6].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 21.8f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[7].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[8].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 9.4f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 5f / 100f), Align.center);
        price[9].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 6.4f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[10].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[11].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 6.4f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[12].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 11f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[13].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 17f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[14].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 23.4f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[15].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 21.8f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[16].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 15.4f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);
        price[17].setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 9.4f / 100f),
                Gdx.graphics.getHeight() * 16f / 100f, Align.center);

        stage.addActor(price[0]);
        stage.addActor(price[1]);
        stage.addActor(price[2]);
        stage.addActor(price[3]);
        stage.addActor(price[4]);
        stage.addActor(price[5]);
        stage.addActor(price[6]);
        stage.addActor(price[7]);
        stage.addActor(price[8]);
        stage.addActor(price[9]);
        stage.addActor(price[10]);
        stage.addActor(price[11]);
        stage.addActor(price[12]);
        stage.addActor(price[13]);
        stage.addActor(price[14]);
        stage.addActor(price[15]);
        stage.addActor(price[16]);
        stage.addActor(price[17]);
        stage.addActor(label[0]);
        stage.addActor(label[1]);
        stage.addActor(label[2]);
        stage.addActor(label[3]);
        stage.addActor(label[4]);
        stage.addActor(btnBack);
        store = new StoreButton(stage, LoadResources.getFontSkin(), pref.getBoolean("sound"));

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(stage);

        for (Label pr : price) {
            pr.clear();
        }
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0.03F, 0.0F, 0.05F, 1.0F);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        game.batch.begin();
        game.background.render(game.batch, 0.3f);
        game.earth.render(game.batch);
        gold.render(game.batch);
        serebro.render(game.batch);
        bronza.render(game.batch);
        game.batch.end();

        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    private void update(float delta) {
        gold.update(delta);
        serebro.update(delta);
        bronza.update(delta);

        label[1].setText(pref.getInteger("bronza") + "");
        label[2].setText(pref.getInteger("serebro") + "");
        label[3].setText(pref.getInteger("gold") + "");

        if (pref.getInteger("gold") > 1000) pref.putInteger("gold", 1000);
        if (pref.getInteger("serebro") > 1000) pref.putInteger("serebro", 1000);
        if (pref.getInteger("bronza") > 1000) pref.putInteger("bronza", 1000);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 800f, 480f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (Label l : label)l.clear();

        gold.dispose();
        serebro.dispose();
        bronza.dispose();

        if (stage != null) stage.dispose();

        store.dispose();
    }
}
