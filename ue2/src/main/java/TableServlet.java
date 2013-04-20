import Beans.Player;
import Beans.Game;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonFactory;

public class TableServlet extends HttpServlet {
  private TableController tableController = new TableController();
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession(true);

    tableController.resetGame(session);

    session.setAttribute("player1", tableController.getGame(session).getPlayer1());
    session.setAttribute("player2", tableController.getGame(session).getPlayer2());
    session.setAttribute("game", tableController.getGame(session));
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
    dispatcher.forward(request, response);

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //get objects
    Game game = tableController.getGame(request.getSession());
    Player player1 = game.getPlayer1();
    Player player2 = game.getPlayer2();

    //do business logic
    game.performDice(player1);
    game.performDice(player2);
    game.increaseRound();

    // prepare request
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // set up JSON writer
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonResponse = jsonFactory.createJsonGenerator(response.getWriter());
    jsonResponse.writeStartObject();

    //JSON Body
    jsonResponse.writeStringField("currentSession", request.getSession().getId());
    jsonResponse.writeStringField("gameTime", game.getTime());
    jsonResponse.writeNumberField("gameRound", game.getRound());
    jsonResponse.writeNumberField("player1DiceResult", player1.getDiceResult());
    jsonResponse.writeNumberField("player2DiceResult", player2.getDiceResult());
    jsonResponse.writeNumberField("player1Position", player1.getPositon());
    jsonResponse.writeNumberField("player2Position", player2.getPositon());
    jsonResponse.writeStringField("gameLeader", game.getLeader());
    jsonResponse.writeStringField("gameFinished", String.valueOf(game.isFinished()));
    
    // end JSON writer
    jsonResponse.writeEndObject();
    jsonResponse.close();

  }

  @Override
  public String getServletInfo() {
    return "Handles Table Requests";
    }
  }
