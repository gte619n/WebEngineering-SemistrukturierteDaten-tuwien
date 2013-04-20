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
    if (!gameHashMap.containsKey(session.getId())) {
      Game game = new Game(new Player("Spieler"), new Player("Super C"));
      gameHashMap.put(session.getId(), game);
      
    }

    return gameHashMap.get(session.getId());
  }

  public void resetGame(HttpSession session) {
    Game game = new Game(new Player("Spieler"), new Player("Super C"));
    gameHashMap.put(session.getId(), game);
  }

}