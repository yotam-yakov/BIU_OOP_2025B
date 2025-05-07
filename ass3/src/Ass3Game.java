package src;

/**
 * @author Yotam
 * Main function that initialize and runs the game.
 */
public class Ass3Game {
  /**
   * Main function.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Game game = new Game();
    game.initialize();
    game.run();
  }
}
