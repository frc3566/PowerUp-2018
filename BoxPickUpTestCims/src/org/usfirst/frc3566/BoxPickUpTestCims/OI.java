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

import org.usfirst.frc3566.BoxPickUpTestCims.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc3566.BoxPickUpTestCims.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //public JoystickButton button6ToggleRightSidePickUp;
    public JoystickButton button5ToggleLeftSidePickUp;
    public JoystickButton button6ToggleRightSidePickUp;
    public JoystickButton button3TogglePickUpDirection;
    public JoystickButton button1ToggleShooterMotors;
    public Joystick logitech;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton button2ToggleGrabbers;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        logitech = new Joystick(0);
        
        button1ToggleShooterMotors = new JoystickButton(logitech, 1);
        button1ToggleShooterMotors.whenPressed(new ToggleShooterMotorsOnOff());
        
        button3TogglePickUpDirection = new JoystickButton(logitech, 3);
        button3TogglePickUpDirection.whenPressed(new TogglePickUpMotorDirection());
        
        button5ToggleLeftSidePickUp = new JoystickButton(logitech, 7 );
        button5ToggleLeftSidePickUp.whenPressed(new ToggleLeftSideOnOff());
        
        button6ToggleRightSidePickUp = new JoystickButton(logitech, 6);
        button6ToggleRightSidePickUp.whenPressed(new ToggleRighSideOnOff());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("RunPickUpOnJoysticks", new RunPickUpOnJoysticks());
        SmartDashboard.putData("ToggleRighSideOnOff", new ToggleRighSideOnOff());
        SmartDashboard.putData("ToggleLeftSideOnOff", new ToggleLeftSideOnOff());
        SmartDashboard.putData("TogglePickUpMotorDirection", new TogglePickUpMotorDirection());
        SmartDashboard.putData("ToggleShooterMotorsOnOff", new ToggleShooterMotorsOnOff());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        button2ToggleGrabbers = new JoystickButton(logitech, 2);
        button2ToggleGrabbers.whenPressed(new ToggleGrabberMotor());
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getLogitech() {
        return logitech;
    }


        
    public double getLeftStick()
    {
    	return logitech.getRawAxis(1);
    }
    
    public double getRightStick()
    {
    	return logitech.getRawAxis(5);
    }
}

