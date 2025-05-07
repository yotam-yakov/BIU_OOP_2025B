package src;

/**
 * @author Yotam
 * Class that holds info about collision.
 */
public class CollisionInfo {
  private Point point;
  private ICollidable collidable;

  /**
   * Constructor for CollisionInfo object.
   * @param point point of collision
   * @param collidable collided object
   */
  public CollisionInfo(Point point, ICollidable collidable) {
    this.point = point;
    this.collidable = collidable;
  }

  /**
   * Return the point of collision.
   * @return point object
   */
  public Point collisionPoint() {
    return this.point;
  };

  /**
   * Returns the collided object.
   * @return collidable object
   */
  public ICollidable collisionObject() {
    return this.collidable;
  };
}
