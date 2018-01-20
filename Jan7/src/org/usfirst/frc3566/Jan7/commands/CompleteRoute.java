package org.usfirst.frc3566.Jan7.commands;

import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc3566.Jan7.POINT;
import org.usfirst.frc3566.Jan7.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CompleteRoute extends CommandGroup {

	double deltaTheta, previousTheta=0;
	Scanner fileIn; String path;
	
    public CompleteRoute(ArrayList<POINT> route) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	
    	RobotMap.pigeon.setYaw(0, 0);
    	for(int p=0; p<route.size()-1; p++) {
    		POINT p1 = route.get(p);
    		POINT p2 = route.get(p+1);
    		double distance = Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2)+Math.pow((p1.getY()-p2.getY()), 2));
    		distance *= 1; //scale to feet, because one unit in our simulator is 1s ft.
    		deltaTheta = Math.atan((p1.getX()-p2.getX())/(p1.getY()-p2.getY()));
    		
    		addSequential(new Rotate(deltaTheta-previousTheta));
    		addSequential(new PerformRoute(distance));
    	}
    	
    	
    }
}
 