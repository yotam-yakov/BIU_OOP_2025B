package src;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yotam
 * Paddle sprite and collidable - user controlled.
 */
public class Paddle implements ISprite, ICollidable {
  private Point corner;
  private double width, height;
  private Color color;
  private KeyboardSensor keyboard;

  /**
   * Constructor function.
   * @param corner top left corner of the paddle
   * @param width width of paddle
   * @param height height of paddle
   * @param color color of paddle
   * @param keyboard keyboard sensor reference
   */
  public Paddle(Point corner, double width, double height, Color color, KeyboardSensor keyboard) {
    this.corner = corner;
    this.width = width;
    this.height = height;
    this.color = color;
    this.keyboard = keyboard;
  }

  /**
   * Update movement to the right.
   */
  public void moveRight() {
    if (this.corner.getX() >= 780) {
      this.corner = new Point(-(width + 20), corner.getY());
    } else {
      this.corner = new Point(corner.getX() + 5, corner.getY());
    }
  };

  /**
   * Update movement to the left.
   */
  public void moveLeft() {
    if (this.corner.getX() <= -(width + 20)) {
      this.corner = new Point(780, corner.getY());
    } else {
      this.corner = new Point(corner.getX() - 5, corner.getY());
    }
  };

  /**
   * Update state of paddle after one interval.
   */
  public void timePassed() {
    if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
      moveRight();
    }
    if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
      moveLeft();
    }
  };

  /**
   * Draw paddle on the surface.
   * @param surface reference to the surface
   */
  public void drawOn(DrawSurface surface) {
    surface.setColor(color);
    surface.fillRectangle((int) corner.getX(), (int) corner.getY(), (int) width, (int) height);
    surface.setColor(Color.BLACK);
    surface.drawRectangle((int) corner.getX(), (int) corner.getY(), (int) width, (int) height);
  };

  /**
   * Return rectangle object of the paddle.
   * @return rectangle object
   */
  public Rectangle getCollisionRectangle() {
    return new Rectangle(corner, width, height);
  };

  /**
   * Change direction of ball based on hit section.
   * @param collisionPoint point of collision
   * @param currentVelocity current velocity of ball
   * @return new updated velocity object
   */
  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    double hitX = collisionPoint.getX() - corner.getX();
    int section = (int) (hitX * 5 / width);
    double speed = Math.sqrt(
      (currentVelocity.getX() * currentVelocity.getX())
    + (currentVelocity.getY() * currentVelocity.getY())
    );
    double angle;

    if (section > 4) {
      section = 6;
    }

    if (currentVelocity.getY() > 0) {
      angle = (5 - section) * -30;
    } else {
      angle = (5 - section) * 30;
    }

    return Velocity.fromAngleAndSpeed(angle, speed);
  };

  /**
   * Add paddle to game enviroment.
   * @param game reference to game
   */
  public void addToGame(Game game) {
    game.addCollidable(this);
    game.addSprite(this);
  };
}
