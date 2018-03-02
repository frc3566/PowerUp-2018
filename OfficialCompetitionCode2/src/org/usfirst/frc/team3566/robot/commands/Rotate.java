package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Rotate extends Command {
	//positive power: rotate to the right.
	private double spd=0, maxPower=0.5;
	private double startDegree, endDegree, deltaDegree, error, previous_error;
	public boolean isAuto, close;

	//private double P=0.028, I=0.015, D = 0.006;
	private double P=0.02, I=0.025, D=0.008;
    double integral, derivative;
    double prev_light;
    
    public Rotate() {
    	isAuto=true;
    	requires(Robot.drivetrain);
    }
    
    public Rotate(double deltaD) {
    	deltaDegree=deltaD;
    	isAuto=false;
    	requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
    	if(isAuto)deltaDegree = Robot.var.rotateTheta;
    	this.setTimeout(2.5);
//    	P=SmartDashboard.getNumber("PP", 0);
//    	I=SmartDashboard.getNumber("I", 0);
//    	D=SmartDashboard.getNumber("DD", 0);
    	startDegree = Robot.var.getTheta();
    	endDegree = startDegree - deltaDegree;
    	error = ((startDegree-endDegree)+360)%360;
    	if(Math.abs(error)<30) {integral=25;close=true;}
    	else close=false;
//    	Robot.drivetrain.ramp(0);
    	prev_light = Robot.light.get();
    	Robot.light.set(Robot.var.purple);
    	System.out.printf("rotate for %.0f\n", deltaDegree);
    }

    @Override
    protected void execute() {
    	
    	PID();
    	
    	spd*=maxPower;
    	//Robot.drivetrain.rotate(spd);
    	//Robot.drivetrain.rotate(0.3);
    	RobotMap.drive.tankDrive(spd, -spd);
    }

    @Override
    protected boolean isFinished() {
    	if(this.isTimedOut())return true;
    	if(isAuto&&Math.abs(Robot.var.rotateTheta)<10)return true;
    	if(Math.abs(error)<3 && Math.abs(Robot.encoderL.getRate())<200)return true;
    	if(Math.abs(error)<1)return true;
    	return false;
    }

    @Override
    protected void end() {
    	if(Robot.var.isFinalTurn==true)Robot.var.isFinalTurnFinish=true;
    	Robot.drivetrain.stopDrive();
    //	Robot.drivetrain.ramp(Robot.RAMP);
    	System.out.printf("rotate program stops after %.1f seconds, error is %.1f\n", this.timeSinceInitialized(),error);
    	Robot.light.set(prev_light);
    }
    
    @Override
    protected void interrupted() {
    	end();
    }
    
    public void PID(){
    	double theta = Robot.var.getTheta();
        error = ((theta-endDegree)+360)%360;
        if(error>180)error-=360;
        this.integral += (error*.02);
        derivative = (error - this.previous_error) / .02;
        if(Math.abs(spd)>0.8&&!close)integral=0;
        this.spd = P*error + I*this.integral + D*derivative;
        previous_error = error;
    }
}