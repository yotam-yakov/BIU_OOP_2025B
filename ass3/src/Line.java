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
    this.start = new Point(x1, y1);
    this.end = new Point(x2, y2);

    if (x1 == x2) {
      this.slope = 1000000000;
    } else {
      this.slope = (y2 - y1) / (x2 - x1);
    }
  }

  private int compareDouble(double a, double b) {
    if (Math.abs(a - b) < 0.0001) {
      return 0;
    } else if (a < b) {
      return -1;
    } else {
      return 1;
    }
  }

  /**
   * Get the length of this line.
   * @return length
   */
  public double length() {
    return Math.abs(this.start.distance(this.end));
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

    double minX = Math.max(
      Math.min(this.start.getX(), this.end.getX()),
      Math.min(other.start.getX(), other.end.getX())
      );
    double minY = Math.max(
      Math.min(this.start.getY(), this.end.getY()),
      Math.min(other.start.getY(), other.end.getY())
      );
    double maxX = Math.min(
        Math.max(this.start.getX(), this.end.getX()),
        Math.max(other.start.getX(), other.end.getX())
        );
    double maxY = Math.min(
        Math.max(this.start.getY(), this.end.getY()),
        Math.max(other.start.getY(), other.end.getY())
        );

    if (compareDouble(x, minX) >= 0 && compareDouble(y, minY) >= 0
    &&  compareDouble(x, maxX) <= 0 && compareDouble(y, maxY) <= 0) {
      return new Point(x, y);
    }

    return null;
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
