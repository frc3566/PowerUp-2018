package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.subsystems.BPU;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BPUforSeconds extends Command {
	
	int dir;

    public BPUforSeconds(int DIR, int timeOut) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.bpu);
    	dir = DIR;
    	this.setTimeout(timeOut);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(dir==BPU.IN) {
    		new BPUin().start();
    	}else {
    		new BPUout().start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.bpu.togglePickUpStatus();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
