package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends Command {

	POINT startingPosition;
	
    public Autonomous(POINT startingPos) {
    	startingPosition = startingPos;
    }

    @Override
    protected void initialize() {
    	Robot.var.gameMessage = DriverStation.getInstance().getGameSpecificMessage();
    	Robot.var.setSwitchScaleSides(); //splits gameMessage into individual message
    	SmartDashboard.putString("OurSwitch", Robot.var.ourSwitchPos+"");
    	SmartDashboard.putString("Scale", Robot.var.ScalePos+"");
    	SmartDashboard.putString("OppSwitch", Robot.var.oppSwitchPos+"");
    	
    	
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
