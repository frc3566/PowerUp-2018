package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.POINT;
import org.usfirst.frc.team3566.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;


	public class CalculateAngle extends InstantCommand {
		POINT p;
		
	    public CalculateAngle(POINT _p) {
	    	super();
	    	p=_p;
	    }

	    protected void initialize() {
	    	
	    	Robot.var.distance = Math.sqrt(Math.pow((p.getX()-Robot.var.getX()), 2)+Math.pow((p.getY()-Robot.var.getY()), 2));
			if(Robot.var.distance<0.5) 
			{
				Robot.var.rotateTheta=0;
				Robot.var.distance=0;
			}
			//if too close then don't do this move, may need to change direction later
			else {
				Robot.var.rotateTheta=Robot.var.getTheta()-
						Robot.var.getVectorDegree(p.getX()-Robot.var.getX(), p.getY()-Robot.var.getY());
				//if(Robot.var.rotateTheta<10)Robot.var.rotateTheta=0;
				if(p.getTheta()<-0.5)Robot.var.allowedDriveError=50;
				else Robot.var.allowedDriveError=150;
				System.out.printf("\n%f\n",Robot.var.rotateTheta);
			}
			System.out.printf("now at x=%.1f y=%.1f theta=%.0f\ngoto x=%.1f y=%.1f theta=%.0f\nrotate %.0f go %.1f\n\n", 
					Robot.var.getX(), Robot.var.getY(), Robot.var.getTheta(), p.getX(),p.getY(),p.getTheta(),
					Robot.var.rotateTheta, Robot.var.distance);
	    }
	}
