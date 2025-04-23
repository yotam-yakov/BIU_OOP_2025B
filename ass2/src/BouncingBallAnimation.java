package src;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Yotam
 * Creates a bouncing ball animation based on input parameters.
 */
public class BouncingBallAnimation {
   private static int[] screenSize = {800, 600};
   private static int[] defaultBall = {
      screenSize[0] / 2, screenSize[1] / 2, 45, 5
   };

   protected static int[] getScreenSize() {
      return screenSize;
   }

  /**
   * Check if string input is not a number.
   * @param str string input
   * @return true - argument is not a number, false - argument is a number
   */
  protected static Boolean isNotNumber(String str) {
   try {
     Double.parseDouble(str);
     return false;
   } catch (Exception e) {
     return true;
   }
 }

   /**
    * Draws the animation with given ball parameters.
    * @param start starting center point of the ball
    * @param v ball's velocity
    */
   private static void drawAnimation(Point start, Velocity v) {
      GUI gui = new GUI("Bouncing Ball Animation", screenSize[0], screenSize[1]);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      Ball ball = new Ball(start, 30, java.awt.Color.BLACK);
      ball.setVelocity(v);
      ball.setBorders(0, screenSize[0], 0, screenSize[1]);

      while (true) {
         ball.moveOneStep();
         DrawSurface d = gui.getDrawSurface();
         ball.drawOn(d);
         gui.show(d);
         sleeper.sleepFor(10);
      }
   }

   /**
    * Parse the input argument array to numbers array.
    * @param args command line arguments array
    * @return parsed numbers array
    */
   private static int[] parseInput(String[] args) {
      int[] nums = new int[4];

      for (int i = 0; i < 4; i++) {
         if (args.length > i && isNotNumber(args[i])) {
            System.err.println("Argument " + (i + 1) + " is not a number! using default value");
            nums[i] = defaultBall[i];
            continue;
         }
         nums[i] = args.length > i ? Integer.parseInt(args[i]) : defaultBall[i];
      }

      if (nums[0] < 30) {
         nums[0] = 30;
      } else if (nums[0] > screenSize[0] - 30) {
         nums[0] = screenSize[0] - 30;
      }

      if (nums[1] < 30) {
         nums[1] = 30;
      } else if (nums[1] > screenSize[0] - 30) {
         nums[1] = screenSize[1] - 30;
      }

      return nums;
   }

   /**
    * Main class function.
    * @param args command line arguments array
    */
   public static void main(String[] args) {
      if (args.length < 4) {
         System.out.println("Program recieved "
         + args.length
         + " arguments, using "
         + (4 - args.length)
         + " default values");
      } else if (args.length > 4) {
         System.out.println("Program recieved "
         + args.length
         + " arguments, ignoring "
         + (args.length - 4)
         + " value(s)");
      }

      int[] numArgs = parseInput(args);
      Point center = new Point(numArgs[0], numArgs[1]);
      Velocity v = Velocity.fromAngleAndSpeed(numArgs[2], numArgs[3]);

      drawAnimation(center, v);
   }
}
