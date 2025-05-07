package src;

import biuoop.DrawSurface;

/**
 * @author Yotam
 * Interface for sprites in the game.
 */
public interface ISprite {
  /**
   * Draw sprite on the surface.
   * @param surface surface reference to draw on.
   */
  void drawOn(DrawSurface surface);

  /**
   * Update state of sprite after one interval.
   */
  void timePassed();

  /**
   * Add this sprite to the game.
   * @param game reference to game
   */
  void addToGame(Game game);
}
