package src;

import biuoop.DrawSurface;

/**
 * @author Yotam
 * Interface for shapes classes.
 */
public interface Shape {
  /**
   * Returns x value of shape's root.
   * @return x value
   */
  double getX();

  /**
   * Returns y value of shape's root.
   * @return y value
   */
  double getY();

  /**
   * Returns size value of a shape.
   * @return size value
   */
  int getSize();

  /**
   * Returns color of a shape.
   * @return color
   */
  java.awt.Color getColor();

  /**
   * Draw shape on given DrawSurface.
   * @param surface surface to draw shape on
   */
  void drawOn(DrawSurface surface);
}
