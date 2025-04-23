package src;

import biuoop.DrawSurface;

/**
 * @author Yotam
 * Creates frame object that can detect collision with other objects.
 */
public class Frame implements Shape {
  private Point start;
  private int size;
  private java.awt.Color color;

  /**
   * Constructor function.
   * @param start starting point for square
   * @param size length from starting point
   * @param color object's drawn color
   */
  public Frame(Point start, int size, java.awt.Color color) {
    this.start = start;
    this.size = size;
    this.color = color;
  }

  /**
   * Returns x value of starting point.
   * @return x value
   */
  public double getX() {
    return start.getX();
  }

  /**
   * Returns y value of starting point.
   * @return y value
   */
  public double getY() {
    return start.getY();
  }

  /**
   * Returns size value of frame.
   * @return size value
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Returns color of frame.
   * @return color
   */
  public java.awt.Color getColor() {
    return this.color;
  }

  /**
   * Detects collision on X axis of the frame.
   * @param ball given ball to check collision
   * @return true - collision happened, false - no collision
   */
  public Boolean xCollision(Ball ball) {
    double x = ball.getX();
    double y = ball.getY();
    double xSpeed = ball.getVelocity().getX();
    double ySpeed = ball.getVelocity().getY();
    int size = ball.getSize();

    if (y + size + ySpeed  > this.getY() && y - size + ySpeed < this.getY() + this.size) {
      if (x - size > this.getX() + this.size && x - size + xSpeed < this.getX() + this.size) {
        return true;
      } else if (x + size < this.getX() && x + size + xSpeed > this.getX()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Detects collision on Y axis of the frame.
   * @param ball given ball to check collision
   * @return true - collision happened, false - no collision
   */
  public Boolean yCollision(Ball ball) {
    double x = ball.getX();
    double y = ball.getY();
    double xSpeed = ball.getVelocity().getX();
    double ySpeed = ball.getVelocity().getY();
    int size = ball.getSize();

    if (x + size + xSpeed > this.getX() && x - size + xSpeed < this.getX() + this.size) {
      if (y - size > this.getY() + this.size && y - size + ySpeed < this.getY() + this.size) {
        return true;
      } else if (y + size < this.getY() && y + size + ySpeed > this.getY()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Draw object on given DrawSurface.
   * @param surface surface object to draw frame on
   */
  public void drawOn(DrawSurface surface) {
    surface.setColor(color);
    surface.fillRectangle((int) start.getX(), (int) start.getY(), size, size);
  }
}
