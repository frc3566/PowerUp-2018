package org.usfirst.frc3566.Jan7.subsystems;

import org.usfirst.frc3566.Jan7.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BPU extends Subsystem {
	
	private final WPI_TalonSRX t1 = RobotMap.BPU1;
	private final WPI_TalonSRX t2 = RobotMap.BPU2;
	private final WPI_TalonSRX t3 = RobotMap.BPU3;
	private final WPI_TalonSRX t4 = RobotMap.BPU4;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

