package src;

import java.util.ArrayList;

/**
 * @author Yotam
 * Class that holds refrences of all game collidable objects.
 */
public class GameEnviroment {
  private ArrayList<ICollidable> collidables = new ArrayList<ICollidable>();

  /**
   * Add a collidable object to the enviroment.
   * @param c collidable object
   */
  public void addCollidable(ICollidable c) {
    collidables.add(collidables.size(), c);
  };

  /**
   * Find the first collision point of element trajectory.
   * @param trajectory line trajectory
   * @return first point of collision
   */
  public CollisionInfo getClosestCollision(Line trajectory) {
    ArrayList<Point> interPoints = new ArrayList<Point>();
    Point firstPoint = null;
    ICollidable firstObject = null;

    for (ICollidable object : collidables) {
      for (Point point
      : object.getCollisionRectangle().intersectionPoints(trajectory)) {
        interPoints.add(point);
      }

      if (interPoints.size() == 0) {
        continue;
      }

      for (Point point : interPoints) {
        if (firstPoint == null || isPointCloser(trajectory.start(), point, firstPoint)) {
          firstPoint = point;
          firstObject = object;
        }
      }
    }

    if (interPoints.size() == 0) {
      return null;
    }

    return new CollisionInfo(firstPoint, firstObject);
  }

  /**
   * Compare 2 point and return closest to a start point.
   * @param start start point for referance
   * @param newPoint new point to compare
   * @param point current closest point
   * @return true if new point is closer, false if not
   */
  private Boolean isPointCloser(Point start, Point newPoint, Point point) {
    double distance = start.distance(point);
    double newDistance = start.distance(newPoint);

    if (newDistance < distance) {
      return true;
    }
    return false;
  }

}
