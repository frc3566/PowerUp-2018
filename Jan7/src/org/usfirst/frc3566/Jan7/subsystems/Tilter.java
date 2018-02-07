package org.usfirst.frc3566.Jan7.subsystems;

import org.usfirst.frc3566.Jan7.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Tilter extends Subsystem {

	private final WPI_TalonSRX tilterTalon = RobotMap.Tilter;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

