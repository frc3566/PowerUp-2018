package org.usfirst.frc3566.Jan7;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {
	public static double driveSpeed = 0.1, rotateSpeed=0.15; //0.15 is enough for current driveTrain
	public static double driveTimeOut, rotateAngle = 60;
	public static boolean rotateDirection;
	public static char ourSwitchPos, ScalePos, oppSwitchPos;
	public static String gameMessage;
	
	public static ArrayList<POINT> route1;
	
	public void setUpRoutePoints() {
		route1 = new ArrayList<POINT>();
		route1.add(new POINT(3.5, 1.5));
		route1.add(new POINT(3.5, 14));
		route1.add(new POINT(6.25, 14));
	}
	
	public void SendValuesToDashboard() {
		SmartDashboard.putNumber("driveTimeOut", driveTimeOut);
		SmartDashboard.putNumber("DriveSpeed", driveSpeed);
		SmartDashboard.putNumber("rotateAngle", rotateAngle);
		SmartDashboard.putNumber("rotateSpeed", rotateSpeed);
		SmartDashboard.putBoolean("rotateDirection", rotateDirection);
	}
	
	public void updateValues() {
		driveTimeOut = SmartDashboard.getNumber("driveTimeOut", -1);
		driveSpeed = SmartDashboard.getNumber("DriveSpeed", -0.1);
		rotateAngle = SmartDashboard.getNumber("rotateAngle", -1);
		rotateSpeed = SmartDashboard.getNumber("rotateSpeed", -0.1);
		rotateDirection = SmartDashboard.getBoolean("rotateDirection", true);
		
		SmartDashboard.putNumber("x", x);
		SmartDashboard.putNumber("y", y);
		SmartDashboard.putNumber("theta", getTheta()); 
		SmartDashboard.putNumber("Yaw", getTheta()); 
	}
	
	public void setSwitchScaleSides() {
		ourSwitchPos = gameMessage.charAt(0);
		ScalePos = gameMessage.charAt(1);
		oppSwitchPos = gameMessage.charAt(2);
	}
	
	
	//coordinate system
	public static double x=0,y=0;
	public static double lastEncoderDis=0;
	/*
	 * return the degree in the polar system, range [0,360), add 90 degrees to make polar
	 */
	public static double getTheta() {
    	double[] ypr = new double[3];
    	RobotMap.pigeon.getYawPitchRoll(ypr);
    	return ((ypr[0]%360)+360+90)%360;
    }
	
	/* 
	 * return the theta angle in degree of the vector (_x,_y) in the polar system, range[0,360)
	 * try not to call if x==0 && y==0, that may cause problematic rotation
	 */
	public static double getVectorDegree(double _x,double _y)
	{
		if(_x==0)return _y>0? 90:270;
		if(_y==0)return _x>0? 0:180;
		double degree=Math.toDegrees(Math.atan(_y/_x));
		if(_x<0)degree+=180;
		else if(_x>0 && _y<0)degree+=360;
		return degree;
	}
	
	/*
	 * update the x,y value according to theta angle and lastEncoderDis
	 * please change lastEncoderDis when first call this method
	 */
	public static void updateXY()
	{
		double theta=getTheta(), curDis=Robot.encoder1.getDistance(), dis=curDis-lastEncoderDis;
		x+= dis* Math.cos(Math.toRadians(theta));
		y+= dis* Math.sin(Math.toRadians(theta));
		lastEncoderDis=curDis;
	}
	
	public double rotateTheta;
	public double distance;
}
