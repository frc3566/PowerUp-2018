package org.usfirst.frc3566.A.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3566.A.Robot;
import org.usfirst.frc3566.A.RobotMap;

public class EncoderRun extends Command {

	double  P=0.03, I=0.10,D=0;
    double integral, previousError, derivative, setpoint = 2000;
    double power=0,error=0;
    double maxPower=1;
    int cnt=0;
    boolean arrived=false;
    
    public EncoderRun() {
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.encoder1.reset();
    	arrived=false;
    	cnt=0;
    }

    void PID() {
    	error = setpoint - Robot.encoder1.getDistance(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - previousError) / .02;
        previousError=error;
        if(Math.abs(error)<100)arrived=true;
        power = P*error + I*this.integral + D*derivative;
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	P=SmartDashboard.getNumber("DB/Slider 0", 0.03);
        I=SmartDashboard.getNumber("DB/Slider 1", 0);
        D=SmartDashboard.getNumber("DB/Slider 2", 0);
    	maxPower=SmartDashboard.getNumber("DB/Slider 3", 1);
        PID();
    	if(arrived)cnt++;
    	SmartDashboard.putNumber("power", power);
    	power*=maxPower;
    	RobotMap.drivetrainRobotDrive21.tankDrive(power,power);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if(Robot.oi.joystick1.getRawButton(1))return true;
    	//if(arrived&&cnt>30)return true;
    	return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end(){
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
