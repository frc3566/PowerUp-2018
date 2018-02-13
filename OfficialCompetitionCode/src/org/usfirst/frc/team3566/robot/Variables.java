package org.usfirst.frc.team3566.robot;

import java.util.ArrayList;

import org.usfirst.frc.team3566.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	public class Variables {

		public static final double rotateNonStopSpd = 0.5;

		public static char ourSwitchPos, ScalePos, oppSwitchPos;
		public static String gameMessage;
		
		public static ArrayList<POINT> route1, route2, route3, route4, route5, route6,
		route7, route8, route9, route10, route11;
		
		public static Collision collision = new Collision();
		
		public double rotateTheta;
		public double distance;
		
		public Variables() {
			setUpRoutePoints();
		}
		
		public void setUpRoutePoints() {
			
			route1 = new ArrayList<POINT>();	//left switch left
			route1.add(new POINT(3.75, 1.5));
			route1.add(new POINT(3.75, 14.0));
			route1.add(new POINT(6.25, 14.0));
			
			route2 = new ArrayList<POINT>();
	    	route2.add(new POINT(3.75, 1.5));	//left scale left
	    	route2.add(new POINT(3.75, 27.0));
	    	route2.add(new POINT(4.75, 27.0));

	    	route3 = new ArrayList<POINT>();
	    	route3.add(new POINT(3.75, 1.5));	//left both right, to post 
	    	route3.add(new POINT(10.5, 1.5));
	    	//deliver
	    	route3.add(new POINT(10.5, 10.0));  ////go to auto line
	    	
	    	route4 = new ArrayList<POINT>();
	    	route4.add(new POINT(23.25, 1.5));	//right switch right
	    	route4.add(new POINT(23.25, 14.0));
	    	route4.add(new POINT(20.75, 14.0));
	    	
	    	route5 = new ArrayList<POINT>();
	    	route5.add(new POINT(23.25, 1.5));	//right scale right
	    	route5.add(new POINT(23.25, 27.0));
	    	route5.add(new POINT(22.25, 27.0));
	    	
	    	route6 = new ArrayList<POINT>();
	    	route6.add(new POINT(23.25, 1.5));	//right both left
	    	route6.add(new POINT(10.5, 1.5));
	    	//deliver
	    	route6.add(new POINT(10.5, 10));   ////to auto line
	    	
	    	route7 = new ArrayList<POINT>();
	    	route7.add(new POINT(14.5, 1.5));	//mid scale right
	    	route7.add(new POINT(14.5, 4.25));
	    	route7.add(new POINT(23.5, 4.25));
	    	route7.add(new POINT(23.5, 27.0));
	    	route7.add(new POINT(22.25, 27.0));
	    	
	    	route8 = new ArrayList<POINT>();
	    	route8.add(new POINT(14.5, 1.5));	//mid scale left
	    	route8.add(new POINT(14.5, 4.25));
	    	route8.add(new POINT(3.5, 4.25));
	    	route8.add(new POINT(3.5, 27.0));
	    	route8.add(new POINT(4.75, 27.0));
	    	
	    	route9 = new ArrayList<POINT>();
	    	route9.add(new POINT(14.5, 1.5));	//mid switch right
	    	route9.add(new POINT(14.5, 4.5));
	    	route9.add(new POINT(21.25, 4.5));
	    	route9.add(new POINT(21.25, 14.0));
	    	route9.add(new POINT(20.75, 14.0));
	    	
	    	route10 = new ArrayList<POINT>();
	    	route10.add(new POINT(14.5, 1.5));	//mid switch left
	    	route10.add(new POINT(14.5, 4.75));
	    	route10.add(new POINT(5.75, 4.75));
	    	route10.add(new POINT(5.75, 14.0));
	    	route10.add(new POINT(6.25, 14.0));
	    	
	    	route11 = new ArrayList<POINT>();
	    	route11.add(new POINT(14.5, 1.5));	//to the post
	    	route11.add(new POINT(10.5, 1.5));
	    	route11.add(new POINT(10.5, 10.0));
		}
		
		public void updateValues() {
			collision.getTimeSpan();//make sure this is the first to call
			updateXY();
			collision.updateCollide();
			
			SmartDashboard.putNumber("x", x);
			SmartDashboard.putNumber("y", y);
			SmartDashboard.putNumber("theta", getTheta()); 
			SmartDashboard.putNumber("encoderL", Robot.encoderL.getDistance());
		}
		
		public void setSwitchScaleSides() {
			System.out.println("GameMessage!:"+gameMessage+gameMessage.indexOf('L'));
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
			return (Robot.encoderL.getDistance()+Robot.encoderR.getDistance())/2;//Robot.encoderR.getDistance())/2;
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
		
		public static void XYReset(double X, double Y)
		{
			coordinateReset();
			x=X;
	        y=Y;
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

