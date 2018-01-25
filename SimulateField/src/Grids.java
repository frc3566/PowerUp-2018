import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Program to draw grids.
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
class POINT {
	double x, y;
	
	POINT(double X, double Y){
		x=X; y=Y;
	}
	
}

class GridsCanvas extends Canvas {
 public int width, height, squareLength, offset, rbLength, rbWidth;
 public double robotX, robotY;
 public char prev_dir='n';
 public POINT prev_point, new_point;
 public ArrayList<ArrayList<POINT>> routes;
 public ArrayList<Color> routeColors;

 public double deltaAngle=0;
 
  int rows; int cols;
  
  int[] XpointsYCoord, YpointsXCoord;
  
  boolean registered = false, routeCapture = false;
  

  GridsCanvas(int w, int h, int r, int c, int singleSquareL, int offSet, int rbL, int rbW) {
	  
    setSize(w, h);
    rows = r;
    cols = c;
    width = w;
    height = h;
    squareLength = singleSquareL;
    offset = offSet;
    robotX = 0; robotY = 0;
    rbLength = rbL;
    rbWidth = rbW;
    
    routes = new ArrayList<ArrayList<POINT>>();
    routeColors = new ArrayList<Color>();
    
    XpointsYCoord= new int[r]; //pointsX[1] would find out what pixel coord is for all (1,y) points (note
    //that the pixel coord is actually y, because field is inverted
    YpointsXCoord= new int[c];
    
  }

  public void paint(Graphics g) {
	  
    int i;

    // draw the rows
    for (i = 0; i < rows; i++) {
      g.drawLine(0+offset, i * squareLength+offset, width+offset, i * squareLength+offset);
      if(!registered) {
      XpointsYCoord[27-i] = i * squareLength+offset;
      }
    }
    

    // draw the columns
    for (i = 0; i < cols; i++) {
      g.drawLine(i * squareLength+offset, 0+offset, i * squareLength+offset, height+offset);
      if(!registered) {
      YpointsXCoord[54-i] = i * squareLength+offset;
      if(i==54) registered = true;
      }
    }
    
    g.setColor(Color.red);
	  g.fillOval(YpointsXCoord[0]-5, XpointsYCoord[0]-5, 10, 10); //Origin
    
    drawThickLine(g,YpointsXCoord[0], XpointsYCoord[2]-squareLength/2, YpointsXCoord[0], XpointsYCoord[24]-squareLength/2,
    		3, Color.blue);
   //right bound
    drawThickLine(g,YpointsXCoord[54], XpointsYCoord[2]-squareLength/2, YpointsXCoord[54], XpointsYCoord[24]-squareLength/2,
    		3, Color.blue);
   //left bound
    drawThickLine(g,YpointsXCoord[2]-squareLength/2, XpointsYCoord[0], YpointsXCoord[0], XpointsYCoord[2]-squareLength/2,
    		3, Color.blue);
    drawThickLine(g,YpointsXCoord[2]-squareLength/2, XpointsYCoord[27], YpointsXCoord[0], XpointsYCoord[24]-squareLength/2,
    		3, Color.blue);
   
    drawThickLine(g,YpointsXCoord[51]-squareLength/2, XpointsYCoord[0], YpointsXCoord[54], XpointsYCoord[2]-squareLength/2,
    		3, Color.blue);
    drawThickLine(g,YpointsXCoord[51]-squareLength/2, XpointsYCoord[27], YpointsXCoord[54], XpointsYCoord[24]-squareLength/2,
    		3, Color.blue);
  
    drawThickLine(g,YpointsXCoord[51]-squareLength/2, XpointsYCoord[0], YpointsXCoord[2]-squareLength/2, XpointsYCoord[0],3, Color.blue);
    drawThickLine(g,YpointsXCoord[51]-squareLength/2, XpointsYCoord[27], YpointsXCoord[2]-squareLength/2, XpointsYCoord[27],3, Color.blue);
  

    drawThickLine(g,YpointsXCoord[10], XpointsYCoord[0], YpointsXCoord[10], XpointsYCoord[27],3,Color.green);
    drawThickLine(g,YpointsXCoord[44], XpointsYCoord[0], YpointsXCoord[44], XpointsYCoord[27],3,Color.green);
    //two auto lines
    
    
    g.setColor(Color.gray);
    g.fillRect(YpointsXCoord[16], XpointsYCoord[10]-squareLength/2, 4*squareLength, 3*squareLength);
    g.fillRect(YpointsXCoord[16], XpointsYCoord[19]-squareLength/2, 4*squareLength, 3*squareLength);
    //our switch
    g.fillRect(YpointsXCoord[42], XpointsYCoord[10]-squareLength/2, 4*squareLength, 3*squareLength);
    g.fillRect(YpointsXCoord[42], XpointsYCoord[19]-squareLength/2, 4*squareLength, 3*squareLength);
    //their switch
    drawThickLine(g,YpointsXCoord[14], XpointsYCoord[10]-squareLength/2, YpointsXCoord[14], 
    		XpointsYCoord[16]-squareLength/2,3,Color.cyan);
    drawThickLine(g,YpointsXCoord[40], XpointsYCoord[10]-squareLength/2, YpointsXCoord[40], 
    		XpointsYCoord[16]-squareLength/2,3,Color.cyan);
   
   
    g.setColor(Color.magenta);
    g.fillRect(YpointsXCoord[29], XpointsYCoord[9], 4*squareLength, 3*squareLength);
    g.fillRect(YpointsXCoord[29], XpointsYCoord[21], 4*squareLength, 3*squareLength);
    drawThickLine(g,YpointsXCoord[27], XpointsYCoord[9], YpointsXCoord[27],
    		XpointsYCoord[18],3,Color.cyan);
    //scale
    
    drawThickLine(g,YpointsXCoord[54], XpointsYCoord[13]-squareLength/2, YpointsXCoord[0],
    		XpointsYCoord[13]-squareLength/2,3,Color.red);
    //horizontal Midline
  
    
    drawThickLine(g,YpointsXCoord[0], XpointsYCoord[8]-squareLength/2, YpointsXCoord[0], XpointsYCoord[12]-squareLength/2,
    		3, Color.YELLOW);
   //right exchange
    drawThickLine(g,YpointsXCoord[54], XpointsYCoord[18]-squareLength/2, YpointsXCoord[54], XpointsYCoord[14]-squareLength/2,
    		3, Color.YELLOW);
   //left exchange
    
    
    Graphics2D g2d = (Graphics2D)g;
    g2d.setColor(Color.orange);
    int rectX = (int)(YpointsXCoord[(int)(robotY-robotY%1)]-rbLength/2-(robotY%1)*squareLength);
    int rectY = (int)(XpointsYCoord[(int)(robotX-robotX%1)]-rbWidth/2-(robotX%1)*squareLength);
    g2d.rotate(deltaAngle, rectX+rbLength/2, rectY+rbWidth/2);
    Rectangle rect = new Rectangle(rectX, rectY, 
    		rbLength, rbWidth);
    g2d.draw(rect);
    g2d.fill(rect);
    g2d.setColor(Color.green);
    g2d.fillOval((int)(YpointsXCoord[(int)(robotY-robotY%1)]-3-(robotY%1)*squareLength), 
                             (int)(XpointsYCoord[(int)(robotX-robotX%1)]-3-(robotX%1)*squareLength), 6, 6);
    //robot
    
    g2d.rotate(-deltaAngle, rectX+rbLength/2, rectY+rbWidth/2);
    g.setColor(Color.black);
    g.drawString("X: "+Double.toString(robotX), YpointsXCoord[(int)robotY]-3, XpointsYCoord[(int)robotX]-15);
    g.drawString("Y: "+Double.toString(robotY), YpointsXCoord[(int)robotY]-3, XpointsYCoord[(int)robotX]+20);
    //print out coords of robot
    
    
    for(int r=0; r<routes.size(); r++){
    	drawOneRoute(routes.get(r), g, routeColors.get(r));
    }
    //drawing the route by connecting the points
    
  }
  
  public void drawOneRoute(ArrayList<POINT> route, Graphics g, Color myColor){
	  for(int p=0; p<route.size()-1; p++){
	    	double fpx = YpointsXCoord[(int)(route.get(p).y - (route.get(p).y % 1))]-
	    			(route.get(p).y%1)*squareLength;
	    	double fpy = XpointsYCoord[(int)(route.get(p).x - (route.get(p).x % 1))]-
	    			(route.get(p).x%1)*squareLength;
	    	double spx = YpointsXCoord[(int)(route.get(p+1).y - (route.get(p+1).y % 1))]-
	    			(route.get(p+1).y%1)*squareLength;
	    	double spy = XpointsYCoord[(int)(route.get(p+1).x - (route.get(p+1).x % 1))]-
	    			(route.get(p+1).x%1)*squareLength;
	    	
	    	drawThickLine(g, (int)fpx, (int)fpy, (int)spx, (int)spy, 5, myColor);
	    }
  }
  
  public void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness, Color c) {
		  // The thick line is in fact a filled polygon
		  g.setColor(c);
		  int dX = x2 - x1;
		  int dY = y2 - y1;
		  // line length
		  double lineLength = Math.sqrt(dX * dX + dY * dY);

		  double scale = (double)(thickness) / (2 * lineLength);

		  // The x,y increments from an endpoint needed to create a rectangle...
		  double ddx = -scale * (double)dY;
		  double ddy = scale * (double)dX;
		  ddx += (ddx > 0) ? 0.5 : -0.5;
		  ddy += (ddy > 0) ? 0.5 : -0.5;
		  int dx = (int)ddx;
		  int dy = (int)ddy;

		  // Now we can compute the corner points...
		  int xPoints[] = new int[4];
		  int yPoints[] = new int[4];

		  xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
		  xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
		  xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
		  xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

		  g.fillPolygon(xPoints, yPoints, 4);
		  }

  public void addPointsToRoute(ArrayList<POINT> route, POINT point){
	  if(routeCapture){
	  route.add(point);
	  }
  }
  
  public void replaceLastPointOnRoute(ArrayList<POINT> route, POINT point){
	  if(routeCapture){
	  route.set(route.size()-1, point);
	  }
  }
  
  public void writePoints(){
	  
	  File newFile = new File("./routePoints"+LocalDateTime.now()+".txt");
	  try {
		newFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
		for(ArrayList<POINT> route: routes){
		writer.write("START\n\n");
		for(POINT p:route){
		writer.write("X: "+p.x+" Y: "+p.y+"\n");
		}
		writer.write("\n\nDONE\n\n\n");
		}
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
	  
	  
  }
  
}

/** This is the demo class. */

public class Grids extends Frame implements KeyListener{
  /*
   * Construct a GfxDemo2 given its title, width and height. Uses a
   * GridBagLayout to make the Canvas resize properly.
   */
	public static Grids d;
	public static GridsCanvas xyz;
	public static NetworkTable nt;
	
  Grids(String title, int w, int h, int rows, int cols, int sqrL, int rbL, int rbW) {
    setTitle(title);
    toFront();
    requestFocus();
    addKeyListener(this);
    // Now create a Canvas and add it to the Frame.
    xyz = new GridsCanvas(w, h, rows, cols, sqrL, 25, rbL, rbW); 
    //rb Length and Width in pixels, and 25 pixel offset
    add(xyz);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        setVisible(false);
        dispose();
        System.exit(0);
      }
    });

    // Normal end ... pack it up!
    pack();
    
   
  }

  public static void main(String[] a) {
	  String OS;
	  OS = System.getProperty("os.name");
	  if (OS.startsWith("Windows")) {
		  d = new Grids("Field", 1800, 1200, 28, 55, 30, 80, 70);
	  }else{
    d = new Grids("Field", 1250, 750, 28, 55, 20, 53, 47);
	  }
    d.setVisible(true);
    d.requestFocus();
    
  }

  public final double getRobotX() {
  	  return xyz.robotX;
    }
  
  public final double getRobotY() {
  	  return xyz.robotY;
    }
  
@Override
public void keyTyped(KeyEvent e) {
}

@Override
public void keyPressed(KeyEvent e) {
	switch(e.getKeyCode()) {
	case KeyEvent.VK_UP:
		if(getRobotX()<27) {
			if((xyz.prev_dir!='x')){//changes direction or start
			xyz.prev_point = new POINT(xyz.robotX, xyz.robotY); //record the turning point
			xyz.robotX+=0.25;
			xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
			if(xyz.routeCapture){
				xyz.prev_dir = 'x';
				xyz.addPointsToRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
			}
			}else{//if not, keep the original turning point and go on, only setting the new_point
			xyz.robotX+=0.25;
			xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
			if(xyz.routeCapture)
			xyz.replaceLastPointOnRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
			}
			
		}
		break;
	case KeyEvent.VK_DOWN:
		if(getRobotX()>0) {
			if(xyz.prev_dir!='x'){//changes direction or start
				xyz.prev_point = new POINT(xyz.robotX, xyz.robotY); //record the turning point
				xyz.robotX-=0.25;
				xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
				if(xyz.routeCapture){
					xyz.prev_dir = 'x';
				xyz.addPointsToRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
				}
				}else{//if not, keep the original turning point and go on, only setting the new_point
				xyz.robotX-=0.25;
				xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
				if(xyz.routeCapture)
			    xyz.replaceLastPointOnRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
				}
		}
		break;
	case KeyEvent.VK_LEFT:
		if(getRobotX()<54) {
			if(xyz.prev_dir!='y'){//changes direction or start
				xyz.prev_point = new POINT(xyz.robotX, xyz.robotY); //record the turning point
				xyz.robotY+=0.25;
				xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
				if(xyz.routeCapture){
				xyz.prev_dir = 'y';
				xyz.addPointsToRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
				}
				}else{//if not, keep the original turning point and go on, only setting the new_point
				xyz.robotY+=0.25;
				xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
				if(xyz.routeCapture)
				xyz.replaceLastPointOnRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
				}
		}
		break;
	case KeyEvent.VK_RIGHT:
		if(getRobotX()>0) {
			if(xyz.prev_dir!='y'){//changes direction or start
				xyz.prev_point = new POINT(xyz.robotX, xyz.robotY); //record the turning point
				xyz.robotY-=0.25;
				xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
				if(xyz.routeCapture){
				xyz.prev_dir = 'y';
				xyz.addPointsToRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
				}
				}else{//if not, keep the original turning point and go on, only setting the new_point
				xyz.robotY-=0.25;
				xyz.new_point = new POINT(xyz.robotX, xyz.robotY);
				if(xyz.routeCapture)
				xyz.replaceLastPointOnRoute(xyz.routes.get(xyz.routes.size()-1), xyz.new_point);
				}
		}
		break;
	case KeyEvent.VK_SPACE:
		int Lholder = xyz.rbLength;
		xyz.rbLength = xyz.rbWidth;
		xyz.rbWidth = Lholder;
		//swaps length and width; equal to rotating robot
		break;
	case KeyEvent.VK_R:
		xyz.routeCapture = !xyz.routeCapture;
		if(xyz.routeCapture){
			xyz.routes.add(new ArrayList<POINT>());
			xyz.routeColors.add(Color.getHSBColor((float)Math.random(), 
					(float)Math.random(), (float)Math.random()));
			xyz.routes.get(xyz.routes.size()-1).add(new POINT(xyz.robotX,xyz.robotY));
			System.out.println("capture start: "+xyz.routes.size()+" "+
					xyz.routes.get(xyz.routes.size()-1).size());
		}else{
			System.out.println("capture finish: "+xyz.routes.size()+" "+
					xyz.routes.get(xyz.routes.size()-1).size());
			xyz.prev_point = null;
			xyz.new_point = null;
			xyz.prev_dir = 'n';
		}
		break;
	case KeyEvent.VK_W:
		xyz.writePoints();
		break;
	case KeyEvent.VK_Z:
		xyz.deltaAngle+=Math.toRadians(30);
		break;
	default:
		System.out.println("not sure what you want to do, but apparently you pressed a key.");
		break;
	}
	
	xyz.repaint();
}

@Override
public void keyReleased(KeyEvent e) {
	
}

} //end of demo class