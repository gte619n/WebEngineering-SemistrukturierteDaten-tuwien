import Beans.Player;
import Beans.Game;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class TableController {

  private HashMap<String,Game> gameHashMap = null;

  public TableController() {
    gameHashMap = new HashMap<String,Game>();

  }

  public Game getGame(HttpSession session) {
    if (gameHashMap.containsKey(session.getId()))
      gameHashMap.put(session.getId(), new Game(new Player("Spieler"), new Player("Super C")));

    return gameHashMap.get(session.getId());
  }

  public Game resetGame(HttpSession session) {

    return null;
  }

}