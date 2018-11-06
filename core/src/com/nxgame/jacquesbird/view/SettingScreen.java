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
import com.nxgame.jacquesbird.media.Media;
import com.nxgame.jacquesbird.others.LoadResources;

import static com.badlogic.gdx.Gdx.input;

/**
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

class SettingScreen implements Screen {
    private NXGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private Preferences pref;
    private Button chMusic, chSound, low, medium, hard;
    private Button chControlsClassic;
    private Button chControlsButtons;
    private Label[] label;

    SettingScreen(final NXGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800f, 480f);

        stage = new Stage();
        pref = Gdx.app.getPreferences("JacquesBird");

        label = new Label[10];
        label[0] = new Label("Настройки", LoadResources.getFontSkin().get("blue", Label.LabelStyle.class));
        label[0].setAlignment(Align.center);
        label[0].setFontScale(Gdx.graphics.getHeight() / 900f);
        label[0].setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 10f / 100f), Align.center);

        label[1] = new Label("Музыка", LoadResources.getFontSkin().get("whiteBlue", Label.LabelStyle.class));
        label[1].setAlignment(Align.center);
        label[1].setFontScale(Gdx.graphics.getHeight() / 1200f);
        label[1].setPosition(Gdx.graphics.getWidth() * 12f / 100f,
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 18f / 100f), Align.center);

        label[2] = new Label("Звуки", LoadResources.getFontSkin().get("whiteBlue", Label.LabelStyle.class));
        label[2].setAlignment(Align.center);
        label[2].setFontScale(Gdx.graphics.getHeight() / 1200f);
        label[2].setPosition(Gdx.graphics.getWidth() * 12f / 100f,
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 6f / 100f), Align.center);

        label[3] = new Label("Сложность", LoadResources.getFontSkin().get("green", Label.LabelStyle.class));
        label[3].setAlignment(Align.center);
        label[3].setFontScale(Gdx.graphics.getHeight() / 1100f);
        label[3].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 18f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 18f / 100f), Align.center);

        label[4] = new Label("Легко", LoadResources.getFontSkin().get("yellow", Label.LabelStyle.class));
        label[4].setAlignment(Align.center);
        label[4].setFontScale(Gdx.graphics.getHeight() / 1100f);
        label[4].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 25f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 8f / 100f), Align.center);

        label[5] = new Label("Средне", LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
        label[5].setAlignment(Align.center);
        label[5].setFontScale(Gdx.graphics.getHeight() / 1100f);
        label[5].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 25f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 4f / 100f), Align.center);

        label[6] = new Label("Сложно", LoadResources.getFontSkin().get("red", Label.LabelStyle.class));
        label[6].setAlignment(Align.center);
        label[6].setFontScale(Gdx.graphics.getHeight() / 1100f);
        label[6].setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 25f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 16f / 100f), Align.center);

        label[7] = new Label("Управление", LoadResources.getFontSkin().get("green", Label.LabelStyle.class));
        label[7].setAlignment(Align.center);
        label[7].setFontScale(Gdx.graphics.getHeight() / 1100f);
        label[7].setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 9f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 12f / 100f), Align.center);

        label[8] = new Label("Классическое", LoadResources.getFontSkin().get("yellow", Label.LabelStyle.class));
        label[8].setAlignment(Align.center);
        label[8].setFontScale(Gdx.graphics.getHeight() / 1400f);
        label[8].setPosition(Gdx.graphics.getWidth() * 15f / 100f,
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 24f / 100f), Align.center);

        label[9] = new Label("Кнопочное", LoadResources.getFontSkin().get("yellow", Label.LabelStyle.class));
        label[9].setAlignment(Align.center);
        label[9].setFontScale(Gdx.graphics.getHeight() / 1400f);
        label[9].setPosition((Gdx.graphics.getWidth() / 2f) - Gdx.graphics.getWidth() * 5f / 100f,
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 24f / 100f), Align.center);

        Button.ButtonStyle back = new Button.ButtonStyle();
        back.up = LoadResources.getUi().getDrawable("12");
        back.down = LoadResources.getUi().getDrawable("13");

        Button btnBack = new Button(back);
        btnBack.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
        btnBack.setPosition(Gdx.graphics.getWidth() * 3f / 100f, Gdx.graphics.getHeight() * 4f / 100f);

        btnBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android) input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        Button.ButtonStyle btnStyle = new Button.ButtonStyle();
        btnStyle.checked = LoadResources.getUi().getDrawable("checkOn");
        btnStyle.up = LoadResources.getUi().getDrawable("checkOff");

        chControlsClassic = new Button(btnStyle);
        chControlsClassic.setSize(Gdx.graphics.getWidth() * 5.3f / 100f, Gdx.graphics.getHeight() * 9f / 100f);
        chControlsClassic.setPosition((Gdx.graphics.getWidth() / 2f) - Gdx.graphics.getWidth() * 18f / 100f,
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 24f / 100f), Align.center);

        chControlsClassic.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                chControlsClassic.setChecked(true);
                pref.putString("controls", "classic");
                pref.flush();
                updateButtons();
            }
        });

        chControlsButtons = new Button(btnStyle);
        chControlsButtons.setSize(Gdx.graphics.getWidth() * 5.3f / 100f, Gdx.graphics.getHeight() * 9f / 100f);
        chControlsButtons.setPosition((Gdx.graphics.getWidth() / 2f) + (Gdx.graphics.getWidth() * 9f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 24f / 100f), Align.center);

        chControlsButtons.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                pref.putString("controls", "buttons");
                pref.flush();
                updateButtons();
            }
        });

        chMusic = new Button(btnStyle);
        chMusic.setSize(Gdx.graphics.getWidth() * 5.3f / 100f, Gdx.graphics.getHeight() * 9f / 100f);
        chMusic.setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 18 / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 18f / 100f), Align.center);


        chMusic.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (chMusic.isChecked()) {
                    Media.playMusic(true);
                    pref.putBoolean("music", true);
                } else {
                    Media.playMusic(false);
                    pref.putBoolean("music", false);
                }
                pref.flush();
            }
        });

        chSound = new Button(btnStyle);
        chSound.setSize(Gdx.graphics.getWidth() * 5.3f / 100f, Gdx.graphics.getHeight() * 9f / 100f);
        chSound.setPosition((Gdx.graphics.getWidth() / 2f) - (Gdx.graphics.getWidth() * 18 / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 6f / 100f), Align.center);

        chSound.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (chSound.isChecked()) pref.putBoolean("sound", true);
                else pref.putBoolean("sound", false);
                pref.flush();
            }
        });

        low = new Button(btnStyle);
        low.setSize(Gdx.graphics.getWidth() * 6.3f / 100f, Gdx.graphics.getHeight() * 10f / 100f);
        low.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 7f / 100f),
                (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 8f / 100f), Align.center);

        medium = new Button(btnStyle);
        medium.setSize(Gdx.graphics.getWidth() * 6.3f / 100f, Gdx.graphics.getHeight() * 10f / 100f);
        medium.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 7f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 4f / 100f), Align.center);

        hard = new Button(btnStyle);
        hard.setSize(Gdx.graphics.getWidth() * 6.3f / 100f, Gdx.graphics.getHeight() * 10f / 100f);
        hard.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 7f / 100f),
                (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 16f / 100f), Align.center);

        low.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                pref.putInteger("diff", 1);
                setDiff((byte) 1);
            }
        });

        medium.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                pref.putInteger("diff", 2);
                setDiff((byte) 2);
            }
        });

        hard.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                pref.putInteger("diff", 3);
                setDiff((byte) 3);
            }
        });

        stage.addActor(label[0]);
        stage.addActor(label[1]);
        stage.addActor(label[2]);
        stage.addActor(label[3]);
        stage.addActor(label[4]);
        stage.addActor(label[5]);
        stage.addActor(label[6]);
        stage.addActor(label[7]);
        stage.addActor(label[8]);
        stage.addActor(label[9]);
        stage.addActor(low);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(btnBack);
        stage.addActor(chMusic);
        stage.addActor(chSound);
        stage.addActor(chControlsClassic);
        stage.addActor(chControlsButtons);

        input.setInputProcessor(stage);
        input.setCatchBackKey(true);
        loadData();
    }

    private void saveData() {
        pref.flush();
    }

    private void updateButtons() {
        if (pref.getString("controls").equals("classic")) {
            chControlsClassic.setChecked(true);
            chControlsClassic.setDisabled(true);
            chControlsButtons.setChecked(false);
            chControlsButtons.setDisabled(false);
        } else {
            chControlsClassic.setChecked(false);
            chControlsClassic.setDisabled(false);
            chControlsButtons.setChecked(true);
            chControlsButtons.setDisabled(true);
        }
    }

    private void loadData() {
        setDiff((byte) pref.getInteger("diff"));

        if (pref.getBoolean("sound")) chSound.setChecked(true);
        else chSound.setChecked(false);
        if (pref.getBoolean("music")) chMusic.setChecked(true);
        else chMusic.setChecked(false);
        updateButtons();
    }

    private void setDiff(byte diff) {
        switch (diff) {
            case 1:
                low.setChecked(true);
                low.setDisabled(true);
                medium.setChecked(false);
                medium.setDisabled(false);
                hard.setChecked(false);
                hard.setDisabled(false);
                break;
            case 2:
                low.setChecked(false);
                low.setDisabled(false);
                medium.setChecked(true);
                medium.setDisabled(true);
                hard.setChecked(false);
                hard.setDisabled(false);
                break;
            case 3:
                low.setChecked(false);
                low.setDisabled(false);
                medium.setChecked(false);
                medium.setDisabled(false);
                hard.setChecked(true);
                hard.setDisabled(true);
                break;
        }
        pref.flush();
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0.03F, 0.0F, 0.05F, 1.0F);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.background.render(game.batch, 0.3f);
        game.earth.render(game.batch);
        game.batch.end();

        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
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
        saveData();
        low.clear();
        medium.clear();
        hard.clear();
        chControlsClassic.clear();
        chControlsButtons.clear();
        chMusic.clear();
        chSound.clear();
        label[0].clear();
        label[1].clear();
        label[2].clear();
        label[3].clear();
        label[4].clear();
        label[5].clear();
        label[6].clear();
        label[7].clear();
        label[8].clear();
        label[9].clear();
        stage.dispose();
    }
}
