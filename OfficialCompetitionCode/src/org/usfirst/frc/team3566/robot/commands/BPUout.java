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
    }

    protected void initialize() {
    	Robot.bpu.togglePickUpStatus();
    	Robot.bpu.setPickUpMotorDirection(BPU.OUT);
    }

}
