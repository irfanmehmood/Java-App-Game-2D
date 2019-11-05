package com.me.ufoattack.game.Class;


public class CScores{

	int planesShot = 0;
	int submarineShot = 0;
	int boatShot = 0;
	int colectedGoldenStars = 0;
	int colectedSilverStars = 0;
	int stageNo = 1;
	
	
	public CScores(int stageNo) {
		this.stageNo = stageNo;
	}
	
	public void increaseColectedGoldenStars() {
		colectedGoldenStars+=1;
	}
	
	public void increaseColectedSilverStars() {
		colectedSilverStars+=1;
	}
	
	public void increaseSubmarineShot() {
		submarineShot+=1;
	}
	
	public int getSubmarineShot() {
		return submarineShot;
	}
	
	public void increasePlaneShot() {
		planesShot+=1;
	}
	
	
	
	public int getPlaneShot() {
		return planesShot;
	}
	
	public int getScore() {
		return (planesShot * 20) +
				(submarineShot * 100) +
				(colectedGoldenStars * 1000) +
				colectedGoldenStars * 500;
	}
	
	public int getStage() {
		return this.stageNo;
	}
	
	
	
}