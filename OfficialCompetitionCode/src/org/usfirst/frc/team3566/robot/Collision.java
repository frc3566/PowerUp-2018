package org.usfirst.frc.team3566.robot;

public class Collision {

	//collide detection
	private static final double COLLIDE_LIMIT=12000;
	private static double collideSpan=0;
	public static double lastLSpeed,lastRSpeed;
	private static double lastStuck;
	
	public static boolean isCollide;
	
	public static void collideReset()
	{
		isCollide=false;
		lastLSpeed=Robot.encoderL.getRate();
		lastRSpeed=Robot.encoderL.getRate();//Robot.encoderR.getRate();
		collideSpan=0;
		lastStuck=Robot.time.get();
	}
	public static void updateCollide()
	{	
		collideSpan+=timeSpan;
		if( !(Math.abs(RobotMap.left.get())>0.2&&Math.abs(Robot.encoderL.getRate())<300 ||
				Math.abs(RobotMap.right.get())>0.2&&Math.abs(Robot.encoderR.getRate())<300) )
			lastStuck=Robot.time.get();
		else if(Robot.time.get()-lastStuck>0.3)isCollide=true;
		if(collideSpan<0.15)return;
		double newL=Robot.encoderL.getRate(),newR=Robot.encoderL.getRate();
		//hard collision
		if(Math.abs(newL-lastLSpeed)/collideSpan>COLLIDE_LIMIT||Math.abs(newR-lastRSpeed)/collideSpan>COLLIDE_LIMIT)
			isCollide=true;
		collideSpan=0;
		lastLSpeed=newL;lastRSpeed=newR;
	}
	
	
	
	public static double lastTime;
	public static double timeSpan;
	public static void getTimeSpan()
	{
		double thisTime=Robot.time.get();
		timeSpan=thisTime-lastTime;
		if(timeSpan<1e-5)timeSpan=1e-5;
		lastTime=thisTime;
	}
}