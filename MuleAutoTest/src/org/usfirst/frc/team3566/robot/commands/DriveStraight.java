package org.usfirst.frc.team3566.robot.commands;

import org.usfirst.frc.team3566.robot.Robot;
import org.usfirst.frc.team3566.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*Always use for going forward
 */
public class DriveStraight extends Command {
	public static double initMaxSpeed=3000; //mm * s^-1, the actual speed is about 300 slower than this
	double maxSpeed;
	double startAngle,errAngle,targetAngle,curTargetAngle;
	double angleP=0.03, angleI=0.004, angleD=0.004,angleIntegral, anglePreviousError, angleDerivative;
	double finalP=0.001, finalI=0.0008, finalD=0.00016,finalIntegral, finalPreviousError, finalDerivative;
	double speedP=4/initMaxSpeed,speedI=2/initMaxSpeed,speedD=1/initMaxSpeed,speedIntegral,speedPreviousError,speedDerivative;
    double power=0,anglePower=0;
    double startTime,time;
    double length,minDis,curDis;
    
    public DriveStraight() {
    	requires(Robot.drivetrain);
    }
    @Override
    protected void initialize() {
    	startAngle=Robot.var.getTheta();
    	maxSpeed=initMaxSpeed;
    	targetAngle=Robot.var.getVectorDegree(Robot.var.ptToGo.getX()-Robot.var.getX(),Robot.var.ptToGo.getY()-Robot.var.getY());
    	startTime=Robot.time.get();
    	Robot.drivetrain.ramp(1);
    	minDis=curDis=Math.sqrt(Math.pow(Robot.var.ptToGo.getX()-Robot.var.getX(), 2) + Math.pow(Robot.var.ptToGo.getY()-Robot.var.getY(), 2) );
    	System.out.printf("driveStraight starts curDis is %.0f\n",curDis);
    }
    
    @Override
    protected void execute() {
    	time=Robot.time.get()-startTime;
    	calculateAngle();
    	//calculate dis
    	curDis=Math.sqrt(Math.pow(Robot.var.ptToGo.getX()-Robot.var.getX(), 2) + Math.pow(Robot.var.ptToGo.getY()-Robot.var.getY(), 2));
    	minDis=Math.min(minDis,curDis);
    	
    	power=1;
    	speedPID();
        anglePID();
        if(Robot.var.ptToGo.getTheta()>-0.1)finalPID();
    	RobotMap.drive.arcadeDrive(power, anglePower);
    }

    @Override
    protected boolean isFinished() {
    	if(curDis<Robot.var.allowedDriveError/304.8)return true;
    	else if((curDis>minDis+0.01&&curDis<4)&&time>0.5)
    	{
    		System.out.printf("!!go pass pt!! ");
    		return true;
    	}
    	//in feet
    	//if (time> Math.abs(length) / 300 ) return true;
    	return false;
    }
    @Override
    protected void end(){
    	System.out.printf("straight stop in %.1f second error %.1f\n",time,curDis);
    	Robot.drivetrain.stopDrive();
    	Robot.drivetrain.ramp(Robot.RAMP);
    }
    @Override
    protected void interrupted() {
    	Robot.drivetrain.ramp(Robot.RAMP);
    }
    void calculateAngle()
    {
    	curTargetAngle=Robot.var.getVectorDegree(Robot.var.ptToGo.getX()-Robot.var.getX(),Robot.var.ptToGo.getY()-Robot.var.getY());
    	if((curTargetAngle-targetAngle+360)%360>40&&(curTargetAngle-targetAngle+360)%360<180)curTargetAngle=(targetAngle+40)%360;
    	else if((curTargetAngle-targetAngle+360)%360>180&&(curTargetAngle-targetAngle+360)%360<320)curTargetAngle=(targetAngle+320)%360;
    	errAngle = ( Robot.var.getTheta()-curTargetAngle+360) % 360;
    	if(errAngle>180)errAngle-=360;
    }
    void speedPID()
    {
    	if(curDis*304.8<1000)maxSpeed=1000+(curDis/2000)*initMaxSpeed;
    	double error = maxSpeed-Robot.encoderL.getRate();
    	speedIntegral += (error*.02);
        speedDerivative = (error - speedPreviousError) / .02;
        speedPreviousError=error;
        if(power>=0.99)speedIntegral=0;
    	power = error*speedP;
    }
    void anglePID() {
        angleIntegral += (errAngle*.02);
        angleDerivative = (errAngle - anglePreviousError) / .02;
        anglePreviousError=errAngle;
        if(anglePower>=0.99)angleIntegral=0;
        anglePower = angleP*errAngle + angleI*angleIntegral + angleD*angleDerivative;
    }
    void finalPID()
    {
    	double error = curDis*304.8;
        finalIntegral += (error*.02);
        finalDerivative = (error - finalPreviousError) / .02;
        finalPreviousError=error;
        if(power>=0.99||power<=-0.99||Math.abs(Robot.encoderL.getRate())>maxSpeed)finalIntegral=0;
        power = Math.min(power, finalP*error + finalI*finalIntegral + finalD*finalDerivative);
    }
}