/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3566.robot;

import org.usfirst.frc.team3566.robot.commands.Autonomous;
import org.usfirst.frc.team3566.robot.subsystems.DriveTrain;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain drivetrain;
	public static Variables var;
	public static double maxCurrent;
	public final static double RAMP=0.4;
	public static Encoder encoderL, encoderR;
	UsbCamera cam1;
	
	public static Timer time;
	
	Autonomous auto;
	SendableChooser<POINT> startingPosition = new SendableChooser<>();
	SendableChooser<Double> startingPositionX = new SendableChooser<>();
	SendableChooser<Double> startingPositionY = new SendableChooser<>();


	@Override
	public void robotInit() {
		RobotMap.init();
		//IMPORTANT THAT VAR IS INSTANTIATED FIRST
		var = new Variables();
		drivetrain = new DriveTrain();
		oi = new OI();
		startingPosition.addDefault("P1", new POINT(3.75, 1.5));
		startingPosition.addObject("P2", new POINT(14.5, 1.5));
		startingPosition.addObject("P3", new POINT(23.5, 1.5));
		
		startingPositionX.addObject("X1", 3.0);
		startingPositionX.addObject("X2", 13.0);
		startingPositionX.addObject("X3", 25.0);
		
		startingPositionY.addObject("Y1", 1.75);
		startingPositionY.addObject("Y2", 3.0);
		
		SmartDashboard.putData("startingPosition", startingPosition);
		
		time = new Timer();
		
		//encoder wheel perimeter 227.13mm
		encoderL = new Encoder(0,1,false,Encoder.EncodingType.k4X);
		encoderL.setDistancePerPulse(-0.63);
		
//		encoderR = new Encoder(2,3,false,Encoder.EncodingType.k4X);
//		encoderR.setDistancePerPulse(2.394);
		encoderR=encoderL;
		
		cam1 = CameraServer.getInstance().startAutomaticCapture(0);
        cam1.setResolution(1024, 768);
        cam1.setFPS(30);
        
        var.reset();
        drivetrain.ramp(RAMP);
        SmartDashboard.putNumber("maxPower", 1);
	}


	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {
		
		auto = new Autonomous(new POINT(startingPosition.getSelected().getX(), startingPosition.getSelected().getY()));
		
		if (auto != null) {
			auto.start();
		}
		
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		if (auto != null) {
			auto.cancel();
		}
		encoderL.reset();
		encoderR.reset();
		//var.XYReset(0, 0);
		maxCurrent=0;
		var.reset();
	}

	int cnt=0;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.updateCommands();
		var.updateValues();
		maxCurrent=Math.max(maxCurrent,RobotMap.RL.getOutputCurrent());
		cnt++;
		if(cnt%100==0)System.out.printf("max current %.2f\n",maxCurrent);
		//System.out.printf("L %.0f R%.0f\n",encoderL.getDistance(),encoderR.getDistance());
	}

	@Override
	public void testPeriodic() {
	}
}
