package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.me.ufoattack.game.Abstracts.AShipEnemy.DIRECTION;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class AHive implements Poolable {
	
	public Array<AShipEnemy> ships;
	protected GameScreen game;
	
	protected float lastUpdated;
	protected float updateLimit = 1/30f;

	protected boolean valuesInitialised = false;
	public boolean alive = false;
	public AHive() {
		
		
	}
	public FormationType formationType;
	
	public enum FormationType {
		 zeroSideWays,
		 fortyFive,
		 ninety,
		 OneThreeFive,
		 OneEighty,
		 TwoTwoFive,
		 TwoSeventy,
		 ThreeOneFive
	 }
	public abstract void init(GameScreen game);
	
	public abstract void update(float time);
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		
		for (int i = 0; i < this.ships.size; i++){
			AShip ship = (AShip) this.ships.get(i);
			ship.render(batch,deltaTime);
		}
	}
	
	public abstract void createShips();

}
