package com.me.ufoattack.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.me.ufoattack.game.screens.GameScreen;

public abstract class APowerUp   implements Poolable{
	
	protected int x;   // the X coordinate
	protected int y; 
	protected int height  = 10;
	protected int width  = 4;
	protected boolean loopAnimation = true;
	public enum PowerUpType {pointsGoldStar, pointsSilverStar, weaponSpeed, weaponUpgrade, extraLife}
	protected PowerUpType powerUpType;
	protected AShip plane;
	protected Rectangle rect;
	protected float lastUpdated;
	public boolean alive = false;
	protected Animation planeAnimation;
	protected TextureRegion[] planeAnimationFramesRegion;
	protected TextureRegion currentFrameRegion;
	protected boolean valuesInitialised = false;

	protected float stateTime = 1/2f;
	protected GameScreen game;
	
	
	public APowerUp() {
		this.alive = false;
	}
	
	public void init(GameScreen game,Rectangle parentRectangle,PowerUpType powerUpType ) {
		
		if (!this.valuesInitialised) {
			this.game = game;
			this.rect = new Rectangle();
			this.planeAnimationFramesRegion = new TextureRegion[2];
			this.planeAnimationFramesRegion[0] = new TextureRegion(this.game.myGame.newTexture);
			this.planeAnimationFramesRegion[1] = new TextureRegion(this.game.myGame.newTexture);
			this.planeAnimation = new Animation(0.025f, this.planeAnimationFramesRegion);
			this.valuesInitialised = true;
		}
		
		 
	}
	
	public void render(SpriteBatch batch, float deltaTime) {
		
		stateTime += deltaTime;
        currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrameRegion, this.rect.x,this.rect.y,  this.rect.width, this.rect.height);
		
	}
	
	
	@Override
	public void reset() {
		this.rect.x =0;
		this.rect.y =0;
		this.alive = false;
	}

	public abstract void update(float delta);

}
