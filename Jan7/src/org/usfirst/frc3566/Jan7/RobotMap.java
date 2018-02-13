package org.usfirst.frc3566.Jan7;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class RobotMap {

    public static WPI_TalonSRX FrontLeft;
    public static WPI_TalonSRX RearLeft;
    public static WPI_TalonSRX FrontRight;
    public static WPI_TalonSRX RearRight;
    
    
    public static WPI_TalonSRX BPU1, BPU2, BPU3, BPU4;
    public static WPI_TalonSRX Elevator1, Elevator2, Elevator3;
    public static WPI_TalonSRX Tilter;
    
    public static PigeonIMU pigeon;
    
    public static SpeedControllerGroup left;
    public static SpeedControllerGroup right;
    
    public static DifferentialDrive driveTrainDrive;

    public static void init() {
        FrontLeft = new WPI_TalonSRX(10);
        
        
        RearLeft = new WPI_TalonSRX(11);
       // RearLeft.setInverted(true);
        
        
        FrontRight = new WPI_TalonSRX(12);
        FrontRight.setInverted(true);
        pigeon = new PigeonIMU(FrontRight);
        //pigeonIMU is connected to the talon with port 2. 
        
        RearRight = new WPI_TalonSRX(13);
        RearRight.setInverted(true);
        
        
        left = new SpeedControllerGroup(FrontLeft, RearLeft);
        right = new SpeedControllerGroup(FrontRight, RearRight);
        
       driveTrainDrive = new DifferentialDrive(left, right);
        
       BPU1 = new WPI_TalonSRX(10); 
       BPU2 = new WPI_TalonSRX(20); 
       BPU3 = new WPI_TalonSRX(30); 
       BPU4 = new WPI_TalonSRX(40); 
       
       Elevator1 = new WPI_TalonSRX(50);
       Elevator2 = new WPI_TalonSRX(60);
       Elevator3 = new WPI_TalonSRX(25);
       
       Tilter = new WPI_TalonSRX(35);
       
        driveTrainDrive.setSafetyEnabled(true);
        driveTrainDrive.setExpiration(0.1);
        driveTrainDrive.setMaxOutput(1.0);


    }
    
}
