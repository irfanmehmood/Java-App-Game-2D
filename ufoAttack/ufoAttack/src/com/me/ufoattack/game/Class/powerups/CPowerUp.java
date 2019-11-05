package com.me.ufoattack.game.Class.powerups;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.APowerUp;
import com.me.ufoattack.game.Abstracts.AShip;
import com.me.ufoattack.game.Abstracts.APowerUp.PowerUpType;
import com.me.ufoattack.game.screens.GameScreen;

public class CPowerUp extends APowerUp{

	public CPowerUp() {
		super();
	}
	
	public void init(GameScreen game,Rectangle parentRectangle,PowerUpType powerUpType) {
		
		super.init(game, parentRectangle, powerUpType);
		
		this.powerUpType = powerUpType;
		this.rect.height = 32;
		this.rect.width= 32;
		
		this.rect.x = (game.rectangleScreenSize.width / 2) - (this.rect.width /2);
		this.rect.y = game.rectangleScreenSize.height-this.height;
		
		
		if (this.powerUpType == PowerUpType.pointsGoldStar) {
			
			
			this.planeAnimationFramesRegion[0].setRegion(38, 640, 100, 100);
			this.planeAnimationFramesRegion[1].setRegion(38, 640, 100, 100);
			this.rect.x = parentRectangle.x;
			this.rect.y = parentRectangle.y;
			
		} else if (this.powerUpType == PowerUpType.pointsSilverStar) {
			
			this.planeAnimationFramesRegion[0].setRegion(159, 640, 100, 100);
			this.planeAnimationFramesRegion[1].setRegion(159, 640, 100, 100);
			this.rect.x = parentRectangle.x;
			this.rect.y = parentRectangle.y;
			
		} else if (this.powerUpType == PowerUpType.weaponSpeed) {
			
			this.planeAnimationFramesRegion[0].setRegion(279, 563, 32, 32);
			this.planeAnimationFramesRegion[1].setRegion(279, 569, 32, 32);
			
		} else if (this.powerUpType == PowerUpType.weaponUpgrade) {
			
			this.planeAnimationFramesRegion[0].setRegion(242, 563, 32, 32);
			this.planeAnimationFramesRegion[1].setRegion(242, 569, 32, 32);
			
		} else if (this.powerUpType == PowerUpType.extraLife) {
			
			this.planeAnimationFramesRegion[0].setRegion(201, 563, 32, 32);
			this.planeAnimationFramesRegion[1].setRegion(201, 563, 32, 32);
			
		}
		
		this.alive = true;
	}


	@Override
	public void update(float delta) {
		
		if(this.rect.overlaps(this.game.plane.rect)) {
			if (this.powerUpType == PowerUpType.pointsGoldStar) {
				this.game.level.scores.increaseColectedGoldenStars();
			} else if (this.powerUpType == PowerUpType.pointsSilverStar) {
				this.game.level.scores.increaseColectedGoldenStars();
			} else if (this.powerUpType == PowerUpType.weaponSpeed) {
				this.game.plane.upgradeFire();
			} else if (this.powerUpType == PowerUpType.weaponUpgrade) {
				this.game.plane.upgradeSpeed();
			} else if (this.powerUpType == PowerUpType.extraLife) {
				this.game.plane.addLife();
			}
			this.alive = false;
		} else {
			if ((this.rect.y + this.rect.height) < 0) {
				this.alive = false;
			} else {
				this.rect.y -= 1;
			}
		
		}
		
	}

	
}