package src;

import java.util.ArrayList;

/**
 * @author Yotam
 * Class that create a rectangle object.
 */
public class Rectangle {
  private Point upperLeft;
  private double width, height;

  /**
   * Constructor function.
   * @param upperLeft top left point of rectangle
   * @param width width of rectangle
   * @param height height od rectangle
   */
  public Rectangle(Point upperLeft, double width, double height) {
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
  };

  /**
   * Find all intersection point of the rectangle with given line.
   * @param line line to intersect with block
   * @return arraylist of all intersection point of line with rectangle
   */
  public ArrayList<Point> intersectionPoints(Line line) {
    double x = upperLeft.getX(), y = upperLeft.getY();
    Line[] edges = {
      new Line(x, y, x + width, y),
      new Line(x, y, x, y + height),
      new Line(x, y + height, x + width, y + height),
      new Line(x + width, y, x + width, y + height),
    };

    ArrayList<Point> interPoints = new ArrayList<Point>();
    for (int i = 0; i < edges.length; i++) {
      if (line.intersectionWith(edges[i]) != null) {
        interPoints.add(line.intersectionWith(edges[i]));
      }
    }

    return interPoints;
  };

  /**
   * Returns the width of the rectangle.
   * @return rectangle width
   */
  public double getWidth() {
    return this.width;
  };

  /**
   * Returns the height of the rectangle.
   * @return rectangle height
   */
  public double getHeight() {
    return this.height;
  };

  /**
   * Returns the top left corner of the rectangle.
   * @return rectangle corner point object
   */
  public Point getUpperLeft() {
    return this.upperLeft;
  };
}
