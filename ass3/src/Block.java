package src;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Yotam
 * Block sprite and collidable.
 */
public class Block implements ICollidable, ISprite {
  private Point corner;
  private double width, height;
  private Color color;

  /**
   * Constructor function.
   * @param corner top left corner of block
   * @param width width of block
   * @param height height of block
   * @param color color of block
   */
  public Block(Point corner, double width, double height, Color color) {
    this.corner = corner;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * Compare general equality of doubles.
   * @param a first double
   * @param b second double
   * @return true if double generally equal, false if not
   */
  private Boolean doubleEqual(double a, double b) {
    return Math.abs(a - b) < 0.00001;
  }

  /**
   * Returns rectangle object of block.
   * @return rectangle object
   */
  public Rectangle getCollisionRectangle() {
    return new Rectangle(corner, width, height);
  }

  /**
   * Flip direction of ball based on hit location.
   * @param collisionPoint point of collision with block
   * @param currentVelocity current speed of the ball
   * @return new updated velocity object
   */
  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    double dx = currentVelocity.getX(), dy = currentVelocity.getY();

    if (doubleEqual(collisionPoint.getX(), corner.getX())
    || doubleEqual(collisionPoint.getX(), corner.getX() + width)) {
      dx *= -1;
    }

    if (doubleEqual(collisionPoint.getY(), corner.getY())
    || doubleEqual(collisionPoint.getY(), corner.getY() + height)) {
      dy *= -1;
    }

    return new Velocity(dx, dy);
  }

  /**
   * Draw block on surface.
   * @param surface reference of surface to draw on
   */
  public void drawOn(DrawSurface surface) {
    surface.setColor(color);
    surface.fillRectangle((int) corner.getX(), (int) corner.getY(), (int) width, (int) height);
    surface.setColor(Color.BLACK);
    surface.drawRectangle((int) corner.getX(), (int) corner.getY(), (int) width, (int) height);
  }

  /**
   * Update state of block after one interval.
   */
  public void timePassed() { }

  /**
   * Add block to game enviroment.
   * @param game reference to game
   */
  public void addToGame(Game game) {
    game.addSprite(this);
    game.addCollidable(this);
  }
}
