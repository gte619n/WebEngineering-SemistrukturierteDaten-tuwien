package formel0api;

import java.util.ArrayList;

public class PlayerStorage {
  private static PlayerStorage ps;

  private static ArrayList<Player> players = null;

  private PlayerStorage(){
    players = new ArrayList<Player>();

    // add conami code Player
    Player conamiPlayer = new Player();
    conamiPlayer.setUsername("aa");
    conamiPlayer.setPassword("a1");
    players.add(conamiPlayer);
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
    computerPlayer.setFirstname("Computer");
    computerPlayer.setUsername("Super C");
    return computerPlayer;
  }
}
