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

package com.nxgame.jacquesbird.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nxgame.jacquesbird.NXGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.resizable = false;
		config.addIcon("icon.png", Files.FileType.Internal);
		config.title = "Jacques Bird";
		config.fullscreen = false;
		config.useGL30 = false;
		new LwjglApplication(new NXGame(), config);
	}
}

