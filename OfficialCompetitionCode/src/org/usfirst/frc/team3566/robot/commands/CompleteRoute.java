package org.usfirst.frc.team3566.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.POINT;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class CompleteRoute extends CommandGroup {
	
    public CompleteRoute(ArrayList<POINT> routeToComplete) {

    	ArrayList<POINT> route = new ArrayList<POINT>();
//    	route.add(new POINT(0, 0));
//    	route.add(new POINT(1000, 0));
//    	route.add(new POINT(-1000, 1000));
//    	route.add(new POINT(0, 4000));
//    	route.add(new POINT(-2000, 3000));
//    	route.add(new POINT(-3000, 2000));
    
    	
    	for(int p=0; p<routeToComplete.size(); p++) {
    		addSequential(new CalculateAngle(route.get(p)));
    		addSequential(new Rotate());
    		addSequential(new DriveStraight());
    	} 
    }
}
