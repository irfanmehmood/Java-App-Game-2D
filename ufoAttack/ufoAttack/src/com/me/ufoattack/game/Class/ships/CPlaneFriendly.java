package com.me.ufoattack.game.Class.ships;



import java.util.Iterator;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Logger;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyEight;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyNine;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlySide;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyStraight;
import com.me.ufoattack.game.Class.fires.friendly.fireTargeted;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;


public class CPlaneFriendly extends AShip{

public int gotHit(int bulletValue) { 
		
		

		return bulletValueLeft;

	}
	public CPlaneFriendly(GameScreen game, int x, int y) {
		super();

		this.game = game;
		this.rect = new Rectangle();
		this.rect.x = x;
		this.rect.y = y;
		
		this.rect.height = 128;
		this.rect.width= 128;
		
		this.rect.x = (game.rectangleScreenSize.width / 2) - (this.rect.width /2);
		this.rect.y = 0;
		

		this.firingSpeed = 1.0f;
		

		this.planeAnimationFramesRegion = new TextureRegion[3];
		this.planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.gameTexture, 301, 103, 64, 64);
		this.planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.gameTexture, 301, 169, 64, 64);
		this.planeAnimationFramesRegion[2] = new TextureRegion(this.game.myGame.gameTexture, 301, 235, 64, 64);
		this.planeAnimation = new Animation(1/30f, this.planeAnimationFramesRegion);
		this.health = 5;
	}
	
	boolean handled = false;
	float moveMent = 8;

	float LastX = 0;
	float LastY = 0;
	float currentX = 0;
	float currentY = 0;
	boolean firstTimeTouch;
	
	
	public void checkInput() {
		
		/* This is for phone tilt 
		float accelX = Gdx.input.getAccelerometerX();
		float accelY = Gdx.input.getAccelerometerY();
		
		//Gdx.app.debug("CFriendlyPlane", Float.toString(accelX));
		//Gdx.app.debug("CFriendlyPlane", Float.toString(accelY));
		//Gdx.app.debug("CFriendlyPlane", Float.toString(accelY));
		if(accelX < -1.0 || accelX > 1.0){ 
			handled = true;
			if (accelX < 0) {
				this.moveRight();
			}else {
				this.moveLeft();
			}
        }else if(accelY > 6.0 && accelY < 7.0){
        	handled = true;
        	//this.moveUp();
        	
        } else if(accelY > 9.1){
        	handled = true;
        	//this.moveDown();
        	//Gdx.app.debug("CFriendlyPlane", Float.toString(accelY));
        }
		*/
		
		
		
		if(Gdx.input.isTouched()) {
			
			 Vector3 touchPos = new Vector3();
	         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	         this.game.myGame.getCamera().unproject(touchPos);
	         
	         /*
	         currentX = Gdx.input.getX();
	         currentY = Gdx.input.getY();
	         
			 if (firstTimeTouch) {
				 LastX = Gdx.input.getX();
				 LastY = Gdx.input.getY();
				 firstTimeTouch = false;
			 } else {
				 
				 if (LastX < currentX) {
					 
					 this.rect.x+=  4.0f;
					 handled = true;
					 Gdx.app.debug("Going right", "LastX:"+ Float.toString(LastX) +  " currentX:"+ Float.toString(currentX));
					 LastX = currentX;
				 } else if  (LastX > currentX){ 
					 LastX = currentX;
					 this.rect.x -=  4.0f;
					 handled = true;
					 Gdx.app.debug("Going right", "LastX:"+ Float.toString(LastX) +  " currentX:"+ Float.toString(currentX));
				 }
				 
				 
				 if (currentY > LastY) {
					 
					 this.rect.y -=  4.0f;
					 handled = true;
					 Gdx.app.debug("Going Down", "LastY:"+ Float.toString(LastY) +  " currentY:"+ Float.toString(currentY));
					 LastY = currentY;
				 } else if  (currentY < LastY){ 
					 
					 this.rect.y +=  4.0f;
					 handled = true;
					 Gdx.app.debug("Going Up", "LastY:"+ Float.toString(LastY) +  " currentY:"+ Float.toString(currentY));
					 LastY = currentY;
				 }
				 
			 }
			 
			 */
	         
	         this.rect.x = Gdx.input.getX() - this.rect.width / 2;
	         this.rect.y = this.game.rectangleScreenSize.height - (Gdx.input.getY()) + this.rect.height / 2;

			 
	    	 //Gdx.app.debug("touchPos", "Y:"+ Float.toString(touchPos.y) + " Gdx.input.getY():"+ Float.toString(Gdx.input.getY()));
	         /*
	    	 Vector3 touchPos = new Vector3();
	         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	         
	         this.game.myGame.getCamera().unproject(touchPos);
	         /*
	         if ((Gdx.input.getX() > 0 && Gdx.input.getX() < 64)){
	        	 
	        	 CBomb Bomb = this.CBombPool.obtain();
	        	 Bomb.init(this.plane.rect,this);
				 this.activeBullets.add(Bomb);
	         }
	  
	         
	         Gdx.app.debug("Touch after", "X:"+ Float.toString(touchPos.x) + "Y:"+ Float.toString(touchPos.y));*/
	    }
		
		
		
		/*
		if(!handled && Gdx.input.isKeyPressed(Keys.RIGHT)) {
			handled = true;
			this.setX(this.getRectangle().x + 4.0f);
			
		}
	    if(!handled && Gdx.input.isKeyPressed(Keys.LEFT)) {
	    	handled = true;
	    	this.setX(this.getRectangle().x - 4.0f);
	    }
	    if(!handled && Gdx.input.isKeyPressed(Keys.UP)) {
	    	handled = true;
	    	//this.setY(this.getRectangle().y + 1.0f);
	    	this.game.myGame.getCamera().position.y++;
	    	this.game.myGame.getCamera().update();
	    }
	    if(!handled && Gdx.input.isKeyPressed(Keys.DOWN)) {
	    	//this.setY(this.getRectangle().y - 4.0f);
	    }
	    
	    */
	    handled = false;
	}
	
	public void moveLeft() {
		if (this.rect.x > this.game.rectangleScreenSize.x) {
			this.rect.x -= this.moveMent/2;
		}
	}
	
	public void moveUp() {
		
		if (this.rect.y  <  (this.game.rectangleScreenSize.height/2)) {
			this.rect.y += this.moveMent;
		}
	}
	
	public void moveDown() {
		
		if (this.rect.y + 128 > 0) {
			this.rect.y -= this.moveMent;
			
		}else{
			this.rect.y = 0;
		}
		
	}
	
	public void moveRight() {
		if ((this.rect.x + this.rect.width) <  this.game.rectangleScreenSize.width) {
			this.rect.x += this.moveMent/2;
		}
	}
	
	
	
	public void upgradeFire() {
		if (fireUpgrade < 9) {
			fireUpgrade = fireUpgrade + 1;
		}
		Gdx.app.debug("fireUpgrade level:", Integer.toString(this.fireUpgrade));
	}
	
	public void addLife() {
		this.health++;
	}
	
	public void upgradeSpeed() {
		if (this.firingSpeed - this.fireSpeedIncrease > 0){
			this.firingSpeed -= this.fireSpeedIncrease;
		}
		Gdx.app.debug("upgradeSpeed level:", Float.toString(this.firingSpeed));
	}
	
	
	float rocketTicks = 3;
	float rocketTicksCount = 0;
	public int fireUpgrade = 6;
	float fireSpeedIncrease = 10/1f;
	
	
	
	public void update(float currentTime) {
		
		this.firingSpeed = 0.5f;
		
		lastUpdated+=currentTime;
		if (lastUpdated >= this.firingSpeed) {
			rocketTicksCount++;
			lastUpdated = 0;
			
			if (rocketTicksCount >= rocketTicks) {
				
				CFireFriendlyRockets rocketLeft = this.game.CFireFriendlyRocketsPool.obtain();
				
				rocketLeft.direction = Enums.DIRECTION.Left;
				rocketLeft.init(this.rect,  this.game);
				this.game.level.activeBullets.add(rocketLeft);
				
				CFireFriendlyRockets rocketRight = this.game.CFireFriendlyRocketsPool.obtain();
				
				rocketRight.direction = Enums.DIRECTION.Right;
				rocketRight.init(this.rect,  this.game);
				this.game.level.activeBullets.add(rocketRight);
				rocketTicksCount = 0;
				/**/
				
				Gdx.app.debug("Bullets count:", Integer.toString(this.game.level.activeBullets.size));
			}
			
			
			if (this.fireUpgrade <= 6) {
				int y = 0;
				/*
				
					CFireFriendlyStraight fireStraight =  this.game.CFireFriendlyStraightPool.obtain();
					fireStraight.init(this.rect, this.game);
					fireStraight.setY(fireStraight.getRectangle().y +  y );
					this.game.level.activeBullets.add(fireStraight);
					*/
				
				if (AShipEnemy.IsMyTargetShipOne != null){
					fireTargeted fire =  this.game.fireTargetedPool.obtain();
					fire.init(this.rect, this.game);
					this.game.level.activeBullets.add(fire);
				}
			}else {
				CFireFriendlySide fireSided = this.game.CFireFriendlySidePool.obtain();
				fireSided.init(this.rect, this.game);
				this.game.level.activeBullets.add(fireSided);
			}

			
		}
		if (AShipEnemy.IsMyTargetShipOne != null) {

			Vector2 targetPlane = new Vector2();
			AShipEnemy.IsMyTargetShipOne.rect.getCenter(targetPlane);
			
			
			
			
			this.game.shapeRenderer.begin(ShapeType.Line);
			this.game.shapeRenderer.setColor(Color.RED);
			
			this.game.shapeRenderer.line(
						this.rect.x + (this.rect.width /2), 
						this.rect.y+(this.rect.height/2), 
						targetPlane.x,
						targetPlane.y);
			
			
			this.game.shapeRenderer.line(
					this.rect.x + (this.rect.width /2), 
					this.rect.y+(this.rect.height/2), 
					700, 
					600);
			
	
			
			
			this.game.shapeRenderer.end();
		}
		super.update(currentTime);
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		if (!handlingHit) {
			stateTime += deltaTime; 
	        currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
	        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
		}
		
		
		
		
		
		
	}
}
