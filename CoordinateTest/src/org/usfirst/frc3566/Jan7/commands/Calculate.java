package org.usfirst.frc3566.Jan7.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3566.Jan7.POINT;
import org.usfirst.frc3566.Jan7.Robot;
import org.usfirst.frc3566.Jan7.RobotMap;

/**
 *
 */
public class Calculate extends Command {
	
	POINT p;
	
    public Calculate(POINT _p) {
    	p=_p;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    	Robot.var.distance = Math.sqrt(Math.pow((p.getX()-Robot.var.x), 2)+Math.pow((p.getY()-Robot.var.y), 2));
		Robot.var.distance *= 1; //scale to feet, because one unit in our simulator is 1s ft.
		if(Robot.var.distance<0.5) {Robot.var.rotateTheta=0;Robot.var.distance=0;}
		//if too close then don't do this move, may need to change direction later
		else Robot.var.rotateTheta=Robot.var.getTheta()-Robot.var.getVectorDegree(p.getX()-Robot.var.x, p.getY()-Robot.var.y);
		System.out.printf("rotate %.0f go %.0f\n", Robot.var.rotateTheta,Robot.var.distance);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}