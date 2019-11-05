package com.me.ufoattack.game.Abstracts;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.Class.explosions.CExplosion;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class AShip {
	
	protected GameScreen game;
	
	public Rectangle rect;
	protected float lastUpdated;
	protected float firingSpeed = 2.0f;
	protected boolean loopAnimation = true;
	protected int health = 5;
	protected AExplosion explosion;
	
	
	
	protected Animation planeAnimation;
	protected TextureRegion[] planeAnimationFramesRegion;
	protected TextureRegion currentFrameRegion;
	protected float hitWaitTime = 1/30f;
	protected float hitWaitTimeCounter = 0;

    
	protected float stateTime = 0;

	
	
	
	public AShip() {
		
	}
	
	
	
	public void move(){
	 }
	
	public void render(SpriteBatch batch, float deltaTime) {

	    if (this.explosion != null){
	      	this.explosion.render(batch, deltaTime);
	    }
	}
	
	
	public int getHealth() {
		return this.health;
	}
	
	protected boolean handlingHit = false;
	protected int bulletValueLeft = 0;
	
	public int gotHit(int bulletValue) { 
		
		bulletValueLeft = bulletValue - this.health;
		handlingHit = true;
		hitStrength = bulletValue;
		this.health = this.health-bulletValue;

		return bulletValueLeft;

	}
	
	private int hitStrength = 0;
	public boolean isHandlingHit() {
		
		return handlingHit;
	}

	public void explosionFinished() {
		
		
		this.explosion = null;
		handlingHit = false;
	}
	
	public void setX(float x) {
		this.rect.x =x;
	}
	
	public void setY(float y) {
		this.rect.y =y;
	}
	
	boolean rocketLeftSelected = false;
	
	
	public void update(float currentTime) {

		if (handlingHit) {
			hitWaitTimeCounter += currentTime;
			if (hitWaitTimeCounter >= hitWaitTime){
				handlingHit = false;
				hitWaitTimeCounter = 0;
				if (this.health <= 0) {
					this.explosion = new CExplosion(this, this.rect);
				}
			}
		}
		
		
		if (this.explosion != null){
        	this.explosion.rect.x = this.rect.x;
        	this.explosion.rect.y = this.rect.y;
        } 
		
		
	}
	
	public Rectangle getRectangle() {
		return this.rect;
	}
	
	
}
