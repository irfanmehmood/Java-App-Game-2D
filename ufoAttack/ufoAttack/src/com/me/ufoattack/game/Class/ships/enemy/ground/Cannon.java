package com.me.ufoattack.game.Class.ships.enemy.ground;

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
import com.me.ufoattack.game.Class.ships.enemy.ships.UBoat;
import com.me.ufoattack.game.screens.GameScreen;

public class Cannon extends AShipEnemy{


    AHive hive;
    
    public Cannon() {
		super();
		
	}
    
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
				
		//To save resources this shall only be called once when the object is created first time
		if (!valuesInitialised) {
					
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 36;
			this.rect.width = 36;
					
			int spriteX = 75;
			int spriteY = 511;
			planeAnimationFramesRegion = new TextureRegion[4];
			for (int i=0;i<planeAnimationFramesRegion.length;i++){
				planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.newTexture, spriteX, spriteY,18, 18);
				spriteX+=18;
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
	public static final Pool<Cannon>CannonPool = Pools.get(Cannon.class);
	public void explosionFinished() {
		
		super.explosionFinished();
		this.game.level.scores.increaseSubmarineShot();
	}
	
	public void move() {
		
		
	}
	
	
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		
		if (lastUpdated >= this.firingSpeed) {
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

