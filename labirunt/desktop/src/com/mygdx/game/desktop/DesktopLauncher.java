package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main.Algo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "test-game";
		//cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new Algo(), cfg);
	}
}
