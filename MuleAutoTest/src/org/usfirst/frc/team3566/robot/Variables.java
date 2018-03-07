package org.usfirst.frc.team3566.robot;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	public class Variables {
		//this is calculate
		public static boolean isFinalTurn=false,isFinalTurnFinish=false;
		public double rotateTheta;//this two are important for driveStraight and rotate
		public double distance,allowedDriveError=150;
		public boolean isJustGo;
		//calculate ends
		
		public static int eleToPosCnt=0;
		public static final double rotateNonStopSpd = 0.5;
		public static double BPU_PICKUP_SPD=0.4;
		
		public static char ourSwitchPos, ScalePos, oppSwitchPos;
		public static String gameMessage;
		
		public static ArrayList<POINT> route1, route2, route3, route4, route5, route6,
		route7, route8, route9, route10, route11, route12, route13, route14, route15, route16, route17,
		route18, defaultRoute, testRoute;  
		/*while route 19 is default (crossing auto), each six routes is for one of the three starting positions
		note that the order is startingPos==left for route1-6 (LRLRLR), right for route7-12 (RLRLRL), 
		middle for route 13-18 (LRLRLR)
		*/
		public static Collision collision = new Collision();
		/*
		 * light values: SOLID COLORS: 0.61 red, 0.64 yellow, 0.77 green, 0.87 blue, 0.91 purple, 0.93 white
		 * -0.99 rainbow; -0.41 ocean; 
		 * 
		 */
		public static final double red = 0.61, yellow = 0.64, green = 0.77, blue = 0.87, purple = 0.91, white = 0.93,
				rainbow = -0.99, ocean = -0.41;
		
		
		
		//those points are delivery destinations
		public static final POINT ourSwitchLeftSide = new POINT(6.25, 14.0, 0),
				ourSwitchLeftBack = new POINT(9.0, 18.5, 270), ourSwitchRightSide = new POINT(20.75, 14.0, 180),
				ourSwitchRightBack = new POINT(18.0, 18.5, 270), scaleLeftSide = new POINT(4.25, 27.0, 0),
				scaleRightFront = new POINT(19.5, 23.5, 90), scaleRightSide = new POINT(22.75, 27.0, 180),
				scaleLeftFront = new POINT(7.5, 23.5, 90), oppSwitchLeftSide = new POINT(6.25, 40.0, 0),
				oppSwitchRightFront = new POINT(17.75, 36.75, 90), oppSwitchRightSide=new POINT(20.75, 40.0, 180),
				oppSwitchLeftFront = new POINT(8.75, 36.75, 90), ourSwitchLeftFront = new POINT(9.0, 10.5, 90),
				ourSwitchRightFront = new POINT(18.0, 10.5, 90), midStartBranchPoint = new POINT(14.5, 6.25);
		
		public Variables() {
			setUpRoutePoints();
		}
		
		public void setUpRoutePoints() {
			
			testRoute = new ArrayList<POINT>();
			testRoute.add(Robot.leftStart);
			testRoute.add(new POINT(0,0,-1));
			testRoute.add(new POINT(0,4,-1));
			testRoute.add(new POINT(4,4,-1));
			testRoute.add(new POINT(4,0,-1));
			testRoute.add(new POINT(0,0,-1));
			testRoute.add(new POINT(0,4,-1));
			testRoute.add(new POINT(4,4,-1));
			testRoute.add(new POINT(4,0,-1));
			testRoute.add(new POINT(0,0,-1));
			testRoute.add(new POINT(0,4,-1));
			testRoute.add(new POINT(4,4,-1));
			testRoute.add(new POINT(4,0,-1));
			testRoute.add(new POINT(0,0,-1));
			
			defaultRoute = new ArrayList<POINT>();	//default route is indistinguishable between starting points
			defaultRoute.add(new POINT(13.5, 13));
			
			
			route1 = new ArrayList<POINT>();	//Starting from left; switch left (CHECK: definitely works)
			route1.add(Robot.leftStart);
			route1.add(new POINT(3.75, 14.0));
			//turn right
			route1.add(ourSwitchLeftSide);
			
			route2 = new ArrayList<POINT>();	//left switch right
			route2.add(Robot.leftStart);
			route2.add(new POINT(3.75, 19));
			//turn right
			//route2.add(new POINT(18.0, 17.75));		transition point cancelled for now
			//turn right
			route2.add(ourSwitchRightBack);
			
			route3 = new ArrayList<POINT>();
	    	route3.add(Robot.leftStart);	//left scale left
	    	//route3.add(new POINT(3, 20)); //added point
	    	route3.add(new POINT(1.5, 17.0)); //3 changed to 2
	    	route3.add(scaleLeftSide);

	    	route4 = new ArrayList<POINT>();
	    	route4.add(Robot.leftStart);	//left scale right
	    	route4.add(new POINT(3.75, 20.5));
	    	//route4.add(new POINT(19.5, 20.5));    cancelled to save time
	    	route4.add(scaleRightFront);
	    	
	    	route5 = new ArrayList<POINT>();
	    	route5.add(Robot.leftStart);	//left OppSwitch left
	    	route5.add(new POINT(3.75, 20));//added
	    	route5.add(new POINT(3, 40.0));
	    	route5.add(oppSwitchLeftSide);

	    	route6 = new ArrayList<POINT>();
	    	route6.add(Robot.leftStart);	//left OppSwitch right
	    	route6.add(new POINT(3.75, 20));//added
	    	route6.add(new POINT(3.75, 33.0));
	    	route6.add(new POINT(17.75, 33.0));
	    	route6.add(oppSwitchRightFront);
	    	
	    	//DONE with starting from the left

			route7 = new ArrayList<POINT>();	//Starting from right; switch right
			route7.add(Robot.rightStart);
			route7.add(new POINT(23.5, 14.0));
			//turn right
			route7.add(ourSwitchRightSide);
			
			route8 = new ArrayList<POINT>();	//right switch left
			route8.add(Robot.rightStart);
			route8.add(new POINT(23.5, 19));
			//turn right
			//route8.add(new POINT(9, 19));
			//turn right
			route8.add(ourSwitchLeftBack);
			
			route9 = new ArrayList<POINT>();
	    	route9.add(Robot.rightStart);	//right scale right
	    //	route9.add(new POINT(23.5, 20));//added
	    	route9.add(new POINT(25.25, 17.0)); //23.5 changed to 25.25
	    	route9.add(scaleRightSide);

	    	route10 = new ArrayList<POINT>();
	    	route10.add(Robot.rightStart);	//right scale left
	    	route10.add(new POINT(23.5, 20.75));
	    	route10.add(new POINT(7.5, 20.75));
	    	route10.add(scaleLeftFront);
	    	
	    	route11 = new ArrayList<POINT>();
	    	route11.add(Robot.rightStart);	//right OppSwitch right
	    	route11.add(new POINT(23.5, 20));
	    	route11.add(new POINT(23.5, 40.0));
	    	route11.add(oppSwitchRightSide);

	    	route12 = new ArrayList<POINT>();
	    	route12.add(Robot.rightStart);	//right OppSwitch left
	    	route12.add(new POINT(23.5, 20));
	    	route12.add(new POINT(23.5, 33.75));
	    	route12.add(new POINT(8.75, 33.75));
	    	route12.add(oppSwitchLeftFront);
	    	
	    	//DONE with starting from the right
	    	
	    
			route13 = new ArrayList<POINT>();	//Starting from middle; switch left
			route13.add(Robot.middleStart);
			route13.add(midStartBranchPoint);
			//turn right
			route13.add(new POINT(9.0, 6.25));
			route13.add(ourSwitchLeftFront);
			
			route14 = new ArrayList<POINT>();	//middle switch right
			route14.add(Robot.middleStart);
			route14.add(midStartBranchPoint);
			//turn right
			route14.add(new POINT(18.0, 6.25));
			//turn right
			route14.add(ourSwitchRightFront);
			
			route15 = new ArrayList<POINT>();
	    	route15.add(Robot.middleStart);	//middle scale left
	    	route15.add(midStartBranchPoint);
	    	route15.add(new POINT(2.75, 6.25));
	    	route15.add(new POINT(2.75, 27.0));
	    	route15.add(scaleLeftSide);

	    	route16 = new ArrayList<POINT>();
	    	route16.add(Robot.middleStart);	//middle scale right
	    	route16.add(midStartBranchPoint);
	    	route16.add(new POINT(23.75, 6.25));
	    	route16.add(new POINT(23.75, 27.0));
	    	route16.add(scaleRightSide);
	    	
	    	route17 = new ArrayList<POINT>();
	    	route17.add(Robot.middleStart);	//middle OppSwitch left
	    	route17.add(midStartBranchPoint);
	    	route17.add(new POINT(2.75, 6.25));
	    	route17.add(new POINT(2.75, 20));
	    	route17.add(new POINT(2.75, 40));
	    	route17.add(oppSwitchLeftSide);

	    	route18 = new ArrayList<POINT>();
	    	route18.add(Robot.middleStart);	//middle OppSwitch right
	    	route18.add(midStartBranchPoint);
	    	route18.add(new POINT(23.75, 6.25));
	    	route18.add(new POINT(23.75, 20));
	    	route18.add(new POINT(23.75, 40));
	    	route18.add(oppSwitchRightSide);
	    	
	    	//DONE with starting from the middle
		}
		
		public void updateValues() {
			collision.getTimeSpan();//make sure this is the first to call
			updateXY();
			collision.updateCollide();
			
			SmartDashboard.putNumber("x", getX());
			SmartDashboard.putNumber("y", getY());
			SmartDashboard.putNumber("theta", getTheta()); 
			SmartDashboard.putNumber("encoderL", Robot.encoderL.getDistance());
			SmartDashboard.putNumber("encoderR", Robot.encoderR.getDistance());
//			SmartDashboard.putBoolean("isCollide", collision.isCollide);
//			SmartDashboard.putNumber("elev", Robot.elevator.elevatorEncoder.getValue());
		}
		
		public void setSwitchScaleSides() {
			System.out.println("GameMessage!:"+gameMessage);
			char[] chars = gameMessage.toCharArray();
			
			ourSwitchPos = chars[0];
			ScalePos = chars[1];
			oppSwitchPos = chars[2];
		}
		
		public void reset()
		{
			RobotMap.pigeon.setYaw(0, 0);
	        Robot.encoderL.reset();
	        Robot.encoderR.reset();
	        
	        collision.collideReset();
	        coordinateReset();
	        collision.lastTime=0;
	        
	        Robot.time.reset();
	        Robot.time.start();
		}
		
		public double getEncoder()
		{
			return (Robot.encoderL.getDistance()+Robot.encoderR.getDistance())/2;
		}
		
		//coordinate system
		private static double x=0,y=0;
		private static double lastL=0,lastR=0;
		private static void coordinateReset()
		{
			setX(0);
			setY(0);
	        lastL=lastR=collision.lastLSpeed=collision.lastRSpeed=0;
		}
		public static void XYReset(double X, double Y)
		{
			System.out.printf("this is xyReset x:%.1f y:%.1f\n",x,y);
			coordinateReset();
			setX(X);
			setY(Y);
		}
		/* return the degree in the polar system, range [0,360), add 90 degrees to make polar
		 */
		public static double getTheta() {
	    	double[] ypr = new double[3];
	    	RobotMap.pigeon.getYawPitchRoll(ypr);
	    	return ((ypr[0]%360)+360+90)%360;
	    }
		/* update the x,y value according to theta angle and lastEncoderDis
		 * please change lastEncoderDis when first call this method
		 */
		private static void updateXY()
		{
			double theta=getTheta();
			double curL=Robot.encoderL.getDistance(),curR=Robot.encoderR.getDistance();//Robot.encoderR.getDistance();
			double dis=(curL-lastL+curR-lastR)/2;
			dis = dis / 304.8;		//convert to ft, so that x,y is in ft
			x+= dis* Math.cos(Math.toRadians(theta));
			y+= dis* Math.sin(Math.toRadians(theta));
			lastL=curL;
			lastR=curR;
		}
		public static void setX(double _x)
		{
			switch(Robot.encoderState)
			{
				case Both: x=_x; break;
				case Left: x=_x-1*Math.sin(Math.toRadians(getTheta())); break;
				default: x=_x+1*Math.sin(Math.toRadians(getTheta())); break;
			}
		}
		public static void setY(double _y)
		{
			switch(Robot.encoderState)
			{
				case Both: y=_y;
				case Left: y=_y+1*Math.cos(Math.toRadians(getTheta()));
				default: y=_y-1*Math.cos(Math.toRadians(getTheta()));
			}
		}
		public static double getX()
		{
			switch(Robot.encoderState)
			{
				case Both: return x;
				case Left: return x+1*Math.sin(Math.toRadians(getTheta()));
				default: return x-1*Math.sin(Math.toRadians(getTheta()));
			}
		}
		public static double getY()
		{
			switch(Robot.encoderState)
			{
				case Both: return y;
				case Left: return y-1*Math.cos(Math.toRadians(getTheta()));
				default: return y+1*Math.cos(Math.toRadians(getTheta()));
			}
		}
		/* return the theta angle in degree of the vector (_x,_y) in the polar system, range[0,360)
		 * try not to call if x==0 && y==0, that may cause problematic rotation
		 */
		public static double getVectorDegree(double _x,double _y)
		{
			if(_x==0)return _y>0? 90:270;
			if(_y==0)return _x>0? 0:180;
			double degree=Math.toDegrees(Math.atan(_y/_x));
			if(_x<0)degree+=180;
			else if(_x>0 && _y<0)degree+=360;
			return degree;
		}
}
