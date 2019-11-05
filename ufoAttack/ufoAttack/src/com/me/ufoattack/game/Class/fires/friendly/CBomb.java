package com.me.ufoattack.game.Class.fires.friendly;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.Abstracts.AFireFriendly;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.screens.GameScreen;

public class CBomb extends AFireFriendly{

	protected Animation planeAnimation;
	protected TextureRegion[] planeAnimationFramesRegion;
	protected TextureRegion currentFrameRegion;
	
	protected float stateTime = 1/30f;
	
	
	public CBomb() {
		super();
	}
	
	public void init(Rectangle parentRect, GameScreen game) {

		this.rect.height = 64;
		this.rect.width = 64;
		this.rect.x = parentRect.x + (parentRect.width/2) - (this.rect.width/2);
		this.rect.y = this.game.rectangleScreenSize.y + (this.rect.height);
		this.region.setRegion(268, 268, 32 ,32);
		this.speed = 2;
		
		
		int spriteX = 4;
		planeAnimationFramesRegion = new TextureRegion[7];
		for (int i=0;i<planeAnimationFramesRegion.length;i++){
			planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.gameTexture, spriteX, 301,64, 64);
			spriteX+=66;
		}
		
		//Empty frame
		planeAnimationFramesRegion[planeAnimationFramesRegion.length - 1] = new TextureRegion(this.game.myGame.gameTexture, 364, 68, 32, 32);

        planeAnimation = new Animation(1/30f, planeAnimationFramesRegion);
        
        stateTime = 0f;
		
		
		
	}
	
	boolean blowUp = false;
	boolean finished = false;
	int animationCount = 0;
	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		if (blowUp) {
			if (!finished) {
				stateTime += deltaTime;
		        if (planeAnimation.isAnimationFinished(stateTime) && animationCount > 2){
		        	finished = true;
		        	this.alive = false;
		        } else if(planeAnimation.isAnimationFinished(stateTime)){
		        	animationCount++;
		        	stateTime = 0;
		        } else {
		        	
			        currentFrameRegion = planeAnimation.getKeyFrame(stateTime, false);
			        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
		        }
			}
		} else {
			batch.draw(this.region, this.rect.x, this.rect.y, this.rect.width, this.rect.height);
		}
	}

	@Override
	public void update(float time) {
		
		if (blowUp) {
			Iterator<AHive> iter = this.game.level.hives.iterator();
			while(iter.hasNext()) {
		    	  AHive hive = iter.next();
		    	  Iterator<AShipEnemy> iterShips = hive.ships.iterator();
		    	  while(iterShips.hasNext()) {
			    	  AShipEnemy ship = iterShips.next();
			    	  
			    	  if(this.rect.overlaps(ship.rect) && !ship.isHandlingHit()) {
			    		  ship.gotHit(20);
				      }
		    	  }
		         
		     }
		} else {
			
			if (this.rect.y < this.game.rectangleScreenSize.height) {
	    	  	if (this.rect.y > this.game.rectangleScreenSize.height/2){
	    	  		blowUp = true;
	    	  		this.rect.width = 512;
	    	  		this.rect.x = (this.rect.x - (this.rect.width/2)) + 32;
	    	  		
	    	  		this.rect.height = 512;
	    	  		this.rect.y = (this.rect.y - (this.rect.height/2)) + 32;
	    	  	}else{
	    	  		
	    	  		this.rect.y += this.speed;
	    	  	}
				
			} else {
	    	  this.alive = false;
			}
			
		}
	      
	      
	}

}
