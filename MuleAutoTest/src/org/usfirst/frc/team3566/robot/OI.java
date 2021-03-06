/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3566.robot;

import org.usfirst.frc.team3566.*;
import org.usfirst.frc.team3566.robot.commands.DriveStraight;
import org.usfirst.frc.team3566.robot.commands.Rotate;
import org.usfirst.frc.team3566.robot.commands.DriveForSeconds;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	//current plan: main for driving and BPU, secondary for elevator function
    public Joystick main, secondary;
    public JoystickButton rotateLeft, rotateRight;
   // public JoystickButton goStraight,rotate;
    public JoystickButton BPUin, BPUout;
    public JoystickButton elevGround1, elevMid1, elevTop1;
    public JoystickButton elevGround2, elevMid2, elevTop2;
    
    public OI() {

        main = new Joystick(0);
        secondary = new Joystick(1);
        
//        BPUin = new JoystickButton(main, 1);
//        BPUin.whenPressed(new BPUin());
        
//        BPUout = new JoystickButton(main, 4);
//        BPUout.whenPressed(new BPUout());
    }

    public void updateCommands() {
//    	SmartDashboard.putData("RotateForAngle2", new Rotate(SmartDashboard.getNumber("rotateAngle", 0)));
//    	SmartDashboard.putData("DriveForFeet2", new DriveStraight(SmartDashboard.getNumber("DriveFT", 0)));
//    	SmartDashboard.putData("DriveForSeconds", 
//    			new DriveForSeconds(false, SmartDashboard.getNumber("DriveTime", 0), 
//    					SmartDashboard.getBoolean("DriveDirection", true)));
    }
    
    
    public double getMainPOV() {
    return main.getPOV(0);
    }
    
}


