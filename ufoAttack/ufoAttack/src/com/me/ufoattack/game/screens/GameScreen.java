package com.me.ufoattack.game.screens;




import java.awt.Color;
import java.util.Iterator;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.me.ufoattack.game.MyGame;
import com.me.ufoattack.game.Abstracts.AHive;
import com.me.ufoattack.game.Abstracts.AFire;
import com.me.ufoattack.game.Abstracts.ALandscape;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.Abstracts.APowerUp;
import com.me.ufoattack.game.Class.CScores;
import com.me.ufoattack.game.Class.landscapes.CIsland;
import com.me.ufoattack.game.Class.landscapes.CIslandThree;
import com.me.ufoattack.game.Class.landscapes.CIslandTwo;
import com.me.ufoattack.game.Class.levels.ClevelOne;
import com.me.ufoattack.game.Class.levels.ClevelTwo;
import com.me.ufoattack.game.Class.ships.CPlaneFriendly;
import com.me.ufoattack.game.Class.fires.enemy.bullet;
import com.me.ufoattack.game.Class.fires.friendly.CBomb;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyEight;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlySide;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyNine;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyRockets;
import com.me.ufoattack.game.Class.fires.friendly.CFireFriendlyStraight;
import com.me.ufoattack.game.Class.fires.friendly.fireTargeted;
import com.me.ufoattack.game.Class.hives.CHiveBossStageOne;
import com.me.ufoattack.game.Class.hives.HiveSubmarine;
public class GameScreen implements Screen {
	
	private Music backgroundMusic;
	private SpriteBatch spriteBatch;

	public CPlaneFriendly plane;
	public Rectangle rectangleScreenSize;
	
	
	
	public final Array<ALandscape> landscapes = new Array<ALandscape>();
	
	
	
	public final Pool<CFireFriendlyRockets> CFireFriendlyRocketsPool = Pools.get(CFireFriendlyRockets.class);
	public final Pool<CFireFriendlySide> CFireFriendlySidePool = Pools.get(CFireFriendlySide.class);
	public final Pool<CFireFriendlyEight> CFireFriendlyEightPool = Pools.get(CFireFriendlyEight.class);
	public final Pool<CFireFriendlyNine> CFireFriendlyNinePool = Pools.get(CFireFriendlyNine.class);
	public final Pool<CBomb> CBombPool = Pools.get(CBomb.class);
	public final Pool<bullet>enemyBulletPool = Pools.get(bullet.class);
	public final Pool<CFireFriendlyStraight>CFireFriendlyStraightPool = Pools.get(CFireFriendlyStraight.class);
	public final Pool<fireTargeted>fireTargetedPool = Pools.get(fireTargeted.class);
	
	


	
	
	MyGame game; 
	protected TextureRegion planeHealthLifeRegion;
	protected TextureRegion scoreRegion;
	protected TextureRegion levelRegion; 
	
	Iterator<ALandscape> iterALandscape;
	
	int planeHealth;
	int startXHealthHeart = 20;
	public MyGame myGame;
	public TextureRegion  buttonWeaponRegion;
	public float cameraStoredStartPoint ;
	
	com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer renderer;
	
	
	int mapWidth;
	public int mapHeight;
	public int mapHeightPixels;
	public TiledMap tiledMap;
	MapProperties mapProperties;
	public ALevel level;
	
	public ShapeRenderer shapeRenderer;
	float textWidth,textHeight;
	float lastUpdated;
	public float cameraMapHeightDifference;
	
	public GameScreen(MyGame myGame, Rectangle rectangleScreenSize,int levelNo) {
		
		this.myGame=myGame;
		this.font = new BitmapFont();
		font.setScale(2.0f);
		this.rectangleScreenSize = rectangleScreenSize;
		this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("space.wav"));
		
		// Create regions needed for games
		scoreRegion = new TextureRegion(this.myGame.tileSetTexture, 0, 442, 160, 54);
		levelRegion = new TextureRegion(this.myGame.tileSetTexture, 166, 442, 140, 54);
		this.buttonWeaponRegion = new TextureRegion(this.myGame.gameTextureGreyed, 268, 268, 32, 32);
		
		
		// Load Tiled Map and get some properties
		this.tiledMap = new TmxMapLoader().load("mapEditor/map.tmx");
		this.mapProperties  = this.tiledMap.getProperties();
		this.mapWidth = this.mapProperties.get("width", Integer.class);
		this.mapHeight = this.mapProperties.get("height", Integer.class);
		this.mapHeightPixels = this.mapHeight * 32;
		MapLayer layer = this.tiledMap.getLayers().get(1);
		// get the frame tiles
		Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(2);
		Iterator<TiledMapTile> tiles = tiledMap.getTileSets().getTileSet("tileSet").iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			
				if(tile.getProperties().containsKey("stop"))
						frameTiles.add((StaticTiledMapTile) tile);
		}

		// Information 
		//Galaxy nexus 720x1280
		//Most of new galaxy phones 800x1280
		//Iphones 640x960
		
		
		// Information
		//My Tiled map dimensions
		//height = 125 units * 32 px = 4000 pixel;
		//width  = 25 units * 32 px = 800 pixel; 
		
		
		// Information
		// So we can convert to tiled map camera view, we divide width and height by twenty
		// Our tiled map width is 800 pixels 20 units
		
		//Our map of 
		// Height 4000px / 32 = 125 units
		// Width 800px /32 = 25 units
		
		// Android screen 
		// Width size = 720px / 32 = 22.5
		// Height
		
		//To ignore stretthing map disable below
		//float cameraWidth = Gdx.graphics.getWidth(); 
		float cameraWidth = 800;
		float cameraHeight = Gdx.graphics.getHeight(); //Gives us 32Pixel format needed to render tiled map
		
		//Or camera and tiled map renderer our in 1 unit format pixels 
		this.myGame.getCamera().setToOrtho(false, cameraWidth  , cameraHeight); 
    	this.myGame.getCamera().update();

    	
    	//Store camera start view point
    	this.cameraStoredStartPoint = this.myGame.getCamera().position.y;
    	this.cameraMapHeightDifference = this.mapHeightPixels - this.cameraStoredStartPoint;
    	
    	//Tiled Map Renderer
    	renderer = new com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer(tiledMap, 1f);
    	renderer.setView(this.myGame.getCamera());
    	
    	
    	//this.myGame.getCamera().position.y = 0;
    	Gdx.app.debug("GameScreen", "Map Height:" +Integer.toString(mapHeight));
    	Gdx.app.debug("GameScreen", "Map Height:" +Integer.toString(mapHeight) + " Width:" +Integer.toString(mapWidth));
    	Gdx.app.debug("GameScreen", "Camera Start Y:" +Float.toString(this.myGame.getCamera().position.y));
		
		
    	// Create SpriteBatch
		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer=new ShapeRenderer();
		
		// Start game
		// Start back ground music
		// this.backgroundMusic.setLooping(true);
		// this.backgroundMusic.play();
		
		
		// Start Game
		//Select game level
		switch(levelNo)	 {
			case 1:
				this.level = new ClevelOne(this);
				break;
			case 2:
				this.level = new ClevelTwo(this);
				break;
		}
		
		// Create plane
		this.plane = new CPlaneFriendly(this, 0,0);
		this.planeHealthLifeRegion = new TextureRegion(this.myGame.gameTexture, 202, 268, 32, 32);
		
		//Add island
		this.landscapes.add(new CIsland(this));
		this.landscapes.add(new CIslandTwo(this));
		this.landscapes.add(new CIslandThree(this));
		
		// Game is running
	}
	
	
	
	
	public void dispose() {
	      // dispose of all the native resources
		this.backgroundMusic.dispose();
		this.spriteBatch.dispose();
	}
	private BitmapFont font;
	int currentStage = 1;
	int currentStageProgress = 0;
	int currentScore = 0;
	String levelText;
	public float mapScrollSpeed = 1/4f;
	public void render(float deltaTime) {
		
		
		//Gdx.app.debug("FPS", Integer.toString(Gdx.graphics.getFramesPerSecond()));
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    //this.myGame.getCamera().position.set(this.plane.rect.x + this.plane.rect.width / 2, this.plane.rect.y + this.plane.rect.height / 2, 0);
	    //this.myGame.getCamera().update();
	    
	    
	    
	    /*
	     * Start Scrolling background map
	     */
	    
	    	
	    	if ((this.myGame.getCamera().position.y  + mapScrollSpeed) <= cameraMapHeightDifference) {
	    		
	    		this.myGame.getCamera().position.y += mapScrollSpeed;
	    		
	    		//Gdx.app.debug("camera new", "y:"  + Float.toString(this.myGame.getCamera().position.y));
	    		
	    	} else {
	    		this.level.increaseLevelProgress();
	    		this.myGame.getCamera().position.y = this.cameraStoredStartPoint;
	    	}
	    	
	    	//update camera as the position must have changed
	    	this.myGame.getCamera().update();
	    	this.renderer.setView(this.myGame.getCamera());
	    	this.renderer.render(); // can't be in the batch block for some reason
	   
	    /*
	     * End Scrolling background map
	     */
	    
	    //this.spriteBatch.setProjectionMatrix(this.myGame.getCamera().combined);
	    this.spriteBatch.begin();
	    
	    /*
	    for (int landscapeI = 0; landscapeI < this.landscapes.size; landscapeI++){
	    	ALandscape land = this.landscapes.get(landscapeI);
	    	//land.render(this.spriteBatch, deltaTime);
	    }
	    */
	    this.level.render(this.spriteBatch, deltaTime);
	    
	    
	    this.plane.render(this.spriteBatch,deltaTime);
	    float x = this.rectangleScreenSize.width-5;
		for (int i=0;i< this.plane.getHealth() -1; i++){
	    	this.spriteBatch.draw(this.planeHealthLifeRegion, x, 0, 64, 64);
	    	x-=50;
	    }
		this.spriteBatch.draw(this.buttonWeaponRegion, 0, 32, 64, 64);
		
		
		this.spriteBatch.draw(levelRegion, this.rectangleScreenSize.width-this.levelRegion.getRegionWidth(), this.rectangleScreenSize.height - this.levelRegion.getRegionHeight());
		this.font.draw(this.spriteBatch, Integer.toString(ALevel.currentLevel), this.rectangleScreenSize.width-this.levelRegion.getRegionWidth()+100, this.rectangleScreenSize.height - 15);
		
        this.spriteBatch.draw(this.scoreRegion, 0, this.rectangleScreenSize.height - (this.scoreRegion.getRegionHeight()), 160, 54);
        this.font.draw(this.spriteBatch, Integer.toString(this.currentScore), 100, this.rectangleScreenSize.height - 15);


        
        
        
        
		spriteBatch.end();
		
		/////End of Draw
	    
		
		
		
	    
	    this.level.update(deltaTime);
	    
	    /*
	    this.iterALandscape = this.landscapes.iterator();
	    while(iterALandscape.hasNext()) {
	    	iterALandscape.next().update(deltaTime);
	    }
	    */
	    
	    
	    
	    
	    
	    
	    
	    this.plane.checkInput();
		this.plane.update(deltaTime);

	    
	    
	    
		
	    
	    
	    
	    this.lastUpdated += deltaTime;
	    

		this.currentStageProgress = this.level.getLevelProgress();
		this.currentScore = this.level.scores.getScore();
		
		
	
	    
	    //Kill game
	    if (this.plane.getHealth() == 0){
	    	
	    	this.myGame.getCamera().position.y = this.cameraStoredStartPoint;
			this.myGame.showEndScreen();
			
		}else{
			
			if (this.level.levelCompleted){
		    	this.myGame.getCamera().position.y = this.cameraStoredStartPoint;
				this.myGame.showLevelCompleteScreen(this.level);
				
			}
			
		}
	    
	    //Gdx.app.debug("activeBullets Size:", Integer.toString(this.activeBullets.size));
	}
	float velX, velY;
	boolean flinging = false;
	public boolean fling (float velocityX, float velocityY, int button) {
		Gdx.app.log("GestureDetectorTest", "fling " + velocityX + ", " + velocityY);
		flinging = true;
		//velX = camera.zoom * velocityX * 0.5f;
		//velY = camera.zoom * velocityY * 0.5f;
		return false;
		}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void show() {
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
	
	

}
