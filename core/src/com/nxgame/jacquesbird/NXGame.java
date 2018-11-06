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
 *       1.2. Не разрешается осуществлять вскрытие технологии, декомпиляцию и дизассемблирование
 *       программы, за исключением и только в той степени, в которой такие действия явно разрешены
 *       действующим законодательством, несмотря на наличие в соглашении данного ограничения.
 *
 *       1.3. Разделение программы. Программа лицензируется как единое целое. Ее нельзя разделять на
 *       составляющие части для использования на нескольких устройствах.
 *
 *       1.4. Запрещается продавать данное приложение, предоставлять это приложение в прокат или во временное
 *       пользование имея при этом любую выгоду.
 *
 *       1.5. Автор приложение не несёт ни какой ответственности за какой-либо причинённый вред устройству
 *       данным приложением.
 *
 */

package com.nxgame.jacquesbird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nxgame.jacquesbird.media.Media;
import com.nxgame.jacquesbird.objects.background.Background;
import com.nxgame.jacquesbird.objects.background.Earth;
import com.nxgame.jacquesbird.others.LoadResources;
import com.nxgame.jacquesbird.others.StateGame;
import com.nxgame.jacquesbird.view.LogoScreen;

public class NXGame extends Game {
	public SpriteBatch batch;
	public Label label;
	public Label money;
	public Label label1;
	public Background background;
	public Earth earth;
	public StateGame stateGame;

	@Override
	public void create() {
		batch = new SpriteBatch(600);
		batch.maxSpritesInBatch = 200;

		Preferences pref = Gdx.app.getPreferences("JacquesBird");
		if (!pref.contains("diff")) pref.putInteger("diff", 2);
		if (!pref.contains("music")) pref.putBoolean("music", true);
		if (!pref.contains("sound")) pref.putBoolean("sound", true);
		if (!pref.contains("highScoresLow")) pref.putInteger("highScoresLow", 0);
		if (!pref.contains("highScoresMedium")) pref.putInteger("highScoresMedium", 0);
		if (!pref.contains("highScoresHard")) pref.putInteger("highScoresHard", 0);
		if (!pref.contains("rip")) pref.putInteger("rip", 0);
		if (!pref.contains("bronza")) pref.putInteger("bronza", 0);
		if (!pref.contains("serebro")) pref.putInteger("serebro", 0);
		if (!pref.contains("gold")) pref.putInteger("gold", 0);
		if (!pref.contains("name")) pref.putString("name", "Jacque");
		if (!pref.contains("skin")) pref.putString("skin", "bird");
		if (!pref.contains("itemID0")) pref.putBoolean("itemID0", false);
		if (!pref.contains("itemID1")) pref.putBoolean("itemID1", false);
		if (!pref.contains("itemID2")) pref.putBoolean("itemID2", false);
		if (!pref.contains("itemID3")) pref.putBoolean("itemID3", false);
		if (!pref.contains("itemID4")) pref.putBoolean("itemID4", false);
		if (!pref.contains("itemID5")) pref.putBoolean("itemID5", false);
		if (!pref.contains("itemIDisChecked[0]")) pref.putBoolean("itemIDisChecked[0]", false);
		if (!pref.contains("itemIDisChecked[1]")) pref.putBoolean("itemIDisChecked[1]", false);
		if (!pref.contains("itemIDisChecked[2]")) pref.putBoolean("itemIDisChecked[2]", false);
		if (!pref.contains("itemIDisChecked[3]")) pref.putBoolean("itemIDisChecked[3]", false);
		if (!pref.contains("itemIDisChecked[4]")) pref.putBoolean("itemIDisChecked[4]", false);
		if (!pref.contains("itemIDisChecked[5]")) pref.putBoolean("itemIDisChecked[5]", false);
		if (!pref.contains("controls")) pref.putString("controls", "classic");
		pref.flush();

		Media.initialization();
		LoadResources.initializationTexture();

		background = new Background();
		earth = new Earth();

		stateGame = StateGame.PLAY;
		setScreen(new LogoScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		if (label != null) label.clear();
		if (money != null) money.clear();
		if (label1 != null) label1.clear();

		background.dispose();
		background = null;
		earth.dispose();
		earth = null;

        LoadResources.dispose();
        Media.dispose();
	}
}
