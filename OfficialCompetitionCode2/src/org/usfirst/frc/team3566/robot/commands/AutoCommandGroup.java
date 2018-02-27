package org.usfirst.frc.team3566.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.POINT;
import org.usfirst.frc.team3566.robot.subsystems.BPU;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommandGroup extends CommandGroup {

    public AutoCommandGroup(ArrayList<POINT> theRoute, int targetNumber) {
    	
    	
    	addParallel(new ElevatorToPosition(0));
    	addSequential(new CompleteRoute(theRoute));
    	//if going for our switch or the opponent switch, raise elevator to position one (middle)
    	//if going for the scale, raise elevator to position two (top)
    	//this is done while the elevator is driving and completing the route
    	addSequential(new ElevatorToPosition((targetNumber == 0 || targetNumber == 2)? 1:2));
    	addSequential(new BPUforSeconds(BPU.OUT, 3));
    	//spitting out the box when the route is complete, elevator raised, and ready to deliver.
    	 
    	
    }
}
