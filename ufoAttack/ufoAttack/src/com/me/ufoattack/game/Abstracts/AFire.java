package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;

public abstract class AFire  implements Poolable {
	
	protected GameScreen game;
	protected TextureRegion region;
	protected Rectangle rect;
	protected boolean alive = false;
	protected boolean friendlyFire = true;
	protected boolean valuesInitialised = false;
	protected int firePower = 2;
    public Enums.DIRECTION direction = Enums.DIRECTION.Left;
	
	
	protected boolean targetSet = false;
	public boolean targetingBullet = false;
	public boolean roatatryBullet  = false;

	//Distance to jump
	protected int speed = 8;
	
	public AFire() {
		this.alive = false;
	}
	
	public abstract void  init(Rectangle parentRect, GameScreen game);
	
	public void reset() {
		this.rect.x =0;
		this.rect.y =0;
		this.targetSet = false;
		this.roatatryBullet = false;
		this.targetingBullet = false;
		this.alive = false;
    }

	
	public abstract void update(float time);
	
	public abstract void render(SpriteBatch batch, float deltaTime);	
	
	public void setX(float x) {
		this.rect.x =x;
	}
	
	public boolean IsAlive() {
		return this.alive;
	}
	
	public void setY(float y) {
		this.rect.y =y;
	}
	
	public void setGame(float y) {
		this.rect.y =y;
	}
	
	public Rectangle getRectangle() {
		return this.rect;
	}
}
