package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
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
    	if(Robot.oi.main.getRawButton(5)&&!Robot.elevator.topSwitch.get())
    		Robot.elevator.runElevator(Robot.elevator.elevUpSPD*-1);
    	else if(Robot.oi.main.getRawButton(6)&&!Robot.elevator.bottomSwitch.get())
    		Robot.elevator.runElevator(Robot.elevator.elevDownSPD);
    	else
    		Robot.elevator.stopElevator();
    	if(Robot.time.get()%2<0.03)
    		System.out.println(Robot.elevator.bottomSwitch.get()+" "+Robot.elevator.topSwitch.get());
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    }
    
    protected void interrupted() {
    }
}
