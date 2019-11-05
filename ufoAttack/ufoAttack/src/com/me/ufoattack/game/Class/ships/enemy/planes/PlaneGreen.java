package com.me.ufoattack.game.Class.ships.enemy.planes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Abstracts.APowerUp.PowerUpType;
import com.me.ufoattack.game.Abstracts.AShipEnemy.DirectionRotation;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.powerups.CPowerUp;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate.GliderType;
import com.me.ufoattack.game.screens.GameScreen;

public class PlaneGreen extends AShipEnemy{


    AHive hive;
    
	public PlaneGreen() {
		super();
		
	}
	public static final Pool<PlaneGreen>planeGreenPool = Pools.get(PlaneGreen.class);
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
		
		if (!valuesInitialised) {
			
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 64;
			this.rect.width= 64;
			this.health = 20;
			this.firingSpeed = 2.0f;

			this.planeAnimationFramesRegion = new TextureRegion[3];
			int spriteX = 480;
			int spriteY = 320;
			for (int i=0;i<this.planeAnimationFramesRegion.length;i++){
				this.planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.tileSetTexture, spriteX, spriteY, 64, 64);
				spriteX+=64;
			}

			this.planeAnimation = new Animation(1/3f, this.planeAnimationFramesRegion);
			this.planeSprite = new Sprite(this.game.myGame.newTexture,480,320,64,64);
			this.planeSprite.setPosition(this.rect.x,this.rect.y);
			
			valuesInitialised = true;
			
		}
		
		this.rect.x = x;
		this.rect.y = y;
		
		currentFrameRegion = planeAnimation.getKeyFrame(0, true);
		this.shipDirection = AShipEnemy.DIRECTION.Down;
		
		
		
		this.alive = true;

	}
	
	public void explosionFinished() {		
    	this.game.level.scores.increasePlaneShot();
		super.explosionFinished();
		
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		stateTime+=deltaTime;
		if (!handlingHit) {
			
			currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
	       // batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
	        
	        this.planeSprite.setPosition(this.rect.x, this.rect.y);
	        this.planeSprite.setRegion(currentFrameRegion);
	        this.planeSprite.draw(batch);
		}
		
		super.render(batch, deltaTime);
	}
	
	

	int diveAfterCount = 0;
	boolean dive = false;
	
	public void move() {


		
		if (this.shipRotation == DirectionRotation.zeroSidewaysLeft){
			if (this.rect.x <= 5){
				this.shipRotation = DirectionRotation.zeroSidewaysRight;
				diveAfterCount++;
			}else{
				this.rect.x-=2;
			}
		}else if(this.shipRotation == DirectionRotation.zeroSidewaysRight){
			if (this.rect.y+this.rect.height >= (this.game.rectangleScreenSize.width)){
				this.shipRotation = DirectionRotation.zeroSidewaysLeft;
				diveAfterCount++;
			}else{
				this.rect.x+=2;
			}
		}
		
		if (diveAfterCount > 1) {
			dive = true;
			
			
			this.rect.y-=2;

		}
	}
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		if (lastUpdated >= this.firingSpeed) {
			
			bullet.fireType = AFireEnemy.FireType.oneCircle;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			fire.setX(this.rect.x + this.rect.width/2);
			fire.setY(this.rect.y + 10);
			this.game.level.activeBullets.add(fire);
			lastUpdated = 0;
			
		}
		
		rotateSpriteBasedOnSetDirection();
		super.update(currentTime);
	    
	}
}
