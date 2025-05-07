package src;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Yotam
 * Creates ball object with velocity capabilities.
 */
public class Ball implements ISprite {
  private Point center;
  private int r;
  private Color color;
  private Velocity v;
  private GameEnviroment enviroment;

  /**
   * Constructor function.
   * @param center starting center point of the ball
   * @param r radius of the ball
   * @param color color of the ball to draw
   * @param enviroment reference to game enviroment
   */
  public Ball(Point center, int r, java.awt.Color color, GameEnviroment enviroment) {
    this.center = center;
    this.r = r;
    this.color = color;
    this.enviroment = enviroment;
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
  public Color getColor() {
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
   * Update ball's location based on speed and check for borders.
   */
  public void timePassed() {
    double startX = this.getX(), startY = this.getY();
    double endX = startX + this.getVelocity().getX();
    double endY = startY + this.getVelocity().getY();

    Line trajectory = new Line(startX, startY, endX, endY);
    CollisionInfo collision = enviroment.getClosestCollision(trajectory);

    if (collision == null) {
      this.center = this.getVelocity().applyToPoint(this.center);
    } else {
      Point point = collision.collisionPoint();
      Velocity newV = collision.collisionObject().hit(point, this.getVelocity());
      this.setVelocity(newV);
    }
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

  /**
   * Add ball to the game.
   * @param game reference to game
   */
  public void addToGame(Game game) {
    game.addSprite(this);
  }
}