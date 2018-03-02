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
import org.usfirst.frc.team3566.robot.subsystems.CameraServo;
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
	public static final double RAMP=0.3;
	public static final POINT leftStart = new POINT(3.75, 1.5), 
			middleStart = new POINT(14.5, 1.5), rightStart = new POINT(23.5, 1.5);
	//variables
	public static EncoderState encoderState=EncoderState.Left;
	public static Variables var;
	public static Timer time;
	public static double maxCurrent;
	public static boolean isAuto = false;
	//subsystems
	public static BPU bpu;
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static Climber climber;
	public static CameraServo camServo;
	public static Spark light;
	public static OI oi;
	//sensors
	public static Encoder encoderL, encoderR;
	public static Encoder encoderMotorL, encoderMotorR;
	UsbCamera camMain, cam1;	//cam one looks out from BPU
	/*
	 * light values: SOLID COLORS: 0.61 red, 0.64 yellow, 0.77 green, 0.87 blue, 0.91 purple, 0.93 white
	 * -0.99 rainbow; -0.41 ocean; 
	 */

	Autonomous auto;
	
	//SendableChooser<POINT> startingPosition = new SendableChooser<>();
	//SendableChooser<Integer> autoTarget = new SendableChooser<>();
	@Override
	public void robotInit() {
		RobotMap.init();
		//IMPORTANT THAT VAR IS INSTANTIATED FIRST
		var = new Variables();
		
		drivetrain = new DriveTrain();
		bpu = new BPU();
		elevator = new Elevator();
		climber = new Climber();
		camServo = new CameraServo();
		
		oi = new OI();
		time = new Timer();
//		startingPosition.addDefault("P1", leftStart);
//		startingPosition.addObject("P2", middleStart);
//		startingPosition.addObject("P3", rightStart);
//		startingPosition.setName("startingPos");
//		autoTarget.addDefault("OurSwitch", 0);
//		autoTarget.addObject("Scale", 1);
//		autoTarget.addObject("OppSwitch", 2);
//		autoTarget.setName("autoTarget");
//		SmartDashboard.putData("startingPosition", startingPosition);
//		SmartDashboard.putData("autoTarget", autoTarget);
		
//		encoder wheel perimeter 227.13mm
//		encoderL = new Encoder(1,2,false,Encoder.EncodingType.k4X);//this is main encoder
//		encoderL.setDistancePerPulse(0.63);
		
		encoderL=encoderMotorL = new Encoder(4,5,false,Encoder.EncodingType.k4X);
		encoderL.setDistancePerPulse(1.925);
		encoderR=encoderMotorR = encoderL;//new Encoder(6,7,false,Encoder.EncodingType.k4X);
		encoderState=Robot.encoderState.Left;
		
		camMain = CameraServer.getInstance().startAutomaticCapture(1);
		camMain.setResolution(480,  360);
		camMain.setFPS(20);
		cam1 = CameraServer.getInstance().startAutomaticCapture(0);
        cam1.setResolution(480, 360);
        cam1.setFPS(20);
        
		var.reset();
        light = new Spark(0);
        //SmartDashboard.putNumber("LightPattern", -0.41);
        SmartDashboard.putNumber("maxPower", 1);
        //BELOW IS CODE FOR TESTING.
//        SmartDashboard.putData("elevToBottom", new ElevatorToPosition(0));
//        SmartDashboard.putData("elevToMiddle", new ElevatorToPosition(1));
//        SmartDashboard.putData("elevToTop", new ElevatorToPosition(2));
//		SmartDashboard.putNumber("PP", 0);
//		SmartDashboard.putNumber("I", 0);
//		SmartDashboard.putNumber("DD", 0);
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
		isAuto = true;
		/*
		auto = new Autonomous(new POINT(startingPosition.getSelected().getX(), startingPosition.getSelected().getY()), 
				autoTarget.getSelected(), (startingPosition.getSelected().equals(leftStart)? 'L' : 
					(startingPosition.getSelected().equals(middleStart)? 'M':'R')));
		*/
		
		//SELECT FROM ONE BELOW AND COMMENT OUT THE OTHERS
//		auto = new Autonomous(leftStart, 0, 'L'); //our switch
		auto = new Autonomous(leftStart, 1, 'L'); //scale
//		auto = new Autonomous(rightStart, 0, 'R'); //our switch
//		auto = new Autonomous(rightStart, 1, 'R'); //scale

		//auto will receive info on our starting position coordinates, the code char for our starting position, and the target
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
		isAuto = false;
		Robot.drivetrain.ramp(RAMP);
		if (auto != null) {
			auto.cancel();
		}
		encoderL.reset();
		encoderR.reset();
		var.reset();
		maxCurrent=0;
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.updateCommands();
		var.updateValues();
		SmartDashboard.putNumber("elev", Robot.elevator.elevatorEncoder.getValue());
		//System.out.println("motorL encoder: "+encoderMotorL.get()+" motorR encoder: "+encoderMotorR.get());
	}

	@Override
	public void testPeriodic() {
		SmartDashboard.putNumber("elev", Robot.elevator.elevatorEncoder.getValue());
		SmartDashboard.putNumber("theta", Robot.var.getTheta());
		light.set(SmartDashboard.getNumber("LightPattern", 0));
	}
}
