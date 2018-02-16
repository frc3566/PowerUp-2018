package org.usfirst.frc.team3566.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverBox extends CommandGroup {
	
	public static final int DeliverToSwitch = 0, DeliverToScale = 1;
	
	/*
	 * The deliverBox command group will involved two subsystems: the elevator and the BPU
	 * First the elevator needs to go to a certain height (higher for the scale and lower for the switch)
	 * Then BPU shoots the box out. I think for this specific task, it's best to use timeout. 
	 */
	
    public DeliverBox(int whichTarget) {
    	//addSequential();	   elevator goes to a certain height. This could be done in parallel with previous auto driving
    	//in order to save time.
    	//addSequential();     BPU spitting out box with timeOut in seconds
    }
    
    
}
