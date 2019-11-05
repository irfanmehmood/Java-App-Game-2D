package com.me.ufoattack.game.screens;

import java.awt.Point;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.MyGame;
public class EndScreen implements Screen
{
        MyGame myGame;
        ShapeRenderer shapeRenderer;
        OrthographicCamera camera;
        TextureRegion  mainImageTextureRegion;
        Rectangle  mainImageDrawSize;
        SpriteBatch spriteBatch;
        Rectangle rectangleScreenSize;
        Rectangle gameStartOptionsTextTextureRegion_stretchSize;
        Rectangle gameStartOptionsArrowRegion_stretchSize;
        TextureRegion gameStartOptionsArrow;
        
        
        protected float stateTime = 0;
        protected float animateFramesPerSecond = 1/10f;
        Animation                       planeAnimation;
        TextureRegion[]                 planeAnimationFramesRegion;
        TextureRegion                   currentFrameRegion;
        
        
        
        
        public Array<TextureRegion> gameStartOptionsTextTextureRegionArray;
        
        Iterator<TextureRegion> gameStartOptionsTextTextureRegionArrayIter;
        
        public EndScreen(MyGame game, Rectangle rectangleScreenSize){
        	
                myGame = game;
                this.rectangleScreenSize = rectangleScreenSize;
                
                int Scale = 1;
                
                if (rectangleScreenSize.height <= 800) {
                	Scale = 1;
                }else if(rectangleScreenSize.height > 800){
                	Scale = 2;
                }
                
                
                
                mainImageTextureRegion = new TextureRegion(game.gameTexture, 301,50,100,16);
                
                mainImageDrawSize = new Rectangle (0,0,mainImageTextureRegion.getRegionWidth() * (3*Scale),mainImageTextureRegion.getRegionHeight() * (3*Scale));
                mainImageDrawSize.x = (rectangleScreenSize.width / 2) - (mainImageDrawSize.width / 2);
                mainImageDrawSize.y = rectangleScreenSize.height - mainImageDrawSize.height;
                
                
               
                
                gameStartOptionsTextTextureRegionArray = new Array<TextureRegion>();
                
            	int y = 461;
            	for (int i=0;i<5;i++){
            		TextureRegion gameStartOptionsTextTextureRegion = new TextureRegion(myGame.gameTexture, 580,y,100,16);
            		gameStartOptionsTextTextureRegionArray.add(gameStartOptionsTextTextureRegion);
            		y-=18;
            	}
            	
            	gameStartOptionsArrow = new TextureRegion(myGame.gameTexture, 569,388,12,16);
            	
                gameStartOptionsTextTextureRegion_stretchSize = new Rectangle(0,0,100*(Scale+Scale), 16*(Scale+Scale));
                gameStartOptionsArrowRegion_stretchSize = new Rectangle(0,0,12*(Scale+Scale), gameStartOptionsTextTextureRegion_stretchSize.height);
                
                
                
                this.spriteBatch = new SpriteBatch();
                this.imageAnnimation();
        }
        
        
        
        public void imageAnnimation()
        {
	        int spriteX = 298;
	        int spriteY = 499;
			planeAnimationFramesRegion = new TextureRegion[2];
			for (int i=0;i<planeAnimationFramesRegion.length;i++){
				planeAnimationFramesRegion[i] = new TextureRegion(this.myGame.gameTexture, spriteX, spriteY,100, 17);
				spriteY+=17;
			}
	        planeAnimation = new Animation(this.animateFramesPerSecond, planeAnimationFramesRegion);
	        
	        stateTime = 0f;
        }
       
        int selectedArrow = 4;
        @Override
        public void render(float delta)
        {	
        	camera = this.myGame.getCamera();

        	Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    	    //this.spriteBatch.setProjectionMatrix(this.camera.combined);
    	    
    	    
    	    this.spriteBatch.begin();
    	    stateTime += delta;
    	    currentFrameRegion = planeAnimation.getKeyFrame(stateTime, true);
    	    //currentFrameRegion.setRegionWidth((int)mainImageDrawSize.width);
    	    //currentFrameRegion.setRegionWidth((int)mainImageDrawSize.height);
    	    spriteBatch.draw(currentFrameRegion, mainImageDrawSize.x,this.rectangleScreenSize.height-100,300,51);
    	    //this.spriteBatch.draw(mainImageTextureRegion, mainImageDrawSize.x,mainImageDrawSize.y,mainImageDrawSize.width,mainImageDrawSize.height);
    	    this.gameStartOptionsTextTextureRegionArrayIter = this.gameStartOptionsTextTextureRegionArray.iterator();
    	    
    	    int y = 389;
    	    int x = 200;
    	    
    	    for (int i=0;i<gameStartOptionsTextTextureRegionArray.size;i++){
        		
    	    	TextureRegion textRegion = gameStartOptionsTextTextureRegionArray.get(i);
    	    	this.spriteBatch.draw(textRegion,x,y,gameStartOptionsTextTextureRegion_stretchSize.width,gameStartOptionsTextTextureRegion_stretchSize.height);
    	    	
    	    	if (i == this.selectedArrow) {
    	    		this.spriteBatch.draw(gameStartOptionsArrow,x-gameStartOptionsArrowRegion_stretchSize.width,y,this.gameStartOptionsArrowRegion_stretchSize.width,gameStartOptionsArrowRegion_stretchSize.height);
    	    	}
    	    	y+=gameStartOptionsTextTextureRegion_stretchSize.height+16;
        	}
    	    spriteBatch.end();
    	    this.checkAndroidInput(delta);
    	    this.checkInput(delta);
    	  //Gdx.app.debug("delta", Float.toString(delta));
    	    
            
        }
        
        boolean handled = false;
        float lastUpdated = 0;
        private void checkAndroidInput(float delta) {
	        if(Gdx.input.isTouched()) {
		    	
		         Vector3 touchPos = new Vector3();
		         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		         this.camera.unproject(touchPos);
		         Gdx.app.debug("Moved", "x:"+Float.toString(Gdx.input.getX())+" y:"+Float.toString(Gdx.input.getY()));
		         
		         
		         int y = 389;
		    	 int x = 200;
		         for (int i=0;i<gameStartOptionsTextTextureRegionArray.size;i++){
		        		
		    	    	Rectangle  rect = new Rectangle(x,y,gameStartOptionsTextTextureRegion_stretchSize.width,gameStartOptionsTextTextureRegion_stretchSize.height);
		    	    	if (Gdx.input.getX() >= rect.x && Gdx.input.getX() <= (rect.x + rect.width) && (Gdx.input.getY() >= rect.y && Gdx.input.getY() <= (rect.y + rect.height))){
		    	    		Gdx.app.debug("touched", Integer.toString(i));
		    	    		if (selectedArrow ==4){
		        				myGame.showGameScreen();
		                    	//Gdx.app.debug("CFriendlyPlane", "show");
		        			}else if (selectedArrow ==0){
		        				Gdx.app.exit();
		        			}
		    	    	}
		    	    	y+=gameStartOptionsTextTextureRegion_stretchSize.height+16;
		    	    	
		        }
		         
		    }
        }
        private void checkInput(float delta) {
    		
        	lastUpdated += delta;
        	//Gdx.app.debug("selectedArrow", Integer.toString(selectedArrow));
        	if (lastUpdated - delta > 0.075) {
        		
            	if(!handled && Gdx.input.isKeyPressed(Keys.ENTER)) {
        			handled = true;
        			if (selectedArrow ==4){
        				myGame.showGameScreen();
        			}else if (selectedArrow ==0){
        				Gdx.app.exit();
        			}
        		}
        	    if(!handled && Gdx.input.isKeyPressed(Keys.UP)) {
        	    	handled = true;
        	    	if (selectedArrow !=4){
            	    	selectedArrow +=1;
            	    }
        	    	
        	    }
        	    if(!handled && Gdx.input.isKeyPressed(Keys.DOWN)) {
        	    	handled = true;
        	    	if (selectedArrow !=0){
        	    	selectedArrow -=1;
        	    	}
        	    	
        	    }
        	    handled = false;
        	    lastUpdated = 0;
    		}
    		
    	    
    	}
        
       

		@Override
		public void resize(int width, int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void hide() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resume() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}
}