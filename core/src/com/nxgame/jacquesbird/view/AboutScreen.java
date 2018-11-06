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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nxgame.jacquesbird.NXGame;
import com.nxgame.jacquesbird.others.LoadResources;

/**
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

class AboutScreen implements Screen {
    private NXGame game;
    private Stage stage;
    private Vector2 pos;
    private OrthographicCamera camera;

    AboutScreen(final NXGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800f, 480f);

        stage = new Stage();

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

        String str = "----------------------------------------------------------------------------";
        pos = new Vector2(Gdx.graphics.getWidth() / 2f, 0f - (Gdx.graphics.getHeight() * 100f / 100f));
        game.label1 = new Label("Автор\nНиколай Федоченко\n" +
                str + "\nЗВУКИ\nSome of the sounds in this project \nwere created by David McKee (ViRiX)\n" +
                "soundcloud.com/virix\n\nBrandon Morris\nhttps://opengameart.org/users/haeldb" +
                "\n\ndklon\nhttps://opengameart.org/users/dklon\n" + str + "\nГрафика\nНиколай Федоченко\n\nbevouliin.com\n" +
                "https://opengameart.org/users/bevouliincom\n" + str + "\nИдея с кнопочным управлением\nДенис Егоров" +
                "\n" + str + "\n\n\nВерсия: 2.8\n",
                LoadResources.getFontSkin().get("green", Label.LabelStyle.class));
        game.label1.setAlignment(Align.center);
        game.label1.setFontScale(Gdx.graphics.getHeight() / 1500f);
        game.label1.setPosition(pos.x, pos.y, Align.center);

        stage.addActor(game.label1);
        stage.addActor(btnBack);

        Gdx.input.setInputProcessor(stage);
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
        game.batch.end();

        stage.act(delta);
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    private void update(float delta) {
        pos.y += 40f * delta;

        game.label1.setPosition(pos.x, pos.y, Align.center);
        if (pos.y > Gdx.graphics.getHeight() + (Gdx.graphics.getHeight() * 120f / 100f)) {
            pos.y = 0 - (Gdx.graphics.getHeight() * 100f / 100f);
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
        camera.setToOrtho(false, 800f, 480f);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
