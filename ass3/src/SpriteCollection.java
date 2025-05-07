package src;

import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * @author Yotam
 * Collection of sprites present in the game.
 */
public class SpriteCollection {
  private ArrayList<ISprite> sprites = new ArrayList<ISprite>();

  /**
   * Add new sprite to the collection.
   * @param sprite sprite object
   */
  public void addSprite(ISprite sprite) {
    sprites.add(sprite);
  };

  /**
   * Move one interval and update all sprites in the collection.
   */
  public void notifyAllTimePassed() {
    for (ISprite sprite : sprites) {
      sprite.timePassed();
    }
  };

  /**
   * Draw all sprites in collection of surface.
   * @param surface reference to surface
   */
  public void drawAllOn(DrawSurface surface) {
    for (ISprite sprite : sprites) {
      sprite.drawOn(surface);
    }
  };

}
