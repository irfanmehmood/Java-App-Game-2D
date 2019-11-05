package com.me.ufoattack.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.me.ufoattack.game.Abstracts.ALevel;
import com.me.ufoattack.game.screens.EndScreen;
import com.me.ufoattack.game.screens.GameScreen;
import com.me.ufoattack.game.screens.LevelCompleteScreen;
import com.me.ufoattack.game.screens.SplashScreen;


public class MyGame extends Game {
	
	
	OrthographicCamera camera;
	GameScreen gameScreen= null;
    SplashScreen splashScreen= null;
    EndScreen endScreen= null;
    LevelCompleteScreen levelCompleteScreen= null;
    ShapeRenderer shapeRenderer= null;
    Rectangle rectangleScreenSize;
    public static float screenWidthHeightRatio = 0f;
    

    public Texture  gameTexture;
    public Texture  tileSetTexture;
	public Texture  gameTextureGreyed;
	public Texture  powerUpTextures;
	public Texture  newTexture;
	
	public TextureRegion[] numbersRegion;
	public TextureRegion[] alphabetsRegion;
    @Override
    public void create() {
    	
    	
    	Gdx.app.setLogLevel(Application.LOG_DEBUG);
    	this.rectangleScreenSize = new Rectangle(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    	
    	//As we have the screen 
    	this.screenWidthHeightRatio = Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    	this.newTexture = new Texture(Gdx.files.internal("images/textures/newTexture.png"));
    	this.tileSetTexture = new Texture(Gdx.files.internal("mapEditor/tileSet.png"));
    	this.gameTexture = new Texture(Gdx.files.internal("images/textures/1945texture1024_1024_3.png"));
		this.gameTextureGreyed = new Texture(Gdx.files.internal("images/textures/1945texture1024_1024_greyed.png"));
		this.powerUpTextures = new Texture(Gdx.files.internal("images/textures/powertextures.png"));
		
		
		
		numbersRegion = new TextureRegion[10];
		int spriteX = 580;
		int spriteY = 105;
		for (int i=0;i<numbersRegion.length;i++){
			numbersRegion[i] = new TextureRegion(gameTexture, spriteX, spriteY,10, 17);
			spriteX+=10;
		}
		
		alphabetsRegion = new TextureRegion[10];
		spriteX = 580;
		spriteY = 105;
		for (int i=0;i<numbersRegion.length;i++){
			numbersRegion[i] = new TextureRegion(gameTexture, spriteX, spriteY,12, 13);
			spriteX+=10;
		}
		
		
    	this.createCamera();
    	Gdx.app.debug("Resolutions", "w:" +Integer.toString(Gdx.graphics.getWidth()) + " h:" + Integer.toString(Gdx.graphics.getHeight()));
    	this.showSplashScreen();
    	
    	

    	
    	
    }
    
    
    
    
    public void showGameScreen() {
    	
    	if (this.gameScreen == null){
    		this.gameScreen = new GameScreen(this,this.rectangleScreenSize,ALevel.currentLevel);
    		
    	}
    	
        setScreen(this.gameScreen);
        
        if (this.endScreen != null){
    		this.endScreen.dispose();
    		this.endScreen = null;
    	}
        
        if (this.splashScreen != null){
    		this.splashScreen.dispose();
    		this.splashScreen = null;
    	}
        
        if (this.levelCompleteScreen != null){
        	this.levelCompleteScreen.dispose();
    		this.levelCompleteScreen = null;
    	}
        
    }
    
    public void showSplashScreen() {
    	
    	if (this.splashScreen == null){
    		this.splashScreen = new SplashScreen(this,this.rectangleScreenSize);
    	}
        setScreen(this.splashScreen); 
        

        if (this.endScreen != null){
    		this.endScreen.dispose();
    		this.endScreen = null;
    	}
        
        if (this.gameScreen != null){
    		this.gameScreen.dispose();
    		this.gameScreen = null;
    	}
        
        if (this.levelCompleteScreen != null){
        	this.levelCompleteScreen.dispose();
    		this.levelCompleteScreen = null;
    	}
    }
    
    
    public void showEndScreen() {
    	
    	
    	if (this.endScreen == null){
    		this.endScreen = new EndScreen(this,this.rectangleScreenSize);
    	}
        setScreen(this.endScreen);
        
        if (this.splashScreen != null){
    		this.splashScreen.dispose();
    		this.splashScreen = null;
    	}
        
        if (this.gameScreen != null){
    		this.gameScreen.dispose();
    		this.gameScreen = null;
    	}
        
        if (this.levelCompleteScreen != null){
        	this.levelCompleteScreen.dispose();
    		this.levelCompleteScreen = null;
    	}
    }
    
    public void showLevelCompleteScreen(ALevel level) {

    	if (this.levelCompleteScreen == null){
    		this.levelCompleteScreen = new LevelCompleteScreen(this,this.rectangleScreenSize, level);	
    	}
    	
    	setScreen(this.levelCompleteScreen);
    	
    	if (this.gameScreen != null){
    		this.gameScreen.dispose();
    		this.gameScreen = null;
    		
    	}

    	if (this.splashScreen != null){
    		this.splashScreen.dispose();
    		this.splashScreen = null;
    	}
    	
    	if (this.endScreen != null){
    		this.endScreen.dispose();
    		this.endScreen = null;
    	}
    }
    
    private void createCamera(){
		
		// create the camera and the SpriteBatch
	    this.camera = new OrthographicCamera();
	    this.camera.setToOrtho(false, this.rectangleScreenSize.width,this.rectangleScreenSize.height);

	}
    
    public OrthographicCamera getCamera(){
		
	    return this.camera;

	}
    
    public ShapeRenderer getShapeRenderer(){
		
	    return this.shapeRenderer;

	}
    
 }

