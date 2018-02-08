/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3566.robot;

import org.usfirst.frc.team3566.robot.commands.Autonomous;
import org.usfirst.frc.team3566.robot.subsystems.DriveTrain;

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
	
	public static Encoder encoderL, encoderR;
	
	public static Timer time;
	
	Autonomous auto;
	SendableChooser<POINT> startingPosition = new SendableChooser<>();


	@Override
	public void robotInit() {
		//IMPORTANT THAT VAR IS INSTANTIATED FIRST
		var = new Variables();
		
		
		oi = new OI();
		drivetrain = new DriveTrain();
		
		startingPosition.addDefault("Pos1", new POINT(3, 1.75));
		startingPosition.addDefault("Pos2", new POINT(13, 1.75));
		startingPosition.addDefault("Pos3", new POINT(25, 1.75));
		
		auto = new Autonomous(startingPosition.getSelected());
		
		time = new Timer();
		
	//	encoderL = new Encoder();
	//	encoderL.setDistancePerPulse(distancePerPulse);
	//	encoderR = new Encoder();
//		encoderR.setDistancePerPulse(distancePerPulse);
		
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
		
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		var.updateValues();
		
		
	}


	@Override
	public void testPeriodic() {
	}
}
