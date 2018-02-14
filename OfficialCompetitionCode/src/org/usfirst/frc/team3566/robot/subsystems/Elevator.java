package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	private final WPI_TalonSRX eT1 = RobotMap.Elev1;
	private final WPI_TalonSRX eT2 = RobotMap.Elev2;
	

    public void initDefaultCommand() {
    }
}

