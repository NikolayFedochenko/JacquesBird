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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.nxgame.jacquesbird.NXGame;
import com.nxgame.jacquesbird.model.Logo;

public class LogoScreen implements Screen {
    private NXGame game;
    private Texture texture;
    private OrthographicCamera camera;
    private Logo logo;

    private float alpha;
    private float timer;

    public LogoScreen(NXGame game) {
        this.game = game;

        if (camera == null) camera = new OrthographicCamera();
        camera.setToOrtho(false, 800f, 480f);
        if (texture == null) texture = new Texture(Gdx.files.internal("objects/logo.png"));
        logo = new Logo(texture, (800f / 2f) - 175f, (480f / 2f) - 175f,800f * 42f / 100f, 480f * 70f / 100f);

        alpha = 0f;
        timer = 0f;

        Gdx.gl.glClearColor(0.06f, 0.001f, 0.02f, 1.0f);
    }

    @Override
    public void show() {

    }

    private void update(float delta) {
        if (timer <= 8f) {
            timer += delta;
        } else {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
        if (timer > 6.5f) {
            alpha -= delta;
            if (alpha < 0f) alpha = 0f;
        } else {
            alpha += 0.008;
            if (alpha > 1f) alpha = 1f;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        game.batch.begin();
        logo.draw(game.batch, alpha);
        game.batch.end();
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
        texture.dispose();
    }
}
