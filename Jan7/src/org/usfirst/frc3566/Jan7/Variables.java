package org.usfirst.frc3566.Jan7;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {

	public static double driveSpeed = 0.1, rotateSpeed=0.1;
	public static double driveTimeOut = 1, rotateTimeOut = 1;
	public static boolean rotateDirection;
	
	
	public void SendValuesToDashboard() {
		SmartDashboard.putNumber("driveTimeOut", driveTimeOut);
		SmartDashboard.putNumber("DriveSpeed", driveSpeed);
		SmartDashboard.putNumber("rotateTimeOut", rotateTimeOut);
		SmartDashboard.putNumber("rotateSpeed", rotateSpeed);
		SmartDashboard.putBoolean("rotateDirection", rotateDirection);
	}
	
	public void updateValues() {
		driveTimeOut = SmartDashboard.getNumber("driveTimeOut", -1);
		driveSpeed = SmartDashboard.getNumber("DriveSpeed", -1);
		rotateTimeOut = SmartDashboard.getNumber("rotateTimeOut", -1);
		rotateSpeed = SmartDashboard.getNumber("rotateSpeed", -1);
		rotateDirection = SmartDashboard.getBoolean("rotateDirection", true);
	}
	
}
