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
import com.nxgame.jacquesbird.objects.Coin;
import com.nxgame.jacquesbird.objects.Mimic;
import com.nxgame.jacquesbird.objects.Player;
import com.nxgame.jacquesbird.objects.Tube;
import com.nxgame.jacquesbird.others.LoadResources;
import com.nxgame.jacquesbird.others.StateGame;

/**
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

class GameScreen implements Screen {
    private OrthographicCamera camera;
    private NXGame game;
    private Player player;
    private Mimic mimic;
    private Label label;

    private boolean gameOver;
    private boolean activeBtn;
    private boolean sound;
    private boolean controls;

    private Stage stage;
    private Button btnRestart;
    private Button btnBack;
    private Button btnPause;

    private Tube[] tubes;
    private Coin coin;

    private Preferences pref;

    GameScreen(final NXGame game) {
        this.game = game;

        if (stage == null) stage = new Stage();
        if (camera == null) {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800f, 480f);
        }

        pref = Gdx.app.getPreferences("JacquesBird");
        final byte diff = (byte) pref.getInteger("diff");
        if (pref.getInteger("diff") == 1) game.earth.setSpeed(180f);
        if (pref.getInteger("diff") == 2) game.earth.setSpeed(160f);
        if (pref.getInteger("diff") == 3) game.earth.setSpeed(140f);
        sound = pref.getBoolean("sound");
        controls = pref.getString("controls").equals("buttons");

        game.background.setMove(true);
        game.earth.setMove(true);
        game.earth.setAlpha(1f);

        player = new Player();
        mimic = new Mimic(850f, (480f * 12f) / 100f);
        tubes = new Tube[3];
        for (byte i = 0; i < tubes.length; i++)
            tubes[i] = new Tube(game, i * 285f, player, diff);

        if (coin == null) coin = new Coin(true);

        gameOver = false;
        activeBtn = false;

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            if (controls) {
                LoadResources.updateJump();
                LoadResources.getBtnJump().addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (Gdx.app.getType() == Application.ApplicationType.Android)
                            Gdx.input.vibrate(100);
                        if (!activeBtn) player.setJump(true);
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                        if (!activeBtn) player.setJump(false);
                    }
                });

                LoadResources.updateBtnLeft(pref.getString("controls"));
                LoadResources.getBtnLeft().addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (Gdx.app.getType() == Application.ApplicationType.Android)
                            Gdx.input.vibrate(100);
                        if (!activeBtn) player.setLeft(true);
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                        if (!activeBtn) player.setLeft(false);
                    }
                });

                LoadResources.updateBtnRight(pref.getString("controls"));
                LoadResources.getBtnRight().addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (Gdx.app.getType() == Application.ApplicationType.Android)
                            Gdx.input.vibrate(100);
                        if (!activeBtn) player.setRight(true);
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                        if (!activeBtn) player.setRight(false);
                    }
                });
            }
        }

        Button.ButtonStyle pause = new Button.ButtonStyle();
        pause.up = LoadResources.getUi().getDrawable("16");
        pause.down = LoadResources.getUi().getDrawable("17");

        if (btnPause == null) {
            btnPause = new Button(pause);
            btnPause.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
            btnPause.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 4f / 100f),
                    Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 6f / 100f), Align.center);

        }

        btnPause.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (!gameOver) {
                    if (game.stateGame == StateGame.PLAY) pause();
                    else resume();
                }
            }
        });

        Button.ButtonStyle res = new Button.ButtonStyle();
        res.up = LoadResources.getUi().getDrawable("14");
        res.down = LoadResources.getUi().getDrawable("15");

        if (btnRestart == null) {
            btnRestart = new Button(res);
            btnRestart.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
            btnRestart.setPosition(Gdx.graphics.getWidth() * 20f / 100f, Gdx.graphics.getHeight() * 4f / 100f);
            btnRestart.setVisible(false);
        }

        Button.ButtonStyle back = new Button.ButtonStyle();
        back.up = LoadResources.getUi().getDrawable("12");
        back.down = LoadResources.getUi().getDrawable("13");

        if (btnBack == null) {
            btnBack = new Button(back);
            btnBack.setSize(Gdx.graphics.getWidth() * 7f / 100f, Gdx.graphics.getHeight() * 11.5f / 100f);
            btnBack.setPosition(Gdx.graphics.getWidth() * 3f / 100f, Gdx.graphics.getHeight() * 4f / 100f);
            btnBack.setVisible(false);
        }

        btnRestart.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtn) recreate();
            }
        });

        btnBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.app.getType() == Application.ApplicationType.Android)
                    Gdx.input.vibrate(100);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (activeBtn) {
                    game.setScreen(new MenuScreen(game));
                    dispose();
                }
            }
        });

        if (game.label == null)
            game.label = new Label("", LoadResources.getFontSkin().get("blue", Label.LabelStyle.class));
        game.label.setAlignment(Align.center);
        game.label.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 3f / 100f), Align.center);
        game.label.setFontScale(Gdx.graphics.getHeight() / 1300f);

        if (game.money == null)
            game.money = new Label("", LoadResources.getFontSkin().get("green", Label.LabelStyle.class));
        game.money.setAlignment(Align.right);
        game.money.setPosition(Gdx.graphics.getWidth() * 8f / 100f, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 3f / 100f), Align.center);
        game.money.setFontScale(Gdx.graphics.getHeight() / 1300f);

        if (controls) stage.addActor(LoadResources.getBtnJump());
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            if (controls) {
                stage.addActor(LoadResources.getBtnLeft());
                stage.addActor(LoadResources.getBtnRight());
            }
        }
        stage.addActor(btnPause);
        stage.addActor(btnRestart);
        stage.addActor(btnBack);
        stage.addActor(game.label);
        stage.addActor(game.money);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        game.label.setText("" + player.getScore());
        game.money.setText("$  " + player.getMoney());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameOver){
            repositionLabel();
            btnRestart.setVisible(true);
            btnBack.setVisible(true);
            if (Gdx.app.getType() == Application.ApplicationType.Android) {
                if (controls) {
                    LoadResources.getBtnRight().setVisible(false);
                    LoadResources.getBtnLeft().setVisible(false);
                }
            }
            if (controls) LoadResources.getBtnJump().setVisible(false);
            activeBtn = true;
        }

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.background.render(game.batch, 1f);
        for (Tube tube : tubes) tube.render(game.batch);
        game.earth.render(game.batch);
        coin.render(game.batch);
        mimic.render(game.batch);
        if (!gameOver) player.render(game.batch);
        game.batch.end();

        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (game.stateGame == StateGame.PLAY) pause();
            else resume();
        }
    }

    private void repositionLabel() {
        if (gameOver) {
            if (label == null) {
                switch (pref.getInteger("diff")) {
                    case 1:
                        label = new Label("Лучший результат\n" + pref.getInteger("highScoresLow"), LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
                        break;
                    case 2:
                        label = new Label("Лучший результат\n" + pref.getInteger("highScoresMedium"), LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
                        break;
                    case 3:
                        label = new Label("Лучший результат\n" + pref.getInteger("highScoresHard"), LoadResources.getFontSkin().get("orange", Label.LabelStyle.class));
                        break;
                }

                label.setAlignment(Align.center);
                label.setFontScale(Gdx.graphics.getHeight() / 1500f);
                stage.addActor(label);
                game.label.setText("Ваш результат\n" + player.getScore());
                game.money.setText("Собрано монет\n" + player.getMoney());
                game.money.setAlignment(Align.center);

                game.label.setPosition(Gdx.graphics.getWidth() / 2f, (Gdx.graphics.getHeight() / 2f) + (Gdx.graphics.getHeight() * 15f / 100f), Align.center);
                label.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, Align.center);
                game.money.setPosition(Gdx.graphics.getWidth() / 2f, (Gdx.graphics.getHeight() / 2f) - (Gdx.graphics.getHeight() * 15f / 100f), Align.center);
            }
        }
    }

    private void update(float delta) {
        if (game.stateGame == StateGame.PLAY) {
            if (!gameOver) {
                game.background.update(delta);
                game.earth.update(delta);
                for (Tube tube : tubes) {
                    tube.update(delta);

                    if (tube.collides(player.getBounds())) {
                        gameOver = true;
                        player.addRip();
                    }

                    if ((tube.getCreateMimic() >= 20) && (tube.getCreateMimic() <= 24)) {
                        mimic.setMove(true);
                    }
                }
                coin.update(delta);
                mimic.update(delta);
                player.update(delta);

                if (player.getMoney() < 1001) {
                    if (coin.collides(player.getBounds())) {
                        player.addMoney((short) 1);
                        game.money.setText("$  " + player.getMoney());
                        coin.recreate();
                    }
                }

                if (mimic.collides(player.getBounds())) {
                    mimic.deactive();
                    if (sound) Media.playChest();
                }

                if ((player.getPos().y < (480f * 13f) / 100f) || (player.getPos().y > 460f)) {
                    gameOver = true;
                    player.addRip();
                }
            }
        }
    }

    private void recreate() {
        dispose();
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        game.stateGame = StateGame.PAUSE;
        activeBtn = true;
        if (pref.getBoolean("music")) Media.pauseMusic();
    }

    @Override
    public void resume() {
        game.stateGame = StateGame.PLAY;
        activeBtn = false;
        if (pref.getBoolean("music")) Media.playMusic(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        btnPause.clear();
        btnBack.clear();
        btnRestart.clear();
        player.saveData();
        if (label != null) label.clear();
        stage.dispose();
        for (Tube tube : tubes) tube.dispose();
        coin.dispose();
        player.dispose();
    }
}
