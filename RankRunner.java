import java.util.*;
import java.io.*;

public class RankRunner {
  static int playerNumber;
  static ArrayList<Player> playerList = new ArrayList<Player>();

  // Gets data from the entire game and writes to the playerData file of the
  // parsed data
  // From the playerData file it creates player objects and runs readPlayerData
  public static void runGameData(String fileName) {
    // rows are the players
    // columns are the stats
    // Stats are in this order: name, score, kills, deaths, assists, damage, obj,
    // teamNumber, elo, score
    String[][] gameData = new String[8][11];
    try {
      // turns the data from the text file into a 2D String array
      int i = 0;
      Scanner myReader = new Scanner(new File(fileName));
      while (myReader.hasNextLine()) {
        String[] data = myReader.nextLine().split(", ");
        for (int j = 0; j < 10; j++) {
          gameData[i][j] = data[j];
        }
        i++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    // getting team values
    int team1Elo = 0;
    int team2Elo = 0;
    int team1Obj = 0;
    int team2Obj = 0;
    int team1Kills = 0;
    int team2Kills = 0;
    int team1Score = Integer.valueOf(gameData[0][9]);
    int team2Score = Integer.valueOf(gameData[5][9]);
    for (int i = 0; i < 4; i++) {
      team1Elo += Integer.valueOf(gameData[i][8]);
      team1Obj += Integer.valueOf(gameData[i][6]);
      team1Kills += Integer.valueOf(gameData[i][2]);
    }
    for (int i = 4; i < 8; i++) {
      team2Elo += Integer.valueOf(gameData[i][8]);
      team2Obj += Integer.valueOf(gameData[i][6]);
      team2Kills += Integer.valueOf(gameData[i][2]);
    }
    if (team1Obj > team2Obj) {
      team1Score = 2;
      team2Score = 1;
    } else {
      team1Score = 1;
      team2Score = 2;
    }

    // Altering playerData text file
    for (int r = 0; r < 8; r++) {
      playerList.add(new Player(gameData[r][0], Integer.valueOf(gameData[r][8])));
      try {
        FileWriter myWriter = new FileWriter("playerData.txt");
        if (r < 4) {
          myWriter.write(gameData[r][6] + "\n");
          myWriter.write(gameData[r][2] + "\n");
          myWriter.write(team2Elo + "\n");
          myWriter.write(team1Elo + "\n");
          myWriter.write(team1Score + "\n");
          myWriter.write(team2Score + "\n");
          myWriter.write(team1Kills + "\n");
          myWriter.write(team2Kills + "\n");
          myWriter.write(team1Obj + "\n");
          myWriter.write(team2Obj + "\n");
        } else {
          myWriter.write(gameData[r][6] + "\n");
          myWriter.write(gameData[r][2] + "\n");
          myWriter.write(team1Elo + "\n");
          myWriter.write(team2Elo + "\n");
          myWriter.write(team2Score + "\n");
          myWriter.write(team1Score + "\n");
          myWriter.write(team2Kills + "\n");
          myWriter.write(team1Kills + "\n");
          myWriter.write(team2Obj + "\n");
          myWriter.write(team1Obj + "\n");
        }

        myWriter.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      readPlayerData("playerData.txt");
      playerNumber++;
    }
  }

  // reads data from the playerData file and parses that into an array with the
  // paramaters set for the addGame method
  // calls the addGame method
  public static void readPlayerData(String fileName) {
    int[] gameData = new int[10];
    try {
      int i = 0;
      Scanner myReader = new Scanner(new File(fileName));
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        gameData[i] = Integer.valueOf(data);
        i++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    playerList.get(playerNumber).addGame(gameData[0], gameData[1], gameData[2], gameData[3], gameData[4], gameData[5],
        gameData[6], gameData[7],
        gameData[8], gameData[9]);
  }

  public static void main(String[] args) {
    // Generates a random game and reads the data
    // Adds the game and changes elo for everyone
    GameGenerator.generateGame();
    runGameData("randomGame.txt");
    /*
     * for(Player test : playerList){
     * System.out.println("Elo Change: " + (test.getElo()-test.getPreviousElo()));
     * }
     */
    try {
      FileWriter myWriter = new FileWriter("output.txt");
      for (Player test : playerList) {
        myWriter.write(test.getName() + ", " + (test.getElo() - test.getPreviousElo()) + "\n");
      }
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
