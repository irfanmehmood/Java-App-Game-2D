package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyStraight;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class AShipEnemy extends AShip  implements Poolable {
	
	protected boolean valuesInitialised = false;
	public boolean alive = false;
	public float velocity = 4.0f;
	protected Sprite  planeSprite;
	public DIRECTION shipDirection = DIRECTION.Right;
	
	public DirectionRotation shipRotation = DirectionRotation.clockWise;
	
	public enum DIRECTION {
		 Right,
		 Up,
		 UpLeft,
		 UpRight,
		 Left,
		 Down,
		 DownRight,
		 DownLeft,
		 Stop,
		 Surfaced,
		 GoUnderWater,
		 IsUnderWater,
		 RiseFromWater
	 }
	
	public enum DirectionRotation {
		 zeroSidewaysLeft,
		 zeroSidewaysRight,
		 clockWise,
		 antiClockWise,
		 fortyFiveDive,
		 fortyFiveStraight,
		 threeOneFiveDive,
		 threeOneFiveStraight,
	 }
	
	public AShipEnemy() {
		super();
	}
	
	
	public static AShipEnemy IsMyTargetShipOne = null;
	public static boolean IsMyTargetShipOneAvailable = false;
	public static AShipEnemy IsMyTargetShipTwo = null;
	
	
	public void init(GameScreen game, float x, float y, AHive hive){
		
		this.rect.x = x;
		this.rect.y = y;
		
		
		this.alive = true;
	}
	
	
	public void fireBulletForHive() {
		
	}
	
	public void reset() {
		this.rect.x =0;
		this.rect.y =0;
		stateTime = 0;
		this.alive = false;
		
	}
	
	public abstract void move();
	
	public void update(float deltaTime) {
		
		
			if (AShipEnemy.IsMyTargetShipOne == null) {
					if (this.rect.y > 0 
							&& this.rect.y < this.game.rectangleScreenSize.height
							&& this.rect.x > 0 
							&& this.rect.x < this.game.rectangleScreenSize.width){
						
						AShipEnemy.IsMyTargetShipOne = this;					
						
						CFireFriendlyRockets.lastLeftRocketTargetX = this.rect.x+(this.rect.width/2);
						CFireFriendlyRockets.lastLeftRocketTargetY = this.rect.y+(this.rect.width/2);
						
						CFireFriendlyRockets.lastRightRocketTargetX = this.rect.x + (this.rect.width/2);
						CFireFriendlyRockets.lastRightRocketTargetY = this.rect.y+(this.rect.width/2);
					}
			}
		
		if (AShipEnemy.IsMyTargetShipOne != null) {
			if (!AShipEnemy.IsMyTargetShipOne.alive) {
				AShipEnemy.IsMyTargetShipOne = null;
			}else if (this.rect.y < 0 
					|| this.rect.y > this.game.rectangleScreenSize.height
					|| this.rect.x < 0 
					|| this.rect.x > this.game.rectangleScreenSize.width){
				AShipEnemy.IsMyTargetShipOne = null;
				
			}
		}
		super.update(deltaTime);

		
	}
	
	protected void rotateSpriteBasedOnSetDirection() {

		
		if (this.shipDirection == AShipEnemy.DIRECTION.Right){
			
			if (this.planeSprite.getRotation() !=  90f)
				this.planeSprite.rotate	(- this.planeSprite.getRotation() + 90f);

		}  else if (this.shipDirection == AShipEnemy.DIRECTION.Left){
			
			if (this.planeSprite.getRotation() != 270f)
				this.planeSprite.rotate	(- this.planeSprite.getRotation() + 270f);

		}  else if (this.shipDirection == AShipEnemy.DIRECTION.UpLeft){
			
			if (this.planeSprite.getRotation() != 225f)
				this.planeSprite.rotate	( -this.planeSprite.getRotation() + 225f);

		} else if (this.shipDirection == AShipEnemy.DIRECTION.UpRight){
			
			if (this.planeSprite.getRotation() != 135f)
				this.planeSprite.rotate	(- this.planeSprite.getRotation() + 135f);
			
		} else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
			
			if (this.planeSprite.getRotation() != 180f)
				this.planeSprite.rotate	(- this.planeSprite.getRotation() + 180f);

		}else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
			
			if (this.planeSprite.getRotation() != 315f)
			this.planeSprite.rotate	(- this.planeSprite.getRotation() + 315f);

		}  else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
			if (this.planeSprite.getRotation() != 45f)
				this.planeSprite.rotate	(- this.planeSprite.getRotation() + 45f);
		}
			else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			
			if (this.planeSprite.getRotation() != 0f)
			this.planeSprite.rotate	(- this.planeSprite.getRotation() + 0f);
			//this.planeSprite.rotate(180f);

		}
		//Gdx.app.debug("planeAnimation", "stateTime:" + Float.toString(stateTime) + " getKeyFrameIndex:" +Integer.toString(index));
		

	}
	
	public void explosionFinished() {
		
		this.alive = false;
		super.explosionFinished();
		
	}
	
	public void render(SpriteBatch batch, float deltaTime) {

	    super.render(batch, deltaTime);
	}
	
	public int gotHit(int bulletValue) { 
		
		if(this.getClass().toString().equals("class com.me.ufoattack.game.Class.ships.enemy.ships.Submarine")  && 
				this.shipDirection != DIRECTION.Surfaced) {
				return bulletValue;
		}
		
		return super.gotHit(bulletValue);

	}
	
	
	
}
