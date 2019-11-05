package com.me.ufoattack.game.Class.fires.friendly;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.Abstracts.AFireFriendly;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;

public class CFireFriendlyStraight extends AFireFriendly{

	public CFireFriendlyStraight() {
		super();
	}
	

	public void init(Rectangle parentRect,  GameScreen game) {
		
		int width = 16;		
		int height = 16;
		
		if (!valuesInitialised) {
			this.game = game;
			this.rect = new Rectangle();
			this.region = new TextureRegion(this.game.myGame.newTexture);
			
			
			valuesInitialised = true;
		}
		
		switch(this.game.plane.fireUpgrade) {
		
		case 1:
			width = 16;
			this.firePower = 2;
			break;
		case 2:
			width = 36;
			this.firePower = 3;
			break;
		case 3:
			width = 56;
			this.firePower = 4;
			break;
		case 4:
			width = 76;
			this.firePower = 5;
			break;
		case 5:
			width = 96;
			this.firePower = 6;
			break;
		case 6:
			width = 116;
			this.firePower = 7;
			break;
		
		}
		
		this.rect.height = 16;
		this.rect.width = width;
		this.region.setRegion(0, 531, width ,height);
		this.speed = 16;
		
		this.rect.x = parentRect.x + (parentRect.width/2) - (this.rect.width/2);
		this.rect.y = parentRect.y + (parentRect.height);
		this.alive = true;
	}
	
	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		

			batch.draw(this.region, this.rect.x, this.rect.y,this.rect.width, this.rect.height);
		
	}

	public float targetX2;
	public float targetY2;
	@Override
	public void update(float time) {
		
		
		
		
		if (this.firePower > 0) {
			Iterator<AHive> iter = this.game.level.hives.iterator();
			
			while(iter.hasNext()) {
		    	  AHive hive = iter.next();
		    	  Iterator<AShipEnemy> iterShips = hive.ships.iterator();
		    	  while(iterShips.hasNext()) {
			    	  AShipEnemy ship = iterShips.next();
			    	  
			    	  if(this.rect.overlaps(ship.rect) && !ship.isHandlingHit()) {
			    		  this.firePower = ship.gotHit(firePower);
			    		  if (this.firePower <= 0) {
			    			  this.alive = false;
			    		  }
				      }
		    	  }
		         
		      }
		}  
	    if (this.rect.y < this.game.rectangleScreenSize.height) {
			this.rect.y += this.speed;
				
			
	    } else {
	    	this.alive = false;
	    }
	}

}
