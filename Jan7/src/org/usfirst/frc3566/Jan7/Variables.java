package org.usfirst.frc3566.Jan7;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {

	public static double driveSpeed = 0.1, rotateSpeed=0.15; //0.15 is enough for current driveTrain
	public static double rotateNonStopSpd = 0.25;
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
	}
	
	public void setSwitchScaleSides() {
		ourSwitchPos = gameMessage.charAt(0);
		ScalePos = gameMessage.charAt(1);
		oppSwitchPos = gameMessage.charAt(2);
	}
}
