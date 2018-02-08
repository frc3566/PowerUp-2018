package org.usfirst.frc.team3566.robot;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	public class Variables {

		public static final double rotateNonStopSpd = 0.25;

		public static char ourSwitchPos, ScalePos, oppSwitchPos;
		public static String gameMessage;
		
		public static ArrayList<POINT> route1, route2;
		
		public static Collision collision = new Collision();
		
		public double rotateTheta;
		public double distance;
		
		
		public void setUpRoutePoints() {
			route1 = new ArrayList<POINT>();
			route1.add(new POINT(3.5, 1.5));
			route1.add(new POINT(3.5, 14));
			route1.add(new POINT(6.25, 14));
			
	    	route2.add(new POINT(0, 0));
	    	route2.add(new POINT(0, 1000));
	    	route2.add(new POINT(-1000, 1000));
	    	route2.add(new POINT(-1000, 0));
		}
		
		public void SendValuesToDashboard() {

		}
		
		public void updateValues() {
			collision.getTimeSpan();//make sure this is the first to call
			updateXY();
			collision.updateCollide();
			
			SmartDashboard.putNumber("x", x);
			SmartDashboard.putNumber("y", y);
			SmartDashboard.putNumber("theta", getTheta()); 
			
		}
		
		public void setSwitchScaleSides() {
			ourSwitchPos = gameMessage.charAt(0);
			ScalePos = gameMessage.charAt(1);
			oppSwitchPos = gameMessage.charAt(2);
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
		public static double x=0,y=0;
		public static double lastL=0,lastR=0;
		private static double lastTheta=90;
		public static boolean isCollide;
		
		private static void coordinateReset()
		{
			x=0;
	        y=0;
	        lastL=lastR=collision.lastLSpeed=collision.lastRSpeed=0;
	        lastTheta=90;
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
			double curL=Robot.encoderL.getDistance(),curR=Robot.encoderR.getDistance();
			double dis=(curL-lastL+curR-lastR)/2;
			x+= dis* Math.cos(Math.toRadians(theta));
			y+= dis* Math.sin(Math.toRadians(theta));
			lastL=curL;
			lastR=curR;
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
		
		//collide detection
		
	}

