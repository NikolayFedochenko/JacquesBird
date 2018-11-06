package com.nxgame.jacquesbird.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Neutronx on 04.09.2018.
 * Если ты это читаешь, то знай,
 * что этот код хуже кожи разлагающегося бомжа.
 */

public class Media {
    private static Sound flap;
    private static Sound coin;
    private static Sound hit;
    private static Sound bird;
    private static Sound buy;
    private static Sound errorbuy;
    private static Sound chest;
    private static Sound openChest;
    private static Music music;

    public static void initialization() {
        flap = Gdx.audio.newSound(Gdx.files.internal("media/flap.ogg"));
        coin = Gdx.audio.newSound(Gdx.files.internal("media/coin.ogg"));
        hit = Gdx.audio.newSound(Gdx.files.internal("media/hit.ogg"));
        bird = Gdx.audio.newSound(Gdx.files.internal("media/bird.ogg"));
        buy = Gdx.audio.newSound(Gdx.files.internal("media/buy.ogg"));
        errorbuy = Gdx.audio.newSound(Gdx.files.internal("media/errorBuy.ogg"));
        chest = Gdx.audio.newSound(Gdx.files.internal("media/chest.ogg"));
        openChest = Gdx.audio.newSound(Gdx.files.internal("media/openChest.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("media/music.ogg"));
    }

    public static void playChest() {
        chest.play();
    }

    public static void playOpenChest() {
        openChest.play();
    }

    public static void startFlap() {
        flap.play(2f);
    }

    public static void startHit() {
        hit.play(0.8f);
    }

    public static void startCoin() {
        coin.play(0.7f);
    }

    public static void startBird() {
        bird.play();
    }

    public static void playBuy() {
        buy.play();
    }

    public static void playErrorBuy() {
        errorbuy.play();
    }

    public static void playMusic(boolean play) {
        if (play) startMusic();
        else stopMusic();
    }

    private static void stopMusic() {
        if (music.isPlaying()) {
            music.stop();
            music.setLooping(false);
        }
    }

    public static void pauseMusic() {
        if (music.isPlaying()) music.pause();
    }

    private static void startMusic() {
        music.setVolume(0.7f);
        if (!music.isPlaying()) {
            music.play();
            music.setLooping(true);
        }
    }

    public static void dispose() {
        if (music.isPlaying()) {
            if (music != null) {
                music.setLooping(false);
                music.stop();
                music.dispose();
            }
        }
        flap.dispose();
        coin.dispose();

        bird.dispose();
        hit.dispose();
    }
}
