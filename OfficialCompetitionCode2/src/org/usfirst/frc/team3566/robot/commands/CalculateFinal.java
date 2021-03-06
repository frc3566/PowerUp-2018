package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.POINT;
import org.usfirst.frc.team3566.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CalculateFinal extends InstantCommand {
	POINT p;
    public CalculateFinal(POINT _p) {
        super();
        p=_p;
    }

    protected void initialize() {
    	Robot.var.ptToGo=p;
    	Robot.var.isFinalTurn=true;
    	double curTheta=Robot.var.getTheta();
    	if(Math.abs(curTheta-p.getTheta())<10)
    		Robot.var.rotateTheta=0;
    	else Robot.var.rotateTheta=(curTheta-p.getTheta()+360)%360;
    }
}
