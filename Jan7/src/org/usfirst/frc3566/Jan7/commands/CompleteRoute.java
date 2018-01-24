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
	
    public CompleteRoute() {

    	ArrayList<POINT> route = new ArrayList<POINT>();
    	route.add(new POINT(3.75, 3.5));
    	route.add(new POINT(6.059, 7.5));
    	route.add(new POINT(6.059, 3.5));
    	route.add(new POINT(3.75, 7.5));
    	
    	RobotMap.pigeon.setYaw(0, 0);
    	for(int p=0; p<route.size()-1; p++) {
    		POINT p1 = route.get(p);
    		POINT p2 = route.get(p+1);
    		double distance = Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2)+Math.pow((p1.getY()-p2.getY()), 2));
    		distance *= 1; //scale to feet, because one unit in our simulator is 1s ft.
    		
    		if(p1.getY()-p2.getY()==0) {
    			if(p2.getX()>p1.getX()) {
    				deltaTheta = 90;
    			}else {
    				deltaTheta = -90;
    			}
    		}else
    		
    		if(p1.getX()-p2.getX()==0) {
    			if(p2.getY()>p1.getY()) {
    				deltaTheta = 0;
    			}else {
    				deltaTheta = 0;
    				distance *= -1;
    			}
    		}else {
    		deltaTheta = Math.toDegrees(Math.atan((p1.getX()-p2.getX())/(p1.getY()-p2.getY())));
    		}
    		
    		if(!((deltaTheta-previousTheta)==0)) {
    		addSequential(new Rotate(deltaTheta-previousTheta));
    		}
    		System.out.println("Rotate!"+ (deltaTheta-previousTheta));
    		
    		
    		previousTheta = deltaTheta;
    		
    		addSequential(new DriveForSomething(true, Math.abs(distance), 0.1, (distance>0? true:false)));
    		System.out.println("Drive!");
    		
    	} //end for loop
    	
    	
    }
}
 