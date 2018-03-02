package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.subsystems.BPU;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BPUforSeconds extends Command {
	
	int dir;
	private double timeToFinish,startTime=-1;

    public BPUforSeconds(int DIR, int timeOut) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.bpu);
    	dir = DIR;
    	timeToFinish=timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.var.isFinalTurnFinish)return;
    	else if(startTime<0)startTime=Robot.time.get();
    	Robot.bpu.runBPUmotors(dir*1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return startTime>0&&Robot.time.get()-startTime>timeToFinish;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.bpu.stopPickUp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
