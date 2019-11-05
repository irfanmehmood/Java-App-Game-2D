package com.me.ufoattack.game.utility;

public class Enums {
	
	public enum DIRECTION {
		 Right,
		 RightUp,
		 Up,
		 UpLeft,
		 Left,
		 LeftDown,
		 Down,
		 DownRight,
		 Stop,
		 Surfaced,
		 GoUnderWater,
		 IsUnderWater,
		 RiseFromWater
	 }
	
	public static float getAngle(float targetX, float targetY,float myX, float myY) {
	    float angle = (float) Math.toDegrees(Math.atan2(targetX - myX, targetY - myY));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

}
