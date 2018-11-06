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

package com.nxgame.jacquesbird.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Neutronx on 07.09.2018.
 * Input Text
 */

public class MyTextInput implements Input.TextInputListener {
    private Stage stage;
    private Skin skin;

    public MyTextInput(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    @Override
    public void input(String text) {
        if (text.length() <= 13) {
            Preferences pref = Gdx.app.getPreferences("JacquesBird");
            if (text.equals("")) text = pref.getString("name");
            pref.putString("name", text);
            pref.flush();
        } else {
            new MyDialog("Имя не должно превышать 13 символов!\n", skin) {
                {
                    scaleBy(-0.6f);
                    button("ok");
                }

                @Override
                protected void result(Object object) {
                    super.result(object);
                }
            }.show(stage);
        }
    }

    @Override
    public void canceled() {

    }
}
