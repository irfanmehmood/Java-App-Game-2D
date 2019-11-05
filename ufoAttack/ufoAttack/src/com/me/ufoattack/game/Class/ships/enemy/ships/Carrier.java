package com.me.ufoattack.game.Class.ships.enemy.ships;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.screens.GameScreen;

public class Carrier extends AShipEnemy{


    AHive hive;
    
	public Carrier() {
		super();
	}
	
	public static final Pool<Carrier>carrierPool = Pools.get(Carrier.class);
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		
		// In case we need to be part of a new hive
		this.hive = hive;
						
		//To save resources this shall only be called once when the object is created first time
		if (!valuesInitialised) {
			
			this.game = game;
			this.rect = new Rectangle();
			
			
			this.loopAnimation = false;
			this.rect.height = 64;
			this.rect.width = 648;
			
			this.planeAnimationFramesRegion = new TextureRegion[2];
			this.planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.newTexture, 0, 442, 306, 32);
			this.planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.newTexture, 0, 475, 306, 32);

			this.planeAnimation = new Animation(1/2f, this.planeAnimationFramesRegion);
			
			valuesInitialised = true;
		}
				
		
		this.rect.x = x;
		this.rect.y = y;
		this.hive = hive;
		
		this.firingSpeed = 5.0f;
		this.health = 40;
		this.alive = true;

	}
    
	public void explosionFinished() {
		
    	this.hive.ships.removeValue(this, true);
    	Carrier.carrierPool.free((this));
		super.explosionFinished();
		this.game.level.scores.increaseSubmarineShot();

	}
	
	public void move() {
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Right){
			if (this.rect.x+this.rect.width >= (this.game.rectangleScreenSize.width)){
				this.shipDirection = AShipEnemy.DIRECTION.Left;
			}else{
				this.rect.x+=1;
			}
		}else if(this.shipDirection == AShipEnemy.DIRECTION.Left){
			if (this.rect.x<0){
				this.shipDirection = AShipEnemy.DIRECTION.Right;
			}else{
				this.rect.x-=1;
			}
		}
	}
	
	
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		if (lastUpdated >= this.firingSpeed) {
			boolean direction = this.rect.x < this.game.rectangleScreenSize.width /2 ? true : false;
			bullet.fireType = AFireEnemy.FireType.bulletSmallOneDouble;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			this.game.level.activeBullets.add(fire);
			lastUpdated = 0;
		}
		
		
		
		this.game.shapeRenderer.begin(ShapeType.Line);
		this.game.shapeRenderer.setColor(Color.RED);
		this.game.shapeRenderer.line(this.rect.x + (this.rect.width /2), this.rect.y+this.rect.height, this.game.plane.rect.x+( this.game.plane.rect.width/2), this.game.plane.rect.y-(this.game.plane.rect.height/2));
		this.game.shapeRenderer.end();
		
		
		
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
