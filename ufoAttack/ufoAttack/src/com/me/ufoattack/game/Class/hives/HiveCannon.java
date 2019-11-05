package com.me.ufoattack.game.Class.hives;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.ships.enemy.ground.Cannon;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.ships.UBoat;
import com.me.ufoattack.game.screens.GameScreen;

public class HiveCannon extends AHive{

	public HiveCannon() {
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
		
		float startX = this.game.rectangleScreenSize.width  - 128;
		float startY = 300;
		
		
		Cannon submarineLeft = Cannon.CannonPool.obtain();
		submarineLeft.init(this.game, startX,startY ,this);
		this.ships.add(submarineLeft);
		

		Cannon cannonRight = Cannon.CannonPool.obtain();
		cannonRight.init(this.game, startX,startY ,this);
		this.ships.add(cannonRight);
		
		/*
		startX = this.game.rectangleScreenSize.width + submarineLeft.rect.width;
		startY = this.game.rectangleScreenSize.height - 100;
		UBoat submarineRight = UBoat.uBoatPool.obtain();
		submarineRight.init(this.game, startX,startY ,this);
		this.ships.add(submarineRight);
		*/
		
		//Boat submarine = new Boat(this.game, startX,startY ,this);
		//this.ships.add(submarine);
		/*
		startX = this.game.rectangleScreenSize.width - submarine.rect.width;
		startY = 0;
		Boat submarine2 = new Boat(this.game, startX,startY ,this);
		this.ships.add(submarine2);
		
		startX = this.game.rectangleScreenSize.width + submarine.rect.width;
		startY = this.game.rectangleScreenSize.height - 100;
		UBoat Boat = new UBoat(this.game, startX,startY ,this);
		this.ships.add(Boat);
		
		startX = -100;
		startY = this.game.rectangleScreenSize.height - 200;
		AircraftCarrier Carrier = new AircraftCarrier(this.game, startX,startY ,this);
		this.ships.add(Carrier);
		
		*/
		
	}

	
	
	
	public void update(float currentTime) {
		
		
		
		if (this.ships.size > 0) {
			
			for (AShipEnemy ship: this.ships) {
				
		   		ship.update(currentTime);
		   		
			}
			
		} else {
			
			this.alive = false;
			
			
		}
	}

	
	public static final Pool<HiveCannon> hiveCannonPool = Pools.get(HiveCannon.class);
	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
			
			ship.alive = false;
			
			this.ships.removeValue(ship, true);
			
			GliderRotate.GliderPool.free((GliderRotate) (ship));
			
		}
		
		this.game.level.hives.removeValue(this, true);
		
	}

	

}
