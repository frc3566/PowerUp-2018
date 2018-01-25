package org.usfirst.frc3566.Jan7.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3566.Jan7.Robot;
import org.usfirst.frc3566.Jan7.RobotMap;

public class DriveStraight extends Command {

	double  P=0.0008, I=0.0004, D=0.00018;
    double integral, previousError, derivative, setPoint = 2000;
    double power=0,error=0;
    double maxPower=1;
    double time=0;
  
    //input in feet
    public DriveStraight(double _setPoint) {
    	setPoint=_setPoint*304.8;
    }

    @Override
    protected void initialize() {
    	Robot.encoder1.reset();
    	time=0;
    }

    void PID() {
    	error = setPoint - Robot.encoder1.getDistance();
        this.integral += (error*.02);
        derivative = (error - previousError) / .02;
        previousError=error;
        if(power>=0.9999||power<=-0.9999)integral=0;
        power = P*error + I*this.integral + D*derivative;
    }
    
    @Override
    protected void execute() {
    	time+=0.02;
        PID();
//    	if(Robot.encoder1.getRate()>1800)power=Math.min(0.6, power);
//    	else if(Robot.encoder1.getRate()<-1800)power=Math.max(-0.6, power);
    	SmartDashboard.putNumber("power", power);
    	RobotMap.driveTrainDrive.tankDrive(power,power);
    }

    @Override
    protected boolean isFinished() {
    	if(Robot.oi.joystick1.getRawButton(1))return true;
    	if(Math.abs(error)<100&&Robot.encoder1.getRate()<100&&time>0.5)return true;//needs consideration
    	if(time> Math.abs(setPoint) / 300 )return true;
    	return false;
    }

    @Override
    protected void end(){
    	//RobotMap.drive.tankDrive(0, 0);
    }

    @Override
    protected void interrupted() {
    }
}