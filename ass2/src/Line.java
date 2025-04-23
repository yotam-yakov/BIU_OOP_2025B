package src;

/**
 * Class that creates a line from 2 points.
 * @author Yotam
 */
public class Line {

  private Point start, end;
  private double slope;

  /**
   * Constructor for a Line instance.
   * @param x1 x for start point
   * @param y1 y for start point
   * @param x2 x for end point
   * @param y2 y for end point
   */
  public Line(double x1, double y1, double x2, double y2) {
    double minX = Math.min(x1, x2);

    this.start = new Point(minX, minX == x1 ? y1 : y2);
    this.end = new Point(minX == x1 ? x2 : x1, minX == x1 ? y2 : y1);
    if (x1 == x2) {
      this.slope = 0.0000000000001;
    } else {
      this.slope = (y2 - y1) / (x2 - x1);
    }
  }

  /**
   * Get the length of this line.
   * @return length
   */
  public double length() {
    return this.start.distance(this.end);
  }

  /**
   * Get middle point of this line.
   * @return point instance for middle
   */
  public Point middle() {
    double midX = this.start.getX() + ((this.end.getX() - this.start.getX()) / 2);
    double midY = this.start.getY() + ((this.end.getY() - this.start.getY()) / 2);
    return new Point(midX, midY);
  }

  /**
   * Get start point of this line.
   * @return Start point
   */
  public Point start() {
    return this.start;
  }

  /**
   * Get end point of this line.
   * @return End point
   */
  public Point end() {
    return this.end;
  }

  /**
   * Get the slope of this line.
   * @return Slope value
   */
  public double getSlope() {
    return this.slope;
  }

  /**
   * Check if a given line intersects this line.
   * @param other Another line to check
   * @return True is lines intersects, False if not
   */
  public boolean isIntersecting(Line other) {
    if (this.equals(other) || this.intersectionWith(other) != null) {
      return true;
    }
    return false;
  }

  /**
   * Find intersection point of a given line with this line.
   * @param other Another line to check
   * @return Point instance if intersects, null if no intersection
   */
  public Point intersectionWith(Line other) {
    if (this.slope == other.getSlope()) {
      return null;
    }
    double c1 = this.start.getY() - (this.slope * this.start.getX());
    double c2 = other.start.getY() - (other.getSlope() * other.start.getX());

    double x = (c2 - c1) / (this.slope - other.getSlope());
    double y = this.slope * x + c1;

    if (x < this.start.getX()
    ||  x < other.start.getX()
    ||  x > this.end.getX()
    ||  x > other.end.getX()) {
      return null;
    }
    return new Point(x, y);
  }

  /**
   * Check if a given line equals this line.
   * @param other Another line to check
   * @return True if lines are equal, False if not
   */
  public boolean equals(Line other) {
    double c1 = this.start.getY() - (this.slope * this.start.getX());
    double c2 = other.start.getY() - (other.getSlope() * other.start.getX());

    return this.slope == other.getSlope() && c1 == c2;
  }

}
