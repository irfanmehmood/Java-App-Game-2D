package com.me.ufoattack.game.Class.hives;

import java.util.Iterator;
import java.util.Random;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Abstracts.AShipEnemy.DirectionRotation;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderDive;
import com.me.ufoattack.game.Class.ships.enemy.ships.Carrier;
import com.me.ufoattack.game.Class.ships.enemy.ships.Submarine;
import com.me.ufoattack.game.screens.GameScreen;

public class HiveGliderRotate extends AHive{

	public HiveGliderRotate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init(GameScreen game) {
		
		if (!valuesInitialised) {
			this.game = game;
			this.ships = new Array<AShipEnemy>();
			valuesInitialised = true;
		}
		
		this.alive = true;
	}
	
	
	public void createZeroSideWays() {
		
		startX = this.game.rectangleScreenSize.width + 10;
		startY = this.game.rectangleScreenSize.height - 1;
		
		startX = (this.game.rectangleScreenSize.width / 2) - 64;
		startY = this.game.rectangleScreenSize.height - 256;
		for (int i=0;i<5;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.green;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.Down;
			glider.shipRotation = DirectionRotation.zeroSidewaysLeft;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startY+=128;
		}
		
		startX = (this.game.rectangleScreenSize.width / 2) + 64;
		startY = this.game.rectangleScreenSize.height - 256;
		for (int i=0;i<5;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.white;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.Down;
			glider.shipRotation = DirectionRotation.zeroSidewaysRight;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startY+=128;
		}
		
	}
	
	public void createFortyFive() {
		
		startX = this.game.rectangleScreenSize.width;
		startY = this.game.rectangleScreenSize.height;
		for (int i=0;i<3;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.green;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.DownLeft;
			glider.shipRotation = DirectionRotation.fortyFiveStraight;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startX+=128;
		}
		
		startX = this.game.rectangleScreenSize.width + 128;
		startY = this.game.rectangleScreenSize.height - 128;
		for (int i=0;i<3;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.orange;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.DownLeft;
			glider.shipRotation = DirectionRotation.fortyFiveDive;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startX+=128;
		}
	
	}
	
	public void createThreeOneFive() {
		
		startX = -128;
		startY = this.game.rectangleScreenSize.height;
		for (int i=0;i<3;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.green;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.DownRight;
			glider.shipRotation = DirectionRotation.threeOneFiveStraight;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startX+=128;
		}
		
		startX = -256;
		startY = this.game.rectangleScreenSize.height - 128;
		for (int i=0;i<3;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.orange;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.DownRight;
			glider.shipRotation = DirectionRotation.threeOneFiveDive;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startX+=128;
		}
	
	}
	
	public void createTwoTwoFive() {
		
		startX = -100;
		startY =  200;
		
		
		for (int i=0;i<5;i++){
			GliderRotate.gliderType = GliderRotate.GliderType.orange;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipRotation = DirectionRotation.antiClockWise;
			glider.shipDirection = AShipEnemy.DIRECTION.Right;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
			startX-=128;
		}
	
	}
	
	public void createNinety() {
		
		startX = this.game.rectangleScreenSize.width + 100;
		startY = this.game.rectangleScreenSize.height/2;
		
		
		for (int i=0;i<5;i++){
			
			GliderRotate.gliderType = GliderRotate.GliderType.green;
			GliderRotate glider = GliderRotate.GliderPool.obtain();
			glider.shipDirection = AShipEnemy.DIRECTION.Left;
			glider.shipRotation = DirectionRotation.clockWise;
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
	
			startX+=128;
		}
	}
	
	float startX;
	float startY;
	
	@Override
	public void createShips() {
		
	}

	
	
	public static final Pool<HiveGliderRotate> hiveGliderRotatedPool = Pools.get(HiveGliderRotate.class);
	float hiveFiringSpeedLimit = 5.0f;
	float hiveFiringSpeedCounter = 0;
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		hiveFiringSpeedCounter+=currentTime;
		
		if (lastUpdated >= this.updateLimit) {
			lastUpdated = 0;
		}
		
		
		
		if (this.ships.size > 0) {
			
			//This needs to be done in this loop as the minimum and maximum for the random below depend on ship size > 0
			if (hiveFiringSpeedCounter >= this.hiveFiringSpeedLimit) {
				hiveFiringSpeedCounter = 0;
				Random r = new Random();
				int max = this.ships.size - 1;
				int min = 0;
				int random = r.nextInt(((max - min) + 1) + min);
				this.ships.get(random).fireBulletForHive();

			}
			
			for (int count = 0; count < this.ships.size; count++) {
				AShipEnemy ship = this.ships.get(count);
				if (ship.alive) {
					if (lastUpdated == 0){
						ship.move();
				   	}
					ship.update(currentTime);
				} else {
					ships.removeIndex(count);
			    	GliderRotate.GliderPool.free(((GliderRotate) ship));
				}
			}
		} else {
			
			//this.alive = false;
			//this.formationType = AHive.FormationType.values()[(this.formationType.ordinal()+1) % AHive.FormationType.values().length];
			//this.createShips();
		}
	}

	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
			
			ship.alive = false;
			
		}
		
		this.game.level.hives.removeValue(this, true);
		HiveGliderRotate.hiveGliderRotatedPool.free(this);
		
	}
	
	

}
