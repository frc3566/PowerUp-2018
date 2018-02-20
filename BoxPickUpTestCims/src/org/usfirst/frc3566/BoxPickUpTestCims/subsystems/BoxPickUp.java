// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3566.BoxPickUpTestCims.subsystems;

import org.usfirst.frc3566.BoxPickUpTestCims.RobotMap;
import org.usfirst.frc3566.BoxPickUpTestCims.commands.RunPickUpOnJoysticks;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class BoxPickUp extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX talon1RightSidePickUp = RobotMap.boxPickUpTalon1RightSidePickUp;
    private final WPI_TalonSRX talon2LeftSidePickUp = RobotMap.boxPickUpTalon2LeftSidePickUp;
    
    /*
    private final WPI_TalonSRX talon5LeftSideShooter = RobotMap.boxPickUpTalon5LeftSideShooter;
    private final WPI_TalonSRX talon4RightSideShooter = RobotMap.boxPickUpTalon4RightSideShooter;
    
    private final WPI_TalonSRX talon8LeftGrabber = RobotMap.boxPickUpTalon8LeftSideGrabber;
    private final WPI_TalonSRX talon9RightGrabber = RobotMap.boxPickUpTalon9RightSideGrabber;
  
*/
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogInput boxIRSensor = RobotMap.boxPickUpIRDistanceSensor;
    private final DigitalInput magSwitch = RobotMap.magSwitch;
    
    static final boolean ON = true;
    public static final boolean OFF = false;
    
    public static final int IN = 1;
    public static final int OUT = -1;
    
    public static final double PICK_UP_MOTOR_SPEED = 0.35;
  //  public static final double SHOOTER_MOTOR_SPEEDR = 0.8;
   // public static final double SHOOTER_MOTOR_SPEEDL = 0.75;
   // public static final double GRABBER_MOTOR_SPEED = 0.90;
    
    private boolean pickUpOnOffStatus = this.ON;
    private boolean shooterOnOffStatus = this.OFF;
    private int pickUpDirection = this.IN;
    private boolean grabberOnOffStatus = this.OFF;

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    	setDefaultCommand(new RunPickUpOnJoysticks());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void drivePickUpSystem(double leftStick, double rightStick )
    {
    	
    	System.out.println("MagSwitch says " + this.magSwitch.get());
    	if(this.pickUpOnOffStatus == this.ON)
    	{
    		if(this.pickUpDirection == this.IN && boxIRSensor.getValue() > 1100) {
    			talon1RightSidePickUp.set(0.0);
        	    talon2LeftSidePickUp.set(0.0);
    			pickUpOnOffStatus = this.OFF;
    			this.pickUpDirection = this.OUT;
    		} else {
	    		talon1RightSidePickUp.set(this.PICK_UP_MOTOR_SPEED * this.pickUpDirection * (-1.0));
	    	    talon2LeftSidePickUp.set(this.PICK_UP_MOTOR_SPEED * this.pickUpDirection  );
    		}
    	    //System.out.println("Pick up is on");
    	    
    	} else {
    		talon1RightSidePickUp.set(0.0);
    	    talon2LeftSidePickUp.set(0.0);
    	   // System.out.println("Pick up is off");
    		
    	}
    	
    	/*
    	if(this.shooterOnOffStatus == this.ON)
    	{
    		talon4RightSideShooter.set(this.SHOOTER_MOTOR_SPEEDR );
    		talon5LeftSideShooter.set(this.SHOOTER_MOTOR_SPEEDL * (-1.0) );
    		//System.out.println("Shooter is on");
    	} else {
    		talon4RightSideShooter.set(0.0);
    		talon5LeftSideShooter.set(0.0);
    		//System.out.println("Shooter is off");
    	}
    	
    	if(this.grabberOnOffStatus == this.ON)
    	{
    		talon8LeftGrabber.set( (this.GRABBER_MOTOR_SPEED)  + leftStick);
    		talon9RightGrabber.set((this.GRABBER_MOTOR_SPEED * -1.0) + rightStick);
    		//System.out.println("Shooter is on");
    		
    	} else {
    		talon8LeftGrabber.set(0.0);
    		talon9RightGrabber.set(0.0);
    		
    	}
    	
    	*/
    	//For IR Sensor Testing
    	//System.out.println("IR Sensor says: " + this.boxIRSensor.getValue());
    	System.out.println("MagSwitch says " + this.magSwitch.get());
    }
    
    public void togglePickUpStatus()
    {
    	if(pickUpOnOffStatus == this.ON)
    	{
    		pickUpOnOffStatus = this.OFF;
    		System.out.println("Pick Up is OFF");
    	} else {
    		pickUpOnOffStatus = this.ON;
    		System.out.println("Pick Up is ON");
    	}
    }
    
    public void toggleMotorDirection()
    {
    	if(pickUpDirection == this.IN)
    	{
    		pickUpDirection = this.OUT;
    		System.out.println("Pick Up direction is OUT");
    	} else {
    		pickUpDirection = this.IN;
    		System.out.println("Pick Up direction is IN");
    	}
    }
    
    public void toggleShooterOnOff()
    {
    	if(shooterOnOffStatus == this.ON)
    	{
    		shooterOnOffStatus = this.OFF;
    		System.out.println("Shooter Status is OFF");
    		
    	} else {
    		shooterOnOffStatus = this.ON;
    		System.out.println("Shooter Status is ON");
    	}
    }
    
    public void toggleGrabberOnOff()
    {
    	if(this.grabberOnOffStatus == this.ON)
    	{
    		grabberOnOffStatus = this.OFF;
    		System.out.println("Grabber Status is OFF");
    	} else {
    		grabberOnOffStatus = this.ON;
    		System.out.println("Grabber Status is O N");
    	}
    }

}

