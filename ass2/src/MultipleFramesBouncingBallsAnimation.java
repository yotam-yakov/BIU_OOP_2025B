package src;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Yotam
 * Creates multi bouncing balls animation with frame borders based on input parameters.
 */
public class MultipleFramesBouncingBallsAnimation extends MultipleBouncingBallsAnimation {
  private static int[] screenSize = getScreenSize();
  private static int[][] frameData = {{50, 450}, {450, 150}};
  private static Color[] fColors = {Color.GRAY, Color.YELLOW};

  private static Frame[] createFrames(int[][] frameData, Color[] colors) {
    Frame[] frames = new Frame[frameData.length];

    for (int i = 0; i < frameData.length; i++) {
      Point start = new Point(frameData[i][0], frameData[i][0]);
      frames[i] = new Frame(start, frameData[i][1], colors[i]);
    }

    return frames;
  }

  /**
   * Aligns balls in relevant starting position.
   * @param balls ball objects array
   */
  private static void alignBalls(Ball[] balls) {
    for (int i = 0; i < balls.length; i++) {
      int minX, minY, maxX, maxY;

      if (i < (balls.length / 2)) {
        minX = frameData[0][0] + balls[i].getSize();
        minY = minX;
        maxX = frameData[0][0] + frameData[0][1] - minX;
        maxY = maxX;

        balls[i].setBorders(
          frameData[0][0], frameData[0][0] + frameData[0][1],
          frameData[0][0], frameData[0][0] + frameData[0][1]
          );
      } else {
        minX = frameData[0][0] + frameData[0][1] + balls[i].getSize();
        minY = balls[i].getSize();
        maxX = screenSize[0] - balls[i].getSize();
        maxY = screenSize[1] - frameData[1][1] - balls[i].getSize();

        balls[i].setColor(Color.BLUE);
      }

      balls[i].setCenter(
          new Point(
            minX + (Math.random() * (maxX - minX)),
            minY + (Math.random() * (maxY - minY))
            )
          );
    }
  }

  /**
   * Creates animation with frames from given balls array.
   * @param balls ball objects array
   */
  private static void drawAnimation(Ball[] balls) {
      GUI gui = new GUI("Multiple Frames Bouncing Balls", screenSize[0], screenSize[1]);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      Frame[] frames = createFrames(frameData, fColors);

      while (true) {
        DrawSurface d = gui.getDrawSurface();

        frames[0].drawOn(d);

        for (int i = 0; i < balls.length; i++) {
          if (i >= (balls.length / 2)) {
            if (frames[0].xCollision(balls[i]) || frames[1].xCollision(balls[i])) {
              balls[i].bounceX();
            }
            if (frames[0].yCollision(balls[i]) || frames[1].yCollision(balls[i])) {
              balls[i].bounceY();
            }
          }
          balls[i].moveOneStep();
          balls[i].drawOn(d);
        }

        frames[1].drawOn(d);
        gui.show(d);
        sleeper.sleepFor(10);
      }
  }

  /**
   * Class main function.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Program must get arguments!");
      return;
    }

    int[] nums = parseInput(args);
    Ball[] balls = createBalls(nums);
    alignBalls(balls);

    drawAnimation(balls);
  }
}
