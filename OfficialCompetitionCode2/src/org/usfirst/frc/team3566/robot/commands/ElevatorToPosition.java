package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotState;

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
    	if(Robot.isAuto)
    		if(Robot.var.isFinalTurn)Robot.var.isFinalTurn=false;
    		else
    			isNeedWait=true;
    	else isNeedWait=false;
    	//this.setTimeout(5);
    	timeToFinish=5;
    	Robot.light.set(Robot.var.yellow);
    	if(Robot.state==RobotState.STANDSTILL)Robot.state=RobotState.ELEVATER;
    }

    protected void execute() {
    	if(isNeedWait&&Robot.isAuto&&Robot.var.isFinalTurn==false)return;
    	else if(startTime<0)startTime=Robot.time.get();
    	int dir = Robot.elevator.checkDirectionToGo(position);
    	double spd  = Robot.elevator.findAppropriateSPD(Robot.elevator.checkDirectionToGo(position), position);
    	Robot.elevator.runElevator(spd);	
//    	System.out.println("tryin to get to "+Robot.elevator.elevatorTargetValues[position]+", now at "+
//    	Robot.elevator.elevatorEncoder.getValue()+" spd is "+ spd + " dir "+dir);
    	
    }

    protected boolean isFinished() {
    	//if(Robot.state!=RobotState.ELEVATER)return true;
        return startTime>0&&(Robot.elevator.reachedPosition(position)||Robot.time.get()-startTime>timeToFinish);
    }

    protected void end() {
    	Robot.state=RobotState.STANDSTILL;
    	Robot.elevator.stopElevator();
    	Robot.light.set(Robot.var.green);
    }

    protected void interrupted() {
    	end();
    }
}
