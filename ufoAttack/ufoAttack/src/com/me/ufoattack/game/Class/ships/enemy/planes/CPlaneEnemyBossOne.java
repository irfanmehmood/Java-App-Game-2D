package com.me.ufoattack.game.Class.ships.enemy.planes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.screens.GameScreen;

public class CPlaneEnemyBossOne extends AShipEnemy{


    AHive hive;
    
	public CPlaneEnemyBossOne() {
		super();
		
	}
	Sprite  roatatryBulletSprite;
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
		
		if (!valuesInitialised) {
			
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 128;
			this.rect.width= 128;
			this.health = 100;
			this.firingSpeed = 2.0f;

			this.planeAnimationFramesRegion = new TextureRegion[3];
			int spriteX = 368;
			int spriteY = 235;
			for (int i=0;i<this.planeAnimationFramesRegion.length;i++){
				this.planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.gameTexture, spriteX, spriteY, 64, 64);
				spriteX+=66;
			}

			this.planeAnimation = new Animation(1/3f, this.planeAnimationFramesRegion);
			this.roatatryBulletSprite = new Sprite(this.game.myGame.newTexture,511,50,128,128);
			this.roatatryBulletSprite.setPosition(this.rect.x,this.rect.y);
			
			valuesInitialised = true;
			
		}
		
		this.rect.x = x;
		this.rect.y = y;
		
	
		this.shipDirection = AShipEnemy.DIRECTION.Down;
		
		
		
		this.alive = true;

	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		stateTime+=deltaTime;
		if (!handlingHit) {
			
			currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
	       // batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
	        
	        this.roatatryBulletSprite.setPosition(this.rect.x, this.rect.y);
	        this.roatatryBulletSprite.setRegion(currentFrameRegion);
	        this.roatatryBulletSprite.draw(batch);
		}
		
		super.render(batch, deltaTime);
	}
	
	

	
	public void move() {
		
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			if (this.rect.y+this.rect.height <= (this.game.rectangleScreenSize.height/2)){
				this.shipDirection = AShipEnemy.DIRECTION.Up;
			}else{
				this.rect.y-=2;
			}
		}else if(this.shipDirection == AShipEnemy.DIRECTION.Up){
			if (this.rect.y+this.rect.height >= (this.game.rectangleScreenSize.height)){
				this.shipDirection = AShipEnemy.DIRECTION.Down;
				this.roatatryBulletSprite.rotate(+180);
			}else{
				this.rect.y+=2;
			}
		}
	}
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		if (lastUpdated >= this.firingSpeed) {
			
			bullet.fireType = AFireEnemy.FireType.oneCircle;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			fire.setX(this.rect.x + 24);
			fire.setX(this.rect.y + 24);
			this.game.level.activeBullets.add(fire);
			lastUpdated = 0;
			
			bullet.fireType = AFireEnemy.FireType.oneCircle;
			bullet fire1 = this.game.enemyBulletPool.obtain();
			fire1.init(this.rect,this.game);
			fire1.setX(this.rect.x + this.rect.width - 24);
			fire.setY(this.rect.y + 24);
			this.game.level.activeBullets.add(fire1);
			lastUpdated = 0;
			
		}
		
		super.update(currentTime);
	    
	}
}
