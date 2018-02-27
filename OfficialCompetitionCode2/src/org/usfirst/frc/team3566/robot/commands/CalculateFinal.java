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
    	double curTheta=Robot.var.getTheta();
    	System.out.printf("%.0f %.0f\n", curTheta, p.getTheta());
    	if(Math.abs(curTheta-p.getTheta())<10)
    		Robot.var.rotateTheta=0;
    	else Robot.var.rotateTheta=(curTheta-p.getTheta()+360)%360;
    }
}
