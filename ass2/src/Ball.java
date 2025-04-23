package src;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Yotam
 * Creates ball object with velocity capabilities.
 */
public class Ball implements Shape {
  private Point center;
  private int r;
  private java.awt.Color color;
  private Velocity v;
  private int[] borderX = new int[2];
  private int[] borderY = new int[2];

  /**
   * Constructor function.
   * @param center starting center point of the ball
   * @param r radius of the ball
   * @param color color of the ball to draw
   */
  public Ball(Point center, int r, java.awt.Color color) {
    this.center = center;
    this.r = r;
    this.color = color;
  }

  /**
   * Returns x value of ball center.
   * @return x value
   */
  public double getX() {
    return this.center.getX();
  };

  /**
   * Returns y value of ball center.
   * @return y value
   */
  public double getY() {
    return this.center.getY();
  };

  /**
   * Returns radius of ball.
   * @return radius value
   */
  public int getSize() {
    return this.r;
  };

  /**
   * Returns color of ball.
   * @return color
   */
  public java.awt.Color getColor() {
    return this.color;
  };

  /**
   * Returns ball's velocity component.
   * @return velocity object
   */
  public Velocity getVelocity() {
    return this.v;
  }

  /**
   * Set new center point of the ball.
   * @param center new center point
   */
  public void setCenter(Point center) {
    this.center = center;
  }

  /**
   * Set new color for the ball.
   * @param color new color
   */
  public void setColor(java.awt.Color color) {
    this.color = color;
  }

  /**
   * Change ball's velocity component.
   * @param v new velocity object
   */
  public void setVelocity(Velocity v) {
    this.v = v;
  }

  /**
   * Set borders for the ball to bounce on.
   * @param xStart minimum x border
   * @param xEnd maximum x border
   * @param yStart minimum y border
   * @param yEnd maximum y border
   */
  public void setBorders(int xStart, int xEnd, int yStart, int yEnd) {
    this.borderX[0] = xStart;
    this.borderX[1] = xEnd;
    this.borderY[0] = yStart;
    this.borderY[1] = yEnd;
  }

  /**
   * Bounce ball on X axis.
   */
  public void bounceX() {
    this.v.updateVelocity(-(this.v.getX()), this.v.getY());
  }

  /**
   * Bounce ball on Y axis.
   */
  public void bounceY() {
    this.v.updateVelocity(this.v.getX(), -(this.v.getY()));
  }

  /**
   * Update ball's location based on speed and check for borders.
   */
  public void moveOneStep() {
    if (this.getX() - this.r < borderX[0]
    || this.getX() + this.r > borderX[1]) {
      bounceX();
    }
    if (this.getY() - this.r < borderY[0]
    || this.getY() + this.r > borderY[1]) {
      bounceY();
    }
    this.center = this.getVelocity().applyToPoint(this.center);
  }

  /**
   * Draw ball on given DrawSurface.
   * @param surface surface object to draw ball on
   */
  public void drawOn(DrawSurface surface) {
    surface.setColor(color);
    surface.fillCircle((int) this.getX(), (int) this.getY(), this.r);
    if (color == Color.BLACK) {
      surface.setColor(Color.WHITE);
    } else {
      surface.setColor(Color.BLACK);
    }
    surface.drawCircle((int) this.getX(), (int) this.getY(), this.r);
  };
}