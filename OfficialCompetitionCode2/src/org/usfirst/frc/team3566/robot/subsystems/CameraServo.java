package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.RobotMap;
import org.usfirst.frc.team3566.robot.commands.RunCamServoOnPOV;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraServo extends Subsystem {
	
	double currentPos;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Servo servo = RobotMap.cameraServo;
	
    public void initDefaultCommand() {
        setDefaultCommand(new RunCamServoOnPOV());
    }
    
    public synchronized void moveServo(double stickValue)
    {
    	System.out.println("The stick value is: " + stickValue);
    	if(Math.abs(stickValue-180) <1e-6) {
    		
    		currentPos -= 1;
    		if( currentPos < 0) currentPos = 0;
    		
    	} else  if(Math.abs(stickValue)<1e-6){
    		currentPos += 1;
    		if( currentPos > 180) currentPos = 180;
    	}
    	
    	System.out.println("The current angle is " + currentPos);
    	servo.setAngle(currentPos);
    	
    	
    }
    
    public double getCurrentPosition() {
    	return currentPos;
    }
    
    
}

