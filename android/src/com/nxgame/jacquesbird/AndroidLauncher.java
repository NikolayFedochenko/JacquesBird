package com.nxgame.jacquesbird;

import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGyroscope = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.useAccelerometer = false;
		config.hideStatusBar = true;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			config.useImmersiveMode = true;
		}
		config.useGL30 = false;
		initialize(new NXGame(), config);
	}
}
