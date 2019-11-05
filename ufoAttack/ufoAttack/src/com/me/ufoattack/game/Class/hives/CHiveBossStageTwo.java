package com.me.ufoattack.game.Class.hives;

import java.util.Iterator;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.levels.ClevelOne;
import com.me.ufoattack.game.Class.levels.ClevelTwo;
import com.me.ufoattack.game.Class.ships.enemy.planes.CPlaneEnemyBossOne;
import com.me.ufoattack.game.Class.ships.enemy.planes.CPlaneEnemyBossTwo;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.ships.Carrier;
import com.me.ufoattack.game.Class.ships.enemy.ships.Submarine;
import com.me.ufoattack.game.screens.GameScreen;

public class CHiveBossStageTwo extends AHive{

	public CHiveBossStageTwo() {
		super();

	}
	
	
	public void init(GameScreen game) {
		
		if (!valuesInitialised) {
			
			this.game = game;
			this.level = (ClevelTwo) game.level;
			this.ships = new Array<AShipEnemy>();
			valuesInitialised = true;
		}
		
		this.alive = true;
		
	}

	@Override
	public void createShips() {
		float startX = 100;
		float startY = this.game.rectangleScreenSize.height;
		//CPlaneEnemyBossTwo boss = new CPlaneEnemyBossTwo(this.game, startX,startY ,this,1);
		//this.ships.add(boss);
		
		startX += 300;
		
		//CPlaneEnemyBossTwo bossClone = new CPlaneEnemyBossTwo(this.game, startX,startY ,this,1);
		//this.ships.add(bossClone);
		
	}

	
	String hivePositon = "right";
	ClevelTwo level;
	public void update(float currentTime) {

		lastUpdated+=currentTime;
		
		if (lastUpdated >= this.updateLimit) {
			lastUpdated = 0;
		}
		Iterator<AShipEnemy> iter = this.ships.iterator();

	      while(iter.hasNext()) {
	    	  AShipEnemy ship = (AShipEnemy) iter.next();
	    	 if(ship.alive) {
	    		if((ship.rect.y + ship.rect.height) >= this.game.rectangleScreenSize.height + 300 ){
	    			 //ship.rect.y = -200;
	    		 }else {
	    			 if (lastUpdated == 0){ship.move();}
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
