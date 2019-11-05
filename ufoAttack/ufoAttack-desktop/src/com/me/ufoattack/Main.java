package com.me.ufoattack;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.ufoattack.game.MyGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Iphone5 Resolutions";
		cfg.useGL20 = false;
		cfg.width = 640;
		cfg.height = 960;
		
		new LwjglApplication(new MyGame(), cfg);
	}
}
