package src;

/**
 * @author Yotam
 * Class component for object's velocity and movement
 */
public class Velocity {
  private double dx;
  private double dy;

  /**
   * Constructor function.
   * @param dx starting change in X axis
   * @param dy starting change in Y axis
   */
  public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  };

  /**
   * Returns dx value of the velocity.
   * @return dx value
   */
  public double getX() {
    return this.dx;
  }

  /**
   * Returns dy value of the velocity.
   * @return dy value
   */
  public double getY() {
    return this.dy;
  }

  /**
   * Update dx/dy values of the velocity.
   * @param dx new dx value
   * @param dy new dy value
   */
  public void updateVelocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  }

  /**
   * Convert angle/speed velocity to dx/dy values.
   * @param angle given angle in degrees
   * @param speed speed factor
   * @return new velocity object with given parameters
   */
  public static Velocity fromAngleAndSpeed(double angle, double speed) {
    double radians = angle * (Math.PI / 180);
    double dx = Math.cos(radians) * speed;
    double dy = Math.sin(radians) * speed;
    return new Velocity(dx, dy);
  }

  /**
   * Apply velocity interval to point's coordinates.
   * @param p moving point
   * @return new coordinate after interval
   */
  public Point applyToPoint(Point p) {
    return new Point(p.getX() + dx, p.getY() + dy);
  };
}