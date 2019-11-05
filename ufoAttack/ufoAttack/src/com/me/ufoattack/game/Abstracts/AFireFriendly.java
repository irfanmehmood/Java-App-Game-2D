package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class AFireFriendly extends AFire{

	
	public AFireFriendly() {
		super();
	}
	
	public void reset() {
		super.reset();
    }
	
	public abstract void  init(Rectangle parentRect, GameScreen game);
	
}
