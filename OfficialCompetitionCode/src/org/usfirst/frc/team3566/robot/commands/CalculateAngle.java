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
	    	
	    	Robot.var.distance = Math.sqrt(Math.pow((p.getX()-Robot.var.x), 2)+Math.pow((p.getY()-Robot.var.y), 2));
			Robot.var.distance *= 1; //scale to feet, because one unit in our simulator is 1s ft.
			if(Robot.var.distance<0.5) {Robot.var.rotateTheta=0;Robot.var.distance=0;}
			//if too close then don't do this move, may need to change direction later
			else Robot.var.rotateTheta=Robot.var.getTheta()-Robot.var.getVectorDegree(p.getX()-Robot.var.x, p.getY()-Robot.var.y);
			System.out.printf("rotate %.0f go %.0f\n", Robot.var.rotateTheta,Robot.var.distance);
	    }
	    
	}
