package org.usfirst.frc.team3566.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotMap {

    public static WPI_TalonSRX FL;
    public static WPI_TalonSRX RL;
    public static WPI_TalonSRX FR;
    public static WPI_TalonSRX RR;
    
    
    public static WPI_TalonSRX BPUleft, BPUright, BPU3, BPU4;
    public static WPI_TalonSRX ElevLeft, ElevRight;
    public static WPI_TalonSRX Tilter;
    public static WPI_TalonSRX Climber;
    
    public static PigeonIMU pigeon;
    
    public static SpeedControllerGroup left, right;
    public static SpeedControllerGroup Elev;
    
    public static DifferentialDrive drive;

    public static void init() {
    	
        FL = new WPI_TalonSRX(17); //17
        
        
        RL = new WPI_TalonSRX(15);  //15
       // RearLeft.setInverted(true);
        
        
        FR = new WPI_TalonSRX(18);  //18
        FR.setInverted(true);
        pigeon = new PigeonIMU(FR);
        //pigeonIMU is connected to the talon with port 2. 
        
        RR = new WPI_TalonSRX(16);    //16
        RR.setInverted(true);
        
        
        left = new SpeedControllerGroup(FL, RL);
        right = new SpeedControllerGroup(FR, RR);
        
       drive = new DifferentialDrive(left, right);
   
      
       BPUleft = new WPI_TalonSRX(1); //left grabber
       
       BPUright = new WPI_TalonSRX(2); //right grabber
       BPUright.setInverted(true);
       
       
       /*
       BPU3 = new WPI_TalonSRX(50); 
       BPU4 = new WPI_TalonSRX(60); 
              */
       
       
       ElevRight = new WPI_TalonSRX(30);
       ElevRight.setInverted(true);
       
       ElevLeft = new WPI_TalonSRX(40); 
       
       Elev = new SpeedControllerGroup(ElevLeft, ElevRight);
       SmartDashboard.putData("Elevator", Elev);
       
       /*
       Climber = new WPI_TalonSRX(25);
       
       Tilter = new WPI_TalonSRX(35);
       
       */
       
        drive.setSafetyEnabled(true);
        drive.setExpiration(0.1);
        drive.setMaxOutput(1.0);


    }
    
}
