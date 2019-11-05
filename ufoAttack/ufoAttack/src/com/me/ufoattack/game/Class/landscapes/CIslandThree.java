package com.me.ufoattack.game.Class.landscapes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.ufoattack.game.Abstracts.ALandscape;
import com.me.ufoattack.game.screens.GameScreen;

public class CIslandThree extends ALandscape{

	public CIslandThree(GameScreen game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		this.rect.height = 256;
		this.rect.width = 256;
		
		this.rect.x = 500;
		this.rect.y = this.game.rectangleScreenSize.height;
		
		this.region = new TextureRegion(this.game.myGame.gameTexture, 233, 499, 64 ,64);
	}

	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		if (this.rect.y < -800) {
			this.rect.y = this.game.rectangleScreenSize.height;
			this.rect.x = 500;
		} else {
			this.rect.y -= 1;
		}
		
		
	}

}
