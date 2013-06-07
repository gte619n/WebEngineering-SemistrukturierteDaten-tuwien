package formel0api;

import java.util.ArrayList;
import javax.persistence.*;

public class PlayerStorage {
  private static PlayerStorage ps;

  private static ArrayList<Player> players = null;

  private PlayerStorage(){
    players = new ArrayList<Player>();

    // add conami code Player
    Player conamiPlayer = new Player();
    conamiPlayer.setUsername("aa");
    conamiPlayer.setPassword("a1");
    conamiPlayer.setSex("FEMALE");
    conamiPlayer.setBirthdate("1966-06-06");
    players.add(conamiPlayer);

    // //hibernate
    // EntityManager eManager =  Persistence.createEntityManagerFactory("lab4").createEntityManager();


    // System.out.println("\n\n>>>>>>>>>>>>>    (pmig log)    " + eManager.createQuery("select now()", Player.class).getResultList().get(0));
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
    computerPlayer.setSex("MALE");
    computerPlayer.setBirthdate("1966-06-06");
    return computerPlayer;
}
}
