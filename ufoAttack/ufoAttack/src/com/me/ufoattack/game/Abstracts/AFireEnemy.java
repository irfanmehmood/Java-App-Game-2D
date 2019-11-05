package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class AFireEnemy extends AFire{

	
	
	public AShipEnemy.DIRECTION direction = AShipEnemy.DIRECTION.Right;
	public AFireEnemy() {
		super();
	}
	
	public abstract void update(float time);
	
	public enum FireType {
		 oneCircle,
		 twoCircle,
		 threeCircle,
		 fourCircle,
		 oneDoubleCircle,
		 twoDoubleCircle,
		 bulletSmallOne,
		 bulletSmallTwo,
		 bulletSmallThree,
		 bulletSmallOneDouble,
		 level5,
		 rocket,
		 missile
	 }
	
	public abstract void render(SpriteBatch batch, float deltaTime);	
	
	public void reset() {
		
		super.reset();
    }
	
	
	public abstract void  init(Rectangle parentRect, GameScreen game);
}
