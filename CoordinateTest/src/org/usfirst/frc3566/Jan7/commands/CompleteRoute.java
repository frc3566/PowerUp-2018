package org.usfirst.frc3566.Jan7.commands;

import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc3566.Jan7.POINT;
import org.usfirst.frc3566.Jan7.Robot;
import org.usfirst.frc3566.Jan7.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CompleteRoute extends CommandGroup {
	
    public CompleteRoute() {

    	ArrayList<POINT> route = new ArrayList<POINT>();
    	Robot.var.x=0;
    	Robot.var.y=0;
//    	route.add(new POINT(0, 0));
//    	route.add(new POINT(1000, 0));
//    	route.add(new POINT(-1000, 1000));
//    	route.add(new POINT(0, 4000));
//    	route.add(new POINT(-2000, 3000));
//    	route.add(new POINT(-3000, 2000));
    	
    	route.add(new POINT(0, 0));
    	route.add(new POINT(0, 3000));
    	route.add(new POINT(1000, 4000));
    	route.add(new POINT(-1000, 5000));
    	route.add(new POINT(0, 7000));
    	route.add(new POINT(0, 3000));
    	
    	RobotMap.pigeon.setYaw(0, 0);// set up polar system
    	
    	for(int p=0; p<100; p++) {
    		addSequential(new Calculate(route.get(p%route.size())));
    		addSequential(new Rotate());
    		addSequential(new DriveStraight());
    	} 
    }
}
 