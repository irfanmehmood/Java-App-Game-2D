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
import com.me.ufoattack.game.Class.explosions.CExplosion;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderDive;
import com.me.ufoattack.game.screens.GameScreen;

public class Submarine extends AShipEnemy{


    AHive hive;
    Animation goInWaterAnimation;
	TextureRegion[] goInWaterFramesRegion;
	TextureRegion goInWaterCurrentFrameRegion;
	
	public static final Pool<Submarine>submarinePool = Pools.get(Submarine.class);
	public void explosionFinished() {
		
    	this.hive.ships.removeValue(this, true);
    	Submarine.submarinePool.free((this));
		super.explosionFinished();
		this.game.level.scores.increaseSubmarineShot();
	}
    
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		
		// In case we need to be part of a new hive
		this.hive = hive;
		
		//To save resources this shall only be called once when the object is created first time
		if (!valuesInitialised) {

			int spriteX = 532;
			int spriteY = 103;
			
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 196;
					
			this.planeAnimationFramesRegion = new TextureRegion[6];
			for (int i=0;i<this.planeAnimationFramesRegion.length;i++){
				this.planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.gameTexture, spriteX, spriteY,  32, 98);
				spriteX-=33;
			}
			this.planeAnimation = new Animation(1/3f, this.planeAnimationFramesRegion);
			
			spriteX = 367;
			this.goInWaterFramesRegion = new TextureRegion[6];
			for (int i=0;i<this.goInWaterFramesRegion.length;i++){
				this.goInWaterFramesRegion[i] =  new TextureRegion(this.game.myGame.gameTexture, spriteX, spriteY,  32, 98);
				spriteX+=33;
			}
			this.goInWaterAnimation = new Animation(1/3f, this.goInWaterFramesRegion);
					
			valuesInitialised = true;
		}
		
		//This needs to be set each time
		this.rect.x = x;
		this.rect.y = y;
		this.firingSpeed = 5.0f;
		this.health = 5;
		this.loopAnimation = true;
		this.shipDirection = AShipEnemy.DIRECTION.IsUnderWater;
		this.alive = true;

	}
	
	public Submarine() {
		super();

	}
	
	boolean handled = false;
	float surfaceTimeCounter = 0;
	
	public void update(float currentTime) {
		

		if (this.shipDirection ==AShipEnemy.DIRECTION.Surfaced ) {
			surfaceTimeCounter += currentTime;
			if (surfaceTimeCounter>= 1/0.5f){
				surfaceTimeCounter = 0;
				lastUpdated=0;
				stateTime=0;
				this.shipDirection = AShipEnemy.DIRECTION.GoUnderWater;
			}
		}else if(this.shipDirection ==AShipEnemy.DIRECTION.RiseFromWater ){
			lastUpdated += currentTime;
			if (planeAnimation.isAnimationFinished(lastUpdated)){
				boolean direction = this.rect.x < this.game.rectangleScreenSize.width /2 ? true : false;
				bullet.fireType = AFireEnemy.FireType.twoCircle;
				bullet fire = this.game.enemyBulletPool.obtain();
				fire.init(this.rect,this.game);
				this.game.level.activeBullets.add(fire);
				lastUpdated=0;
				stateTime=0;
				this.shipDirection = AShipEnemy.DIRECTION.Surfaced;
			}
	        
		}else if(this.shipDirection == AShipEnemy.DIRECTION.GoUnderWater ){
			lastUpdated += currentTime;
			if (goInWaterAnimation.isAnimationFinished(lastUpdated)){
				lastUpdated=0;
				stateTime=0;
				this.shipDirection =AShipEnemy.DIRECTION.IsUnderWater;
			}
		}else if(this.shipDirection == AShipEnemy.DIRECTION.IsUnderWater ){
			
		}
		
		super.update(currentTime);
	    
	}
	
	
	boolean setOnce = false;
	public void move() {
		if (this.shipDirection == AShipEnemy.DIRECTION.IsUnderWater){
			if ((this.rect.y+this.rect.height) > (this.game.rectangleScreenSize.height)){
				setOnce = false;
				this.rect.y = -200;
			}else if(this.shipDirection == AShipEnemy.DIRECTION.IsUnderWater && (this.rect.y+this.rect.height) >= (this.game.rectangleScreenSize.height/2) && setOnce == false){
				setOnce = true;
				this.shipDirection =  AShipEnemy.DIRECTION.RiseFromWater;
			}else{
				this.rect.y+=2;
			}
		}
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		if (!handlingHit) {
			if (this.shipDirection ==AShipEnemy.DIRECTION.Surfaced ) {
				batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
			}else if(this.shipDirection ==AShipEnemy.DIRECTION.GoUnderWater ){
				stateTime += deltaTime; 
				goInWaterCurrentFrameRegion = this.goInWaterAnimation.getKeyFrame(stateTime, false);
		        batch.draw(goInWaterCurrentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
			}else if(this.shipDirection == AShipEnemy.DIRECTION.RiseFromWater ){
				stateTime += deltaTime; 
		        currentFrameRegion = planeAnimation.getKeyFrame(stateTime, false);
		        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
			}else if(this.shipDirection ==AShipEnemy.DIRECTION.IsUnderWater ){
		        batch.draw(planeAnimationFramesRegion[0], this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
			}

		}
		super.render(batch, deltaTime);
	}

	
}
