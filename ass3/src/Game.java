package src;

import java.awt.Color;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Yotam
 * Class that holds all game info initialize and run it.
 */
public class Game {
  private SpriteCollection sprites = new SpriteCollection();
  private GameEnviroment env = new GameEnviroment();
  private GUI gui;

  /**
   * Add a collidable to the enviroment.
   * @param c collidable object
   */
  public void addCollidable(ICollidable c) {
    env.addCollidable(c);
  };

  /**
   * Add sprite to the game sprite collection.
   * @param s sprite object
   */
  public void addSprite(ISprite s) {
    sprites.addSprite(s);
  };

  /**
   * Create a line of collidable blocks.
   * @param start top left start point of the line
   * @param width width of each block
   * @param height height of the line
   * @param numOfBlocks amount of blocks to create
   * @param color color of blocks
   * @return array of block objects
   */
  public Block[] lineOfBlocks(Point start, double width, double height, int numOfBlocks, Color color) {
    Block[] blocks = new Block[numOfBlocks];

    for (int i = 0; i < numOfBlocks; i++) {
      blocks[i] = new Block(new Point(start.getX() + (width * i), start.getY()), width, height, color);
    }

    return blocks;
  }

  /**
   * Create all sprites and collidables and add them to game and enviroment.
   */
  public void initialize() {
    gui = new GUI("Game", 800, 600);
    KeyboardSensor keyboard = gui.getKeyboardSensor();

    Paddle paddle = new Paddle(new Point(340, 520), 140, 10, Color.RED, keyboard);

    Ball[] balls = {
      new Ball(new Point(350, 400), 5, Color.WHITE, env),
      new Ball(new Point(450, 400), 5, Color.WHITE, env)
    };

    Block[] borders = {
      new Block(new Point(0, 0), 780, 20, Color.GRAY),
      new Block(new Point(20, 580), 780, 20, Color.GRAY),
      new Block(new Point(0, 20), 20, 580, Color.GRAY),
      new Block(new Point(780, 0), 20, 580, Color.GRAY)
    };

    Block[][] blockLines = {
      lineOfBlocks(new Point(180, 150), 50, 25, 12, Color.ORANGE),
      lineOfBlocks(new Point(230, 175), 50, 25, 11, Color.RED),
      lineOfBlocks(new Point(280, 200), 50, 25, 10, Color.GREEN),
      lineOfBlocks(new Point(330, 225), 50, 25, 9, Color.PINK),
      lineOfBlocks(new Point(380, 250), 50, 25, 8, Color.CYAN),
      lineOfBlocks(new Point(430, 275), 50, 25, 7, Color.YELLOW)
    };


    paddle.addToGame(this);
    for (Ball ball : balls) {
      ball.setVelocity(Velocity.fromAngleAndSpeed(45, 5));
      ball.addToGame(this);
    }
    for (Block border : borders) {
      border.addToGame(this);
    }
    for (Block[] line : blockLines) {
      for (Block block : line) {
        block.addToGame(this);
      }
    }
  };

  /**
   * Run game based on fps and update each frame as time passes.
   */
  public void run() {
    Sleeper sleeper = new Sleeper();
    int fps = 60;
    int msPerFrame = 1000 / fps;

    while (true) {
      long startTime = System.currentTimeMillis(); // timing
      DrawSurface surface = gui.getDrawSurface();
      surface.setColor(new Color(0x2456C9));
      surface.fillRectangle(0, 0, 800, 600);

      this.sprites.drawAllOn(surface);
      this.sprites.notifyAllTimePassed();
      gui.show(surface);

      // timing
      long usedTime = System.currentTimeMillis() - startTime;
      long msLeftToSleep = msPerFrame - usedTime;
      if (msLeftToSleep > 0) {
          sleeper.sleepFor(msLeftToSleep);
      }
    }
  };
}
