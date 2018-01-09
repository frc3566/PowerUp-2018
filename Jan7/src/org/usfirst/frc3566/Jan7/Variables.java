package org.usfirst.frc3566.Jan7;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Variables {

	public static double driveSpeed = 0.1;
	public static double driveTimeOut = 1;
	
	
	public void SendValuesToDashboard() {
		SmartDashboard.putNumber("driveTimeOut", driveTimeOut);
		SmartDashboard.putNumber("DriveSpeed", driveSpeed);
	}
	
}
