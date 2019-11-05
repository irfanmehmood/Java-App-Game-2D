package com.me.ufoattack.game.Class.landscapes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.ufoattack.game.Abstracts.ALandscape;
import com.me.ufoattack.game.screens.GameScreen;

public class CIslandTwo extends ALandscape{

	public CIslandTwo(GameScreen game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		this.rect.height = 128;
		this.rect.width = 128;
		
		this.rect.x = 400;
		this.rect.y = this.game.rectangleScreenSize.height;
		
		this.region = new TextureRegion(this.game.myGame.gameTexture, 103, 499, 64 ,64);
	}

	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		if (this.rect.y < -400) {
			this.rect.y = this.game.rectangleScreenSize.height;
			this.rect.x = 400;
		} else {
			this.rect.y -= 1;
		}
		
		
	}

}
