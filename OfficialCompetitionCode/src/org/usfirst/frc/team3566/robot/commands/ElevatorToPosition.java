package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToPosition extends Command {
	
	private int position;

    public ElevatorToPosition(int positionNum) {
    	requires(Robot.elevator);
    	position = positionNum;
    	//0 is bottom, 1 is middle, 2 is top
    }

    protected void initialize() {
    	Robot.light.set(Robot.var.yellow);
    }

    protected void execute() {
    	int dir = Robot.elevator.checkDirectionToGo(position);
    	double spd  = Robot.elevator.findAppropriateSPD(Robot.elevator.checkDirectionToGo(position), position);
    	Robot.elevator.runElevator(spd);	
    	System.out.println("tryin to get to "+Robot.elevator.elevatorTargetValues[position]+", now at "+
    	Robot.elevator.elevatorEncoder.getValue()+" spd is "+ spd + " dir "+dir);
    }

    protected boolean isFinished() {
        return Robot.elevator.reachedPosition(position);
    }

    protected void end() {
    	Robot.elevator.stopElevator();
    	Robot.light.set(Robot.var.green);
    }

    protected void interrupted() {
    	end();
    }
}
