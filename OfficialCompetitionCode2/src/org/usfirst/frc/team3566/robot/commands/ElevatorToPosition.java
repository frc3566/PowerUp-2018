package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToPosition extends Command {
	
	private int position;
	private boolean isNeedWait=false;
	private double timeToFinish,startTime=-1;

    public ElevatorToPosition(int positionNum) {
    	requires(Robot.elevator);
    	position = positionNum;
    	timeToFinish=5;
    	//0 is bottom, 1 is middle, 2 is top
    }

    protected void initialize() {
    	Robot.var.eleToPosCnt++;
    	if(Robot.isAuto&&Robot.var.eleToPosCnt==1)isNeedWait=true;
    	else isNeedWait=false;
    	//this.setTimeout(5);
    	timeToFinish=5;
    	Robot.light.set(Robot.var.yellow);
    }

    protected void execute() {
    	if(isNeedWait&& !Robot.var.isFinalTurn)return;
    	else if(startTime<0)startTime=Robot.time.get();
    	int dir = Robot.elevator.checkDirectionToGo(position);
    	double spd  = Robot.elevator.findAppropriateSPD(Robot.elevator.checkDirectionToGo(position), position);
    	Robot.elevator.runElevator(spd);	   	
    }

    protected boolean isFinished() {
        return startTime>0&&(Robot.elevator.reachedPosition(position)||Robot.time.get()-startTime>timeToFinish);
    }

    protected void end() {
    	Robot.elevator.stopElevator();
    	Robot.light.set(Robot.var.green);
    }

    protected void interrupted() {
    	end();
    }
}
