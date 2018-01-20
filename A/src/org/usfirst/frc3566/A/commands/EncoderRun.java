package org.usfirst.frc3566.A.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3566.A.Robot;
import org.usfirst.frc3566.A.RobotMap;

public class EncoderRun extends Command {

	double  P, I, D;
    double integral, previousError, derivative, setPoint = 2000;
    double power=0,error=0;
    double maxPower=1;
    double time=0;
  
    public EncoderRun() {
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.encoder1.reset();
    	setPoint=SmartDashboard.getNumber("Distance", 1000);
    	time=0;
    }

    void PID() {
    	error = setPoint - Robot.encoder1.getDistance();
        this.integral += (error*.02);
        derivative = (error - previousError) / .02;
        previousError=error;
        if(power>1)integral=0;
        power = P*error + I*this.integral + D*derivative;
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	time+=0.02;
    	P=SmartDashboard.getNumber("P", 0.0012);
        I=SmartDashboard.getNumber("I", 0.001);
        D=SmartDashboard.getNumber("D", 0.0001);
        PID();
    	SmartDashboard.putNumber("power", power);
    	maxPower=SmartDashboard.getNumber("maxPower", 1);
    	power*=maxPower;
    	//new ControlledDrive(power,power).start();
    	RobotMap.drive.tankDrive(power,power);
    }

    // Make this return true when this Command no		 longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if(Robot.oi.joystick1.getRawButton(1))return true;
    	if(Math.abs(error)<100&&Robot.encoder1.getRate()<100&&time>0.5)return true;//needs consideration
    	return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end(){
    	RobotMap.drive.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
