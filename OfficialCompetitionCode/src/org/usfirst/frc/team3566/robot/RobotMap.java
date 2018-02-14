package org.usfirst.frc.team3566.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class RobotMap {

    public static WPI_TalonSRX FL;
    public static WPI_TalonSRX RL;
    public static WPI_TalonSRX FR;
    public static WPI_TalonSRX RR;
    
    
    public static WPI_TalonSRX BPU1, BPU2, BPU3, BPU4;
    public static WPI_TalonSRX Elev1, Elev2;
    public static WPI_TalonSRX Tilter;
    public static WPI_TalonSRX Climber;
    
    public static PigeonIMU pigeon;
    
    public static SpeedControllerGroup left;
    public static SpeedControllerGroup right;
    
    public static DifferentialDrive drive;

    public static void init() {
        FL = new WPI_TalonSRX(10); //17
        
        
        RL = new WPI_TalonSRX(11);  //15
       // RearLeft.setInverted(true);
        
        
        FR = new WPI_TalonSRX(12);  //18
        FR.setInverted(true);
        pigeon = new PigeonIMU(FR);
        //pigeonIMU is connected to the talon with port 2. 
        
        RR = new WPI_TalonSRX(13);    //16
        RR.setInverted(true);
        
        
        left = new SpeedControllerGroup(FL, RL);
        right = new SpeedControllerGroup(FR, RR);
        
       drive = new DifferentialDrive(left, right);
   
       /*
       BPU1 = new WPI_TalonSRX(10); 
       BPU2 = new WPI_TalonSRX(20); 
       BPU3 = new WPI_TalonSRX(30); 
       BPU4 = new WPI_TalonSRX(40); 
       
       Elev1 = new WPI_TalonSRX(50);
       Elev2 = new WPI_TalonSRX(60);
       
       Climber = new WPI_TalonSRX(25);
       
       Tilter = new WPI_TalonSRX(35);
       
       */
       
        drive.setSafetyEnabled(true);
        drive.setExpiration(0.1);
        drive.setMaxOutput(1.0);


    }
    
}
