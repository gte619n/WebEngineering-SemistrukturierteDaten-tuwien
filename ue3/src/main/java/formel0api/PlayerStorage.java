package formel0api;

import java.util.ArrayList;

public class PlayerStorage {
  private static PlayerStorage ps;

  private static ArrayList<Player> players = null;

  private PlayerStorage(){
    players = new ArrayList<Player>();
  }

  public static PlayerStorage getInstance(){
    if(ps == null){
      ps = new PlayerStorage();
    }
    return ps;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  public Player getComputerPlayer() {
    Player computerPlayer = new Player();
    computerPlayer.setFirstname("Super C");
    return computerPlayer;
  }
}
