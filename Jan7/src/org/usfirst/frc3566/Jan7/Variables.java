package org.usfirst.frc3566.Jan7;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {

	public static double driveSpeed = 0.1, rotateSpeed=0.15; //0.15 is enough for current driveTrain
	public static double driveTimeOut = 1, rotateAngle = 60;
	public static boolean rotateDirection;
	public static char ourSwitchPos, ScalePos, oppSwitchPos;
	public static String gameMessage;
	
	
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
