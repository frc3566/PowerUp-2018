// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3566.BoxPickUpTestCims;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static WPI_TalonSRX boxPickUpTalon1RightSidePickUp;
    public static WPI_TalonSRX boxPickUpTalon2LeftSidePickUp;
    
    /*
    public static WPI_TalonSRX boxPickUpTalon5LeftSideShooter;
    public static WPI_TalonSRX boxPickUpTalon4RightSideShooter;
    
    public static WPI_TalonSRX boxPickUpTalon8LeftSideGrabber;
    public static WPI_TalonSRX boxPickUpTalon9RightSideGrabber;

*/
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static AnalogInput boxPickUpIRDistanceSensor;
    public static DigitalInput magSwitch;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        boxPickUpTalon1RightSidePickUp = new WPI_TalonSRX(1);
        
        
        boxPickUpTalon2LeftSidePickUp = new WPI_TalonSRX(2);
        
        /*
        
        boxPickUpTalon5LeftSideShooter = new WPI_TalonSRX(5);
        
        
        boxPickUpTalon4RightSideShooter = new WPI_TalonSRX(4);
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        boxPickUpTalon8LeftSideGrabber = new WPI_TalonSRX(8);
        boxPickUpTalon9RightSideGrabber = new WPI_TalonSRX(9);
        
        */
        
        boxPickUpIRDistanceSensor = new AnalogInput(0);
        magSwitch = new DigitalInput(0);
        
    }
}
