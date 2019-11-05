package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class ALandscape {
	
	protected GameScreen game;
	protected TextureRegion region;
	protected Rectangle rect;
	
	public ALandscape(GameScreen game) {
		this.game = game;
		this.rect = new Rectangle();
	}
	
	public abstract void update(float time);
	
	public void render(SpriteBatch batch,float deltaTime) {
		batch.draw(this.region, this.rect.x, this.rect.y,  this.rect.width, this.rect.height);
	}

}
