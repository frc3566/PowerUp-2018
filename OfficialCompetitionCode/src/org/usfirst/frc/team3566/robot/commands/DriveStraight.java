package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	double  P=0.0008, I=0.00035, D=0.00018;
    double integral, previousError, derivative, setPoint = 2000;
    double power=0,error=0;
    double maxPower=1;
    double startTime,time,length;
    double startAngle;
    boolean isAuto;
    //input in feet
    public DriveStraight(double distanceInFt) {
    	setPoint= distanceInFt*304.8;
    	isAuto=false;
    	requires(Robot.drivetrain);
    }
    
    public DriveStraight() {
    	isAuto=true;
    	requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
    	if(isAuto)setPoint=Robot.var.distance;
    	length=setPoint;
    	Robot.var.collision.isCollide=false;
    	startAngle=Robot.var.getTheta();
    	setPoint+=Robot.var.getEncoder();
    	error = setPoint - Robot.var.getEncoder();
    	startTime=Robot.time.get();
    }
    
    void ramp()
    {
    	double max=1;
    	if(time<0.5)max=time*1.5;
    	if(Robot.encoderL.getRate()>1800)power=Math.min(0.8, power);
    	else if(Robot.encoderL.getRate()<-1800)power=Math.max(-0.8, power);
    	power=Math.min(max, power);
    }
    
    void PID() {
    	error = setPoint - Robot.var.getEncoder();
        this.integral += (error*.02);
        derivative = (error - previousError) / .02;
        previousError=error;
        if(power>=0.99||power<=-0.99)integral=0;
        power = P*error + I*this.integral + D*derivative;
    }
    
    @Override
    protected void execute() {
    	//Robot.var.updateXY();
    	time=Robot.time.get()-startTime;
        PID();
        ramp();
    	RobotMap.drive.tankDrive(power,power);
    }

    @Override
    protected boolean isFinished() {
    	double errAngle=(Robot.var.getTheta()-startAngle+360)%360;
    	if(errAngle>15 && errAngle<345)return true;
    	if(Robot.oi.joystick1.getRawButton(3))return true;
    	if(Robot.var.isCollide)return true;
    	if(Math.abs(error)<100&&Robot.encoderL.getRate()<100)return true;//needs consideration
    	if(time> Math.abs(length) / 300 )return true;
    	return false;
    }

    @Override
    protected void end(){
    	if(Robot.var.isCollide)
    		System.out.println("drive straight stopped due to collision");
    	double errAngle=(Robot.var.getTheta()-startAngle+360)%360;
    	if(errAngle>15 && errAngle<345)
    		System.out.println("drive straight stopped due to bad direction");
    	System.out.println("straight stop");
    	Robot.drivetrain.stopDrive();
    }

    @Override
    protected void interrupted() {
    }
}
