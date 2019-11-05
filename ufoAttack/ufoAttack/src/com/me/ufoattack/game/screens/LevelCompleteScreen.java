package com.me.ufoattack.game.screens;

import java.awt.Point;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.me.ufoattack.game.MyGame;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.Class.fires.friendly.CBomb;
public class LevelCompleteScreen implements Screen
{
        MyGame myGame;
        ShapeRenderer shapeRenderer;
        OrthographicCamera camera;
        com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer renderer;
        Rectangle  mainImageDrawSize;
        SpriteBatch spriteBatch;
        Rectangle rectangleScreenSize;
        int mapWidth;
    	int mapHeight;
    	int tilePixelWidth;
    	int tilePixelHeight;
    	
    	int mapPixelWidth;
    	int mapPixelHeight;
    	TiledMap tiledMap;
    	MapProperties mapProperties;
        
        Iterator<TextureRegion> gameStartOptionsTextTextureRegionArrayIter;
        ALevel level;
        public LevelCompleteScreen(MyGame game, Rectangle rectangleScreenSize, ALevel level){
        	
                myGame = game;
                this.rectangleScreenSize = rectangleScreenSize;
                this.level = level;
                
                this.loadTiledMap();
        		this.setCamera();
            	
            	renderer = new com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer(tiledMap, 1 / 32f);
            	renderer.setView(this.myGame.getCamera());
     
            	this.font = new BitmapFont();
        		
                this.spriteBatch = new SpriteBatch();
        }
        
        
       private BitmapFont font;
        @Override
        
        public void render(float deltaTime)
        {	
        	
        	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
     	    this.tiledMapScroller();
    	    
    	    this.spriteBatch.begin();
    	    font.setScale(2.5f);
    	    this.font.draw(this.spriteBatch, "Stage " + ALevel.currentLevel + " Complete", 200, this.rectangleScreenSize.height - 60);
    	    font.setScale(2.0f);
    	    this.font.draw(this.spriteBatch, "Score " + level.scores.getScore(), 200, this.rectangleScreenSize.height - 160);
    	    spriteBatch.end();
    	    this.lastUpdated += deltaTime;
    	    
    	    
    	    if(Gdx.input.isTouched()) {
   	    	 
    	    	ALevel.currentLevel+=1;
    	    	this.myGame.showGameScreen();
   	         
    	    }
        }
        
        float cameraStoredStartPoint;
        private void loadTiledMap(){
    		
    		this.tiledMap = new TmxMapLoader().load("mapEditor/map.tmx");
    		this.mapProperties  = this.tiledMap.getProperties();
    		
    		this.mapWidth = this.mapProperties.get("width", Integer.class);
    		this.mapHeight = this.mapProperties.get("height", Integer.class);
    		this.tilePixelWidth = this.mapProperties.get("tilewidth", Integer.class);
    		this.tilePixelHeight = this.mapProperties.get("tileheight", Integer.class);
    		
    		this.mapPixelWidth = this.mapWidth * this.tilePixelWidth;
    		this.mapPixelHeight = this.mapHeight * this.tilePixelHeight;
    		MapLayer layer = this.tiledMap.getLayers().get(1);
    		
    		// get the frame tiles
    		/*
    		Iterator<TiledMapTile> tiles = tiledMap.getTileSets().getTileSet("cannon").iterator();
    		while(tiles.hasNext()) {
    			TiledMapTile tile = tiles.next();
    				if(tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals("flower"))
    						frameTiles.add((StaticTiledMapTile) tile);
    		}
    		*/
    		
    		
    		Gdx.app.debug("GameScreen", "Map Height:" +Integer.toString(mapHeight));
    	}
        
        float lastUpdated;
        private void tiledMapScroller() {
    		
    		/*
    	     * Start Scrolling background map
    	     */
    	    if (this.lastUpdated >= 0.02f) {
    	    	
    	    	if ((this.myGame.getCamera().position.y  + 0.05f) <= (this.mapHeight - this.cameraStoredStartPoint)) {
    	    		
    	    		this.myGame.getCamera().position.y += 0.05f;
    	    		
    	    	} else {
    	    		//Gdx.app.debug("Camera", "y:" +Float.toString(this.myGame.getCamera().position.y));
    	    		this.myGame.getCamera().position.y = this.cameraStoredStartPoint;
    	    	}
    	    	
    	    	this.lastUpdated = 0;
    	    	//update camera as the position must have changed
    	    	this.myGame.getCamera().update();
    	    }
    	    /*
    	     * End Scrolling background map
    	     */
    		
    	    this.renderer.setView(this.myGame.getCamera());
    	    this.renderer.render(); // can't be in the batch block for some reason
    	    
    	}
        
        private void setCamera(){
    		
    		this.myGame.getCamera().setToOrtho(false, mapWidth, mapHeight/4);
        	this.myGame.getCamera().update();
        	this.cameraStoredStartPoint = this.myGame.getCamera().position.y;
        	
        	
        	Gdx.app.debug("GameScreen", "Map Height:" +Integer.toString(mapHeight) + "Map Width:" +Integer.toString(mapWidth));
        	Gdx.app.debug("GameScreen", "Camera Start Y:" +Float.toString(this.myGame.getCamera().position.y));
        	
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