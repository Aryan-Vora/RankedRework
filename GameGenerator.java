import java.io.*;

public class GameGenerator {
  public static void generateGame() {
    // writes to file here
    try {
      FileWriter myWriter = new FileWriter("randomGame.txt");
      for (int i = 1; i <= 4; i++) {
        // variables required
        int kills = ((int) (Math.random() * 70) + 10);
        int deaths = ((int) (Math.random() * 40) + 30);
        int assists = ((int) (Math.random() * 20) + 10);
        int obj = ((int) (Math.random() * 50) + 60) * 10;
        int elo = ((int) (Math.random() * 200) + 900);
        myWriter.write("Player" + i + ", " + ((125 * kills) + obj) + ", " + kills + ", " + deaths + ", " + assists
            + ", " + (77 * kills) + ", " + obj + ", " + ((i - 1) / 4 + 1) + ", " + elo + ", " + 2 + "\n");
      }
      for (int i = 5; i <= 8; i++) {
        // variables required
        int kills = ((int) (Math.random() * 70) + 10);
        int deaths = ((int) (Math.random() * 40) + 30);
        int assists = ((int) (Math.random() * 20) + 10);
        int obj = ((int) (Math.random() * 50) + 30) * 10;
        int elo = ((int) (Math.random() * 200) + 900);
        myWriter.write("Player" + i + ", " + ((125 * kills) + obj) + ", " + kills + ", " + deaths + ", " + assists
            + ", " + (77 * kills) + ", " + obj + ", " + ((i - 1) / 4 + 1) + ", " + elo + ", " + 1 + "\n");
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
