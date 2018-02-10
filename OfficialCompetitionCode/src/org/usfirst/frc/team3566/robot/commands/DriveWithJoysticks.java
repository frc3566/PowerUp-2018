// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team3566.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3566.robot.*;

/**
 *
 */
public class DriveWithJoysticks extends Command {


    public DriveWithJoysticks() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
    	System.out.println("joystick starts");
    }

    @Override
    protected void execute() {
    	double maxPower=SmartDashboard.getNumber("maxPower", 1);
    	RobotMap.drive.tankDrive(Robot.oi.joystick1.getRawAxis(1)*-1*maxPower, Robot.oi.joystick1.getRawAxis(1)*maxPower);
    	//Robot.drivetrain.runMotor(Robot.oi.joystick1.getRawAxis(1));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    	System.out.println("joystick ends");
    }

    @Override
    protected void interrupted() {
    	System.out.println("joystick interrupts");
    }
}
