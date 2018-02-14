package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.*;
import org.usfirst.frc.team3566.robot.commands.RunBPUPickUp;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BPU extends Subsystem {
	
	private final WPI_TalonSRX leftPickUp = RobotMap.BPUleft;
	private final WPI_TalonSRX rightPickUp = RobotMap.BPUright;
	
	/*
	private final WPI_TalonSRX t3 = RobotMap.BPU3;
	private final WPI_TalonSRX t4 = RobotMap.BPU4;
	*/
	
	private final AnalogInput boxIRSensor =  new AnalogInput(0);//box infrared sensor
	
	public static final int IN = 1, OUT = -1;
	
	private boolean pickUpOnOff = true;
	private int pickUpDirection = this.IN;
	
	private final WPI_TalonSRX Tilter = RobotMap.Tilter;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RunBPUPickUp());
    }
    
    public void pickUp() {
    	if(pickUpOnOff) {
    		if(pickUpDirection == this.IN && boxIRSensor.getValue() > 1100) { //box is in confirmed
    			leftPickUp.set(0);
    			rightPickUp.set(0);
    			pickUpOnOff = false;
    			pickUpDirection = this.OUT;
       		}else {
       			leftPickUp.set(Robot.var.BPU_PICKUP_SPD * pickUpDirection);
       			rightPickUp.set(Robot.var.BPU_PICKUP_SPD * pickUpDirection);
       		}
    		
    	}else {
    		stopPickUp();
    	}
    	
    }
    
    public void stopPickUp() {
    	leftPickUp.set(0);
    	rightPickUp.set(0);
    }
    
    public void togglePickUpStatus() {
    	pickUpOnOff = !pickUpOnOff;
    }
    
    public void togglePickUpMotorDirection() {
    	pickUpDirection *= -1;
    }
    
    public void setPickUpMotorDirection(int InOrOut) {
    	pickUpDirection = InOrOut;
    }
    
    public void runTilter() {
    	
    }
}

