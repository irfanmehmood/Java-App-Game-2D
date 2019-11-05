package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.me.ufoattack.game.Class.ships.enemy.planes.GliderRotate;
import com.me.ufoattack.game.Class.ships.enemy.planes.PlaneGreen;
import com.me.ufoattack.game.screens.GameScreen;

public class EnemeyPoolFactory  implements Poolable {
	
	public static final Pool<AShipEnemy>enemeyPool = Pools.get(AShipEnemy.class);
	public enum enemyType {
		 greenPlane,
		 Glider
	 }
	
	public boolean alive = false;
	
	
	PlaneGreen plane;
	public EnemeyPoolFactory(){
		
		this.alive = false;
		
		AShipEnemy plane = EnemeyPoolFactory.enemeyPool.obtain();
		
	}
	
	public void init(GameScreen game,float x,float y,AHive hive){
		plane.init(game, x, y, hive);
	}
	
	public void reset() {
		plane.reset();
		
	}

}
