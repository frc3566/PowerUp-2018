package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

/**
 *
 */
public class DriveForSeconds extends Command {
	//on smooth STEM base floor, drive at speed=0.1 for 4 seconds goes 1 meter, or 3.28 feet. 
	//which means that each second, the robot goes 0.82 foot. 

	private double spd;
	private boolean dir;
	
	//provide the distance in ft
    public DriveForSeconds(boolean DriveForDistance, double timeoutORdistance, boolean direction) {
    	if(DriveForDistance) {
    		timeoutORdistance = (Math.abs(timeoutORdistance)/0.82); //convert from distance in ft to timeout needed
    		//even if driving in distance, should never be negative, but should use direction
    	}
    	this.setTimeout(timeoutORdistance);
    	spd = 0.4;
    	dir = direction;
    }

    @Override
    protected void initialize() {
    }


    @Override
    protected void execute() {
    	Robot.drivetrain.goForward((dir?1:-1) * spd);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.drivetrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
}