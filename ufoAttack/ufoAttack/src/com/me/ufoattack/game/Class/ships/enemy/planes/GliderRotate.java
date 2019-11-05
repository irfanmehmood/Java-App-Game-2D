package com.me.ufoattack.game.Class.ships.enemy.planes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Abstracts.APowerUp.PowerUpType;
import com.me.ufoattack.game.Abstracts.AShipEnemy.DirectionRotation;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.powerups.CPowerUp;
import com.me.ufoattack.game.screens.GameScreen;


public class GliderRotate extends AShipEnemy{


    AHive hive;
    

	public enum GliderType {green, orange, white, blue, lightgreen}
	public static final Pool<GliderRotate>GliderPool = Pools.get(GliderRotate.class);
	public static GliderRotate.GliderType gliderType = GliderRotate.GliderType.green;
	
	public static int orangeGlidersDestroyed = 0;
	public static int greenGlidersDestroyed = 0;
	
	public GliderRotate() {
		super();
	}
	
	
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		stateTime+=deltaTime;
		if (!handlingHit) {
			
			currentFrameRegion = planeAnimation.getKeyFrame(stateTime, false);
	        
	        this.planeSprite.setPosition(this.rect.x, this.rect.y);
	        this.planeSprite.setRegion(currentFrameRegion);
	        this.planeSprite.draw(batch);
		}
		
		super.render(batch, deltaTime);
		
		
	}
	
	public void explosionFinished() {
		
		
		if (this.gliderType == GliderType.green) {
			GliderRotate.greenGlidersDestroyed++;
			if (GliderRotate.greenGlidersDestroyed > 3) {
				CPowerUp pointsStar = this.game.level.powerUpPool.obtain();
				this.game.level.activePowerUp.add(pointsStar);
				pointsStar.init(this.game,this.rect,PowerUpType.pointsGoldStar);
				GliderRotate.greenGlidersDestroyed = 0;
			}
		} else if (this.gliderType == GliderType.orange) {
			GliderRotate.orangeGlidersDestroyed++;
			if (GliderRotate.orangeGlidersDestroyed > 3) {
				CPowerUp pointsStar = this.game.level.powerUpPool.obtain();
				this.game.level.activePowerUp.add(pointsStar);
				pointsStar.init(this.game,this.rect,PowerUpType.pointsSilverStar);
				GliderRotate.orangeGlidersDestroyed = 0;
			}
		} 
		
    	this.game.level.scores.increasePlaneShot();
		super.explosionFinished();
		
	}
	
	int ClockWiseRotateCount = 0;
	int ClockWiseRotateLimit = 1;
	
	
	int AntiClockWiseRotateCount = 0;
	int AntiClockWiseRotateLimit = 1;
	public void move() {

			if (this.shipRotation == DirectionRotation.antiClockWise) {
				if (this.shipDirection == AShipEnemy.DIRECTION.Right){
					this.rect.x += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.UpRight){
					this.rect.x += velocity;
					this.rect.y += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
					this.rect.y += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.UpLeft){
					this.rect.x -= velocity;
					this.rect.y += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Left){
					this.rect.x -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
					this.rect.x -= velocity;
					this.rect.y -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
					this.rect.y -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
					this.rect.x += velocity;
					this.rect.y -= velocity;
				}
			
			}else if (this.shipRotation == DirectionRotation.clockWise) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.Right){
					this.rect.x += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.UpRight){
					this.rect.x += velocity;
					this.rect.y += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
					this.rect.y += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.UpLeft){
					this.rect.x -= velocity;
					this.rect.y += velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Left){
					this.rect.x -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
					this.rect.x -= velocity;
					this.rect.y -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
					this.rect.y -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
					this.rect.x += velocity;
					this.rect.y -= velocity;
				}
				
			} else if (this.shipRotation == DirectionRotation.fortyFiveDive) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
					this.rect.x -= velocity;
					this.rect.y -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
					this.rect.y -= velocity;
				}
				
			} else if (this.shipRotation == DirectionRotation.fortyFiveStraight) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
					this.rect.x -= velocity;
					this.rect.y -= velocity;
				}
				
			} else if (this.shipRotation == DirectionRotation.threeOneFiveDive) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
					this.rect.x += velocity;
					this.rect.y -= velocity;
				}else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
					this.rect.y -= velocity;
				}
				
			} else if (this.shipRotation == DirectionRotation.threeOneFiveStraight) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
					this.rect.x += velocity;
					this.rect.y -= velocity;
				}
				
			}else if (this.shipRotation == DirectionRotation.zeroSidewaysLeft) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.Down){
					this.rect.y -= velocity;
				} else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft) {
					this.rect.x -= velocity;
					this.rect.y -= velocity;
				} else if (this.shipDirection == AShipEnemy.DIRECTION.Left) {
					this.rect.x -= velocity;
				}
				
			}else if (this.shipRotation == DirectionRotation.zeroSidewaysRight) {
				
				if (this.shipDirection == AShipEnemy.DIRECTION.Down){
					this.rect.y -= velocity;
				} else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight) {
					this.rect.x += velocity;
					this.rect.y -= velocity;
				} else if (this.shipDirection == AShipEnemy.DIRECTION.Right) {
					this.rect.x += velocity;
				}
				
			}
			
	}
	
	
	
	
	private void updateAntiClockWiseAnimation() {
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Right){
			if (this.rect.x + this.rect.width >= this.game.rectangleScreenSize.width - (this.rect.width*2)){
				this.shipDirection = AShipEnemy.DIRECTION.UpRight;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.UpRight){
			if(this.rect.x + this.rect.width >= (this.game.rectangleScreenSize.width - this.rect.width)){
				this.shipDirection = AShipEnemy.DIRECTION.Up;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
			if(this.rect.y  >= (this.game.rectangleScreenSize.height - 128)){
				if (AntiClockWiseRotateCount <= AntiClockWiseRotateLimit) {
					AntiClockWiseRotateCount++;
					this.shipDirection = AShipEnemy.DIRECTION.UpLeft;
					
				}else{
					//Let it go up and destroy itself
					if (this.rect.y < (this.game.rectangleScreenSize.height + 50)) {
						this.alive = false;
					}
				}
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.UpLeft){
			if(this.rect.y  >= (this.game.rectangleScreenSize.height - 64)){
				this.shipDirection = AShipEnemy.DIRECTION.Left;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Left){
			if(this.rect.x <= (this.rect.width * 2)){
				this.shipDirection = AShipEnemy.DIRECTION.DownLeft;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
			if(this.rect.x <= this.rect.width){
				this.shipDirection = AShipEnemy.DIRECTION.Down;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			if(this.rect.y  <= (this.game.rectangleScreenSize.height - 500)){
				this.shipDirection = AShipEnemy.DIRECTION.DownRight;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
			if(this.rect.y  <= (this.game.rectangleScreenSize.height - (500+64))){
				this.shipDirection = AShipEnemy.DIRECTION.Right;
				
			}
		}
		
	}
	
	
	
	
	
	

	
	
	
	
	private void updateClockWiseAnimation() {
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Right){
			if (this.rect.x + this.rect.width >= this.game.rectangleScreenSize.width - (this.rect.width*2)){
				this.shipDirection = AShipEnemy.DIRECTION.DownRight;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
			if(this.rect.x + this.rect.width >= (this.game.rectangleScreenSize.width - this.rect.width)){
				this.shipDirection = AShipEnemy.DIRECTION.Down;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			if(this.rect.y  <= (this.game.rectangleScreenSize.y + 128)){
				if (ClockWiseRotateCount <= ClockWiseRotateLimit) {
					ClockWiseRotateCount++;
					this.shipDirection = AShipEnemy.DIRECTION.DownLeft;
					
				}else{
					//Let it go up and destroy itself
					if (this.rect.y > - 50) {
						this.alive = false;
					}
				}//Let it go down and destroy itself
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
			if(this.rect.y  <= (this.game.rectangleScreenSize.y + 64)){
				this.shipDirection = AShipEnemy.DIRECTION.Left;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Left){
			if(this.rect.x <= (this.rect.width * 2)){
				this.shipDirection = AShipEnemy.DIRECTION.UpLeft;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.UpLeft){
			if(this.rect.x <= this.rect.width){
				this.shipDirection = AShipEnemy.DIRECTION.Up;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.Up){
			if(this.rect.y  >= (this.game.rectangleScreenSize.height - 128)){
				this.shipDirection = AShipEnemy.DIRECTION.UpRight;
				
			}
		}else if (this.shipDirection == AShipEnemy.DIRECTION.UpRight){
			if(this.rect.y  >= (this.game.rectangleScreenSize.height - 64)){
				this.shipDirection = AShipEnemy.DIRECTION.Right;
				
			}
		}
		
	}
	
	
	private void updateZeroSideWaysLeftAnimation() {
		
		 if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			if(this.rect.y  <= ((this.game.rectangleScreenSize.height /2) - 64)){
				this.shipDirection = AShipEnemy.DIRECTION.DownLeft;
				
			}
		 }else if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
			 if(this.rect.y  <= ((this.game.rectangleScreenSize.height /2))){
				 this.shipDirection = AShipEnemy.DIRECTION.Left;
				
			 }
		 }else if (this.shipDirection == AShipEnemy.DIRECTION.Left){
			 if (this.rect.x < 0) {
				this.alive = false;
			 }
		 }
	}
	
	private void updateZeroSideWaysRightAnimation() {
		
		 if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			if(this.rect.y  <= ((this.game.rectangleScreenSize.height /2) + 64)){
				this.shipDirection = AShipEnemy.DIRECTION.DownRight;
				
			}
		 }else if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
			 if(this.rect.y  <= ((this.game.rectangleScreenSize.height /2))){
				 this.shipDirection = AShipEnemy.DIRECTION.Right;
				
			 }
		 }else if (this.shipDirection == AShipEnemy.DIRECTION.Right){
			 if (this.rect.x > this.game.rectangleScreenSize.width) {
				this.alive = false;
			 }
		 }
	}
	
	private void updateFortyFiveStarightAnimation() {
		
		 if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
			 if (this.rect.y < - 50) {
				 this.alive = false;
			 }
		}
	}
	
	private void updateFortyFiveDiveAnimation() {
		
		 if (this.shipDirection == AShipEnemy.DIRECTION.DownLeft){
			 
				if(this.rect.y  <= (this.game.rectangleScreenSize.height /2)){
					this.shipDirection = AShipEnemy.DIRECTION.Down;
					
				}
				
		 }else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			 if (this.rect.y < - 50) {
					this.alive = false;
			 }
		}
	}
	
	private void updateThreeOneFiveStarightAnimation() {
		
		 if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
			 if (this.rect.y < - 50) {
				 this.alive = false;
			 }
		}
	}
	
	private void updateThreeOneFiveDiveAnimation() {
		
		 if (this.shipDirection == AShipEnemy.DIRECTION.DownRight){
				if(this.rect.y  <= (this.game.rectangleScreenSize.height /2)){
					this.shipDirection = AShipEnemy.DIRECTION.Down;
					
				}
		 }else if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			 if (this.rect.y < - 50) {
					this.alive = false;
			 }
		}
	}
	
	public void fireBulletForHive() {
		
		bullet.fireType = AFireEnemy.FireType.oneCircle;
		bullet fire = this.game.enemyBulletPool.obtain();
		
		this.game.level.activeBullets.add(fire);
		fire.init(this.rect,this.game);
		
	}
	
	
	public void update(float deltaTime) {
		
		
		if (this.shipRotation == DirectionRotation.antiClockWise) {
			this.updateAntiClockWiseAnimation();
		}else if (this.shipRotation == DirectionRotation.clockWise) {
			this.updateClockWiseAnimation();
		}else if (this.shipRotation == DirectionRotation.fortyFiveDive) {
			this.updateFortyFiveDiveAnimation();
		}else if (this.shipRotation == DirectionRotation.fortyFiveStraight) {
			this.updateFortyFiveStarightAnimation();
		}else if (this.shipRotation == DirectionRotation.threeOneFiveDive) {
			this.updateThreeOneFiveDiveAnimation();
		}else if (this.shipRotation == DirectionRotation.threeOneFiveStraight) {
			this.updateThreeOneFiveStarightAnimation();
		}else if (this.shipRotation == DirectionRotation.zeroSidewaysLeft) {
			this.updateZeroSideWaysLeftAnimation();
		}else if (this.shipRotation == DirectionRotation.zeroSidewaysRight) {
			this.updateZeroSideWaysRightAnimation();
		}
		rotateSpriteBasedOnSetDirection();
		super.update(deltaTime);
	    
	}

	int spriteX = 0;
	int spriteY = 466;
	int X = 0;

	@Override
	public void  init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
		
		//For initialising and making new Object we need this logic each time
		if (this.gliderType == GliderType.lightgreen) {
			spriteY = 64;
			this.health = 4;
		} else if (this.gliderType == GliderType.orange) {
			spriteY = 0;
			this.health = 4;
		}  else if (this.gliderType == GliderType.blue) {
			spriteY = 128;
			this.health = 4;
		}  else if (this.gliderType == GliderType.green) {
			spriteY = 192;
			this.health = 4;
		}  else if (this.gliderType == GliderType.white) {
			spriteY = 256;
			this.health = 4;
		} 
		
		if (!valuesInitialised) {
			
			
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 64;
			this.rect.width= 64;
			
			
			//Right Animation
			this.planeAnimationFramesRegion = new TextureRegion[3];
			
			
			int count = 0;
			
			
			//This needs to be set each time
			
			for (int i=count;i < count + 3;i++){
				planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.tileSetTexture,480, spriteY, 64, 64);
				X+=64;
			}
			
			this.planeAnimation = new Animation(1/3f, planeAnimationFramesRegion);
			
			this.currentFrameRegion = planeAnimation.getKeyFrame(0, true);
			
			this.planeSprite = new Sprite(this.game.myGame.tileSetTexture,480,0,64,64);
			this.planeSprite.setPosition(this.rect.x,this.rect.y);
			
			valuesInitialised = true;
			
		}
		
		
		
		//As render is called before frame update, this is needed to avoid null frame in render
		
		
		
		//We get the initial direction from the hive direction set from the hive loop
		this.velocity = 8.0f;
		this.firingSpeed = 10.0f;
		
		
		super.init(game, x, y, hive);
		
	}



	
}
