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
 *        1.2. Не разрешается осуществлять вскрытие технологии, декомпиляцию и дизассемблирование
 *        программы, за исключением и только в той степени, в которой такие действия явно разрешены
 *        действующим законодательством, несмотря на наличие в соглашении данного ограничения.
 *
 *        1.3. Разделение программы. Программа лицензируется как единое целое. Ее нельзя разделять на
 *        составляющие части для использования на нескольких устройствах.
 *
 *        1.4. Запрещается продавать данное приложение, предоставлять это приложение в прокат или во временное
 *        пользование имея при этом любую выгоду.
 *
 *        1.5. Автор приложение не несёт ни какой ответственности за какой-либо причинённый вред устройству
 *        данным приложением.
 *
 */

package com.nxgame.jacquesbird.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nxgame.jacquesbird.media.Media;
import com.nxgame.jacquesbird.others.LoadResources;

/**
 * Created by Neutronx on 03.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

public class Player {
    private Vector2 pos;
    private Rectangle bounds;
    private Preferences pref;
    private Animation animation;
    private TextureRegion region1;

    private float vy, gravity;
    private short score;
    private short gold;
    private short serebro;
    private short bronza;
    private short rip;
    private byte checkBird;
    private boolean sound;
    private boolean left;
    private boolean right;
    private boolean jump;
    private boolean control;
    private float timer;

    private void playerFrame() {
        if (LoadResources.getPlayer() != null) {
            TextureRegion[][] region3 = TextureRegion.split(LoadResources.getPlayer(),
                    LoadResources.getPlayer().getWidth() / 3, LoadResources.getPlayer().getHeight());
            TextureRegion[] region2 = new TextureRegion[3];
            byte index = 0;
            timer = 0.0f;
            for (byte a = 0; a < 1; a++) {
                for (byte b = 0; b < 3; b++) {
                    region2[index] = region3[a][b];
                    index++;
                }
            }
            animation = new Animation<TextureRegion>(0.1f, region2);
            animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        }
    }

    public Player() {
        pref = Gdx.app.getPreferences("JacquesBird");
        playerFrame();
        if (pref.getBoolean("sound")) sound = true;
        control = pref.getString("controls").equals("classic");

        pos = new Vector2(100f, 380f);
        if (pref.getString("skin").equals("bird")) bounds = new Rectangle(100f, 380f, 34f, 26f);
        if (pref.getString("skin").equals("bird_00")) bounds = new Rectangle(100f, 380f, 32f, 22f);
        if (pref.getString("skin").equals("67bird0")) bounds = new Rectangle(100f, 380f, 34f, 26f);
        if (pref.getString("skin").equals("e5bird3fd"))
            bounds = new Rectangle(100f, 380f, 34f, 25f);
        if (pref.getString("skin").equals("bird3dba")) bounds = new Rectangle(100f, 380f, 33f, 24f);
        if (pref.getString("skin").equals("bird3o20")) bounds = new Rectangle(100f, 380f, 33f, 26f);
        if (pref.getString("skin").equals("scbird666on"))
            bounds = new Rectangle(100f, 380f, 29f, 23f);
        if (pref.getString("skin").equals("bird3dba")) checkBird = 1;
        if (pref.getString("skin").equals("scbird666on")) checkBird = 2;

        vy = 0f;
        gravity = -520.7f;
        left = false;
        right = false;
        jump = false;
    }

    public void render(SpriteBatch batch) {
        if (pref.getString("skin").equals("bird_00")) batch.draw(region1, pos.x, pos.y, 39f, 28f);
        if (pref.getString("skin").equals("bird")) batch.draw(region1, pos.x, pos.y, 40f, 32f);
        if (pref.getString("skin").equals("67bird0")) batch.draw(region1, pos.x, pos.y, 40f, 32f);
        if (pref.getString("skin").equals("e5bird3fd")) batch.draw(region1, pos.x, pos.y, 40f, 31f);
        if (pref.getString("skin").equals("bird3dba")) batch.draw(region1, pos.x, pos.y, 39f, 30f);
        if (pref.getString("skin").equals("bird3o20")) batch.draw(region1, pos.x, pos.y, 39f, 32f);
        if (pref.getString("skin").equals("scbird666on"))
            batch.draw(region1, pos.x, pos.y, 35f, 28f);
    }

    private void jump() {
        switch (checkBird) {
            case 1:
                vy = 190f;
                break;
            case 2:
                vy = 170f;
                break;
            default:
                vy = 200f;
        }
        if (sound) Media.startFlap();
    }

    private void left(float delta) {
        pos.x -= 200f * delta;
        if (pos.x < 0f) pos.x = 0f;

    }

    private void right(float delta) {

        pos.x += 200f * delta;
        if (pos.x > Gdx.graphics.getWidth() - 60f) pos.x = Gdx.graphics.getWidth() - 60f;

    }

    public void update(float delta) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if (!control) {
                if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    left(delta);

                if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    right(delta);

                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) jump();
            } else if (Gdx.input.isTouched()) jump();
        }
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            if (control) {
                if (Gdx.input.isTouched()) jump();
            } else {
                if (jump) jump();
                if (left) left(delta);
                if (right) right(delta);
            }
        }

        vy += gravity * delta;
        pos.y += vy * delta;

        timer += delta;
        region1 = (TextureRegion) animation.getKeyFrame(timer);
        bounds.setPosition(getPos().x + 3f, getPos().y + 3f);
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    void addScore() {
        score += (short) 1;
        if (sound) Media.startHit();
    }

    public void addRip() {
        rip += 1;
        if (sound) Media.startBird();
    }

    public short getMoney() {
        switch (pref.getInteger("diff")) {
            case 1:
                return bronza;
            case 2:
                return serebro;
            case 3:
                return gold;
        }
        return 0;
    }

    public void addMoney(short money) {
        switch (pref.getInteger("diff")) {
            case 1:
                this.bronza += money;
                break;
            case 2:
                this.serebro += money;
                break;
            case 3:
                this.gold += money;
                break;
        }
        if (sound) Media.startCoin();
    }

    public void saveData() {
        int highscor;
        switch (pref.getInteger("diff")) {
            case 1:
                highscor = pref.getInteger("highScoresLow");
                if (getScore() > highscor) pref.putInteger("highScoresLow", getScore());
                break;
            case 2:
                highscor = pref.getInteger("highScoresMedium");
                if (getScore() > highscor) pref.putInteger("highScoresMedium", getScore());
                break;
            case 3:
                highscor = pref.getInteger("highScoresHard");
                if (getScore() > highscor) pref.putInteger("highScoresHard", getScore());
                break;
        }

        pref.putInteger("rip", getRip() + pref.getInteger("rip"));
        switch (pref.getInteger("diff")) {
            case 1:
                pref.putInteger("bronza", pref.getInteger("bronza") + getMoney());
                break;
            case 2:
                pref.putInteger("serebro", pref.getInteger("serebro") + getMoney());
                break;
            case 3:
                pref.putInteger("gold", pref.getInteger("gold") + getMoney());
                break;
        }
        pref.flush();
    }

    public short getScore() {
        return score;
    }

    private int getRip() {
        return rip;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void dispose() {
        animation = null;
        region1 = null;
        pref = null;
        bounds = null;
        sound = Boolean.parseBoolean(null);
        pos = null;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
