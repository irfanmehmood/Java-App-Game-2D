package com.me.ufoattack.game.Class.fires.friendly;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.ufoattack.game.MyGame;
import com.me.ufoattack.game.Abstracts.AFireFriendly;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;

public class fireTargeted extends AFireFriendly{

	private boolean ready = false;
	float turnnCountX=0;
	private Music missileReleaseMusic;
	public fireTargeted() {
		super();
	}
	Sprite  roatatryBulletSprite;
	public void init(Rectangle parentRect, GameScreen game) {
		
		
		if (!valuesInitialised) {
			
			this.game = game;
			this.rect = new Rectangle();
			this.region = new TextureRegion(this.game.myGame.tileSetTexture);
			this.rect.height = 15;
			this.rect.width = 15;
			this.region.setRegionX(416);
			this.region.setRegionY(96);
			this.region.setRegionWidth((int) this.rect.width);
			this.region.setRegionHeight((int) this.rect.height);
			this.firePower = 7;
			this.speed = 2;
			this.targetingBullet = true;
			this.roatatryBulletSprite = new Sprite(this.game.myGame.tileSetTexture,416,96,15,15);
			this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
			valuesInitialised = true;
		}
		
		
		//This happens only first time to get target coordinates , which is the plane
		if (AShipEnemy.IsMyTargetShipOne != null) {
			 Vector2 vectorTargetShipOne = new Vector2();
			 Vector2 vectorBullet = new Vector2();
			 AShipEnemy.IsMyTargetShipOne.rect.getCenter(vectorTargetShipOne);
			 this.rect.getCenter(vectorBullet);
			 targetX2 = vectorTargetShipOne.x;
    		 targetY2 = vectorTargetShipOne.y;
	    	 lineSlopeYcombined = targetY2 - vectorBullet.y;
	    	 lineSlopeXcombined = targetX2 - vectorBullet.x;
	    	 lineSlope =  lineSlopeYcombined/lineSlopeXcombined;
	    	 
	    	 lineSlope = Math.abs(lineSlope);
	    	 

	    	 newY = (lineSlope * MyGame.screenWidthHeightRatio) + this.speed;//(Math.abs(lineSlope) + 
	    	 newX = (MyGame.screenWidthHeightRatio / lineSlope) + this.speed;
	    	 
	    	 if (targetX2 > this.rect.x) {
	    		 bulletHorizontalDirection = Enums.DIRECTION.Right;
	    	 }else{
	    		 bulletHorizontalDirection = Enums.DIRECTION.Left;
	    		
	    	 }
	    	 if (targetY2 > this.rect.y) {
		    		bulletVerticalDirection = Enums.DIRECTION.Up;
		     }else{
		    		bulletVerticalDirection = Enums.DIRECTION.Down;
		     }
	    	 float angle = Enums.getAngle(
	    			 	vectorTargetShipOne.x + (AShipEnemy.IsMyTargetShipOne.rect.width/2),
	    			 	vectorTargetShipOne.y + (AShipEnemy.IsMyTargetShipOne.rect.height/2),
						this.rect.x + (this.rect.width/2),
						this.rect.y + (this.rect.height/2));
			 
			 this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
			 this.roatatryBulletSprite.rotate(-angle); 
	    	 targetSet = true;
		}
		
		
		
		
		
		this.speed = 8;
		//this.missileReleaseMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/missile/release.wav"));
		//this.missileReleaseMusic.setLooping(false);
		//this.missileReleaseMusic.play();
		this.rect.x = parentRect.x + (parentRect.width/2) - (this.rect.width/2);
		this.rect.y = parentRect.y + (parentRect.height);
		this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
		this.alive = true;
	}
	
	
	@Override
	public void render(SpriteBatch batch, float deltaTime) {		
		
		this.roatatryBulletSprite.draw(batch);
		
	}
	Enums.DIRECTION bulletVerticalDirection;
	Enums.DIRECTION bulletHorizontalDirection;
	float targetX2;
	float targetY2;
	float myX1 = 200;
	float myY1 = 200;
	float yIntercept = 0;
	float lineSlope = 0;
	float lineSlopeYcombined = 0;
	float lineSlopeXcombined = 0;
	float newY, newX;
	@Override
	
	
public void update(float time) {
		
		//First check if bullet is out of range
		if(this.rect.y < 0) {
	    	this.alive = false;
		} else {
				if (bulletVerticalDirection == Enums.DIRECTION.Up) {
					
					this.rect.y += newY;
					
					if (bulletHorizontalDirection == Enums.DIRECTION.Left) {
						
						this.rect.x -= newX;
						
					}else if(bulletHorizontalDirection == Enums.DIRECTION.Right){
						
						this.rect.x += newX;
					}
							
				}else if(bulletVerticalDirection == Enums.DIRECTION.Down){
					
					this.rect.y = this.rect.y - newY;
					
					if (this.bulletHorizontalDirection == Enums.DIRECTION.Left) {
						
						this.rect.x -= newX;
						
					}else if(bulletHorizontalDirection == Enums.DIRECTION.Right){
						
						this.rect.x += newX;
					}
				}
				
				this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
			
		}
	}

	
	public void reset() {
		
		this.game.fireTargetedPool.free(this);
		this.roatatryBulletSprite.setPosition(0,0);
		super.reset();
	}

}
