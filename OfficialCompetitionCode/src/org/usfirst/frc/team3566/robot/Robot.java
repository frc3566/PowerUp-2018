/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3566.robot;

import org.usfirst.frc.team3566.robot.commands.Autonomous;
import org.usfirst.frc.team3566.robot.commands.ElevatorToPosition;
import org.usfirst.frc.team3566.robot.subsystems.BPU;
import org.usfirst.frc.team3566.robot.subsystems.Climber;
import org.usfirst.frc.team3566.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3566.robot.subsystems.Elevator;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	//constants
	public static final double RAMP=0.4;
	public static final POINT leftStart = new POINT(3.75, 1.5), 
			middleStart = new POINT(14.5, 1.5), rightStart = new POINT(23.5, 1.5);
	//variables
	public static RobotState state=RobotState.STANDSTILL;
	public static Variables var;
	public static Timer time;
	public static double maxCurrent;
	//subsystems
	public static BPU bpu;
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static Climber climber;
	//sensors
	public static Encoder encoderL, encoderR;
	public static Spark light;
	
	/*
	 * light values: SOLID COLORS: 0.61 red, 0.64 yellow, 0.77 green, 0.87 blue, 0.91 purple, 0.93 white
	 * -0.99 rainbow; -0.41 ocean; 
	 * 
	 */

	UsbCamera camMain, cam1;	//cam one looks out from BPU
	
	public static OI oi;
	
	Autonomous auto;
	
	SendableChooser<POINT> startingPosition = new SendableChooser<>();
	SendableChooser<Integer> autoTarget = new SendableChooser<>();

	@Override
	public void robotInit() {
		RobotMap.init();
		//IMPORTANT THAT VAR IS INSTANTIATED FIRST
		var = new Variables();
		
		drivetrain = new DriveTrain();
		bpu = new BPU();
		elevator = new Elevator();
	//	climber = new Climber();
		
		oi = new OI();
		startingPosition.addDefault("P1", leftStart);
		startingPosition.addObject("P2", middleStart);
		startingPosition.addObject("P3", rightStart);
		startingPosition.setName("startingPos");
		
		autoTarget.addDefault("OurSwitch", 0);
		autoTarget.addObject("Scale", 1);
		autoTarget.addObject("OppSwitch", 2);
		autoTarget.setName("autoTarget");
		
		SmartDashboard.putData("startingPosition", startingPosition);
		SmartDashboard.putData("autoTarget", autoTarget);
		
		time = new Timer();
		
		//encoder wheel perimeter 227.13mm
		encoderL = new Encoder(1,2,false,Encoder.EncodingType.k4X);
		encoderL.setDistancePerPulse(0.63);
		
		encoderR=encoderL;
		
//		encoderR = new Encoder(2,3,false,Encoder.EncodingType.k4X);
//		encoderR.setDistancePerPulse(2.394);
		
		camMain = CameraServer.getInstance().startAutomaticCapture(1);
		camMain.setResolution(1024,  768);
		camMain.setFPS(30);
		
		cam1 = CameraServer.getInstance().startAutomaticCapture(0);
        cam1.setResolution(1024, 768);
        cam1.setFPS(30);
        
        SmartDashboard.putNumber("maxPower", 1);
        
		var.reset();
        
        light = new Spark(0);
        SmartDashboard.putNumber("LightPattern", -0.41);
        
        
        //BELOW IS CODE FOR TESTING.
        SmartDashboard.putData("elevToBottom", new ElevatorToPosition(0));
        SmartDashboard.putData("elevToMiddle", new ElevatorToPosition(1));
        SmartDashboard.putData("elevToTop", new ElevatorToPosition(2));

		SmartDashboard.putNumber("PP", 0);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("DD", 0);
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
		auto = new Autonomous(new POINT(startingPosition.getSelected().getX(), startingPosition.getSelected().getY()), 
				autoTarget.getSelected(), (startingPosition.getSelected().equals(leftStart)? 'L' : 
					(startingPosition.getSelected().equals(middleStart)? 'M':'R')));
		//auto will receive info on our starting position coordinates, the code char for our starting position, and the target
		//we're going for
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
		maxCurrent=0;
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.updateCommands();
		var.updateValues();
//		System.out.println(state);
		System.out.println(Robot.elevator.topSwitch.get()+" "+Robot.elevator.bottomSwitch.get());
	}

	@Override
	public void testPeriodic() {
//		SmartDashboard.putNumber("elev", Robot.elevator.elevatorEncoder.getIndex());
		SmartDashboard.putNumber("theta", Robot.var.getTheta());
		light.set(SmartDashboard.getNumber("LightPattern", 0));
	}
}
