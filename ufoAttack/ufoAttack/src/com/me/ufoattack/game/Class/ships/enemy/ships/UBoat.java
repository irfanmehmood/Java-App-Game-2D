package com.me.ufoattack.game.Class.ships.enemy.ships;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.screens.GameScreen;

public class UBoat extends AShipEnemy{


    AHive hive;
    
    public UBoat() {
		super();
		
	}
    
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
				
		//To save resources this shall only be called once when the object is created first time
		if (!valuesInitialised) {
					
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 64;
			this.rect.width = 346;
					
			int spriteX = 0;
			int spriteY = 277;
			planeAnimationFramesRegion = new TextureRegion[4];
			for (int i=0;i<planeAnimationFramesRegion.length;i++){
				planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.newTexture, spriteX, spriteY,173, 32);
				spriteY+=33;
			}
			this.planeAnimation = new Animation(1/1f, this.planeAnimationFramesRegion);
			valuesInitialised = true;
		}
				
				
		this.rect.x = x;
		this.rect.y = y;
		this.firingSpeed = 5.0f;
		this.health = 40;
		this.loopAnimation = false;
		this.alive = true;
	}
	public static final Pool<UBoat>uBoatPool = Pools.get(UBoat.class);
	public void explosionFinished() {
		
    	this.hive.ships.removeValue(this, true);
    	UBoat.uBoatPool.free((this));
		super.explosionFinished();
		this.game.level.scores.increaseSubmarineShot();
	}
	
	public void move() {
		
		if (this.shipDirection != AShipEnemy.DIRECTION.Stop){
			if (this.rect.y+this.rect.height == (this.game.rectangleScreenSize.height)){
				this.rect.y = -200;
			}else{
				this.rect.x-=1;
			}
		}
	}
	
	
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		if (lastUpdated >= this.firingSpeed) {
			boolean direction = this.rect.x < this.game.rectangleScreenSize.width /2 ? true : false;
			bullet.fireType = AFireEnemy.FireType.oneCircle;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			this.game.level.activeBullets.add(fire);
			lastUpdated = 0;
		}
		
		super.update(currentTime);
	    
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		if (!handlingHit) {
			stateTime += deltaTime; 
	        currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
	        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
		}
		
		super.render(batch, deltaTime);
	}
}
