import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Program to draw grids.
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
class GridsCanvas extends Canvas {
 public int width, height, squareLength, offset, rbLength, rbWidth;
 public double robotX, robotY;

  int rows; int cols;
  
  int[] XpointsYCoord, YpointsXCoord;
  
  boolean registered = false;
  

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
    
    g.setColor(Color.orange);
    g.fillRect((int)(YpointsXCoord[(int)robotY]-rbLength/2-(robotY%1)*30), 
    		(int)(XpointsYCoord[(int)robotX]-rbWidth/2-(robotX%1)*30), rbLength, rbWidth);
    g.setColor(Color.green);
    g.fillOval((int)(YpointsXCoord[(int)robotY]-3-(robotY%1)*30), 
                             (int)(XpointsYCoord[(int)robotX]-3-(robotX%1)*30), 6, 6);
    //robot
    
    g.setColor(Color.black);
    g.drawString("X: "+Double.toString(robotX), YpointsXCoord[(int)robotY]-3, XpointsYCoord[(int)robotX]-15);
    g.drawString("Y: "+Double.toString(robotY), YpointsXCoord[(int)robotY]-3, XpointsYCoord[(int)robotX]+20);
    //print out coords of robot
    
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

}

/** This is the demo class. */

public class Grids extends Frame implements KeyListener{
  /*
   * Construct a GfxDemo2 given its title, width and height. Uses a
   * GridBagLayout to make the Canvas resize properly.
   */
	public static Grids d;
	public static GridsCanvas xyz;
	
  Grids(String title, int w, int h, int rows, int cols, int sqrL, int rbL, int rbW) {
    setTitle(title);
    
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
			xyz.robotX+=0.5;
		}
		break;
	case KeyEvent.VK_DOWN:
		if(getRobotX()>0) {
			xyz.robotX-=0.5;
		}
		break;
	case KeyEvent.VK_LEFT:
		if(getRobotX()<54) {
			xyz.robotY+=0.5;
		}
		break;
	case KeyEvent.VK_RIGHT:
		if(getRobotX()>0) {
			xyz.robotY-=0.5;
		}
		break;
	case KeyEvent.VK_SPACE:
		int Lholder = xyz.rbLength;
		xyz.rbLength = xyz.rbWidth;
		xyz.rbWidth = Lholder;
		//swaps length and width; equal to rotating robot
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