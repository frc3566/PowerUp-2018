package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.*;
import org.usfirst.frc.team3566.robot.commands.RunBPU;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BPU extends Subsystem {
	
	private final WPI_TalonSRX leftPickUp = RobotMap.BPUleft;
	private final WPI_TalonSRX rightPickUp = RobotMap.BPUright;
	
	private final DigitalInput boxLimitSwitch =  new DigitalInput(4);//box limit switch
	
	public static final int IN = 1, OUT = -1;
	
	private boolean pickUpOnOff = true;
	private int pickUpDirection = this.IN;
	
	private final WPI_TalonSRX Tilter = RobotMap.Tilter;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new RunBPU());
    }
    
    public void pickUp() {
    	if(pickUpOnOff) {
    		if(pickUpDirection == this.IN && !boxLimitSwitch.get()) { //box is in confirmed
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