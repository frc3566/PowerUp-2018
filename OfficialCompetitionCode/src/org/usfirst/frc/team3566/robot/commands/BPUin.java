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
        requires(Robot.bpu);
    }

    protected void initialize() {
    	Robot.bpu.togglePickUpStatus();
    	Robot.bpu.setPickUpMotorDirection(BPU.IN);
    	System.out.println("BPU direction: IN!");
    }

}
