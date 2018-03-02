package org.usfirst.frc.team3566.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotMap {

    public static WPI_TalonSRX FL;
    public static WPI_TalonSRX RL;
    public static WPI_TalonSRX FR;
    public static WPI_TalonSRX RR;
    
    
    public static WPI_TalonSRX BPUleft, BPUright;
    public static WPI_TalonSRX ElevLeft, ElevRight;
    public static WPI_TalonSRX Tilter;
    public static WPI_TalonSRX climber;
    
    public static PigeonIMU pigeon;
    
    public static SpeedControllerGroup left, right;
    public static SpeedControllerGroup Elev, BPU;
    
    public static DifferentialDrive drive;    
    public static Servo cameraServo;
    
    public static void init() {
       FL = new WPI_TalonSRX(60); //60
//       SmartDashboard.putData(FL);
       RL = new WPI_TalonSRX(40);  //40
//       SmartDashboard.putData(RL);
       FR = new WPI_TalonSRX(10);  //10
//       SmartDashboard.putData(FR);
       RR = new WPI_TalonSRX(50);    //50
//       SmartDashboard.putData(RR);
        
       left = new SpeedControllerGroup(FL, RL);
//       SmartDashboard.putData("Left Drive", left);
       right = new SpeedControllerGroup(FR, RR);
//       SmartDashboard.putData("Right Drive", right);
       drive = new DifferentialDrive(left, right);
       //pigeonIMU is connected to the talon with port 2.
       pigeon = new PigeonIMU(FR);
       
       BPUleft = new WPI_TalonSRX(15); //left grabber
       BPUleft.setInverted(false);
       BPUright = new WPI_TalonSRX(16); //right grabber
       BPUright.setInverted(true);
       BPU = new SpeedControllerGroup(BPUleft, BPUright);
              
       ElevRight = new WPI_TalonSRX(35);  //35
       ElevRight.setInverted(true);
       ElevRight.configOpenloopRamp(0.2, 0);
       ElevLeft = new WPI_TalonSRX(17); //17
       ElevLeft.setInverted(false);
       ElevLeft.configOpenloopRamp(0.2, 0);
       
       //elevator: pos power goes down, corresponding to the elevator encoder.
       Elev = new SpeedControllerGroup(ElevLeft, ElevRight);
     //  SmartDashboard.putData("Elevator", Elev);
  
       climber = new WPI_TalonSRX(25);	//25
    //   SmartDashboard.putData("climber", climber);
//       Tilter = new WPI_TalonSRX(18);	//18+
       drive.setSafetyEnabled(true);
       drive.setExpiration(0.5);
       drive.setMaxOutput(1.0);
       
       cameraServo = new Servo(1);  //servo attached to PWM channel
       
    }
    
}
