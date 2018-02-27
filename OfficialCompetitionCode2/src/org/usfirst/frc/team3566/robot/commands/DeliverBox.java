package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.subsystems.BPU;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverBox extends CommandGroup {
	
	public static final int DeliverToSwitch = 0, DeliverToScale = 1;
	//deliver to switch would be at middleHeight (height 1), to scale would be at top height (height 2)
	
	/*
	 * The deliverBox command group will involved two subsystems: the elevator and the BPU
	 * First the elevator needs to go to a certain height (higher for the scale and lower for the switch)
	 * Then BPU shoots the box out. I think for this specific task, it's best to use timeout. 
	 */
    public DeliverBox(int whichTarget) {
    	//addSequential();	   elevator goes to a certain height. This could be done in parallel with previous auto driving
    	//in order to save time.
    	//addSequential();     BPU spitting out box with timeOut in seconds
    	addSequential(new ElevatorToPosition(whichTarget+1));
    	addSequential(new BPUforSeconds(BPU.OUT, 2));
    	addSequential(new ElevatorToPosition(1)); //back to middle position when box is gotten rid of
    }
    
    
}
