
import biuoop.GUI;
import src.Line;
import src.Point;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class SimpleGuiExample {

  public void drawRandomCircles() {
    Random rand = new Random(); // create a random-number generator
    // Create a window with the title "Random Circles Example"
    // which is 400 pixels wide and 300 pixels high. 
    GUI gui = new GUI("Random Circles Example", 400, 300);
    DrawSurface d = gui.getDrawSurface();
    for (int i = 0; i < 10; ++i) {
       int x = rand.nextInt(400) + 1; // get integer in range 1-400
       int y = rand.nextInt(300) + 1; // get integer in range 1-300
       int r = 5*(rand.nextInt(4) + 1); // get integer in 5,10,15,20
       d.setColor(Color.RED);
       d.fillCircle(x,y,r);
    }
    gui.show(d);
  }

  public void drawRandomLines() {
    Random rand = new Random();
    GUI gui = new GUI("Random Lines", 800, 600);
    DrawSurface d = gui.getDrawSurface();
    Line[] lines = new Line[10] ;
    
    for (int i = 0; i < 10; ++i) {
      int x1 = rand.nextInt(800) + 1;
      int y1 = rand.nextInt(600) + 1;
      int x2 = rand.nextInt(800) + 1;
      int y2 = rand.nextInt(600) + 1;
      
      lines[i] = new Line(x1, y1, x2, y2);
      
      d.setColor(Color.BLACK);
      d.drawLine(x1, y1, x2, y2);
      d.setColor(Color.BLUE);
      d.fillCircle((int) lines[i].middle().getX(), (int) lines[i].middle().getY(), 3);

      System.out.println("Line " + (i + 1) + ": (" + lines[i].start().getX() + ", " + lines[i].start().getY() + "), (" + lines[i].end().getX() + ", " + lines[i].end().getY() + ")");
      System.out.println("M: " + lines[i].getSlope());

      for (Line line : lines) {
        if (line == null) {
          break;
        }

        if (!lines[i].equals(line)) {
          Point interPoint = lines[i].intersectionWith(line);
          if(interPoint != null) {
            d.setColor(Color.RED);
            d.fillCircle((int) interPoint.getX(), (int) interPoint.getY(), 3);

            if (i > 1) {
              for (int j = 0; j < i; j++) {
                Point secondPoint = line.intersectionWith(lines[j]);
                Point thirdPoint = lines[i].intersectionWith(lines[j]);
                if (secondPoint != null && thirdPoint != null) {
                  d.setColor(Color.GREEN);
                  this.drawTriangle(d, interPoint, secondPoint, thirdPoint);
                }
              }
            }
            
          }
        }
      } 
      
      
    }
    gui.show(d);
  }

  private void drawTriangle( DrawSurface d, Point p1, Point p2, Point p3) {
    d.drawLine((int) p1.getX(), (int) p1.getY(),
              (int) p2.getX(), (int) p2.getY());
    d.drawLine((int) p2.getX(), (int) p2.getY(),
              (int) p3.getX(), (int) p3.getY());
    d.drawLine((int) p1.getX(), (int) p1.getY(),
              (int) p3.getX(), (int) p3.getY());
  }

  public static void main(String[] args) {
     SimpleGuiExample example = new SimpleGuiExample();
     example.drawRandomLines();
  }
}