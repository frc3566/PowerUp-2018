package org.usfirst.frc3566.Jan7.commands;

import org.usfirst.frc3566.Jan7.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateNonStop extends Command {

	double SPD;
	
    public RotateNonStop(double spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	SPD = spd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putBoolean("Driving", false);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.rotate(SPD);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopDrive();
    	SmartDashboard.putBoolean("Driving", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
