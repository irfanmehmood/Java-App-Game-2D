package com.me.ufoattack.game.Class.hives;

import java.util.Iterator;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Abstracts.AShipEnemy.DirectionRotation;
import com.me.ufoattack.game.Class.levels.ClevelTwo;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.planes.PlaneGreen;
import com.me.ufoattack.game.Class.ships.enemy.ships.Carrier;
import com.me.ufoattack.game.Class.ships.enemy.ships.Submarine;
import com.me.ufoattack.game.Class.ships.enemy.ships.UBoat;
import com.me.ufoattack.game.screens.GameScreen;

public class HivePlaneGreen extends AHive{

	public HivePlaneGreen() {
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

	@Override
	public void createShips() {
		float startX = 400;
		float startY = 700;

		PlaneGreen planeGreen = PlaneGreen.planeGreenPool.obtain();
		planeGreen.init(this.game, startX,startY ,this);
		planeGreen.shipRotation = DirectionRotation.zeroSidewaysLeft;
		this.ships.add(planeGreen);

		
	}

	
	String hivePositon = "right";
	
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		
		if (lastUpdated >= this.updateLimit) {
			lastUpdated = 0;
		}
		
		if (this.ships.size > 0) {
			
			for (AShipEnemy ship: this.ships) {
				
				if (lastUpdated == 0){
			   		
					ship.move();
			   	}
		   		 
		   		ship.update(currentTime);
		   		
			}
			
		} else {
			
			this.alive = false;
			
			
		}
	}

	
	public static final Pool<HivePlaneGreen> hivePlaneGreen= Pools.get(HivePlaneGreen.class);
	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
			
			ship.alive = false;
			
			this.ships.removeValue(ship, true);
		}
		
		this.game.level.hives.removeValue(this, true);
		HivePlaneGreen.hivePlaneGreen.free(this);
		
	}

	

}
