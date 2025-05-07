package src;

/**
 * @author Yotam
 * Interface for collidable objects.
 */
public interface ICollidable {
  /**
   * Get the ractangle of the collided object.
   * @return generated rectangle object
   */
  Rectangle getCollisionRectangle();

  /**
   * Handle behavior on hit.
   * @param collisionPoint point of collision
   * @param currentVelocity collided object
   * @return new updated velocity object
   */
  Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
