package com.me.ufoattack.game.Class.ships.enemy.planes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.screens.GameScreen;

public class CPlaneEnemyBossThree extends AShipEnemy{


    AHive hive;
    
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		this.rect.x = x;
		this.rect.y = y;
		this.hive = hive;
		this.rect.height = 98*3;
		this.rect.width=  98*3;
		this.health = 20;
		this.firingSpeed = 2.0f;
		int spriteX = 796;
		int spriteY = 4;
		
		planeAnimationFramesRegion = new TextureRegion[3];
		for (int i=0;i<planeAnimationFramesRegion.length;i++){
			planeAnimationFramesRegion[i] = new TextureRegion(this.game.myGame.gameTexture, spriteX, spriteY,98, 98);
			spriteX-=99;
		}
		
		this.planeAnimation = new Animation(1/6f, this.planeAnimationFramesRegion);
		this.alive = true;

	}
	
	public CPlaneEnemyBossThree() {
		super();
		

	}
	
	boolean handled = false;
	
	
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		
		if (lastUpdated >= this.firingSpeed) {
			//CFireEnemyOne fire = new CFireEnemyOne(this.game,this.rect);
			//this.game.bullets.add(fire);
			lastUpdated = 0;
		}
		
		super.update(currentTime);
	    
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
