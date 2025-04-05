package src;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * Main class to initialize random lines window.
 * @author Yotam
 */
public class AbstractArtDrawing {

  /**
   * Function to generate and draw random lines.
   */
  public void drawRandomLines() {
    Random rand = new Random();
    GUI gui = new GUI("Random Lines", 800, 600);
    DrawSurface d = gui.getDrawSurface();
    Line[] lines = new Line[10];

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

      for (Line line : lines) {
        if (line == null) {
          break;
        }

        if (!lines[i].equals(line)) {
          Point interPoint = lines[i].intersectionWith(line);
          if (interPoint != null) {
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

  /**
   * Function to draw triangles based on 3 points.
   * @param d DrawSurface instance
   * @param p1 point 1
   * @param p2 point 2
   * @param p3 point 3
   */
  private void drawTriangle(DrawSurface d, Point p1, Point p2, Point p3) {
    d.drawLine((int) p1.getX(), (int) p1.getY(),
              (int) p2.getX(), (int) p2.getY());
    d.drawLine((int) p2.getX(), (int) p2.getY(),
              (int) p3.getX(), (int) p3.getY());
    d.drawLine((int) p1.getX(), (int) p1.getY(),
              (int) p3.getX(), (int) p3.getY());
  }

  /**
   * Main function.
   * @param args
   */
  public static void main(String[] args) {
    AbstractArtDrawing example = new AbstractArtDrawing();
    example.drawRandomLines();
 }
}
