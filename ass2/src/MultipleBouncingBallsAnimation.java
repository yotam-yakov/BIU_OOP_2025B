package src;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Yotam
 * Creates multi bouncing balls animation based on input balls sizes.
 */
public class MultipleBouncingBallsAnimation extends BouncingBallAnimation {
  private static int maxBallSize = 100;
  private static int[] screenSize = getScreenSize();

  /**
    * Parse the input argument array to numbers array.
    * @param args command line arguments array
    * @return parsed numbers array
    */
  protected static int[] parseInput(String[] args) {
    int[] nums = new int[args.length];

    for (int i = 0; i < args.length; i++) {
      if (isNotNumber(args[i])) {
        System.err.println("Argument " + (i + 1) + " is not a number!");
        nums[i] = 0;
        continue;
      }
      int arg = (int) Math.abs(Double.parseDouble(args[i]));
      nums[i] = arg > maxBallSize ? maxBallSize : arg;
    }

    return nums;
 }
  /**
   * Creates balls objects array from given sizes.
   * @param nums numbers array for ball size
   * @return input based ball objects array
   */
  protected static Ball[] createBalls(int[] nums) {
    Ball[] balls = new Ball[nums.length];

    for (int i = 0; i < nums.length; i++) {
      int min = nums[i];
      int maxX = screenSize[0] - nums[i];
      int maxY = screenSize[1] - nums[i];

      Point center = new Point(
        min + (Math.random() * (maxX - min)),
        min + (Math.random() * (maxY - min))
        );
      double angle = Math.random() * 90;
      double speed = Math.max(1, Math.min(5, (50 - nums[i]) / 10));
      Velocity v = Velocity.fromAngleAndSpeed(angle, speed);

      balls[i] = new Ball(center, nums[i], Color.BLACK);
      balls[i].setVelocity(v);
      balls[i].setBorders(0, screenSize[0], 0, screenSize[1]);
    }

    return balls;
  }

  /**
   * Draw animation from balls array.
   * @param balls ball objects array
   */
  private static void drawAnimation(Ball[] balls) {
      GUI gui = new GUI("Multiple Bouncing Balls", screenSize[0], screenSize[1]);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();

      while (true) {
        DrawSurface d = gui.getDrawSurface();
        for (Ball ball : balls) {
          ball.moveOneStep();
          ball.drawOn(d);
        }
        gui.show(d);
        sleeper.sleepFor(10);
      }
  }

  /**
   * Class main function.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Program must get arguments!");
      return;
    }

    int[] nums = parseInput(args);
    Ball[] balls = createBalls(nums);

    drawAnimation(balls);
  }
}
