package com.me.ufoattack.game.Class.levels;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.ufoattack.game.Mytimer;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.Class.hives.CHiveBossStageOne;
import com.me.ufoattack.game.Class.hives.CHiveBossStageTwo;
import com.me.ufoattack.game.Class.hives.HiveGliderRotate;
import com.me.ufoattack.game.Class.hives.HiveGliderDive;
import com.me.ufoattack.game.Class.hives.HiveSubmarine;
import com.me.ufoattack.game.screens.GameScreen;

public class ClevelTwo extends ALevel{

	
	HiveGliderRotate planes = null;
	HiveGliderDive planesDive = null;
	HiveSubmarine submarine = null;
	CHiveBossStageTwo levelBoss;
	
	
	
	public ClevelTwo(GameScreen game) {
		super(game);
		playAnimation = true;
		Timer.schedule(new Mytimer(this), 8f);
		  
		
	}
	
	public void levelDelayedTask() {
		/*
		if (planes == null) {
				planes = new CHiveGlider(this.game);
				planes.createShips();
				this.hives.add(planes);
		}
		if (levelBoss == null) {
			levelBoss = new CHiveBossStageTwo(this.game,this);
			levelBoss.createShips();
			this.hives.add(levelBoss);
		}
		*/
		super.levelDelayedTask();
	}
	public int levelBossDestroyedCount = 0;
	@Override
	
	public void update(float deltaTime) {
		
		//This is set from the CHiveBossStageOne
		if (levelBossDestroyedCount == 2) {
			levelCompleted = true;
		}
		
		
		for (int i = 0 ; i < this.hives.size;i++){
			AHive hive = this.hives.get(i);
			if (!hive.alive){
				this.hives.removeIndex(i);
			}else{
				hive.update(deltaTime);
			}
		}
		
		switch (this.levelProgress){
		case 0:
			
			
			break;
				
		case 20:
			/*
			if (submarine == null) {
				submarine = new CHiveSubmarine(this.game);
				submarine.createShips();
				this.hives.add(submarine);
				
				CPowerUpFireUpgrade powerUp = this.CPowerUpFireUpgradePool.obtain();
				this.activePowerUp.add(powerUp);
				powerUp.init(this.game);
			}
		
			break;
			

			
		case 40:
			if (planesDive == null) {
				planesDive = new CHiveGliderDive(this.game);
				planesDive.createShips();
				this.hives.add(planesDive);
				
				CPowerUpFireSpeed powerUp = this.CPowerUpFireSpeedPool.obtain();
				this.activePowerUp.add(powerUp);
				powerUp.init(this.game);
			}
			if (planes != null) {
				planes = null;
			}
			break;
			

			
		case 60:
			if (planes == null) {
				
				CPowerUpFireUpgrade powerUp1 = this.CPowerUpFireUpgradePool.obtain();
				this.activePowerUp.add(powerUp1);
				powerUp1.init(this.game);
				
				CPowerUpFireSpeed powerUp = this.CPowerUpFireSpeedPool.obtain();
				this.activePowerUp.add(powerUp);
				powerUp.init(this.game);
				
				planes = new CHiveGlider(this.game);
				planes.createShips();
				this.hives.add(planes);
			}
			if (planesDive != null) {
				planesDive = null;
			}
			
			if (planesDive == null) {
				planesDive = new CHiveGliderDive(this.game);
				planesDive.createShips();
				this.hives.add(planesDive);
				
				CPowerUpFireSpeed powerUp = this.CPowerUpFireSpeedPool.obtain();
				this.activePowerUp.add(powerUp);
				powerUp.init(this.game);
			}
			
*/
			break;
		case 80:
			/*
			if (levelBoss == null) {
				levelBoss = new CHiveBossStageTwo(this.game,this);
				levelBoss.createShips();
				this.hives.add(levelBoss);
			}
			*/
			break;
	    
		
		case 100:
			break;
	}
		
		
		
		
		super.update(deltaTime);
		
	}

	@Override
	public void render(SpriteBatch spriteBatch,float deltaTime) {
		
		for (int hiveI = 0; hiveI < this.hives.size; hiveI++){
	    	AHive hive = this.hives.get(hiveI);
	    	hive.render(spriteBatch, deltaTime);
	    }
		super.render(spriteBatch,deltaTime);
	}

	@Override
	public void increaseLevelProgress() {
		
		if (this.levelProgress != 90){
			this.levelProgress+=20;
		}
		
		
	}

}
