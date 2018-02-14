package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.subsystems.BPU;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class BPUin extends InstantCommand {

    public BPUin() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.bpu);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.bpu.togglePickUpStatus();
    	Robot.bpu.setPickUpMotorDirection(BPU.IN);
    }

}
