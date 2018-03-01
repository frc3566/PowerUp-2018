package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.RobotMap;
import org.usfirst.frc.team3566.robot.commands.RunClimber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	private final WPI_TalonSRX climber = RobotMap.climber;
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RunClimber());
    }
    
    
    
    public void runClimber(double POV)
    {
    	if(POV == 90) { //right on POV
    		climber.set(1);
    	} else  if(POV == 270){
    		climber.set(-1);
    	} else {
    		climber.set(0);
    	}
    }
    
    
    
}

