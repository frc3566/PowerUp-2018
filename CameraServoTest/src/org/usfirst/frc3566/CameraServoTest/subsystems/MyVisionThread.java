package org.usfirst.frc3566.CameraServoTest.subsystems;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class MyVisionThread extends Thread{
	
	UsbCamera camMain;
	CameraSubsystem myCameraSystem;
	CvSink cvSink;
	CvSource outputStream;
	Mat mat;
	//
	
	public MyVisionThread(CameraSubsystem which) {
	
		myCameraSystem = which;
	// Get the UsbCamera from CameraServer
		camMain = CameraServer.getInstance().startAutomaticCapture();
		// Set the resolution
		camMain.setResolution(640, 480);
		camMain.setFPS(20);

		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();

		// This cannot be 'true'. The program will never exit if it is. This
		// lets the robot stop this thread when restarting robot code or
		// deploying.
		/*
		while (!Thread.interrupted()) {
			//System.out.println("Hi from the thread");
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat.  If there is an error notify the output.
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			}
			// Put a rectangle on the image
			Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
					new Scalar(255, 255, 255), 5);
			// Give the output stream a new image to display
			
		    if(myCameraSystem.getCurrentPosition() < 80.0) {
		    	
		    	Mat flippedMat = new Mat();
		    	
		    	Core.flip(mat, flippedMat, 0);
		    	
		    	outputStream.putFrame(flippedMat);
		    } else {
			
		    	outputStream.putFrame(mat);
		    }
		}
	
	//this.setDaemon(true);
	//this.start();
	 *
	 */
	}
	
	public void start() {
		while (!Thread.interrupted()) {
			//System.out.println("Hi from the thread");
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat.  If there is an error notify the output.
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			}
			// Put a rectangle on the image
			Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
					new Scalar(255, 255, 255), 5);
			// Give the output stream a new image to display
			
		    if(myCameraSystem.getCurrentPosition() < 80.0) {
		    	
		    	Mat flippedMat = new Mat();
		    	
		    	Core.flip(mat, flippedMat, 0);
		    	
		    	outputStream.putFrame(flippedMat);
		    } else {
			
		    	outputStream.putFrame(mat);
		    }
		}
		
		
		
		
		
		
	}
	  
}


