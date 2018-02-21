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
	int targetNum; //0 represents our switch, 1 the scale, 2 the opponent switch
	char startingPosChar; //'L', 'M', 'R'
	
	AutoCommandGroup theAuto;
	
    public Autonomous(POINT startingPos, int target, char startingPosC) {
    	startingPosition = startingPos;
    	targetNum = target;
    	startingPosChar = startingPosC;
    }

    @Override
    protected void initialize() {
    	
    	
    	Robot.var.gameMessage = DriverStation.getInstance().getGameSpecificMessage();
    	Robot.var.setSwitchScaleSides(); //splits gameMessage into individual message
    	SmartDashboard.putString("OurSwitch", Robot.var.ourSwitchPos+"");
    	SmartDashboard.putString("Scale", Robot.var.ScalePos+"");
    	SmartDashboard.putString("OppSwitch", Robot.var.oppSwitchPos+"");
    	

    	ArrayList<POINT> routeToPerform = Robot.var.defaultRoute;  //default, go cross auto line
    	
    	Robot.var.XYReset(startingPosition.getX(), startingPosition.getY());
    	
    	switch (targetNum){
    	case 0: //our switch
    		if(Robot.var.ourSwitchPos == 'L') {
    			switch (startingPosChar) {
    			case 'L': routeToPerform = Robot.var.route1; break;
    			case 'M': routeToPerform = Robot.var.route13; break;
    			case 'R':routeToPerform = Robot.var.route8; break;
    			}
    		}else if(Robot.var.ourSwitchPos == 'R') {
    			switch (startingPosChar) {
    			case 'L': routeToPerform = Robot.var.route2; break;
    			case 'M': routeToPerform = Robot.var.route14; break;
    			case 'R': routeToPerform = Robot.var.route7; break;
    			}
    		}
    		break; //break for the bigger switch statement
    	case 1: //scale
    		if(Robot.var.ScalePos == 'L') {
    			switch (startingPosChar) {
    			case 'L': routeToPerform = Robot.var.route3; System.out.println("left scale left!!!!");break;
    			case 'M': routeToPerform = Robot.var.route15; break;
    			case 'R': routeToPerform = Robot.var.route10; break;
    			}
    		}else if(Robot.var.ScalePos == 'R') {
    			switch (startingPosChar) {
    			case 'L': routeToPerform = Robot.var.route4; break;
    			case 'M': routeToPerform = Robot.var.route16; break;
    			case 'R': routeToPerform = Robot.var.route9; break;
    			}
    		}
    		break;
    	case 2: //opp switch
    		if(Robot.var.oppSwitchPos == 'L') {
    			switch (startingPosChar) {
    			case 'L': routeToPerform = Robot.var.route5; break;
    			case 'M': routeToPerform = Robot.var.route17; break;
    			case 'R': routeToPerform = Robot.var.route12; break;
    			}
    		}else if(Robot.var.oppSwitchPos == 'R') {
    			switch (startingPosChar) {
    			case 'L': routeToPerform = Robot.var.route6; break;
    			case 'M': routeToPerform = Robot.var.route18; break;
    			case 'R': routeToPerform = Robot.var.route11; break;
    			}
    		}
    		break;
    		
    	}
    	
    	theAuto = new AutoCommandGroup(routeToPerform, targetNum);
    	theAuto.start();
    	
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
    	theAuto.cancel();
    }

    @Override
    protected void interrupted() {
    }
}
