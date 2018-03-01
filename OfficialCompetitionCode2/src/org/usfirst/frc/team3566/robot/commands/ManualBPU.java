package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualBPU extends Command {

    public ManualBPU() {
        requires(Robot.bpu);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(!Robot.isAuto) {
	    	if(Robot.oi.main.getRawAxis(3)>0.1)
	    	{
	    		Robot.bpu.pickUpOnOff=true;
	    		Robot.var.BPU_PICKUP_SPD=0.4;
	    		Robot.bpu.setPickUpMotorDirection(1);
	    	}
	    	else if(Robot.oi.main.getRawAxis(2)>0.1)
	    	{
	    		Robot.bpu.pickUpOnOff=true;
	    		Robot.var.BPU_PICKUP_SPD=1;
	    		Robot.bpu.setPickUpMotorDirection(-1);
	    	}else {
	    		Robot.bpu.pickUpOnOff = false;
	    	}
    	}
    	
		Robot.bpu.pickUp();
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    protected void interrupted() {
    }
}
