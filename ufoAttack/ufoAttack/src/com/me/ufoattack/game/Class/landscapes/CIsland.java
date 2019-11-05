package com.me.ufoattack.game.Class.landscapes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.ufoattack.game.Abstracts.ALandscape;
import com.me.ufoattack.game.screens.GameScreen;

public class CIsland extends ALandscape{

	public CIsland(GameScreen game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		this.rect.height = 256;
		this.rect.width = 256;
		
		this.rect.x = 5;
		this.rect.y = this.game.rectangleScreenSize.height;
		
		this.region = new TextureRegion(this.game.myGame.gameTexture, 168, 499, 64 ,64);
	}

	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		if (this.rect.y < -1000) {
			this.rect.y = this.game.rectangleScreenSize.height;
			this.rect.x = 10;
		} else {
			this.rect.y -= 1;
		}
		
		
	}

}
