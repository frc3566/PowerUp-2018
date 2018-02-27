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

    protected void execute() {
    	//Robot.state=RobotState.ELEVATER;
    	if(Robot.oi.main.getRawButton(5)&&!Robot.elevator.topSwitch.get())
    		Robot.elevator.runElevator(Robot.elevator.elevUpSPD*-1);
    	else if(Robot.oi.main.getRawButton(6))//&&Robot.elevator.bottomSwitch.get())
    		Robot.elevator.runElevator(Robot.elevator.elevDownSPD);
    	else
    		Robot.elevator.stopElevator();
    	if(Robot.time.get()%1<0.03)
    		System.out.println(Robot.elevator.bottomSwitch.get()+" "+Robot.elevator.topSwitch.get());
    }

    protected boolean isFinished() {
    	if(Robot.state!=RobotState.ELEVATER)return true;
    	return false;
    }

    protected void end() {
    }
    
    protected void interrupted() {
    }
}
