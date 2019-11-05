package com.me.ufoattack.game.Class.hives;

import java.util.Iterator;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyStraight;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderDive;
import com.me.ufoattack.game.Class.ships.enemy.ships.Carrier;
import com.me.ufoattack.game.Class.ships.enemy.ships.Submarine;
import com.me.ufoattack.game.screens.GameScreen;

public class HiveGliderDive extends AHive{

	public HiveGliderDive() {
		super();

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
		float startX = 96;
		float startY = this.game.rectangleScreenSize.height;
		
		for (int i=0;i<6;i++){
			GliderDive.gliderType  = GliderRotate.GliderType.blue;
			GliderDive glider = GliderDive.GliderPool.obtain();
			glider.init(this.game, startX,startY ,this);
			this.ships.add(glider);
			startX+=96;
		}
		
	}

	
	
	public static final Pool<HiveGliderDive> hiveGliderDivePool = Pools.get(HiveGliderDive.class);
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

	

	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
			
			ship.alive = false;
			
			this.ships.removeValue(ship, true);
			
			
			
		}
		
		this.game.level.hives.removeValue(this, true);
		HiveGliderDive.hiveGliderDivePool.free(this);
	}
	
	

}
