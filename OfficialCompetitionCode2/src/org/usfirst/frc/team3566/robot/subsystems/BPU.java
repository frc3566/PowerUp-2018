package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.*;
import org.usfirst.frc.team3566.robot.commands.ManualBPU;
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
	
	
	public static final int IN = 1, OUT = -1;
	
	public boolean pickUpOnOff = false;
	private int pickUpDirection = this.IN;
	
	private final WPI_TalonSRX Tilter = RobotMap.Tilter;

    public void initDefaultCommand() {
    	setDefaultCommand(new ManualBPU());
    }
    
    public void pickUp() {
     	if(pickUpOnOff) {    	
       			leftPickUp.set(Robot.var.BPU_PICKUP_SPD * pickUpDirection);
       			rightPickUp.set(Robot.var.BPU_PICKUP_SPD * pickUpDirection);
//       			System.out.println("BPU+ "+Robot.var.BPU_PICKUP_SPD * pickUpDirection);
       		}
    	else {
    		stopPickUp();
//    		System.out.println("BPU stopped");
    	}
    }
    
    public void stopPickUp() {
    	leftPickUp.set(0);
    	rightPickUp.set(0);
    }
    
    public void runBPUmotors(double spd) {
    	leftPickUp.set(spd);
    	rightPickUp.set(spd);
    }
    
    public void togglePickUpStatus() {
    	pickUpOnOff = !pickUpOnOff;
    }
    
    public void setPickUpStatus(boolean set) {
    	pickUpOnOff = set;
    }
    
    public void togglePickUpMotorDirection() {
    	pickUpDirection *= -1;
    }
    
    public void setPickUpMotorDirection(int InOrOut) {
    	pickUpDirection = InOrOut;
    }
}