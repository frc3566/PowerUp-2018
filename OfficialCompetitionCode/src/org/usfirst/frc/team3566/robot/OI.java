/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3566.robot;

import org.usfirst.frc.team3566.*;
import org.usfirst.frc.team3566.robot.commands.RotateNonStop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
    public Joystick joystick1;
    public JoystickButton rotateLeft, rotateRight;


    public OI() {

        joystick1 = new Joystick(0);
        
        rotateLeft = new JoystickButton(joystick1, 5);
        rotateLeft.whileHeld(new RotateNonStop(Robot.var.rotateNonStopSpd*-1));
        
        rotateRight = new JoystickButton(joystick1, 6);
        rotateRight.whileHeld(new RotateNonStop(Robot.var.rotateNonStopSpd));
        


    }

    public void updateCommands() {
    	
    }

}


