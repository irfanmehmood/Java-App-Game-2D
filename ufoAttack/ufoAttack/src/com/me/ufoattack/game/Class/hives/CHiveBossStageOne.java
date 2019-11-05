package com.me.ufoattack.game.Class.hives;

import java.util.Iterator;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.levels.ClevelOne;
import com.me.ufoattack.game.Class.ships.enemy.planes.CPlaneEnemyBossOne;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.ships.Carrier;
import com.me.ufoattack.game.Class.ships.enemy.ships.Submarine;
import com.me.ufoattack.game.screens.GameScreen;

public class CHiveBossStageOne extends AHive{

	public CHiveBossStageOne() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static final Pool<CHiveBossStageOne> hiveBossStageOnePool = Pools.get(CHiveBossStageOne.class);
	
	public void init(GameScreen game) {
		
		if (!valuesInitialised) {
			
			this.game = game;
			this.level = (ClevelOne) game.level;
			this.ships = new Array<AShipEnemy>();
			valuesInitialised = true;
		}
		
		this.alive = true;
		
	}

	@Override
	public void createShips() {
		float startX = 100;
		float startY = this.game.rectangleScreenSize.height -200;
		CPlaneEnemyBossOne boss = new CPlaneEnemyBossOne();
		boss.init(this.game, startX,startY ,this);
		this.ships.add(boss);
		
		startX += 300;
		
		CPlaneEnemyBossOne boss2 = new CPlaneEnemyBossOne();
		boss2.init(this.game, startX,startY ,this);
		this.ships.add(boss2);
	
	}

	

	ClevelOne level;
	public void update(float currentTime) {

		lastUpdated+=currentTime;
		
		if (lastUpdated >= this.updateLimit) {
			lastUpdated = 0;
		}
		Iterator<AShipEnemy> iter = this.ships.iterator();

	      while(iter.hasNext()) {
	    	  AShipEnemy ship = (AShipEnemy) iter.next();
	    	 if(ship.alive) {
	    		 if (lastUpdated == 0){
    				 ship.move();
    			 }
	    		 ship.update(currentTime);
		     } else{
		    	 level.levelBossDestroyedCount++;
		    	 iter.remove();
		    	 //Gdx.app.debug("ship", "removed");
		     }
	         
	      }
	}

	

	@Override
	public void reset() {
		
		for (AShipEnemy ship: this.ships) {
					
					ship.alive = false;
					
					this.ships.removeValue(ship, true);
					
					GliderRotate.GliderPool.free((GliderRotate) (ship));
					
		}
		
		this.alive = false;
		
	}

}
