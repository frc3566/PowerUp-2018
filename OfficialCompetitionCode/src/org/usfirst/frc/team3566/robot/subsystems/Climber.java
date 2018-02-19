package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	private final WPI_TalonSRX climber = RobotMap.climber;
	
    public void initDefaultCommand() {
    }
    public void runClimber(double spd)
    {
    	climber.set(spd);
    }
}

