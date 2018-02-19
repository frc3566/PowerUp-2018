package org.usfirst.frc.team3566.robot.subsystems;

import org.usfirst.frc.team3566.robot.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private final SpeedControllerGroup elevator = RobotMap.Elev;
	
	public static AnalogInput elevatorEncoder; //bottom: 3200; top (without a box): 1184
	
	public static final int[] elevatorTargetValues = {3240, 1810, 750}; //bottom, middle and top encoder values
	public static final int allowedError = 10;
	public static final double elevUpSPD = 1, elevDownSPD = 0.7, elevCloseFactor = 0.7;
	
	public Elevator() {

		elevatorEncoder=new AnalogInput(2);
		elevatorEncoder.setAccumulatorInitialValue(0);
	}
	
    public void initDefaultCommand() {
    }
    
    public void runElevator(double spd) {
    	elevator.set(spd);
    }
    
    public void stopElevator() {
    	elevator.set(0);
    }
    
    public int checkDirectionToGo(int posNum) {
    	int curr = elevatorEncoder.getValue();
    	
    		return (elevatorTargetValues[posNum]- curr > 0? 1:-1); //-1 is up, 1 is down
    }
    
    public double findAppropriateSPD(int direction, int positionNum) {
    	if(Math.abs(elevatorEncoder.getValue() - elevatorTargetValues[positionNum]) < 30) {
    		return (direction == -1 ? elevUpSPD* direction * elevCloseFactor : elevDownSPD * direction * elevCloseFactor);
    	}else {
    		return (direction == -1 ? elevUpSPD* direction : elevDownSPD * direction);
    	}
    }
    
    public boolean reachedPosition(int posNum) {
    	int curr = elevatorEncoder.getValue();
    	if(curr < (elevatorTargetValues[posNum] +allowedError) &&
    			(curr > (elevatorTargetValues[posNum] - allowedError))) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
}

