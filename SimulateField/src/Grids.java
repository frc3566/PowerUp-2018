import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Program to draw grids.
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
class GridsCanvas extends Canvas {
  int width, height, squareLength, offset;

  int rows; int cols;
  
  int[] XpointsYCoord, YpointsXCoord;
  
  boolean[][] points;
  
  boolean registered = false;

  GridsCanvas(int w, int h, int r, int c, int singleSquareL, int offSet) {
	  
    setSize(w, h);
    rows = r;
    cols = c;
    width = w;
    height = h;
    squareLength = singleSquareL;
    offset = offSet;
    
    
    XpointsYCoord= new int[r]; //pointsX[1] would find out what pixel coord is for all (1,y) points (note
    //that the pixel coord is actually y, because field is inverted
    YpointsXCoord= new int[c];
    
    points = new boolean[r][c];
    
    points[0][0] = true;
    
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
    
    for (int c = 0; c < cols; c++) {
    	  for (int r = 0; r < rows; r++) {
    		  if (points[r][c] == true) {
    			  g.setColor(Color.red);
    			  g.fillOval(YpointsXCoord[c]-5, XpointsYCoord[r]-5, 10, 10);
    			  
    		  }
    	  }
    }
    
    
    drawThickLine(g, YpointsXCoord[0], XpointsYCoord[2]-squareLength/2, YpointsXCoord[0], XpointsYCoord[24]-squareLength/2,
    		3, Color.blue);
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
    drawThickLine(g,YpointsXCoord[14], XpointsYCoord[10]-squareLength/2, YpointsXCoord[14], XpointsYCoord[16]-squareLength/2,3,Color.red);
    drawThickLine(g,YpointsXCoord[40], XpointsYCoord[10]-squareLength/2, YpointsXCoord[40], XpointsYCoord[16]-squareLength/2,3,Color.red);
   
   
  
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

public class Grids extends Frame {
  /*
   * Construct a GfxDemo2 given its title, width and height. Uses a
   * GridBagLayout to make the Canvas resize properly.
   */
  Grids(String title, int w, int h, int rows, int cols, int sqrL) {
    setTitle(title);

    // Now create a Canvas and add it to the Frame.
    GridsCanvas xyz = new GridsCanvas(w, h, rows, cols, sqrL, 25);
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
    new Grids("Field", 1800, 1200, 28, 55, 30).setVisible(true);
  }
}