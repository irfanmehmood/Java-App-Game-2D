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

public class CFireFriendlyEight extends AFireFriendly{

	public CFireFriendlyEight() {
		super();
	}
	
	protected TextureRegion leftBulletregion;
	protected TextureRegion leftHorizontalBulletRegion;
	protected TextureRegion rightHorizontalBulletRegion;
	protected Rectangle rectleftHorizontalBullet;
	protected Rectangle rectrightHorizontalBullet;
	protected TextureRegion righrBulletregion;
	protected Rectangle rectleft1Bullet;
	protected Rectangle rectleft2Bullet;
	protected Rectangle rectleft3Bullet;
	protected Rectangle rectright1Bullet;
	protected Rectangle rectright2Bullet;
	protected Rectangle rectright3Bullet;
	public void init(Rectangle parentRect, GameScreen game) {
	
		this.rect.height = 32;
		this.rect.width = 32 * 4;
		this.rect.x = parentRect.x;
		this.rect.y = this.game.rectangleScreenSize.y + (this.rect.height * 3);
		this.region.setRegion(4, 202, 32 ,32);
		this.leftBulletregion = new TextureRegion(this.game.myGame.gameTexture, 103,235,32,32);
		this.righrBulletregion = new TextureRegion(this.game.myGame.gameTexture, 70,235,32,32);
		
		this.leftHorizontalBulletRegion = new TextureRegion(this.game.myGame.gameTexture, 169,235,32,32);
		this.rightHorizontalBulletRegion = new TextureRegion(this.game.myGame.gameTexture, 136,235,32,32);
		
		rectleftHorizontalBullet = new Rectangle(this.rect.x,this.rect.y-64, 32 ,32);
		rectrightHorizontalBullet = new Rectangle(this.rect.x +this.rect.width,this.rect.y-64, 32 ,32);
		this.rectleft1Bullet = new Rectangle(this.rect.x -64,this.rect.y + 32, 32 ,32);
		this.rectleft2Bullet = new Rectangle(this.rect.x -32,this.rect.y + 32, 32 ,32);
		this.rectleft3Bullet = new Rectangle(this.rect.x,this.rect.y + 32, 32 ,32);
		
		
		this.rectright1Bullet = new Rectangle(this.rect.x + this.rect.width-32,this.rect.y + 32, 32 ,32);
		this.rectright2Bullet = new Rectangle(this.rect.x + this.rect.width,this.rect.y + 32, 32 ,32);
		this.rectright3Bullet = new Rectangle(this.rect.x + this.rect.width+32,this.rect.y + 32, 32 ,32);
		this.speed = 16;
		
	}
	
	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		
		batch.draw(this.leftBulletregion, this.rectleft1Bullet.x, this.rectleft1Bullet.y,32,32);
		batch.draw(this.leftBulletregion, this.rectleft2Bullet.x, this.rectleft2Bullet.y,32,32);
		batch.draw(this.leftBulletregion, this.rectleft3Bullet.x, this.rectleft2Bullet.y,32,32);
		batch.draw(this.region, this.rect.x, this.rect.y);
		batch.draw(this.region, this.rect.x + 32, this.rect.y);
		batch.draw(this.region, this.rect.x + 64, this.rect.y);
		batch.draw(this.region, this.rect.x + 96, this.rect.y);
		batch.draw(this.righrBulletregion, this.rectright1Bullet.x, this.rectright1Bullet.y,32,32);
		batch.draw(this.righrBulletregion, this.rectright2Bullet.x, this.rectright2Bullet.y,32,32);
		batch.draw(this.righrBulletregion, this.rectright3Bullet.x, this.rectright2Bullet.y,32,32);
		batch.draw(this.leftHorizontalBulletRegion, this.rectleftHorizontalBullet.x, this.rectleftHorizontalBullet.y,32,32);
		batch.draw(this.rightHorizontalBulletRegion, this.rectrightHorizontalBullet.x, this.rectrightHorizontalBullet.y,32,32);
		
	}

	@Override
	public void update(float time) {
		
		
		
		Iterator<AHive> iter = this.game.level.hives.iterator();
		
		while(iter.hasNext()) {
	    	  AHive hive = iter.next();
	    	  Iterator<AShipEnemy> iterShips = hive.ships.iterator();
	    	  while(iterShips.hasNext()) {
		    	  AShipEnemy ship = iterShips.next();
		    	  if (!ship.isHandlingHit()){
			    	  if(ship.rect.overlaps(this.rect) ||
			    		 ship.rect.overlaps(this.rectleft1Bullet) ||
			    		 ship.rect.overlaps(this.rectleftHorizontalBullet) ||
			    		 ship.rect.overlaps(this.rectleft2Bullet) ||
			    		 ship.rect.overlaps(this.rectleft3Bullet) ||
			    		 ship.rect.overlaps(this.rectright1Bullet) ||
			    		 ship.rect.overlaps(this.rectrightHorizontalBullet) ||
			    		 ship.rect.overlaps(this.rectright2Bullet) ||
			    		 ship.rect.overlaps(this.rectright3Bullet)) {
			    		  if (ship.gotHit(firePower) <= 0){
			    			  this.alive = false;
			    		  }
				      }
				    	
		    	  }
	    	  }
	         
	      }
	      
	      if (this.rect.y < this.game.rectangleScreenSize.height) {
				// TODO Auto-generated method stub
				this.rect.y += this.speed;
				this.rectleft1Bullet.y += this.speed/2;
				this.rectleft2Bullet.y += this.speed/2;
				this.rectleft3Bullet.y += this.speed/2;
				
				
				this.rectright1Bullet.y += this.speed/2;
				this.rectright2Bullet.y += this.speed/2;
				this.rectright3Bullet.y += this.speed/2;
				
				
				this.rectleft1Bullet.x -= this.speed/2;
				this.rectleft2Bullet.x -= this.speed/2;
				this.rectleft3Bullet.x -= this.speed/2;
				
				this.rectright1Bullet.x += this.speed/2;
				this.rectright2Bullet.x += this.speed/2;
				this.rectright3Bullet.x += this.speed/2;
				
				rectleftHorizontalBullet.x -= this.speed/2;
				rectrightHorizontalBullet.x += this.speed/2;
	      } else {
	    	  this.alive = false;
	      }
	}

}
