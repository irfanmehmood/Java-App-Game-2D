package com.me.ufoattack.game.Class.ships.enemy.planes;

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
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate.GliderType;
import com.me.ufoattack.game.screens.GameScreen;

public class GliderDive extends AShipEnemy{


    AHive hive;
  //Up Animation
  	Animation goUpAnimation;
  	TextureRegion[] goUpFramesRegion;
  	TextureRegion goUpCurrentFrame;
  	
  	public GliderDive() {
  		super();
  	}
	
  	public void explosionFinished() {
		
    	this.hive.ships.removeValue(this, true);
    	GliderDive.GliderPool.free((this));
		super.explosionFinished();
		this.game.level.scores.increasePlaneShot();
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		if (!handlingHit) {
			if (this.shipDirection == AShipEnemy.DIRECTION.Down){
				currentFrameRegion = planeAnimation.getKeyFrame(stateTime, false);
		        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
			}else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
				stateTime+=deltaTime;
				goUpCurrentFrame = this.goUpAnimation.getKeyFrame(stateTime, true);
		        batch.draw(this.goUpCurrentFrame, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
			}
		}
		
		super.render(batch, deltaTime);
	}
	
	
	public void move() {
		
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			
			this.rect.y -= 4;
			
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
			
			if (this.rect.y > this.game.rectangleScreenSize.height + 200) {
				this.hive.ships.removeValue(this, true);
		    	GliderDive.GliderPool.free((this));
				this.alive = false;
				
			} else {
				
				this.rect.y += 4;
			}
			
		}
		
	}
	
	public void update(float currentTime) {
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			stateTime+=currentTime;
			if (planeAnimation.isAnimationFinished(stateTime)){
				stateTime = 0;
				this.shipDirection = AShipEnemy.DIRECTION.Up;
				bullet.fireType = AFireEnemy.FireType.oneCircle;
				bullet fire = this.game.enemyBulletPool.obtain();
				fire.init(this.rect,this.game);
				this.game.level.activeBullets.add(fire);
	        }
		}
		super.update(currentTime);
		
	    
	}
	
	public static final Pool<GliderDive>GliderPool = Pools.get(GliderDive.class);
	public static GliderRotate.GliderType gliderType = GliderRotate.GliderType.green;
	
	int spriteX = 565;
	int spriteY = 203;
	@Override
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
		
		//For initialising and making new Object we need this logic each time
		if (GliderDive.gliderType == GliderType.white) {
			spriteX = 565;
			this.health = 3;
		} else if (GliderDive.gliderType == GliderType.lightgreen) {
			spriteX = 598;
			this.health = 4;
		} else if (GliderDive.gliderType == GliderType.blue) {
			spriteX = 631;
			this.health = 5;
		} else if (GliderDive.gliderType == GliderType.green) {
			spriteX = 664;
			this.health = 5;
		} 
		
		
		//To save resources this shall only be called once when the object is created first time
		if (!valuesInitialised) {

			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 64;
			this.rect.width= 64;
			
			this.planeAnimationFramesRegion = new TextureRegion[5];
			this.planeAnimation = new Animation(1/1f, this.planeAnimationFramesRegion);
			
			this.goUpFramesRegion = new TextureRegion[3];
			this.goUpAnimation = new Animation(1/3f, this.goUpFramesRegion);
			
			valuesInitialised = true;
		}
		
		
		//This needs to be set each time
		this.rect.x = x;
		this.rect.y = y;
		this.health = 3;
		
		this.firingSpeed = 5.0f;
		this.shipDirection = AShipEnemy.DIRECTION.Down;
		
		
		for (int i=0;i<this.planeAnimationFramesRegion.length;i++){
			this.planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.gameTexture, spriteX, spriteY, 32, 32);
			spriteY+=33;
		}
		
		goUpFramesRegion[0] = this.planeAnimationFramesRegion[4];
		goUpFramesRegion[1] = new TextureRegion(this.game.myGame.gameTexture, 70, 367, 32, 32);
		goUpFramesRegion[2] = new TextureRegion(this.game.myGame.gameTexture, 103, 367, 32, 32);
		
		this.alive = true;
		
	}
	
}
