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

package com.nxgame.jacquesbird.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LoadResources {

    private static Texture player;
    private static Texture bird;
    private static Texture coin;
    private static Texture serebro;
    private static Texture bronza;
    private static Texture mimic;
    private static Texture background;
    private static Texture earth;

    private static Button btnLeft;
    private static Button btnRight;
    private static Button btnJump;

    private static Skin fontSkin;
    private static Skin ui;
    private static Skin resources_store;

    private static Preferences pref;

    static void updatePlayer() {
        if (player == null) {
            if (pref.getString("skin").equals("bird"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/jacques.png"));
            if (pref.getString("skin").equals("bird_00"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/bird_dek.png"));
            if (pref.getString("skin").equals("67bird0"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/bird_morg.png"));
            if (pref.getString("skin").equals("e5bird3fd"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/bird_saints.png"));
            if (pref.getString("skin").equals("bird3dba"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/bird_band.png"));
            if (pref.getString("skin").equals("bird3o20"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/jacques_cov.png"));
            if (pref.getString("skin").equals("scbird666on"))
                player = new Texture(Gdx.files.internal("objects/sprite/player/bird_sc.png"));
            player.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            bird = player;
            bird.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } else {
            player.dispose();
            player = null;
            bird.dispose();
            bird = null;
            updatePlayer();
        }
    }

    public static void updateJump() {
        Button.ButtonStyle jump = new Button.ButtonStyle();
        jump.up = ui.getDrawable("24");
        jump.down = ui.getDrawable("25");

        if (btnJump == null) btnJump = new Button(jump);
        btnJump.setSize(Gdx.graphics.getWidth() * 9f / 100f, Gdx.graphics.getHeight() * 15f / 100f);
        btnJump.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 20f / 100f), Gdx.graphics.getHeight() * 18f / 100f);
        btnJump.setVisible(true);
    }

    public static void updateBtnLeft(String active) {
        if (active.equals("buttons")) {
            Button.ButtonStyle left = new Button.ButtonStyle();
            left.up = LoadResources.getUi().getDrawable("20");
            left.down = LoadResources.getUi().getDrawable("21");

            if (btnLeft == null) btnLeft = new Button(left);
            btnLeft.setSize(Gdx.graphics.getWidth() * 7.5f / 100f, Gdx.graphics.getHeight() * 12f / 100f);
            btnLeft.setPosition(Gdx.graphics.getWidth() * 5f / 100f, Gdx.graphics.getHeight() * 10f / 100f);
            btnLeft.setVisible(true);
        }
    }

    public static void updateBtnRight(String active) {
        if (active.equals("buttons")) {
            Button.ButtonStyle right = new Button.ButtonStyle();
            right.up = LoadResources.getUi().getDrawable("22");
            right.down = LoadResources.getUi().getDrawable("23");

            if (btnRight == null) btnRight = new Button(right);
            btnRight.setSize(Gdx.graphics.getWidth() * 7.5f / 100f, Gdx.graphics.getHeight() * 12f / 100f);
            btnRight.setPosition(Gdx.graphics.getWidth() * 17f / 100f, Gdx.graphics.getHeight() * 10f / 100f);
            btnRight.setVisible(true);
        }
    }

    private static void controlsButton() {
        updateJump();
        updateBtnLeft(pref.getString("controls"));
        updateBtnRight(pref.getString("controls"));
    }

    public static void initializationTexture() {
        pref = Gdx.app.getPreferences("JacquesBird");
        fontSkin = new Skin(Gdx.files.internal("font/font_1/font.json"));
        ui = new Skin(new TextureAtlas(Gdx.files.internal("atlas/ui.atlas")));
        resources_store = new Skin(new TextureAtlas(Gdx.files.internal("atlas/resources_store.atlas")));

        updatePlayer();
        if (pref.getString("controls").equals("buttons")) {
            controlsButton();
        }

        coin = new Texture(Gdx.files.internal("objects/sprite/gold.png"));
        bronza = new Texture(Gdx.files.internal("objects/sprite/bronza.png"));
        serebro = new Texture(Gdx.files.internal("objects/sprite/serebro.png"));

        mimic = new Texture(Gdx.files.internal("objects/sprite/mimic.png"));

        background = new Texture(Gdx.files.internal("objects/background/bg.png"));
        earth = new Texture(Gdx.files.internal("objects/background/earth.png"));
    }

    public static Texture getPlayer() {
        return player;
    }

    public static Texture getBird() {
        return bird;
    }

    public static Texture getCoin() {
        return coin;
    }

    public static Texture getSerebro() {
        return serebro;
    }

    public static Texture getBronza() {
        return bronza;
    }

    public static Texture getMimic() {
        return mimic;
    }

    public static Texture getBackground() {
        return background;
    }

    public static Texture getEarth() {
        return earth;
    }

    public static Button getBtnJump() {
        return btnJump;
    }

    public static Button getBtnLeft() {
        return btnLeft;
    }

    public static Button getBtnRight() {
        return btnRight;
    }

    public static Skin getFontSkin() {
        return fontSkin;
    }

    public static Skin getUi() {
        return ui;
    }

    public static Skin getResources_store() {
        return resources_store;
    }

    public static void dispose() {
        if (pref.getString("controls").equals("buttons")) {
            btnJump.clear();
            btnLeft.clear();
            btnRight.clear();
        }
        player.dispose();
        bird.dispose();
        coin.dispose();
        bronza.dispose();
        serebro.dispose();
        mimic.dispose();
        background.dispose();
        earth.dispose();
        ui.dispose();
        fontSkin.dispose();
        resources_store.dispose();
    }
}
