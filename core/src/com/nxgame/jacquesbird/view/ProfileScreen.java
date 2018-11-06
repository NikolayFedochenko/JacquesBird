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
import com.nxgame.jacquesbird.others.MyTextInput;

/**
 * Created by Neutronx on 07.09.2018.
 *
 */

class ProfileScreen implements Screen {
    private NXGame game;
    private Stage in;

    private OrthographicCamera camera;
    private Coin gold;
    private Bronza bronza;
    private Serebro serebro;
    private Label[] label;

    private Preferences pref;

    ProfileScreen(final NXGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800f, 480f);

        pref = Gdx.app.getPreferences("JacquesBird");

        gold = new Coin(false);
        serebro = new Serebro();
        bronza = new Bronza();

        Button.ButtonStyle logo = new Button.ButtonStyle();
        if (pref.getString("skin").equals("bird")) logo.up = LoadResources.getResources_store().getDrawable("bird");
        if (pref.getString("skin").equals("bird_00")) logo.up = LoadResources.getResources_store().getDrawable("bird_dek");
        if (pref.getString("skin").equals("67bird0")) logo.up = LoadResources.getResources_store().getDrawable("bird_morg");
        if (pref.getString("skin").equals("e5bird3fd")) logo.up = LoadResources.getResources_store().getDrawable("bird_saints");
        if (pref.getString("skin").equals("bird3dba")) logo.up = LoadResources.getResources_store().getDrawable("bird_band");
        if (pref.getString("skin").equals("bird3o20")) logo.up = LoadResources.getResources_store().getDrawable("bird_cov");
        if (pref.getString("skin").equals("scbird666on")) logo.up = LoadResources.getResources_store().getDrawable("bird_sc");

        Button birdLogo = new Button(logo);
        birdLogo.setSize(Gdx.graphics.getWidth() * 11f / 100f, Gdx.graphics.getHeight() * 14f / 100f);
        birdLogo.setPosition(Gdx.graphics.getWidth() * 8f / 100f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 11f / 100f), Align.center);

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

        label = new Label[9];
        label[0] = new Label(pref.getString("name"), LoadResources.getFontSkin().get("blue", Label.LabelStyle.class));
        label[0].setAlignment(Align.center);
        label[0].setFontScale(Gdx.graphics.getHeight() / 1800f);
        label[0].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 24f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 8f / 100f), Align.center);

        label[1] = new Label("", LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
        label[2] = new Label("", LoadResources.getFontSkin().get("gray", Label.LabelStyle.class));
        label[3] = new Label("", LoadResources.getFontSkin().get("yellow", Label.LabelStyle.class));

        label[1].setText("" + pref.getInteger("bronza"));
        label[1].setFontScale(Gdx.graphics.getHeight() / 1600f);
        label[1].setPosition(Gdx.graphics.getWidth() * 12f / 100f, Gdx.graphics.getHeight() / 3.15f);

        label[2].setText("" + pref.getInteger("serebro"));
        label[2].setFontScale(Gdx.graphics.getHeight() / 1600f);
        label[2].setPosition(Gdx.graphics.getWidth() * 12f / 100f, Gdx.graphics.getHeight() / 2.6f);

        label[3].setText("" + pref.getInteger("gold"));
        label[3].setFontScale(Gdx.graphics.getHeight() / 1600f);
        label[3].setPosition(Gdx.graphics.getWidth() * 12f / 100f, Gdx.graphics.getHeight() / 2.2f);

        label[4] = new Label("Смертей:  " + pref.getInteger("rip"), LoadResources.getFontSkin().get("red", Label.LabelStyle.class));
        label[4].setAlignment(Align.center);
        label[4].setFontScale(Gdx.graphics.getHeight() / 2100f);
        label[4].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 25f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 15f / 100f), Align.center);

        label[5] = new Label("Рекорды", LoadResources.getFontSkin().get("green", Label.LabelStyle.class));
        label[5].setAlignment(Align.center);
        label[5].setFontScale(Gdx.graphics.getHeight() / 1700f);
        label[5].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 24f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 20f / 100f), Align.center);

        label[6] = new Label("Лёгкий уровень: " + pref.getInteger("highScoresLow"),
                LoadResources.getFontSkin().get("yellow", Label.LabelStyle.class));
        label[6].setAlignment(Align.center);
        label[6].setFontScale(Gdx.graphics.getHeight() / 2000f);
        label[6].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 24f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 26f / 100f), Align.center);

        label[7] = new Label("Средний уровень: " + pref.getInteger("highScoresMedium"),
                LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
        label[7].setAlignment(Align.center);
        label[7].setFontScale(Gdx.graphics.getHeight() / 2000f);
        label[7].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 24f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 34f / 100f), Align.center);

        label[8] = new Label("Сложный уровень: " + pref.getInteger("highScoresHard"),
                LoadResources.getFontSkin().get("red", Label.LabelStyle.class));
        label[8].setAlignment(Align.center);
        label[8].setFontScale(Gdx.graphics.getHeight() / 2000f);
        label[8].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 24f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 42f / 100f), Align.center);

        Button.ButtonStyle styleEdit = new Button.ButtonStyle();
        styleEdit.up = LoadResources.getUi().getDrawable("btnEdit_100");
        styleEdit.down = LoadResources.getUi().getDrawable("btnEdit_100_down");

        Button btnEdit = new Button(styleEdit);
        btnEdit.setSize(Gdx.graphics.getWidth() * 5f / 100f, Gdx.graphics.getHeight() * 8f / 100f);
        btnEdit.setPosition((Gdx.graphics.getWidth() / 2f) - (btnEdit.getWidth() + 20f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 8f / 100f), Align.center);

        btnEdit.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                MyTextInput listener = new MyTextInput(in, LoadResources.getFontSkin());
                Gdx.input.getTextInput(listener, "Введите имя", "", "Максимум 13 символов");
            }
        });

        in = new Stage();
        in.addActor(label[0]);
        in.addActor(label[1]);
        in.addActor(label[2]);
        in.addActor(label[3]);
        in.addActor(label[4]);
        in.addActor(label[5]);
        in.addActor(label[6]);
        in.addActor(label[7]);
        in.addActor(label[8]);
        in.addActor(birdLogo);
        in.addActor(btnEdit);
        in.addActor(btnBack);

        Gdx.input.setInputProcessor(in);
        Gdx.input.setCatchBackKey(true);
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

        in.act();
        in.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    private void update(float delta) {
        label[0].setText(pref.getString("name"));

        gold.update(delta);
        serebro.update(delta);
        bronza.update(delta);
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
        label[0].clear();
        label[1].clear();
        label[2].clear();
        label[3].clear();
        label[4].clear();
        label[5].clear();
        label[6].clear();
        label[7].clear();
        label[8].clear();
        gold.dispose();
        serebro.dispose();
        bronza.dispose();
        in.dispose();
    }
}
