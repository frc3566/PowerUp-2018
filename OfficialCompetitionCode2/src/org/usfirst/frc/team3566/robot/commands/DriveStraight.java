package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {
	static final double maxSpeed=2400; //mm * s^-1
	double  P=0.001, I=0.0008, D=0.00016;
	double integral, previousError, derivative, setPoint = 2000;
    double power=0,error=0;
    double maxPower=1;
    double startTime,time,length;
    double startAngle;
    boolean isAuto;
    
    double prev_light;
    
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
    	//Robot.var.distance's unit is ft, while DriveStraight needs to use Encoder in cm. 
    	//we're converting units here in the beginning to get rid of the problem
//    	P=SmartDashboard.getNumber("PP", 0);
//    	I=SmartDashboard.getNumber("I", 0);
//    	D=SmartDashboard.getNumber("DD", 0);
    	if(isAuto)setPoint=Robot.var.distance * 304.8;   //1 ft = 304.8 mm. Distance (ft) converted to mm for encoder drive
    	Robot.var.collision.collideReset();
    	startAngle=Robot.var.getTheta();
    	setPoint+=Robot.var.getEncoder();
    	error = setPoint - Robot.var.getEncoder();
    	startTime=Robot.time.get();
    	Robot.drivetrain.ramp(0);
    	prev_light = Robot.light.get();
    	Robot.light.set(Robot.var.white);
    	System.out.printf("drive for %.0f\n", setPoint);
    	length=Math.abs(setPoint);
    }
    
    void ramp()
    {
    	if(time<1 && error>0)
    		power=Math.min(time, power);
    	else if(time<1 && error<0) power=Math.max(time*-1, power);
//    	if(Robot.encoderL.getRate()>maxSpeed*0.5)
//    		power=Math.min(1.6-1.4*Robot.encoderL.getRate()/maxSpeed, power);
//    	else if(Robot.encoderL.getRate()<maxSpeed*-0.5)
//    		power=Math.max(-1.6-1.4*Robot.encoderL.getRate()/maxSpeed, power);
    	double sign = (power>0) ? 1:-1;
    	double vError = (Math.abs(Robot.encoderL.getRate()- 0.7*maxSpeed)) / maxSpeed;
    	double max = 1 - vError;
    	power = Math.min(Math.abs(power), max)*sign;
    }
    
    void PID() {
    	error = setPoint - Robot.var.getEncoder();
        this.integral += (error*.02);
        derivative = (error - previousError) / .02;
        previousError=error;
        if(power>=0.99||power<=-0.99||Math.abs(Robot.encoderL.getRate())>maxSpeed)integral=0;
        power = P*error + I*this.integral + D*derivative;
    }
    
    @Override
    protected void execute() {
    	//Robot.var.updateXY();
    	time=Robot.time.get()-startTime;
        PID();
        ramp();
//        System.out.println("Drivin Straight: power is "+power);
    	RobotMap.drive.tankDrive(power,power * 0.98);
    }

    @Override
    protected boolean isFinished() {
    	double errAngle = (Robot.var.getTheta()-startAngle+360) % 360;
    	if (errAngle>15 && errAngle<345) return true;
//    	if (Robot.var.collision.isCollide) return true;
    	if (Math.abs(error)<50&&Robot.encoderL.getRate()<300) return true;//needs consideration
    	else if(Math.abs(error)<Robot.var.allowedDriveError)return true;
    	if (time> Math.abs(length) / 300 ) return true;
    	return false;
    }

    @Override
    protected void end(){
    	if (Robot.var.collision.isCollide)
    		System.out.println("drive straight stopped due to collision");
    	double errAngle = (Robot.var.getTheta()-startAngle+360) % 360;
    	if (errAngle>15 && errAngle<345)
    		System.out.println("drive straight stopped due to bad direction");
    	System.out.printf("straight stop in %.1f second error %.1f\n",time,error);
    	Robot.drivetrain.stopDrive();
    	Robot.drivetrain.ramp(Robot.RAMP);
    	
    	Robot.light.set(prev_light);
    }

    @Override
    protected void interrupted() {
    	Robot.drivetrain.ramp(Robot.RAMP);
    }
}