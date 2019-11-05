package com.me.ufoattack.game.Class.fires.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.ufoattack.game.MyGame;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AFireEnemy.FireType;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;

public class bullet extends AFireEnemy{

	public bullet() {
		super();
	}
	
	public static AFireEnemy.FireType fireType = AFireEnemy.FireType.oneCircle;
	AFireEnemy.FireType bulletType = AFireEnemy.FireType.oneCircle;
	float targetX2;
	float targetY2;
	Sprite  roatatryBulletSprite;
	Enums.DIRECTION bulletVerticalDirection;
	Enums.DIRECTION bulletHorizontalDirection;
	
	public void init(Rectangle parentRect,GameScreen game) {

		
		this.bulletType = bullet.fireType;
		
		if (!valuesInitialised) {
				this.game = game;
				this.rect = new Rectangle();
				this.region = new TextureRegion(this.game.myGame.tileSetTexture);
				this.rect.height = 20;
				this.rect.width = 7;
	
				if (this.bulletType == AFireEnemy.FireType.missile) {
					
					this.rect.height = 50;
					this.rect.width = 24;
					this.region.setRegionX(193);
					this.region.setRegionY(805);
					this.region.setRegionWidth((int) this.rect.width);
					this.region.setRegionHeight((int) this.rect.height);
					this.firePower = 7;
					this.speed = 1;
					this.targetingBullet = true;
					this.roatatryBullet = false;
					this.roatatryBulletSprite = new Sprite(this.game.myGame.tileSetTexture,193,805,24,50);
					this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
					
				}
			
				else if (this.bulletType == AFireEnemy.FireType.oneCircle) {
				
					this.rect.height = 11;
					this.rect.width = 11;
					this.region.setRegionX(672);
					this.region.setRegionY(0);
					this.region.setRegionWidth((int) this.rect.width);
					this.region.setRegionHeight((int) this.rect.height);
					this.firePower = 7;
					this.speed = 2;
					this.targetingBullet = true;
				
			}else if (this.bulletType == AFireEnemy.FireType.twoCircle){
				
				this.rect.height = 11;
				this.rect.width = 28;
				this.region.setRegionX(672);
				this.region.setRegionY(0);
				this.region.setRegionWidth((int) this.rect.width);
				this.region.setRegionHeight((int) this.rect.height);
				this.firePower = 7;
				this.speed = 2;
				this.targetingBullet = true;
				this.roatatryBulletSprite = new Sprite(this.game.myGame.tileSetTexture,672,0,28,11);
				this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
				this.roatatryBullet = true;
				
			} else if (this.bulletType == AFireEnemy.FireType.oneDoubleCircle){
				
				this.rect.height = 28;
				this.rect.width = 11;
				this.region.setRegionX(672);
				this.region.setRegionY(0);
				this.region.setRegionWidth((int) this.rect.width);
				this.region.setRegionHeight((int) this.rect.height);
				this.firePower = 7;
				this.speed = 2;
				this.targetingBullet = true;
				this.roatatryBullet = false;
				
			}else if (this.bulletType == AFireEnemy.FireType.bulletSmallOne){
				
				this.rect.height = 15;
				this.rect.width = 5;
				this.region.setRegionX(416);
				this.region.setRegionY(96);
				this.region.setRegionWidth((int) this.rect.width);
				this.region.setRegionHeight((int) this.rect.height);
				this.firePower = 7;
				this.speed = 2;
				this.targetingBullet = true;
				this.roatatryBulletSprite = new Sprite(this.game.myGame.tileSetTexture,416,96,5,15);
				this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
				this.roatatryBullet = true;
				
			} else if (this.bulletType == AFireEnemy.FireType.bulletSmallTwo){
				
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
				this.roatatryBullet = true;
				
			}else if (this.bulletType == AFireEnemy.FireType.bulletSmallOneDouble){
				
				this.rect.height = 40;
				this.rect.width = 5;
				this.region.setRegionX(416);
				this.region.setRegionY(96);
				this.region.setRegionWidth((int) this.rect.width);
				this.region.setRegionHeight((int) this.rect.height);
				this.targetingBullet = true;
				this.roatatryBulletSprite = new Sprite(this.game.myGame.tileSetTexture,416,96,5,33);
				this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
				this.roatatryBullet = true;
				this.firePower = 7;
				this.speed = 2;
				
			}else if (this.bulletType == AFireEnemy.FireType.rocket){
				
				
				this.rect.height = 50;
				this.rect.width = 13;
				this.region.setRegionX(511);
				this.region.setRegionY(50);
				this.region.setRegionWidth((int) this.rect.width);
				this.region.setRegionHeight((int) this.rect.height);
				this.firePower = 7;
				this.speed = 2;
				this.roatatryBullet = true;
				this.roatatryBulletSprite = new Sprite(this.game.myGame.tileSetTexture,511,50,13,50);
				this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
			}
		}
		
		this.targetingBullet = true;
		
		if (this.bulletType == AFireEnemy.FireType.oneCircle || this.bulletType == AFireEnemy.FireType.oneDoubleCircle) {
			this.roatatryBullet = false;
		}
		if (this.roatatryBullet) {
			this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
		}
		this.rect.x = (parentRect.x + (parentRect.width /2) - 4);
		this.rect.y = parentRect.y;
		
		this.alive = true;
		
	}
	float lineSlope = 0;
	float lineSlopeYcombined = 0;
	float lineSlopeXcombined = 0;
	float newY;
	float newX;
	@Override
	public void update(float time) {
		
		//First check if bullet is out of range
		if(this.rect.y < 0) {
	    	this.alive = false;
		}else if(this.game.plane.rect.overlaps(this.rect)) {
			//Second check if bullet has hit plane
	    	this.game.plane.gotHit(1);
	    	this.alive = false;
		} else {
			//If it has neither hit the plane or has gone out of screen we update bullet
			//Update Target only Bullet
			if (targetingBullet) {
				//This happens only first time to get target coordinates , which is the plane
				if (!targetSet) {
					 Vector2 vectorPlane = new Vector2();
					 Vector2 vectorBullet = new Vector2();
					 this.game.plane.rect.getCenter(vectorPlane);
					 this.rect.getCenter(vectorBullet);
					 targetX2 = vectorPlane.x;
		    		 targetY2 = vectorPlane.y;
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
			    	  
			    	  
			    	  if (this.roatatryBullet){
							 
							 float angle = Enums.getAngle(
										this.game.plane.rect.x + (this.game.plane.rect.width/2),
										this.game.plane.rect.y + (this.game.plane.rect.height/2),
										this.rect.x + (this.rect.width/2),
										this.rect.y + (this.rect.height/2));
							 
							 this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
							 this.roatatryBulletSprite.rotate(-angle);
					 }
			    	  
			    	 targetSet = true;
			    	 /*
			    	Gdx.app.debug("Bullet", "slope:" +Float.toString(lineSlope) 
			    			+ " newX:" +Float.toString(newX) 
			    			+ " newY:" +Float.toString(newY) 
			    			+ " X:" +Float.toString(this.rect.x)
			    			+ " Y:" +Float.toString(this.rect.y)
			    			+ " X2:" +Float.toString(targetX2)
			    			+ " Y2:" +Float.toString(targetY2));
			    	 */
				}
				
				
				//this.rect.y = bulletVerticalDirection == Enums.DIRECTION.Up ? this.rect.y + 4 : this.rect.y - 4;
				//this.rect.x =  bulletVerticalDirection == Enums.DIRECTION.Right ? this.rect.x + 4 : this.rect.x - 4;
				
				
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
				
				if (this.roatatryBullet){

					 this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);

				}
				
				

	    		//this.rect.y += lineSlope;
			} 
			//Update Non Target only Bullet
			else {
				this.rect.y -= this.speed;
			}
		}
	}

	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		if (this.roatatryBullet){
			 
		        this.roatatryBulletSprite.draw(batch);
		}else{
			batch.draw(this.region, this.rect.x, this.rect.y,this.rect.width, this.rect.height);
		}
		
	}
	
	
	

}
