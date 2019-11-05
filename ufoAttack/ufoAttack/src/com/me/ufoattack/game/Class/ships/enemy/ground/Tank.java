package com.me.ufoattack.game.Class.ships.enemy.ground;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AFireEnemy;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.levels.ClevelOne;
import com.me.ufoattack.game.Class.ships.enemy.ships.UBoat;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class Tank extends AShipEnemy{
	public enum TankType {
		 Tank,
		 MobileRocketLauncher,
		 FortressCannon
	 }
	public static TankType tankTypeGolbal = TankType.Tank;
	TankType tankType = TankType.Tank;
    AHive hive;
    
    public Tank() {
		super();
		
	}
    
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		// In case we need to be part of a new hive
		this.hive = hive;
		
		// Check which tank was selcted
		this.tankType = Tank.tankTypeGolbal;
		
				
		//To save resources this shall only be called once when the object is created first time
		if (!valuesInitialised) {
					
			this.game = game;
			this.rect = new Rectangle();
			
			planeAnimationFramesRegion = new TextureRegion[2];
			this.collisionLayer = (TiledMapTileLayer) this.game.tiledMap.getLayers().get(1);
			
			
			if (this.tankType == Tank.TankType.Tank) {
				
				
				planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.tileSetTexture,910, 14,48, 94);
				planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.tileSetTexture,958, 14,48, 94);
				this.planeAnimation = new Animation(1/1f, this.planeAnimationFramesRegion);
				glowSprite = new Sprite(planeAnimationFramesRegion[1]);
				glowSprite.setPosition(this.rect.x,this.rect.y);
				
				this.rect.height = 94;
				this.rect.width = 48;
				
				
			} else if (this.tankType == Tank.TankType.MobileRocketLauncher)  {
				
				
				planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.tileSetTexture,786, 14,48, 94);
				planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.tileSetTexture,834, 14,48, 94);
				this.planeAnimation = new Animation(1/1f, this.planeAnimationFramesRegion);
				glowSprite = new Sprite(planeAnimationFramesRegion[1]);
				glowSprite.setPosition(this.rect.x,this.rect.y);
				
				this.rect.height = 94;
				this.rect.width = 48;
				
				
			} else if (this.tankType == Tank.TankType.FortressCannon)  {
				
				planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.tileSetTexture,0, 64,96, 160);
				planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.tileSetTexture,96, 0,32, 32);
				this.planeAnimation = new Animation(1/1f, this.planeAnimationFramesRegion);
				glowSprite = new Sprite(planeAnimationFramesRegion[1]);
				glowSprite.setPosition(this.rect.x + 32 ,this.rect.y + 24);
				
				this.rect.height = 160;
				this.rect.width = 96;
			}

			//glowSprite.setRegion(planeAnimationFramesRegion[1]);
			
			valuesInitialised = true;
		}

		this.shipDirection = DIRECTION.Down;	
		this.rect.x = x;
		this.rect.y = y;
		this.firingSpeed = 5.0f;
		this.health = 40;
		this.loopAnimation = false;
		this.alive = true;
	}

	
	public static final Pool<Tank>TankPool = Pools.get(Tank.class);
	
	public void explosionFinished() {
		
		super.explosionFinished();
		this.game.level.scores.increaseSubmarineShot();
	}
	
	TiledMapTileLayer collisionLayer;
	
	public void move() {
	}
	
	float angle;
	boolean angleSet = false;
	float differenceInAngles;
	float angleCheckedTime = 0;
	float rotaionIncrement = 0;
	float currentNozzleAngle = 0;
	
	
	
	float LastSavedAgle = 0;
	boolean cleared = true;
	
	
	private void updateTank(float currentTime) {
		
		if (angleCheckedTime >= 3/1f) {
			currentNozzleAngle = glowSprite.getRotation();
			//Rotation Logic for tank nozzle
			
			this.angle = Enums.getAngle(
						this.game.plane.rect.x + (this.game.plane.rect.width/2),
						this.game.plane.rect.y + (this.game.plane.rect.height/2),
						this.rect.x + (this.rect.width/2),
						this.rect.y + (this.rect.height/2));
			
			if (!cleared) {
				//Removes targeted negative angle
				glowSprite.rotate(+LastSavedAgle);
				cleared = true;
			} else {
				float ang = this.angle;
				glowSprite.rotate(-ang);
				LastSavedAgle = ang;
				cleared = false;
				//	Gdx.app.debug("Line: ", Float.toString(this.angle) + " Nozzle: " + Float.toString(currentNozzleAngle));
			}
			
			if (this.bulletFireTime >= 6/1f) {
			//Fires Selected bullet
			bullet.fireType = AFireEnemy.FireType.bulletSmallTwo;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			this.game.level.activeBullets.add(fire);
			}
			
			angleCheckedTime = 0;
		}
	
	}
	
	private void updateLauncher(float currentTime) {
		
		if (angleCheckedTime >= 3/1f) {
			
			//Fires Selected bullet
			bullet.fireType = AFireEnemy.FireType.rocket;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			this.game.level.activeBullets.add(fire);

			
			angleCheckedTime = 0;
		}
	
	}
	
	private void updateStaticCannon(float currentTime) {
		
		if (angleCheckedTime >= 3/1f) {
			currentNozzleAngle = glowSprite.getRotation();
			//Rotation Logic for tank nozzle
			
			this.angle = Enums.getAngle(
						this.game.plane.rect.x + (this.game.plane.rect.width/2),
						this.game.plane.rect.y + (this.game.plane.rect.height/2),
						this.rect.x + (this.rect.width/2),
						this.rect.y + (this.rect.height/2));
			
			if (!cleared) {
				//Removes targeted negative angle
				glowSprite.rotate(+LastSavedAgle);
				cleared = true;
			} else {
				float ang = this.angle;
				glowSprite.rotate(-ang);
				LastSavedAgle = ang;
				cleared = false;
				//	Gdx.app.debug("Line: ", Float.toString(this.angle) + " Nozzle: " + Float.toString(currentNozzleAngle));
			}
			
			//Fires Selected bullet
			bullet.fireType = AFireEnemy.FireType.twoCircle;
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			this.game.level.activeBullets.add(fire);

			
			angleCheckedTime = 0;
		}
	
	}
	
	boolean stop = false;
	float bulletFireTime = 0;
	public void update(float currentTime) {

		lastUpdated+=currentTime;
		angleCheckedTime+=currentTime;
		bulletFireTime+=currentTime;
		
		if (this.lastUpdated >= 1/30f) {
			
			if (this.tankType == Tank.TankType.Tank) {
				this.updateTank(currentTime);
				
				Vector3 pos = new Vector3(this.rect.x, this.rect.y, 0);
				this.game.myGame.getCamera().unproject(pos);
				
				float y =  (( pos.y -(this.rect.y))/32f);
				float x = pos.x/32;
				
				Cell tileCell = collisionLayer.getCell((int) this.rect.x/32, (int) y);
				if (tileCell != null) {
					Gdx.app.debug("y", "stopped");
					stop = true;
				}else {
					//Gdx.app.debug("py ", Float.toString(pos.y/32));
					//Gdx.app.debug("ty", Float.toString(y));
				}
				
				if (!ClevelOne.tankEndPointReached) {
					this.rect.y += 1/4f;
				}else{
					this.rect.y -= this.game.mapScrollSpeed;
					
				}
				
			} else if (this.tankType == Tank.TankType.MobileRocketLauncher)  {
				this.updateLauncher(currentTime);
				this.game.shapeRenderer.begin(ShapeType.Line);
				this.game.shapeRenderer.setColor(Color.RED);
				this.game.shapeRenderer.line(this.rect.x + (this.rect.width /2), this.rect.y+this.rect.height, this.game.plane.rect.x+( this.game.plane.rect.width/2), this.game.plane.rect.y-(this.game.plane.rect.height/2));
				this.game.shapeRenderer.end();
				if (!ClevelOne.tankEndPointReached) {
					this.rect.y += 1/4f;
				}else{
					this.rect.y -= this.game.mapScrollSpeed;
					
				}
				
				
			} else if(this.tankType == Tank.TankType.FortressCannon) {
				this.updateStaticCannon(currentTime);
				if (this.rect.y <= 0) {
					this.alive = false;
				} else {
					this.rect.y -= this.game.mapScrollSpeed;
				}
			}
			
			

		}
		
		
		
		
		super.update(currentTime);
	    
	}
	
	int id = 0;
	Sprite  glowSprite;
	public void render(SpriteBatch batch, float deltaTime) {
		
		if (!handlingHit) {
	        batch.draw(planeAnimationFramesRegion[0], this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
	        if (this.tankType == Tank.tankTypeGolbal.FortressCannon) {
	        	glowSprite.setPosition(this.rect.x + 32 ,this.rect.y + 102);
	        } else {
	        	glowSprite.setPosition(this.rect.x,this.rect.y);
	        }
	        
	        glowSprite.draw(batch);
		}
		
		super.render(batch, deltaTime);
	}
}

