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
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nxgame.jacquesbird.NXGame;
import com.nxgame.jacquesbird.media.Media;
import com.nxgame.jacquesbird.objects.Bird;
import com.nxgame.jacquesbird.others.LoadResources;

/**
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

public class MenuScreen implements Screen {
    private NXGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private Bird bird;
    private boolean render;

    public MenuScreen(final NXGame game) {
        this.game = game;
        render = true;

        Preferences pref = Gdx.app.getPreferences("JacquesBird");
        if (pref.getBoolean("music")) Media.playMusic(true);

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800f, 480f);

        game.background.setMove(false);
        game.earth.setMove(false);
        game.earth.setAlpha(1f);

        bird = new Bird();

        Button.ButtonStyle play = new Button.ButtonStyle();
        play.up = LoadResources.getUi().getDrawable("00");
        play.down = LoadResources.getUi().getDrawable("01");

        Button btnPlay = new Button(play);
        btnPlay.setSize(Gdx.graphics.getWidth() * 25 / 100, Gdx.graphics.getHeight() * 18f / 100f);
        btnPlay.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 10 / 100, Align.center);

        Button.ButtonStyle setting = new Button.ButtonStyle();
        setting.up = LoadResources.getUi().getDrawable("02");
        setting.down = LoadResources.getUi().getDrawable("03");

        Button btnSetting = new Button(setting);
        btnSetting.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 12f / 100f);
        btnSetting.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 4f / 100f), Gdx.graphics.getHeight() * 7 / 100, Align.center);

        Button.ButtonStyle about = new Button.ButtonStyle();
        about.up = LoadResources.getUi().getDrawable("04");
        about.down = LoadResources.getUi().getDrawable("05");

        Button btnAbout = new Button(about);
        btnAbout.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 12f / 100f);
        btnAbout.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 12f / 100f), Gdx.graphics.getHeight() * 7f / 100, Align.center);

        Button.ButtonStyle exit = new Button.ButtonStyle();
        exit.up = LoadResources.getUi().getDrawable("06");
        exit.down = LoadResources.getUi().getDrawable("07");

        Button btnExit = new Button(exit);
        btnExit.setSize(Gdx.graphics.getWidth() * 8f / 100f, Gdx.graphics.getHeight() * 12f / 100f);
        btnExit.setPosition(Gdx.graphics.getWidth() * 5f / 100f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 7f / 100f), Align.center);

        Button.ButtonStyle styleProfile = new Button.ButtonStyle();
        styleProfile.up = LoadResources.getUi().getDrawable("08");
        styleProfile.down = LoadResources.getUi().getDrawable("09");

        Button btnProfile = new Button(styleProfile);
        btnProfile.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
        btnProfile.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 4f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 7f / 100f), Align.center);

        Button.ButtonStyle styleStore = new Button.ButtonStyle();
        styleStore.up = LoadResources.getUi().getDrawable("10");
        styleStore.down = LoadResources.getUi().getDrawable("11");

        Button btnStore = new Button(styleStore);
        btnStore.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
        btnStore.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 12f / 100f),
                Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 7f / 100f), Align.center);

        btnProfile.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new ProfileScreen(game));
                dispose();
            }
        });

        btnStore.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new StoreScreen(game));
                dispose();
            }
        });

        btnPlay.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        btnSetting.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new SettingScreen(game));
                dispose();
            }
        });

        btnAbout.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new AboutScreen(game));
                dispose();
            }
        });

        btnExit.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                dispose();
                Gdx.app.exit();
            }
        });

        stage.addActor(btnProfile);
        stage.addActor(btnStore);
        stage.addActor(btnPlay);
        stage.addActor(btnSetting);
        stage.addActor(btnAbout);
        stage.addActor(btnExit);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    }

    @Override
    public void render(float delta) {
        if (render) {
            update(delta);
            camera.update();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();
            game.background.render(game.batch, 1f);
            game.earth.render(game.batch);
            bird.render(game.batch);
            game.batch.end();

            stage.act();
            stage.draw();
        }
    }

    private void update(float delta) {
        bird.update(delta);
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
        render = false;
        camera = null;
        bird.dispose();
        stage.dispose();
    }
}
