package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Rotate extends Command {
	private double spd=0, maxPower=0.5;
	private double startDegree, endDegree, deltaDegree, error, previous_error;
	public boolean isAuto;

	private double P=0.02, I=0, D = 0.002;
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
    	this.setTimeout(5);
    	if(isAuto)deltaDegree = Robot.var.rotateTheta;
    	
    	SmartDashboard.putNumber("P", P);
        SmartDashboard.putNumber("I", I);
        SmartDashboard.putNumber("D", D);
        SmartDashboard.putNumber("maxPower", maxPower); 
    	
    	 P=1/(2.2*Math.abs(deltaDegree)+18)+0.0175;
    	 I=0;
    	 D=0.002;

    	startDegree = Robot.var.getTheta();
    	endDegree = startDegree - deltaDegree;
    	Robot.drivetrain.ramp(0);
    	
    	prev_light = Robot.light.get();
    	Robot.light.set(Robot.var.purple);
    }

    @Override
    protected void execute() {
    	
    	PID();
    	
    	spd*=maxPower;
    	RobotMap.drive.tankDrive(spd, spd);
    }

    @Override
    protected boolean isFinished() {
    	
    	if( Math.abs(error)<2 && Robot.encoderL.getRate()<500)return true;
    	if(this.isTimedOut())return true;
    	return false;
    	
    }

    @Override
    protected void end() {
    	Robot.drivetrain.stopDrive();
    	SmartDashboard.putBoolean("Driving", true);
    	Robot.drivetrain.ramp(Robot.RAMP);
    	
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
        if(Math.abs(spd)>0.8)integral=0;
        this.spd = P*error + I*this.integral + D*derivative;
        previous_error = error;
    }
}