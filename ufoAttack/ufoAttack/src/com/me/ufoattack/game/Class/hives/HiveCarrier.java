package com.me.ufoattack.game.Class.hives;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.ships.Carrier;
import com.me.ufoattack.game.Class.ships.enemy.ships.UBoat;
import com.me.ufoattack.game.screens.GameScreen;

public class HiveCarrier extends AHive{

	public HiveCarrier() {
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
		
		
		int startX = -100;
		int startY = (int) (this.game.rectangleScreenSize.height - 200);

		Carrier submarineRight = Carrier.carrierPool.obtain();
		submarineRight.init(this.game, startX,startY ,this);
		this.ships.add(submarineRight);

		
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

	
	public static final Pool<HiveCarrier> hiveCarrierPool = Pools.get(HiveCarrier.class);
	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
			
			ship.alive = false;
			
			this.ships.removeValue(ship, true);
			
			Carrier.carrierPool.free((Carrier) (ship));
			
		}
		
		this.game.level.hives.removeValue(this, true);
		HiveCarrier.hiveCarrierPool.free(this);
		
	}

	

}
