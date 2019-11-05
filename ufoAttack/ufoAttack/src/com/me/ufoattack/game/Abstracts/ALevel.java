package com.me.ufoattack.game.Abstracts;

import java.util.Iterator;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.ufoattack.game.Mytimer;
import com.me.ufoattack.game.Class.CScores;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.fires.friendly.CBomb;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyEight;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyNine;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlySide;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyStraight;
import com.me.ufoattack.game.Class.hives.HiveGliderDive;
import com.me.ufoattack.game.Class.powerups.CPowerUp;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class ALevel {
	public static int currentLevel = 1;
	float getReadyAnimationLength = 5/1f;
	protected int levelProgress = 0;
	protected GameScreen game;
	protected boolean playAnimation = false;
	public final Array<AHive> hives = new Array<AHive>();
	public final Array<CPowerUp> activePowerUp = new Array<CPowerUp>();
	protected Mytimer timer;
	public final Pool<CPowerUp> powerUpPool = Pools.get(CPowerUp.class);
	public final Pool<AShipEnemy> enemyObjsPool = Pools.get(AShipEnemy.class);
	public final Array<AShipEnemy> activeEnemyObjs = new Array<AShipEnemy>();
	public CScores scores;
	public final Array<AFire> activeBullets = new Array<AFire>();
	public boolean levelCompleted = false;
	Iterator<AFire> iterABullets;
	public ALevel(GameScreen game) {

		this.game = game;
		this.getReadyFramesRegion = new TextureRegion[2];
		this.getReadyFramesRegion[0] = new TextureRegion(game.myGame.gameTexture, 298, 535, 108, 17);
		this.getReadyFramesRegion[1] = new TextureRegion(game.myGame.gameTexture, 298, 552, 108, 17);
		this.scores = new CScores(ALevel.currentLevel);
		this.getReadyAnimation = new Animation(1/30f, this.getReadyFramesRegion);
	}
	
	public void update(float deltaTime){
		
		//Fix this into a timer delay only once function, also in render
		if (this.playAnimation){
			if (stateTime >= getReadyAnimationLength) {
				this.playAnimation = false;
				stateTime = 0;
			}else{
				stateTime += deltaTime;
			}
		}
		
	/////Only updates
	    this.iterABullets = this.activeBullets.iterator();
	    while(iterABullets.hasNext()) {
	    	AFire fire = iterABullets.next();
	    	if (!fire.IsAlive()){
	    		/*
	    		//Gdx.app.debug("AFire", fire.getClass().toString());
	    		if (fire.getClass().toString().equals("class com.me.ufoattack.game.Class.fires.CFireFriendlySide")) {
	    			this.CFireFriendlySidePool.free((CFireFriendlySide) fire);
	    		}else if(fire.getClass().toString() .equals("class com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets")) {
	    			this.CFireFriendlyRocketsPool.free((CFireFriendlyRockets) fire);
	    		}else if(fire.getClass().toString() .equals("class com.me.ufoattack.game.Class.fires.friendly.CBomb")) {
	    			this.CBombPool.free((CBomb) fire);
	    		}else if(fire.getClass().toString() .equals("class com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyEight")) {
	    			this.CFireFriendlyEightPool.free((CFireFriendlyEight) fire);
	    		}else if(fire.getClass().toString() .equals("class com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyNine")) {
	    			this.CFireFriendlyNinePool.free((CFireFriendlyNine) fire);
	    		}else if(fire.getClass().toString() .equals("class com.me.ufoattack.game.Class.fires.enemy.bullet")) {
	    			this.enemyBulletPool.free((bullet) fire);
	    		}else if(fire.getClass().toString() .equals("class com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyStraight")) {
	    			this.CFireFriendlyStraightPool.free((CFireFriendlyStraight) fire);
	    		}
	    		*/
	    		iterABullets.remove();
	    	} else {
	    		fire.update(deltaTime);
	    	}
	    }
		
		
		
		
		// Update Powerups
		for (int powerUpI = 0; powerUpI < this.activePowerUp.size; powerUpI++){
	    	
	    	CPowerUp powerup = this.activePowerUp.get(powerUpI);
	    	if (powerup.alive){
	    		 powerup.update(deltaTime);
	    	} else {
	    		
	    		this.activePowerUp.removeIndex(powerUpI);
	    		this.powerUpPool.free(powerup);
	    	}
	    }
		
		
	}
	
	public void render(SpriteBatch batch,float deltaTime){
		
		//Fix this into a timer delay only once function, also in update
		if (this.playAnimation){
			this.getReadyCurrentFrame = this.getReadyAnimation.getKeyFrame(stateTime, true);
	        batch.draw(getReadyCurrentFrame, (this.game.rectangleScreenSize.width/2)-162,(this.game.rectangleScreenSize.height/2)-25,  324, 51);
		}
		
		// Update Bullets
		for (int bulletsI = 0; bulletsI < this.activeBullets.size; bulletsI++){
			 AFire fire = this.activeBullets.get(bulletsI);
			 fire.render(batch,deltaTime);
		}
		
		// Power ups
		for (int powerUpI = 0; powerUpI < this.activePowerUp.size; powerUpI++){
	    	APowerUp powerUp = this.activePowerUp.get(powerUpI);
	    	powerUp.render(batch, deltaTime);
	    }
	}
	
	public abstract void increaseLevelProgress();
	
	public int getLevelProgress(){
		return this.levelProgress;
	}
	
	Animation getReadyAnimation;
	TextureRegion[] getReadyFramesRegion;
	TextureRegion getReadyCurrentFrame;
	float stateTime;

	public void levelDelayedTask() {
		// TODO Auto-generated method stub
		
	}
	
	public enum levelPercent {
		 zero,
		 ten,
		 twenty,
		 thirty,
		 fourty,
		 fifty,
		 sixty,
		 seventy,
		 eighty,
		 ninghty,
		 hundered
	 }
	
	
	
	
}
