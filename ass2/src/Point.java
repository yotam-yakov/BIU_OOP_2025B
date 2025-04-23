package src;

/**
 * Class that creates a (x, y) point.
 * @author Yotam
 */
public class Point {

  private double x, y;

  /**
   * Constructor for Point instance with x, y.
   * @param x Value of x
   * @param y Value of y
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Calculate distance from this point to another one.
   * @param other The other point to calculate distance from
   * @return Distance
   */
  public double distance(Point other) {
    double disX = this.x - other.x;
    double disY = this.y - other.y;

    return Math.sqrt((disX * disX) + (disY * disY));
  }

  /**
   * Check if another point is equal to this.
   * @param other The other point to compare
   * @return True if same point, False if not
   */
  public boolean equals(Point other) {
    return this.x == other.x && this.y == other.y;
  }

  /**
   * Get the value of x for this point.
   * @return Value of x
   */
  public double getX() {
    return this.x;
  }

  /**
   * Get the value of y for this point.
   * @return Value of y
   */
   public double getY() {
    return this.y;
  }
}
