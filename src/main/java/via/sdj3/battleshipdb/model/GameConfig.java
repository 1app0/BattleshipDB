package via.sdj3.battleshipdb.model;

public class GameConfig {
  private String username;
  private int[] botGameTilesConfig;
  private int[] playerGameTilesConfig;
  private int botShipsLeft;
  private int playerShipsLeft;

  public GameConfig(String username, int[] botGameTilesConfig, int[] playerGameTilesConfig, int botShipsLeft, int playerShipsLeft) {
    this.username = username;
    this.botGameTilesConfig = botGameTilesConfig;
    this.playerGameTilesConfig = playerGameTilesConfig;
    this.botShipsLeft = botShipsLeft;
    this.playerShipsLeft = playerShipsLeft;
  }

  public int[] getBotGameTilesConfig() {
    return botGameTilesConfig;
  }

  public void setBotGameTilesConfig(int[] botGameTilesConfig) {
    this.botGameTilesConfig = botGameTilesConfig;
  }

  public int[] getPlayerGameTilesConfig() {
    return playerGameTilesConfig;
  }

  public void setPlayerGameTilesConfig(int[] playerGameTilesConfig) {
    this.playerGameTilesConfig = playerGameTilesConfig;
  }

  public int getBotShipsLeft() {
    return botShipsLeft;
  }

  public void setBotShipsLeft(int botShipsLeft) {
    this.botShipsLeft = botShipsLeft;
  }

  public int getPlayerShipsLeft() {
    return playerShipsLeft;
  }

  public void setPlayerShipsLeft(int playerShipsLeft) {
    this.playerShipsLeft = playerShipsLeft;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
