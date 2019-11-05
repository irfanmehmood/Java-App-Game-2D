package com.me.ufoattack.game.Class.fires.friendly;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.Abstracts.AFireFriendly;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.utility.Enums;

public class CFireFriendlyRockets extends AFireFriendly{

	private boolean ready = false;
	float turnnCountX=0;
	private Music missileReleaseMusic;
	public CFireFriendlyRockets() {
		super();
	}
	
	public void init(Rectangle parentRect, GameScreen game) {
		
		
		if (!valuesInitialised) {
			this.game = game;
			this.rect = new Rectangle();
			this.rect.height = 47;
			this.rect.width = 16;
			
			
			//Right Animation
			this.planeAnimationFramesRegion = new TextureRegion[2];
			this.planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.tileSetTexture,745, 40, 16 ,47);
			this.planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.tileSetTexture,766, 40, 16 ,47);
			this.planeAnimation = new Animation(1/4f, planeAnimationFramesRegion);
			this.currentFrameRegion = planeAnimation.getKeyFrame(0, true);
			valuesInitialised = true;
		}
		
		
		this.rect.x = this.direction == Enums.DIRECTION.Left ? parentRect.x : parentRect.x + parentRect.width;
		this.rect.y = parentRect.y + (parentRect.height/2);
		turnnCountX = this.direction == Enums.DIRECTION.Left  ? parentRect.x - 40 :  parentRect.x + parentRect.width + 40;
		
		this.speed = 4;
		//this.missileReleaseMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/missile/release.wav"));
		//this.missileReleaseMusic.setLooping(false);
		//this.missileReleaseMusic.play();
		
		this.alive = true;
	}
	
	
	public static float lastLeftRocketTargetX = 0;
	public static float lastLeftRocketTargetY = 0;
	
	public static float lastRightRocketTargetX = 0;
	public static float lastRightRocketTargetY = 0;

	float stateTime;
	protected Animation planeAnimation;
	protected TextureRegion[] planeAnimationFramesRegion;
	protected TextureRegion currentFrameRegion;
	@Override
	public void render(SpriteBatch batch, float deltaTime) {
		stateTime+=deltaTime;
		currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
	}
	
	float targetX2;
	float targetY2;
	float myX1 = 200;
	float myY1 = 200;
	boolean lineSlopeSet = false;
	float yIntercept = 0;
	float lineSlope = 0;
	float lineSlopeYcombined = 0;
	float lineSlopeXcombined = 0;
	float originXcombined = 0;
	float angle = 0;
	@Override
	
	public void update(float time) {
		
		
		if (ready) {
			
			if (this.rect.y < this.game.rectangleScreenSize.height) {
				this.rect.y+=this.speed;
		      }else {
		    	  this.alive = false;
		      }
			/*
			this.game.shapeRenderer.begin(ShapeType.Line);
			this.game.shapeRenderer.setColor(1, 1, 0, 1);
			this.game.shapeRenderer.line(this.rect.x, this.rect.y, targetX2, targetY2);
			this.game.shapeRenderer.end();
			*/
			Iterator<AHive> iter = this.game.level.hives.iterator();
			
			while(iter.hasNext()) {
		    	  AHive hive = iter.next();
		    	  Iterator<AShipEnemy> iterShips = hive.ships.iterator();
		    	  while(iterShips.hasNext()) {
			    	  AShipEnemy ship = iterShips.next();
			    	  
			    	  if(this.rect.overlaps(ship.rect) && !ship.isHandlingHit()) {
			    		  //this.destroyed = true;
		    			  //Gdx.app.debug("CFriendlyPlane", "Hit");
			    		  if (ship.gotHit(firePower) <= 0){
			    			  this.alive = false;
			    		  }
			    		  
				      }
		    	  }
		         
		      }
		}else {
			if (this.direction == Enums.DIRECTION.Left  && this.rect.x > turnnCountX) {
				// TODO Auto-generated method stub
				this.rect.x -= 4;
			}else if (this.direction == Enums.DIRECTION.Right  && this.rect.x < turnnCountX) {
				// TODO Auto-generated method stub
				this.rect.x += 4;
			}else{
				ready = true;
				if (!lineSlopeSet){
			    	 originXcombined = this.rect.x;
			    	 Gdx.app.debug("slopeFor", "x2:" + Float.toString(this.game.rectangleScreenSize.width) + 
			    			 " x1:" + Float.toString(this.rect.x) +
			    			 " y2:" + Float.toString(this.game.rectangleScreenSize.height) + 
			    			 " y1:" + Float.toString(this.rect.y));
			    	 if (this.direction == Enums.DIRECTION.Left ){
			    		 targetX2 = (CFireFriendlyRockets.lastLeftRocketTargetX == 0.0f ? this.rect.x : CFireFriendlyRockets.lastLeftRocketTargetX);
			    		 targetY2 = CFireFriendlyRockets.lastLeftRocketTargetY == 0.0f ? this.game.rectangleScreenSize.height : CFireFriendlyRockets.lastLeftRocketTargetY;
				    	 lineSlopeYcombined = targetY2 - this.rect.y;
				    	 lineSlopeXcombined = targetX2 - (int)this.rect.x;
				    	 lineSlope =  lineSlopeYcombined/lineSlopeXcombined; 
				    	 this.angle = Enums.getAngle(targetX2,targetY2,this.rect.x,this.rect.y);
				    	 this.angle *= -1;
			    	 } else {
			    		 targetX2 = CFireFriendlyRockets.lastRightRocketTargetX == 0 ? this.rect.x : CFireFriendlyRockets.lastRightRocketTargetX;
			    		 targetY2 = CFireFriendlyRockets.lastRightRocketTargetY == 0 ? this.game.rectangleScreenSize.height : CFireFriendlyRockets.lastRightRocketTargetY;
				    	 lineSlopeYcombined = targetY2 - this.rect.y;
				    	 lineSlopeXcombined = targetX2 - (int)this.rect.x;
				    	 lineSlope =  lineSlopeYcombined/lineSlopeXcombined; 
				    	 this.angle = Enums.getAngle(targetX2,targetY2,this.rect.x,this.rect.y);
				    	 this.angle *= -1;
				    	 //Gdx.app.debug("lineSlope", Float.toString(lineSlope));
				    	 //Gdx.app.debug("getAngle", Float.toString(this.angle));
				    	 //Gdx.app.debug("lineSlopeYcombined", Float.toString(lineSlopeYcombined));
			    	 }
			    	 lineSlopeSet = true;
			    	 
			      }
			}
		}
			
	}
	
	public void reset() {
		this.lineSlopeSet = false;
		this.ready= false;
		super.reset();
	}
	
	
	

}
