package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class AExplosion {
	
	Animation                       planeAnimation;
    TextureRegion[]                 planeAnimationFramesRegion;
    TextureRegion                   currentFrameRegion;
    
    protected float stateTime = 0;
    protected float animateFramesPerSecond = 1/10f;
	protected AShip ship;
	public Rectangle rect;
	protected boolean finished = false;

	public AExplosion(AShip ship, Rectangle rect) {
		this.ship = ship;
		this.rect = new Rectangle();
		this.rect.x = rect.x;
		this.rect.y = rect.y;
		
		this.rect.height = rect.height;
		this.rect.width= rect.width;
		
		int spriteX = 70;
		planeAnimationFramesRegion = new TextureRegion[6];
		for (int i=0;i<planeAnimationFramesRegion.length;i++){
			planeAnimationFramesRegion[i] = new TextureRegion(this.ship.game.myGame.gameTexture, spriteX, 169,32, 32);
			spriteX+=33;
		}
		
		//Empty frame
		planeAnimationFramesRegion[planeAnimationFramesRegion.length - 1] = new TextureRegion(this.ship.game.myGame.gameTexture, 364, 68, 32, 32);

        planeAnimation = new Animation(this.animateFramesPerSecond, planeAnimationFramesRegion);
        
        stateTime = 0f;
	}
	
	private void setDestroyed() {
		
		this.ship.explosionFinished();
		this.planeAnimation = null;
		
	}
	
	public abstract void update(float time);
	
	int animationCount = 0;
	public void render(SpriteBatch batch, float deltaTime) {
	
		if (!finished) {
			stateTime += deltaTime;
	        if (planeAnimation.isAnimationFinished(stateTime) && animationCount > 0){
	        	finished = true;
	        	this.setDestroyed();
	        } else if(planeAnimation.isAnimationFinished(stateTime)){
	        	animationCount++;
	        	stateTime = 0;
	        } else {
	        	
		        currentFrameRegion = planeAnimation.getKeyFrame(stateTime, false);
		        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
	        }
		}
		
	}

}
