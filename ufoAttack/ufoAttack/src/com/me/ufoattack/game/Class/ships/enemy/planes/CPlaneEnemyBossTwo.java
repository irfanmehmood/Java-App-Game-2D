package com.me.ufoattack.game.Class.ships.enemy.planes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.AShipEnemy;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.screens.GameScreen;

public class CPlaneEnemyBossTwo extends AShipEnemy{


    AHive hive;
    
	public void init(GameScreen game, float x, float y, AHive hive) {
		
		this.rect.x = x;
		this.rect.y = y;
		this.hive = hive;
		this.rect.height = 196;
		this.rect.width= 196;
		this.health = 20;
		this.firingSpeed = 2.0f;
		int spriteX = 368;
		int spriteY = 235;
		this.shipDirection = AShipEnemy.DIRECTION.Down;
		
		this.currentFrameRegion = new TextureRegion(this.game.myGame.gameTexture, 697, 103, 98, 98);
		this.alive = true;
	}
	
	public CPlaneEnemyBossTwo() {
		
	}
	
	
	public void move() {
		
		
		if (this.shipDirection == AShipEnemy.DIRECTION.Down){
			if (this.rect.y+this.rect.height <= (this.game.rectangleScreenSize.height/2)){
				this.shipDirection = AShipEnemy.DIRECTION.Up;
			}else{
				this.rect.y-=2;
			}
		}else if(this.shipDirection == AShipEnemy.DIRECTION.Up){
			if (this.rect.y+this.rect.height >= (this.game.rectangleScreenSize.height)){
				this.shipDirection = AShipEnemy.DIRECTION.Down;
			}else{
				this.rect.y+=2;
			}
		}
	}
	public void update(float currentTime) {
		
		lastUpdated+=currentTime;
		if (lastUpdated >= this.firingSpeed) {
			
			bullet fire = this.game.enemyBulletPool.obtain();
			fire.init(this.rect,this.game);
			this.game.level.activeBullets.add(fire);
			lastUpdated = 0;
			
		}
		
		super.update(currentTime);
	    
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		if (!handlingHit) {
			batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
		}
		super.render(batch, deltaTime);
	}
}
