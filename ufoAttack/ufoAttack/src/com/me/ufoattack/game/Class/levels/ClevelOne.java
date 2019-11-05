package com.me.ufoattack.game.Class.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.Abstracts.APowerUp.PowerUpType;
import com.me.ufoattack.game.Class.hives.CHiveBossStageOne;
import com.me.ufoattack.game.Class.hives.HiveGliderRotate;
import com.me.ufoattack.game.Class.hives.HivePlaneGreen;
import com.me.ufoattack.game.Class.hives.HiveSubmarine;
import com.me.ufoattack.game.Class.hives.HiveTank;
import com.me.ufoattack.game.Class.powerups.CPowerUp;
import com.me.ufoattack.game.Class.ships.enemy.planes.PlaneGreen;
import com.me.ufoattack.game.screens.GameScreen;

public class ClevelOne extends ALevel{
	
	
	public static boolean tankEndPointReached = false;
	TiledMapTileLayer collisionLayer;
	Cell tileCell;
	
	//Hives Groups
	HiveTank hiveTank = HiveTank.hiveTankPool.obtain();
	HiveGliderRotate hiveGliders = HiveGliderRotate.hiveGliderRotatedPool.obtain();
	HiveSubmarine hiveSubmarine = HiveSubmarine.hiveSubmarinePool.obtain();
	HivePlaneGreen hivePlaneGreen = HivePlaneGreen.hivePlaneGreen.obtain();
	
	
	public ClevelOne(GameScreen game) {
		
		super(game);
		
		//Timer.schedule(new Mytimer(this), 1f);
		
		playAnimation = true;
		collisionLayer = (TiledMapTileLayer) this.game.tiledMap.getLayers().get(1);
		
		//Create Hives
		hiveTank.init(this.game);
		this.hives.add(hiveTank);
		
		hiveGliders.init(this.game);
		this.hives.add(hiveGliders);
		
		hiveSubmarine.init(this.game);
		this.hives.add(hiveSubmarine);
		
		hivePlaneGreen.init(this.game);
		this.hives.add(hivePlaneGreen);
		
		//First Tank
		hiveTank.createTank();
		
		//Gliders Forty Five
		hiveGliders.createTwoTwoFive();
		
		//Gliders Forty Five
		hivePlaneGreen.createShips();
		
		this.levelPercent = ALevel.levelPercent.ten;
	}
	
	public void levelDelayedTask() {
		super.levelDelayedTask();
	}
	
	public int levelBossDestroyedCount = 0;
	float charactersAddedCounter = 0;
	ALevel.levelPercent levelPercent = ALevel.levelPercent.zero;

	
	private void addStageCharacters(float deltaTime) {
		
		charactersAddedCounter += deltaTime;
		
		//Check stage markers, indicators by checking for tiled with properties
		tileCell = collisionLayer.getCell((int) 0/32, (int) this.game.myGame.getCamera().position.y/32);
		if (tileCell != null) {
			
			//Gdx.app.debug("y ", Float.toString(this.game.myGame.getCamera().position.y/32));
			if (tileCell.getTile().getProperties().containsKey("one")) {
				
				if (this.levelPercent == ALevel.levelPercent.ten) {
					
					tankEndPointReached = true;
					
					//Power Weapon Up
					CPowerUp powerUp = this.powerUpPool.obtain();
					this.activePowerUp.add(powerUp);
					powerUp.init(this.game,null,PowerUpType.weaponSpeed);
					
					//Power Speed Up
					CPowerUp speedUp = this.powerUpPool.obtain();
					this.activePowerUp.add(speedUp);
					speedUp.init(this.game,null,PowerUpType.extraLife);
					
					//Gliders Zero SideWays
					hiveGliders.createZeroSideWays();
				
					// Increase Level
					this.levelPercent = ALevel.levelPercent.twenty;
				}
			
			
			} else if (tileCell.getTile().getProperties().containsKey("two")) {
				
				
				if (this.levelPercent == ALevel.levelPercent.twenty) {
					
					//Gliders ThreeOneFive
					hiveGliders.createThreeOneFive();
					
					//Launcher Mobile
					hiveTank.createLauncher();
					
					this.levelPercent = ALevel.levelPercent.thirty;
					
					//Gliders Ninety
					hiveGliders.createNinety();

				}
				
			} else if (tileCell.getTile().getProperties().containsKey("three")) {
				
				if (this.levelPercent == ALevel.levelPercent.thirty) {

					//Launcher Cannons
					hiveTank.createCannonTowers(this.game.rectangleScreenSize.width - 128, this.game.myGame.getCamera().position.y * 2);
					
					//Gliders TwoTwoFive
					hiveGliders.createTwoTwoFive();
					
					this.levelPercent = ALevel.levelPercent.fourty;

				}
				
			} else if (tileCell.getTile().getProperties().containsKey("four")) {
				
				if (this.levelPercent == ALevel.levelPercent.fourty) {
					
					//Submarine
					hiveSubmarine.createShips();
					
					//Gliders TwoTwoFive
					hiveGliders.createTwoTwoFive();
					
					this.levelPercent = ALevel.levelPercent.fifty;

				}
				
			} else if (tileCell.getTile().getProperties().containsKey("five")) {
				
				if (this.levelPercent == ALevel.levelPercent.fifty) {

					//Launcher Cannons
					hiveTank.createCannonTowers(24, this.game.myGame.getCamera().position.y * 2);
					
					//Launcher Cannons
					hiveTank.createCannonTowers(this.game.rectangleScreenSize.width - 128, this.game.myGame.getCamera().position.y * 2);
					
					this.levelPercent = ALevel.levelPercent.sixty;
				}
				
			} else if (tileCell.getTile().getProperties().containsKey("six")) {
				
				if (this.levelPercent == ALevel.levelPercent.sixty) {

					//Launcher Cannons
					hiveTank.createCannonTowers(24, this.game.myGame.getCamera().position.y * 2);

					//Level One Boss
					CHiveBossStageOne levelBoss = CHiveBossStageOne.hiveBossStageOnePool.obtain();
					levelBoss.init(this.game);
					levelBoss.createShips();
					this.hives.add(levelBoss);
					
					this.levelPercent = ALevel.levelPercent.seventy;

				}
			}
			
		}
	
	}
		

	
	@Override
	public void update(float deltaTime) {
		
		//This is set from the CHiveBossStageOne
		if (levelBossDestroyedCount == 2) {
			levelCompleted = true;
		}
		
		this.addStageCharacters(deltaTime);
		
		for (int i = 0 ; i < this.hives.size;i++){
			AHive hive = this.hives.get(i);
			hive.update(deltaTime);
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
