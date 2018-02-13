package org.usfirst.frc.team3566.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends Command {

	POINT startingPosition;
	
    public Autonomous(POINT startingPos) {
    	startingPosition = startingPos;
    }

    @Override
    protected void initialize() {
    	
    	
    	Robot.var.gameMessage = DriverStation.getInstance().getGameSpecificMessage();
    	Robot.var.setSwitchScaleSides(); //splits gameMessage into individual message
    	SmartDashboard.putString("OurSwitch", Robot.var.ourSwitchPos+"");
    	SmartDashboard.putString("Scale", Robot.var.ScalePos+"");
    	SmartDashboard.putString("OppSwitch", Robot.var.oppSwitchPos+"");
    	
    	Robot.var.XYReset(startingPosition.getX(), startingPosition.getY());
    	System.out.println("XY reset: "+ Robot.var.x+" "+Robot.var.y);
    	
    	ArrayList<POINT> routeToPerform = Robot.var.route11;  //default, go left and cross auto line
    	
    	if(startingPosition.getX()<10) { //starting Left
    		if(Robot.var.ourSwitchPos == 'L') {
    			System.out.println("left "+startingPosition.getX());
    			routeToPerform = Robot.var.route1;
    		}else if(Robot.var.ScalePos == 'L') {
    			routeToPerform = Robot.var.route2;
    		}else { //both not on our side. 
    			routeToPerform = Robot.var.route3;
    		}
    	}else if(startingPosition.getX()>20) { //starting right
    		if(Robot.var.ourSwitchPos == 'R') {
    			routeToPerform = Robot.var.route4;
    		}else if(Robot.var.ScalePos == 'R') {
    			routeToPerform = Robot.var.route5;
    		}else { //both not on our side. 
    			routeToPerform = Robot.var.route6;
    		}
    	}else {//in the middle
    		if(Robot.var.ScalePos == 'R') {
    			routeToPerform = Robot.var.route7;
    		}else if(Robot.var.ScalePos == 'L') {
    			routeToPerform = Robot.var.route8;
    		}else if(Robot.var.ourSwitchPos == 'R'){ //both not on our side. 
    			routeToPerform = Robot.var.route9;
    		}else if(Robot.var.ourSwitchPos == 'L') {
    			routeToPerform = Robot.var.route10;
    		}
    	}
    	
    	new CompleteRoute(routeToPerform).start(); //start AUTO command group
    	
    }

    @Override
    protected void execute() {
    	Robot.var.updateValues();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
