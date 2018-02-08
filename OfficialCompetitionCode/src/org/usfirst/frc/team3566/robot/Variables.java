package org.usfirst.frc.team3566.robot;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	public class Variables {

		public static double rotateNonStopSpd = 0.25;

		public static char ourSwitchPos, ScalePos, oppSwitchPos;
		public static String gameMessage;
		
		public static ArrayList<POINT> route1;
		
		public void setUpRoutePoints() {
			route1 = new ArrayList<POINT>();
			route1.add(new POINT(3.5, 1.5));
			route1.add(new POINT(3.5, 14));
			route1.add(new POINT(6.25, 14));
		}
		
		public void SendValuesToDashboard() {

		}
		
		public void updateValues() {

			
		}
		
		public void setSwitchScaleSides() {
			ourSwitchPos = gameMessage.charAt(0);
			ScalePos = gameMessage.charAt(1);
			oppSwitchPos = gameMessage.charAt(2);
		}
	}

