package org.usfirst.frc.team3566.robot;
public class POINT {
	double x, y; //in own coordinates
	private double theta;
	
	public POINT(double X, double Y){
		x=X; y=Y;
		theta=-1;
	}
	public POINT(double _x,double _y,double _theta)
	{
		x=_x;y=_y;theta=_theta;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getTheta() {
		return theta;
	}
}

