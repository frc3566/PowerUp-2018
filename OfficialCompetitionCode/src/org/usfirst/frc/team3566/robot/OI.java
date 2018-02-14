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
import org.usfirst.frc.team3566.robot.commands.RotateNonStop;
import org.usfirst.frc.team3566.robot.commands.BPUout;
import org.usfirst.frc.team3566.robot.commands.BPUin;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	//current plan: main for driving and BPU, secondary for elevator function
    public Joystick main; //, secondary;
    public JoystickButton rotateLeft, rotateRight;
   // public JoystickButton goStraight,rotate;
    public JoystickButton BPUin, BPUout;
    


    public OI() {

        main = new Joystick(0);
       // secondary = new Joystick(1);
        
        rotateLeft = new JoystickButton(main, 5);
        rotateLeft.whileHeld(new RotateNonStop(Robot.var.rotateNonStopSpd*-1));
        
        rotateRight = new JoystickButton(main, 6);
        rotateRight.whileHeld(new RotateNonStop(Robot.var.rotateNonStopSpd));
        
        /*
        goStraight= new JoystickButton(main,1);
        goStraight.whenPressed(new DriveStraight(10));
        
        rotate= new JoystickButton(main,2);
        rotate.whenPressed(new Rotate(90));
        */
        
        BPUin = new JoystickButton(main, 1);
        BPUin.whenPressed(new BPUin());
        
        BPUout = new JoystickButton(main, 4);
        BPUout.whenPressed(new BPUout());
        
        
        
    }

    public void updateCommands() {
    	goStraight= new JoystickButton(joystick1,1);
        goStraight.whenPressed(new DriveStraight(10));
        
        rotate= new JoystickButton(joystick1,2);
        rotate.whenPressed(new Rotate(90));
    }

}


