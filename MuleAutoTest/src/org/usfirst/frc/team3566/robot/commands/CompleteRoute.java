package org.usfirst.frc.team3566.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.POINT;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class CompleteRoute extends CommandGroup {
	
    public CompleteRoute(ArrayList<POINT> routeToComplete) {

    	ArrayList<POINT> route = routeToComplete;
    	
    	for(int p=1; p<routeToComplete.size(); p++) {
    		addSequential(new CalculateAngle(route.get(p)));
    		addSequential(new Rotate());
    		addSequential(new DriveStraight());
    		if(p==routeToComplete.size()-1)
    		{
    			addSequential(new CalculateFinal(route.get(p)));
    			addSequential(new Rotate());
    		}
    	} 
    }
}
