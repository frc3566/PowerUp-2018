package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualElevator extends Command {

    public ManualElevator() {
        requires(Robot.elevator);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.state!=RobotState.STANDSTILL&&Robot.state!=RobotState.ELEVATER)
    		return;
    	Robot.state=RobotState.ELEVATER;
    	if(Robot.oi.main.getRawButton(5)&&!Robot.elevator.topSwitch.get())
    		Robot.elevator.runElevator(Robot.elevator.elevUpSPD*-1);
    	else if(Robot.oi.main.getRawButton(6)&&!Robot.elevator.bottomSwitch.get())
    		Robot.elevator.runElevator(Robot.elevator.elevDownSPD);
    	else
    	{
    		Robot.elevator.stopElevator();
    		if(Robot.state==RobotState.ELEVATER)
    			Robot.state=RobotState.STANDSTILL;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.state!=RobotState.ELEVATER)return true;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
