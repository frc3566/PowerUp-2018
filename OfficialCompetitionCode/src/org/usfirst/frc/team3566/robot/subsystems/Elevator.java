package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.*;
import org.usfirst.frc.team3566.robot.commands.RunElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private final WPI_TalonSRX Left = RobotMap.ElevLeft;
	private final WPI_TalonSRX Right = RobotMap.ElevRight;
	
	public static AnalogInput elevatorEncoder; //bottom: 3684; top (without a box): 1184
	
	public Elevator() {

		elevatorEncoder=new AnalogInput(2);
		elevatorEncoder.setAccumulatorInitialValue(-197);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RunElevator());
    }
    
    public void runElevator(double spd) {
    	Left.set(spd);
    	Right.set(spd);
    }
    
    public void stopElevator() {
    	Left.set(0);
    	Right.set(0);
    }
}

