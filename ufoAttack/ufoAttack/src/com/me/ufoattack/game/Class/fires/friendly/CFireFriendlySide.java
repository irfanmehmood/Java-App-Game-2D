package com.me.ufoattack.game.Class.fires.friendly;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.Abstracts.AFireFriendly;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.screens.GameScreen;

public class CFireFriendlySide extends AFireFriendly{

	public CFireFriendlySide() {
		super();
	}
	
	protected TextureRegion leftBulletregion;
	protected TextureRegion righrBulletregion;
	protected Rectangle rectleftBullet;
	protected Rectangle rectrighrBullet;
	public void init(Rectangle parentRect,  GameScreen game) {
		
		int width = 116;
		int sideFiresWidth = 24;
		int sideFiresHeight = 16;
		int height = 16;
		
		if (!valuesInitialised) {
			this.game = game;
			
			this.rect = new Rectangle();
			this.rectleftBullet = new Rectangle();
			this.rectrighrBullet = new Rectangle();
			
			this.region = new TextureRegion(this.game.myGame.newTexture);
			this.leftBulletregion = new TextureRegion(this.game.myGame.newTexture);
			this.righrBulletregion = new TextureRegion(this.game.myGame.newTexture);
			
			this.rect.height = 16;
			this.rect.width = width;
			this.region.setRegion(0, 531, width ,height);
			this.firePower = 7;
			
			valuesInitialised = true;
			
		}
		
		
		switch(this.game.plane.fireUpgrade) {
		
		case 7:
			sideFiresWidth = 24;
			break;
		case 8:
			sideFiresWidth = 54;
			break;
		case 9:
			sideFiresWidth = 84;
			break;
		}
		
		this.speed = 8;
		this.leftBulletregion.setRegion( 1,564,sideFiresWidth,sideFiresHeight);
		this.righrBulletregion.setRegion( 1,548,sideFiresWidth,sideFiresHeight);
		
		this.rect.x = parentRect.x + (parentRect.width/2) - (this.rect.width/2);
		this.rect.y = parentRect.y + (parentRect.height);
		
		this.rectleftBullet = new Rectangle(this.rect.x -sideFiresWidth,this.rect.y, sideFiresWidth ,sideFiresHeight);
		this.rectrighrBullet = new Rectangle(this.rect.x +this.rect.width,this.rect.y, sideFiresWidth ,sideFiresHeight);
		
		
		this.alive = true;
	}
	
	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		batch.draw(this.region, this.rect.x, this.rect.y, this.rect.width, this.rect.height);
		batch.draw(this.leftBulletregion, this.rectleftBullet.x, this.rectleftBullet.y, this.rectleftBullet.width, this.rectleftBullet.height);
		batch.draw(this.righrBulletregion, this.rectrighrBullet.x, this.rectrighrBullet.y, this.rectrighrBullet.width, this.rectrighrBullet.height);
	}

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
				      }
		    	  }
		         
		      }
		}  
	      if (this.rect.y < this.game.rectangleScreenSize.height) {
				// TODO Auto-generated method stub
				this.rect.y += this.speed;
				this.rectleftBullet.y += this.speed;
				this.rectrighrBullet.y += this.speed;
				this.rectleftBullet.x -= this.speed;
				this.rectrighrBullet.x += this.speed;
	      } else {
	    	  this.alive = false;
	      }
	}

}
