package com.me.ufoattack.game.Class.hives;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.ships.enemy.ground.Tank;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.ships.UBoat;
import com.me.ufoattack.game.screens.GameScreen;

public class HiveTank extends AHive{

	public HiveTank() {
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
		
	}

	public void createTank() {
		
		float startX = this.game.rectangleScreenSize.width  - 150;
		float startY = 0;
		
		Tank.tankTypeGolbal = Tank.TankType.Tank;
		Tank tank = Tank.TankPool.obtain();
		tank.init(this.game, startX,startY ,this);
		this.ships.add(tank);	
	}
	
	public void createLauncher() {
		
		
	}
	
	public void createCannonTowers(float startX, float startY) {
		
		startY = 800;
		Tank.tankTypeGolbal = Tank.TankType.FortressCannon;
		Tank staticCannon1 = Tank.TankPool.obtain();
		staticCannon1.init(this.game, startX,startY ,this);
		this.ships.add(staticCannon1);
		
		
		startY += 200;
		Tank.tankTypeGolbal = Tank.TankType.FortressCannon;
		Tank staticCannon2 = Tank.TankPool.obtain();
		staticCannon2.init(this.game, startX,startY ,this);
		this.ships.add(staticCannon2);
		
		startY += 200;
		Tank.tankTypeGolbal = Tank.TankType.FortressCannon;
		Tank staticCannon3 = Tank.TankPool.obtain();
		staticCannon3.init(this.game, startX,startY ,this);
		this.ships.add(staticCannon3);
	}
	
	
	public void update(float currentTime) {
		
		if (this.ships.size > 0) {
			
			for (AShipEnemy ship: this.ships) {
				if (ship.alive) {
					ship.update(currentTime);
				} else {
					this.ships.removeValue(ship, true);
					Tank.TankPool.free((Tank) ship);
				}
			}
			
		} else {
			
			this.alive = false;
			
			
		}
	}

	
	public static final Pool<HiveTank> hiveTankPool = Pools.get(HiveTank.class);
	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
			
			ship.alive = false;
			
			this.ships.removeValue(ship, true);
			Tank.TankPool.free((Tank) ship);
			
		}
		
		this.game.level.hives.removeValue(this, true);
		
	}

	

}
