package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.subsystems.BPU;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class BPUout extends InstantCommand {

    public BPUout() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.bpu.togglePickUpStatus();
    	Robot.bpu.setPickUpMotorDirection(BPU.OUT);
    }

}
